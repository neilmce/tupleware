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
import io.github.neilmce.tupleware.util.TtObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A tuple with 4 elements.
 *
 * @param <T1> the type of the 1st element.
 * @param <T2> the type of the 2nd element.
 * @param <T3> the type of the 3rd element.
 * @param <T4> the type of the 4th element.
 */
@TupleGeneration(tupleSize = 4)
public final class Tuple4<T1, T2, T3, T4> extends GeneratedTuple4<T1, T2, T3, T4> implements Tuple {
  private Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
    super(t1, t2, t3, t4);
  }

  /**
   * Constructs a tuple with the provided values as elements. The values can be null.
   * @return a new tuple instance.
   */
  public static <S1, S2, S3, S4> Tuple4<S1, S2, S3, S4> of(S1 s1, S2 s2, S3 s3, S4 s4) {
    return new Tuple4<>(s1, s2, s3, s4);
  }

  /**
   * Constructs a tuple with the provided values as elements. The values cannot be null.
   * @return a new tuple instance.
   * @throws NullPointerException if any of the provided values are null.
   */
  public static <S1, S2, S3, S4> Tuple4<S1, S2, S3, S4> ofNonNull(S1 s1, S2 s2, S3 s3, S4 s4) {
    TtObjects.requireNonNull("Illegal null elements.", s1, s2, s3, s4);

    return new Tuple4<>(s1, s2, s3, s4);
  }

  public static <S1, S2, S3, S4> List<Tuple4<S1, S2, S3, S4>> zip(List<S1> l1, List<S2> l2, List<S3> l3, List<S4> l4) {
    TtObjects.requireNonNull("Illegal null Lists.", l1, l2, l3, l4);

    int shortestListSize = Stream.of(l1, l2, l3, l4)
                                 .map(List::size)
                                 .min(Integer::compareTo).orElse(0);

    List<Tuple4<S1, S2, S3, S4>> result = new ArrayList<>();
    for (int i = 0; i < shortestListSize; i++) {
      result.add(Tuple4.of(l1.get(i), l2.get(i), l3.get(i), l4.get(i)));
    }
    return result;
  }

  public static <S1, S2, S3, S4> Tuple4<List<S1>, List<S2>, List<S3>, List<S4>> unzip(List<Tuple4<S1, S2, S3, S4>> listOfTuples) {
    Objects.requireNonNull(listOfTuples, "Illegal null List argument");

    var l1 = listOfTuples.stream().map(Tuple4::elem1).collect(Collectors.toList());
    var l2 = listOfTuples.stream().map(Tuple4::elem2).collect(Collectors.toList());
    var l3 = listOfTuples.stream().map(Tuple4::elem3).collect(Collectors.toList());
    var l4 = listOfTuples.stream().map(Tuple4::elem4).collect(Collectors.toList());
    return Tuple4.of(l1, l2, l3, l4);
  }

  public <T> Tuple5<T, T1, T2, T3, T4> prepend(T t) {
    return Tuple5.of(t, t1, t2, t3, t4);
  }

  public <T> Tuple5<T1, T2, T3, T4, T> append(T t) {
    return Tuple5.of(t1, t2, t3, t4, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple4<?, ?, ?, ?> tuple4 = (Tuple4<?, ?, ?, ?>) o;
    return Objects.deepEquals(t1, tuple4.t1) && Objects.deepEquals(t2, tuple4.t2) &&
        Objects.deepEquals(t3, tuple4.t3) && Objects.deepEquals(t4, tuple4.t4);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4);
  }
}
