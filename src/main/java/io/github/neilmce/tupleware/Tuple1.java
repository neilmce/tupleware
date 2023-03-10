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

import io.github.neilmce.tupleware.annotations.TupleGeneration;

import java.util.Objects;

/**
 * A tuple with a single element.
 *
 * @param <T1> the type of the element.
 */
@TupleGeneration(tupleSize = 1)
public final class Tuple1<T1> extends GeneratedTuple1<T1> implements Tuple {
  private Tuple1(T1 t1) {
    super(t1);
  }

  /**
   * Constructs a tuple with the provided value as the sole element. The value can be null.
   * @return a new tuple instance.
   */
  public static <S1> Tuple1<S1> of(S1 s1) {
    return new Tuple1<>(s1);
  }

  /**
   * Constructs a tuple with the provided value as the sole element. The value cannot be null.
   * @return a new tuple instance.
   * @throws NullPointerException if the provided value is null.
   */
  public static <S1> Tuple1<S1> ofNonNull(S1 s1) {
    if (s1 == null) {
      throw new NullPointerException("Illegal null element at position 1");
    }
    else {
      return new Tuple1<>(s1);
    }
  }

  public <T> Tuple2<T, T1> prepend(T t) {
    return Tuple2.of(t, t1);
  }

  public <T> Tuple2<T1, T> append(T t) {
    return Tuple2.of(t1, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple1<?> tuple1 = (Tuple1<?>) o;
    return Objects.deepEquals(t1, tuple1.t1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1);
  }
}
