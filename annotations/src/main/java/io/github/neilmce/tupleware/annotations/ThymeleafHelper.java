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

  /** Returns String like "&lt;token&gt;1, &lt;token&gt;2, &lt;token&gt;3".
   *
   * @param fromIncl from number
   * @param toIncl to number
   * @param formatString format String, must include "%s" followed by "%d".
   * @param token the String token to insert.
   */
  public String rangeOneToken(int fromIncl, int toIncl, String formatString, String token) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> String.format(formatString, token, i))
                    .collect(joining(", "));
  }

  /** Returns String like TODO. */
  public String rangeTwoTokens(int fromIncl, int toIncl, String formatString, String token1, String token2) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> String.format(formatString, token1, token2))
                    .collect(joining(", "));
  }

  /** Returns String like "T1, T2, T3". */
  public String charNumberPairs(int fromIncl, int toIncl, char c) {
    return rangeOneToken(fromIncl, toIncl, "%s%d", Character.toString(c));
  }

  /** Returns String like "T1 t1, T2 t2, T3 t3". */
  public String getTypeParams(int fromIncl, int toIncl) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return IntStream.range(fromIncl, toIncl + 1)
                    .mapToObj(i -> String.format("T%d t%d", i, i))
                    .collect(joining(", "));
  }

  /** Returns String like TODO. */
  public String sampleArgs(int fromIncl, int toIncl) {
    requireFromNotAfterTo(fromIncl, toIncl);

    return String.join(", ", sampleElements.subList(fromIncl - 1, toIncl));
  }

  public String sampleArgsReplacing(int fromIncl, int toIncl, int replaceIndex, String replaceWith) {
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

  public String sampleArgsDropping(int fromIncl, int toIncl, int dropIndex) {
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
