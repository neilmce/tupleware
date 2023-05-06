package io.github.neilmce.tupleware.annotations;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

/** Various useful functions reused throughout the thymeleaf template for our tuple classes. */
public class ThymeleafHelper {
  private static final List<String> sampleElements = List.of("\"Hi\"", "2", "3.1", "true", "JANUARY",
                                                             "UTC", "'x'", "List.of(1)", "Set.of(1)", "Map.of(\"One\", 1)");

  private void requireFromNotAfterTo(int fromIncl, int toIncl) {
    if (fromIncl > toIncl) {
      throw new IllegalArgumentException(String.format("From cannot be greater than to: %d > %d", fromIncl, toIncl));
    }
  }

  /** Creates a String of repeated elements into which the number will be substituted.
   * For example, given a format String of "elem%d()" and a range of 1 to 3, we would get
   * "elem1(), elem2(), elem3()".
   *
   * @param fromIncl from number
   * @param toIncl to number
   * @param formatString format String, must include one "%d".
   */
  public String generateRepeatingTokenWithIndex(int fromIncl, int toIncl, String formatString) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> String.format(formatString, i))
                    .collect(joining(", "));
  }

  /** Creates a String of generic type names like "T1, T2, T3".
   *
   * @param fromIncl starting position e.g. 1.
   * @param toIncl ending position e.g. 3.
   * @param c Type prefix e.g. 'T'.
   * @return a String of Type names like "T1, T2, T3".
   */
  public String generateGenericTypeNames(int fromIncl, int toIncl, char c) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> String.format("%s%d", Character.toString(c), i))
                    .collect(joining(", "));
  }

  /** Creates a String of generic type parameters like "T1 t1, T2 t2, T3 t3".
   *
   * @param fromIncl starting position e.g. 1.
   * @param toIncl ending position e.g. 3.
   * @return a String of Type names like "T1 t1, T2 t2, T3 t3".
   */
  public String generateGenericTypeParams(int fromIncl, int toIncl) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> String.format("T%d t%d", i, i))
                    .collect(joining(", "));
  }

  /** Creates a String of sample argument values like "\"Hi\", 2, 3.1".
   *
   * @param fromIncl starting position e.g. 1.
   * @param toIncl ending position e.g. 3.
   * @return a String of sample argument values like "\"Hi\", 2, 3.1"
   */
  public String generateSampleArgs(int fromIncl, int toIncl) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return String.join(", ", sampleElements.subList(fromIncl - 1, toIncl));
  }

  public String generateSampleArgsReplacing(int fromIncl, int toIncl, int replaceIndex, String replaceWith) {
    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> {
                      if (i == replaceIndex) {
                        return replaceWith;
                      }
                      else {
                        return sampleElements.get(i - 1);
                      }
                    })
                    .collect(joining(", "));
  }

  public String generateSampleArgsDropping(int fromIncl, int toIncl, int dropIndex) {
    if (fromIncl == toIncl) {
      return "";
    }

    requireFromNotAfterTo(fromIncl, toIncl);

    return IntStream.range(fromIncl - 1, toIncl - 1)
                    .filter(n -> n != (dropIndex - 1))
                    .mapToObj(sampleElements::get)
                    .collect(joining(", "));
  }

  public String angledTypeParamsDropping(int fromIncl, int toIncl, int dropIndex) {
    if (fromIncl == toIncl) {
      return "";
    }

    return IntStream.range(fromIncl, toIncl + 1)
                    .filter(n -> n != dropIndex)
                    .mapToObj(i -> String.format("T%d", i))
                    .collect(joining(", ", "<", ">"));
  }

  public String paramsDropping(int fromIncl, int toIncl, int dropIndex) {
    if (fromIncl == toIncl) {
      return "";
    }

    return IntStream.range(fromIncl, toIncl + 1)
        .filter(n -> n != dropIndex)
                    .mapToObj(i -> String.format("t%d", i))
                    .collect(joining(", "));
  }

  public String angledTypeParamsReplacing(int fromIncl, int toIncl, int replaceIndex, String replaceWith) {
    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> {
                      if (i == replaceIndex) {
                        return replaceWith;
                      }
                      else {
                        return String.format("T%d", i);
                      }
                    })
                    .collect(joining(", ", "<", ">"));
  }

  public String paramsReplacing(int fromIncl, int toIncl, int replaceIndex, String replaceWith) {
    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> {
                      if (i == replaceIndex) {
                        return replaceWith;
                      }
                      else {
                        return String.format("t%d", i);
                      }
                    })
                    .collect(joining(", "));
  }

  // FIXME Fix name.
  public String paramsReplacingPuke(int fromIncl, int toIncl, int replaceIndex, String replaceWith) {
    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> {
                      if (i == replaceIndex) {
                        return replaceWith.replace("XXX", Integer.toString(i));
                      }
                      else {
                        return String.format("t%d", i);
                      }
                    })
                    .collect(joining(", "));
  }
}
