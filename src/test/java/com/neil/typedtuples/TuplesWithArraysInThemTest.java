package com.neil.typedtuples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/** Test cases that ensure we handle Tuples with arrays as elements. */
class TuplesWithArraysInThemTest {

  @Test void tuple2EqualsAndHashCodeWithEqualArrays() {
    var t1 = Tuple2.of(new int[] {1, 2}, new String[] { "hi", "bye"});
    var t2 = Tuple2.of(new int[] {1, 2}, new String[] { "hi", "bye"});

    assertEquals(t1, t2);
  }

  @Test void tuple2EqualsAndHashCodeWithSimilarArrays() {
    var t1 = Tuple2.of(new int[] {1, 2}, new String[] { "hi", "bye"});
    var t2 = Tuple2.of(new long[] {1L, 2L}, new String[] { "hi", "bye"});

    assertNotEquals(t1, t2);
  }

  // TODO Other arities.
}
