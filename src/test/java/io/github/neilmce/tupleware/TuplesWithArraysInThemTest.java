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

package io.github.neilmce.tupleware;

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
}
