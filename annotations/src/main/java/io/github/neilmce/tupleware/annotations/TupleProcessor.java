// Copyright 2023 Neil Mc Erlean.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package io.github.neilmce.tupleware.annotations;

import com.google.auto.service.AutoService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@SupportedAnnotationTypes("io.github.neilmce.tupleware.annotations.TupleGeneration")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(Processor.class)
public class TupleProcessor extends AbstractProcessor {

  /** An arbitrary limit to stop the type signatures getting out of hand. */
  private static final int MAX_TUPLE_SIZE = 10;

  private final TemplateEngine templateEngine;

  public TupleProcessor() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setTemplateMode(TemplateMode.TEXT);

    this.templateEngine = new TemplateEngine();
    this.templateEngine.setTemplateResolver(templateResolver);
  }

  private void note(String msg) {
    processingEnv.getMessager().printMessage(Kind.NOTE, msg);
  }

  private void error(String msg) {
    processingEnv.getMessager().printMessage(Kind.ERROR, msg);
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Set<? extends Element> elems = roundEnv.getElementsAnnotatedWith(TupleGeneration.class);
    if (!elems.isEmpty()) {
      note(String.format("Processing %d @TupleGeneration elements: %s", elems.size(), elems));
    }

    // You can only annotate _classes_ with TupleGeneration (not interfaces etc).
    Map<ElementKind, List<Element>> elementsByKind = elems.stream()
                                                          .collect(groupingBy(Element::getKind));
    List<Element> classElements = elementsByKind.get(ElementKind.CLASS);

    if (classElements != null) {
      List<TypeElement> taggedClassElements = classElements.stream()
                                                           .map(e -> (TypeElement)e).collect(toList());
      if (elems.size() > taggedClassElements.size()) {
        error("Only classes can be annotated with @TupleGeneration: " + elems);
      }

      taggedClassElements.forEach(classElem -> {
        TupleGeneration anno = classElem.getAnnotation(TupleGeneration.class);
        int tupleSize = anno.tupleSize();

        final String packageName;
        String className = classElem.getQualifiedName().toString();
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
          packageName = className.substring(0, lastDot);
        }
        else {
          packageName = null;
        }

        if (tupleSize < 0 || tupleSize > MAX_TUPLE_SIZE) {
          error("Illegal size on @TupleGeneration: " + tupleSize);
        }
        try {
          writeTupleImplFile(packageName, tupleSize);
        } catch (IOException e) {
          error("IOException while writing builder file");

          throw new RuntimeException(e);
        }
      });
    }

    return true;
  }

  private void writeTupleImplFile(String packageName, int size) throws IOException {
    final String implClassName = String.format("GeneratedTuple%d", size);
    final String fqImplClassName = packageName == null ? implClassName : packageName + "." + implClassName;

    final Context thymeleafContext = new Context();
    thymeleafContext.setVariable("helper", new ThymeleafHelper());

    thymeleafContext.setVariable("packageName", packageName);
    thymeleafContext.setVariable("className", implClassName);
    thymeleafContext.setVariable("tupleSize", size);
    thymeleafContext.setVariable("typeParams", IntStream.range(1, size + 1)
                                                        .mapToObj(i -> String.format("T%d", i))
                                                        .collect(toList()));

    try (Writer writer = processingEnv.getFiler().createSourceFile(fqImplClassName).openWriter()) {
      this.templateEngine.process("TupleTemplate.java.th", thymeleafContext, writer);
    }
  }
}
