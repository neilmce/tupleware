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
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
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

    JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(fqImplClassName);

    try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
      out.println("// Copyright 2023 Neil Mc Erlean.\n" +
                      "//\n" +
                      "// Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                      "// you may not use this file except in compliance with the License.\n" +
                      "// You may obtain a copy of the License at\n" +
                      "//\n" +
                      "// http://www.apache.org/licenses/LICENSE-2.0\n" +
                      "//\n" +
                      "// Unless required by applicable law or agreed to in writing, software\n" +
                      "// distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                      "// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                      "// See the License for the specific language governing permissions and\n" +
                      "// limitations under the License.\n");

      if (packageName != null) {
        out.print("package ");
        out.print(packageName);
        out.println(";");
        out.println();
      }

      out.println("import java.util.List;");
      out.println("import java.util.function.Function;");
      out.println();

      out.println(String.format("// This code was generated on %s by %s", LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)), this.getClass().getName()));
      out.println("// You should not edit it.");

      out.println("/**");
      out.println(String.format(" * This class represents a tuple of {@link #size() %d} elements.", size));
      out.println(" * It provides families of methods as follows (all tuples are immutable, so any changed values are made by copying and returning a new instance):");
      out.println(" * <ul>");
      out.println(String.format(" *   <li>%d {@code elemN()} methods which return the value of the nth element.</li>", size));
      out.println(String.format(" *   <li>%d {@code dropElemN()} methods which drop the nth element in this tuple.</li>", size));
      out.println(String.format(" *   <li>%d {@code dropN()} methods which drop the first n elements in this tuple.</li>", size));
      out.println(String.format(" *   <li>%d {@code dropRightN()} methods which drop the last n elements in this tuple.</li>", size));
      out.println(String.format(" *   <li>%d {@code takeN()} methods which take the first n elements in this tuple dropping the rest.</li>", size));
      out.println(String.format(" *   <li>%d {@code takeRightN()} methods which take the last n elements in this tuple dropping the rest.</li>", size));
      out.println(String.format(" *   <li>%d {@code withElemN()} methods which change the nth element to a new value.</li>", size));
      out.println(String.format(" *   <li>%d {@code mapElemN()} methods which change the nth element to a new value given by a function.</li>", size));
      out.println(String.format(" *   <li>%d {@code splitAfterElementN()} methods which split the tuple in 2 after the nth element.</li>", size));
      out.println(String.format(" *   <li>%d {@code concat()} methods which concatenate this tuple with another to give one bigger tuple.</li>", size));
      out.println(" * </ul>");
      out.println(" * Note: tuple elements are indexed from 1, not from 0. So the first elem is element 1.");
      out.println(" */");
      out.print(String.format("public class %s", implClassName));
      // Generic types
      out.print(" <");
      for (int i = 1; i <= size; i++) {
        out.print(String.format("T%d", i));
        if (i < size) {
          out.print(", ");
        }
      }
      out.println("> implements Tuple {");

      writeFieldsAndConstructor(out, implClassName, size);

      writeGetSizeMethod(out, size);

      writeElementGetters(out, size);

      writeContainsAnyNullsMethod(out, size);

      writeToListMethod(out, size);

      writeReverseMethod(out, size);

      writeSplitMethods(out, size);

      writeConcatMethods(out, size);

      if (size > 0) {
        writeDropElemMethods(out, size);

        writeTakeNMethods(out, size);

        writeTakeRightNMethods(out, size);

        writeDropNMethods(out, size);

        writeDropRightNMethods(out, size);
      }

      writeWithElementMethods(out, size);

      writeMapElementMethods(out, size);

      writeToStringMethod(out, size);

      out.println("}");
    }
  }

  private void writeFieldsAndConstructor(PrintWriter out, String implClassName, int size) {
    // Fields
    for (int i = 1; i <= size; i++) {
      out.println(String.format("  protected final T%d t%d;", i, i));
    }
    out.println();

    // Constructor
    out.print(String.format("  protected %s(", implClassName));
    // Constructor params
    for (int i = 1; i <= size; i++) {
      out.print(String.format("T%d t%d", i, i));
      if (i < size) {
        out.print(", ");
      }
    }
    out.println(") {");
    // Field assignments
    for (int i = 1; i <= size; i++) {
      out.println(String.format("    this.t%d = t%d;", i, i));
    }
    out.println("  }");
    out.println();
  }

  private void writeElementGetters(PrintWriter out, int size) {
    for (int i = 1; i <= size; i++) {
      out.println(String.format("  /** Gets the element at index %d. Remember: tuples are indexed from zero. */", i));
      out.println(String.format("  public final T%d elem%d() { return this.t%d; }", i, i, i));
      out.println();
    }
  }

  private void writeContainsAnyNullsMethod(PrintWriter out, int size) {
    out.println("  @Override public final boolean containsAnyNulls() {");
    out.print("      return ");
    for (int i = 1; i <= size; i++) {
      out.print(String.format("t%d == null", i));
      if (i < size) {
        out.print(" || ");
      }
    }
    out.println(";");
    out.println("  }");
    out.println();
  }

  private void writeToListMethod(PrintWriter out, int size) {
    out.println("  @Override public final List<Object> toList() {");
    out.print("      return List.of(");
    for (int i = 1; i <= size; i++) {
      out.print(String.format("t%d", i));
      if (i < size) {
        out.print(", ");
      }
    }
    out.println(");");
    out.println("  }");
    out.println();
  }

  private void writeGetSizeMethod(PrintWriter out, int size) {
    out.println("  /** @return the number of elements in this tuple. */");
    out.println(String.format("  @Override public final int size() { return %d; }", size));
    out.println();
  }

  private void writeToStringMethod(PrintWriter out, int size) {
    out.println("  @Override public final String toString() {");
    out.print("      return String.format(\"(");
    for (int i = 1; i <= size; i++) {
      out.print("%s");
      if (i < size) {
        out.print(", ");
      }
    }
    out.print(")\", ");
    for (int i = 1; i <= size; i++) {
      out.print(String.format("t%d", i));
      if (i < size) {
        out.print(", ");
      }
    }
    out.println(");");
    out.println("  }");
  }

  private List<String> typeParamsTo(int inclLimit) {
    return IntStream.range(1, inclLimit + 1).mapToObj(i -> String.format("T%d", i)).collect(toList());
  }

  private List<String> paramsTo(int limit) {
    return IntStream.range(1, limit + 1).mapToObj(i -> String.format("t%d", i)).collect(toList());
  }

  private void writeDropElemMethods(PrintWriter out, int size) {
    final List<String> typeParams = typeParamsTo(size);
    final List<String> params = paramsTo(size);

    for (int elemToDrop = 1; elemToDrop <= size; elemToDrop++) {
      final List<String> typeParamsMinusDropped = new ArrayList<>(typeParams);
      final List<String> paramsMinusDropped = new ArrayList<>(params);

      typeParamsMinusDropped.remove(elemToDrop - 1);
      paramsMinusDropped.remove(elemToDrop - 1);

      final String typeParamsString = typeParamsMinusDropped.isEmpty() ? "" : String.format("<%s>", String.join(", ", typeParamsMinusDropped));

      out.println(String.format("   /** Creates a new tuple instance without the element at position %d.", elemToDrop));
      out.println("    * Remember that the position index is one-based, so the first element is elem1.");
      out.println(String.format("    * @return a new tuple instance from which the tuple element at position %d has been removed.", elemToDrop));
      out.println("    */");
      out.println(String.format("  public final Tuple%d%s dropElem%d() {", size - 1, typeParamsString, elemToDrop));

      out.print(String.format("    return Tuple%d.of(", size - 1));
      out.print(String.join(", ", paramsMinusDropped));
      out.println(");");
      out.println("  }");
      out.println();
    }
  }

  private void writeTakeNMethods(PrintWriter out, int size) {
    final List<String> typeParams = typeParamsTo(size);
    final List<String> params = paramsTo(size);

    for (int elemsToTake = 1; elemsToTake < size; elemsToTake++) {
      final List<String> typeParamsTaken = typeParams.subList(0, elemsToTake);
      final List<String> paramsTaken = params.subList(0, elemsToTake);

      final String typeParamsString = typeParamsTaken.isEmpty() ? "" : String.format("<%s>", String.join(", ", typeParamsTaken));

      out.println(String.format("   /** Creates a new tuple instance consisting of the first %d elements of this tuple.", elemsToTake));
      out.println(String.format("    * @return a new tuple instance containing the first %d elements of this tuple.", elemsToTake));
      out.println("    */");
      out.println(String.format("  public final Tuple%d%s take%d() {", elemsToTake, typeParamsString, elemsToTake));

      out.print(String.format("    return Tuple%d.of(", elemsToTake));
      out.print(String.join(", ", paramsTaken));
      out.println(");");
      out.println("  }");
      out.println();
    }
  }

  private void writeTakeRightNMethods(PrintWriter out, int size) {
    final List<String> typeParams = typeParamsTo(size);
    final List<String> params = paramsTo(size);

    for (int elemsToTake = 1; elemsToTake < size; elemsToTake++) {
      final List<String> typeParamsTaken = typeParams.subList(typeParams.size() - elemsToTake, typeParams.size());
      final List<String> paramsTaken = params.subList(params.size() - elemsToTake, params.size());

      final String typeParamsString = typeParamsTaken.isEmpty() ? "" : String.format("<%s>", String.join(", ", typeParamsTaken));

      out.println(String.format("   /** Creates a new tuple instance consisting of the last %d elements of this tuple.", elemsToTake));
      out.println(String.format("    * @return a new tuple instance containing the last %d elements of this tuple.", elemsToTake));
      out.println("    */");
      out.println(String.format("  public final Tuple%d%s takeRight%d() {", elemsToTake, typeParamsString, elemsToTake));

      out.print(String.format("    return Tuple%d.of(", elemsToTake));
      out.print(String.join(", ", paramsTaken));
      out.println(");");
      out.println("  }");
      out.println();
    }
  }

  private void writeDropNMethods(PrintWriter out, int size) {
    final List<String> typeParams = typeParamsTo(size);
    final List<String> params = paramsTo(size);

    for (int elemsToDrop = 1; elemsToDrop < size; elemsToDrop++) {
      final List<String> typeParamsRemaining = typeParams.subList(typeParams.size() - elemsToDrop, typeParams.size());
      final List<String> paramsRemaining = params.subList(params.size() - elemsToDrop, params.size());

      final String typeParamsString = typeParamsRemaining.isEmpty() ? "" : String.format("<%s>", String.join(", ", typeParamsRemaining));

      out.println(String.format("   /** Creates a new tuple instance without the first %d elements of this tuple.", size - elemsToDrop));
      out.println(String.format("    * @return a new tuple instance without the first %d elements of this tuple.", size - elemsToDrop));
      out.println("    */");
      out.println(String.format("  public final Tuple%d%s drop%d() {", elemsToDrop, typeParamsString, size - elemsToDrop));

      out.print(String.format("    return Tuple%d.of(", elemsToDrop));
      out.print(String.join(", ", paramsRemaining));
      out.println(");");
      out.println("  }");
      out.println();
    }
  }

  private void writeDropRightNMethods(PrintWriter out, int size) {
    final List<String> typeParams = typeParamsTo(size);
    final List<String> params = paramsTo(size);

    for (int elemsToDrop = 1; elemsToDrop < size; elemsToDrop++) {
      final List<String> typeParamsRemaining = typeParams.subList(0, typeParams.size() - elemsToDrop);
      final List<String> paramsRemaining = params.subList(0, params.size() - elemsToDrop);

      final String typeParamsString = typeParamsRemaining.isEmpty() ? "" : String.format("<%s>", String.join(", ", typeParamsRemaining));

      out.println(String.format("   /** Creates a new tuple instance without the last %d elements of this tuple.", elemsToDrop));
      out.println(String.format("    * @return a new tuple instance without the last %d elements of this tuple.", elemsToDrop));
      out.println("    */");
      out.println(String.format("  public final Tuple%d%s dropRight%d() {", size - elemsToDrop, typeParamsString, elemsToDrop));

      out.print(String.format("    return Tuple%d.of(", size - elemsToDrop));
      out.print(String.join(", ", paramsRemaining));
      out.println(");");
      out.println("  }");
      out.println();
    }
  }

  private void writeWithElementMethods(PrintWriter out, int size) {
    final List<String> currentTypeParams = typeParamsTo(size);
    final List<String> currentParams = paramsTo(size);

    for (int elemToReplace = 1; elemToReplace <= size; elemToReplace++) {
      final List<String> newTypeParams = new ArrayList<>(currentTypeParams);
      newTypeParams.set(elemToReplace - 1, "R");

      final List<String> newParams = new ArrayList<>(currentParams);
      newParams.set(elemToReplace - 1, "newValue");

      out.println(String.format("  /** Replaces the element at index %d with the provided value.", elemToReplace));
      out.println("   * @param newValue the new element value.");
      out.println(String.format("   * @return a new Tuple%d instance whose element at index %d will have the new value.", size, elemToReplace));
      out.println("   */");
      out.print(String.format("  public final <R> Tuple%d<", size));
      out.print(String.join(", ", newTypeParams));
      out.println(String.format("> withElem%d(R newValue) {", elemToReplace));

      out.print(String.format("    return Tuple%d.of(", size));
      out.print(String.join(", ", newParams));
      out.println(");");
      out.println("  }");
      out.println();
    }
  }

  private void writeMapElementMethods(PrintWriter out, int size) {
    final List<String> currentTypeParams = typeParamsTo(size);
    final List<String> currentParams = paramsTo(size);

    for (int elemToReplace = 1; elemToReplace <= size; elemToReplace++) {
      final List<String> newTypeParams = new ArrayList<>(currentTypeParams);
      newTypeParams.set(elemToReplace - 1, "R");

      final List<String> newParams = new ArrayList<>(currentParams);
      newParams.set(elemToReplace - 1, String.format("function.apply(t%d)", elemToReplace));

      out.println(String.format("  /** Replaces the element at index %d with the result of the provided function.", elemToReplace));
      out.println("   * @param function a function which provides the new element value, based on the current value.");
      out.println(String.format("   * @return a new Tuple%d instance whose element at index %d will have the new value.", size, elemToReplace));
      out.println("   */");
      out.print(String.format("  public final <R> Tuple%d<", size));
      out.print(String.join(", ", newTypeParams));
      out.println(String.format("> mapElem%d(Function<T%d, R> function) {", elemToReplace, elemToReplace));

      out.print(String.format("    return Tuple%d.of(", size));
      out.print(String.join(", ", newParams));
      out.println(");");
      out.println("  }");
      out.println();
    }
  }

  private void writeSplitMethods(PrintWriter out, int size) {
    final List<String> currentTypeParams = typeParamsTo(size);
    final List<String> currentParams = paramsTo(size);

    for (int splitAfterPos = 1; splitAfterPos < size; splitAfterPos++) {
      final List<String> leftTypeParams = currentTypeParams.subList(0, splitAfterPos);
      final List<String> rightTypeParams = currentTypeParams.subList(splitAfterPos, currentTypeParams.size());

      final List<String> leftParams = currentParams.subList(0, splitAfterPos);
      final List<String> rightParams = currentParams.subList(splitAfterPos, currentTypeParams.size());

      out.println(String.format("  public final Tuple2<Tuple%d<%s>, Tuple%d<%s>> splitAfterElement%d() {",
                                leftTypeParams.size(), String.join(", ", leftTypeParams),
                                rightTypeParams.size(), String.join(", ", rightTypeParams),
                                splitAfterPos));
      out.println("    return Tuple2.of(");
      out.println(String.format("      Tuple%d.of(%s),", splitAfterPos, String.join(", ", leftParams)));
      out.println(String.format("      Tuple%d.of(%s)", size - splitAfterPos, String.join(", ", rightParams)));
      out.println("    );");
      out.println("  }");
      out.println();
    }
  }

  private void writeConcatMethods(PrintWriter out, int size) {
    final List<String> currentTypeParams = typeParamsTo(size);
    final List<String> currentParams = paramsTo(size);

    for (int concatSize = 1; concatSize + size <= 10; concatSize++) {
      final List<String> extraTypeParams = IntStream.range(1, concatSize + 1).mapToObj(i -> String.format("S%d", i)).collect(toList());
      final List<String> extraParams = IntStream.range(1, concatSize + 1).mapToObj(i -> String.format("otherTuple.elem%d()", i)).collect(toList());

      final List<String> concatTypeParams = new ArrayList<>(currentTypeParams);
      concatTypeParams.addAll(extraTypeParams);
      final List<String> concatParams = new ArrayList<>(currentParams);
      concatParams.addAll(extraParams);

      out.println("  /** Concatenates this tuple with the provided tuple.");
      out.println("   * @param otherTuple the tuple whose elements should be appended to this tuple.");
      out.println(String.format("   * @return a new Tuple%d instance containing all the elements of this tuple,", concatTypeParams.size()));
      out.println("   *         followed by all the elements of the other tuple. */");
      out.println(String.format("  public final <%s> Tuple%d<%s> concat(Tuple%d<%s> otherTuple) {",
                                String.join(", ", extraTypeParams),
                                concatTypeParams.size(), String.join(", ", concatTypeParams),
                                extraTypeParams.size(), String.join(", ", extraTypeParams)));
      out.println(String.format("    return Tuple%d.of(%s);", currentTypeParams.size() + extraTypeParams.size(), String.join(", ", concatParams)));
      out.println("  }");
      out.println();
    }
  }

  private void writeReverseMethod(PrintWriter out, int size) {
    final List<String> currentTypeParams = typeParamsTo(size);
    final List<String> currentParams = paramsTo(size);

    final List<String> newTypeParams = new ArrayList<>(currentTypeParams);
    final List<String> newParams = new ArrayList<>(currentParams);
    Collections.reverse(newTypeParams);
    Collections.reverse(newParams);

    out.println(String.format("  /** Returns a new Tuple%d with the elements in reverse order. */", size));
    out.println(String.format("  public final Tuple%d<%s> reverse() {",
                              size, String.join(", ", newTypeParams)));
    out.println(String.format("    return Tuple%d.of(%s);", size, String.join(", ", newParams)));
    out.println("  }");
    out.println();
  }
}
