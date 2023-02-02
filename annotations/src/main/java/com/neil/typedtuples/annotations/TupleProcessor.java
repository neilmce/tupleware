package com.neil.typedtuples.annotations;

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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@SupportedAnnotationTypes("com.neil.typedtuples.annotations.TupleGeneration")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
@AutoService(Processor.class)
public class TupleProcessor extends AbstractProcessor {

    private void note(String msg) {
        processingEnv.getMessager().printMessage(Kind.NOTE, msg);
    }

    private void error(String msg) {
        processingEnv.getMessager().printMessage(Kind.ERROR, msg);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elems = roundEnv.getElementsAnnotatedWith(TupleGeneration.class);
        note(String.format("Processing %d @TupleGeneration elements: %s", elems.size(), elems));

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
                int arity = anno.tupleArity();

                final String packageName;
                String className = classElem.getQualifiedName().toString();
                int lastDot = className.lastIndexOf('.');
                if (lastDot > 0) {
                    packageName = className.substring(0, lastDot);
                }
                else {
                    packageName = null;
                }

                if (arity < 0 || arity > 10) {
                    error("Illegal arity on @TupleGeneration: " + arity);
                }
                try {
                    writeTupleImplFile(packageName, arity);
                } catch (IOException e) {
                    error("IOException while writing builder file");

                    throw new RuntimeException(e);
                }
            });
        }

        return true;
    }

    private void writeTupleImplFile(String packageName, int arity) throws IOException {
        final String implClassName = String.format("Tuple%dImpl", arity);
        final String fqImplClassName = packageName == null ? implClassName : packageName + "." + implClassName;

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(fqImplClassName);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
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

            out.print(String.format("public class %s", implClassName));
            // Generic types
            out.print(" <");
            for (int i = 1; i <= arity; i++) {
                out.print(String.format("T%d", i));
                if (i < arity) {
                    out.print(", ");
                }
            }
            out.println("> implements Tuple {");

            writeFieldsAndConstructor(out, implClassName, arity);

            writeGetArityMethod(out, arity);

            writeElementGetters(out, arity);

            writeContainsAnyNullsMethod(out, arity);

            writeToListMethod(out, arity);

            writeSplitMethods(out, arity);

            writeWithElementMethods(out, arity);

            writeMapElementMethods(out, arity);

            writeToStringMethod(out, arity);

            out.println("}");
        }
    }

    private void writeFieldsAndConstructor(PrintWriter out, String implClassName, int arity) {
        // Fields
        for (int i = 1; i <= arity; i++) {
            out.println(String.format("  protected final T%d t%d;", i, i));
        }
        // Constructor
        out.print(String.format("  protected %s(", implClassName));
        // Constructor params
        for (int i = 1; i <= arity; i++) {
            out.print(String.format("T%d t%d", i, i));
            if (i < arity) {
                out.print(", ");
            }
        }
        out.println(") {");
        // Field assignments
        for (int i = 1; i <= arity; i++) {
            out.println(String.format("    this.t%d = t%d;", i, i));
        }
        out.println("  }");
    }

    private void writeElementGetters(PrintWriter out, int arity) {
        for (int i = 1; i <= arity; i++) {
            out.println(String.format("  public final T%d elem%d() { return this.t%d; }", i, i, i));
        }
    }

    private void writeContainsAnyNullsMethod(PrintWriter out, int arity) {
        out.println("  @Override public final boolean containsAnyNulls() {");
        out.print("      return ");
        for (int i = 1; i <= arity; i++) {
            out.print(String.format("t%d == null", i));
            if (i < arity) {
                out.print(" || ");
            }
        }
        out.println(";");
        out.println("  }");
    }

    private void writeToListMethod(PrintWriter out, int arity) {
        out.println("  @Override public final List<Object> toList() {");
        out.print("      return List.of(");
        for (int i = 1; i <= arity; i++) {
            out.print(String.format("t%d", i));
            if (i < arity) {
                out.print(", ");
            }
        }
        out.println(");");
        out.println("  }");
    }

    private void writeGetArityMethod(PrintWriter out, int arity) {
        out.println(String.format("  @Override public final int getArity() { return %d; }", arity));
    }

    private void writeToStringMethod(PrintWriter out, int arity) {
        out.println("  @Override public final String toString() {");
        out.print("      return String.format(\"(");
        for (int i = 1; i <= arity; i++) {
            out.print("%s");
            if (i < arity) {
                out.print(", ");
            }
        }
        out.print(")\", ");
        for (int i = 1; i <= arity; i++) {
            out.print(String.format("t%d", i));
            if (i < arity) {
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

    private void writeWithElementMethods(PrintWriter out, int arity) {
        final List<String> currentTypeParams = typeParamsTo(arity);
        final List<String> currentParams = paramsTo(arity);

        for (int elemToReplace = 1; elemToReplace <= arity; elemToReplace++) {
            final List<String> newTypeParams = new ArrayList<>(currentTypeParams);
            newTypeParams.set(elemToReplace - 1, "R");

            final List<String> newParams = new ArrayList<>(currentParams);
            newParams.set(elemToReplace - 1, "newValue");

            out.print(String.format("  public final <R> Tuple%d<", arity));
            out.print(String.join(", ", newTypeParams));
            out.println(String.format("> withElem%d(R newValue) {", elemToReplace));

            out.print(String.format("    return Tuple%d.of(", arity));
            out.print(String.join(", ", newParams));
            out.println(");");
            out.println("  }");
            out.println();
        }
    }

    private void writeMapElementMethods(PrintWriter out, int arity) {
        final List<String> currentTypeParams = typeParamsTo(arity);
        final List<String> currentParams = paramsTo(arity);

        for (int elemToReplace = 1; elemToReplace <= arity; elemToReplace++) {
            final List<String> newTypeParams = new ArrayList<>(currentTypeParams);
            newTypeParams.set(elemToReplace - 1, "R");

            final List<String> newParams = new ArrayList<>(currentParams);
            newParams.set(elemToReplace - 1, String.format("function.apply(t%d)", elemToReplace));

            out.print(String.format("  public final <R> Tuple%d<", arity));
            out.print(String.join(", ", newTypeParams));
            out.println(String.format("> mapElem%d(Function<T%d, R> function) {", elemToReplace, elemToReplace));

            out.print(String.format("    return Tuple%d.of(", arity));
            out.print(String.join(", ", newParams));
            out.println(");");
            out.println("  }");
            out.println();
        }
    }

    private void writeSplitMethods(PrintWriter out, int arity) {
        final List<String> currentTypeParams = typeParamsTo(arity);
        final List<String> currentParams = paramsTo(arity);

        for (int splitAfterPos = 1; splitAfterPos < arity; splitAfterPos++) {
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
            out.println(String.format("      Tuple%d.of(%s)", arity - splitAfterPos, String.join(", ", rightParams)));
            out.println("    );");
            out.println("  }");
            out.println();
        }
    }
}
