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

package com.neil.tupleware;

import com.neil.tupleware.annotations.TupleGeneration;
import com.neil.tupleware.util.TtObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A tuple with 3 elements. A triple.
 *
 * @param <T1> the type of the 1st element.
 * @param <T2> the type of the 2nd element.
 * @param <T3> the type of the 3rd element.
 */
@TupleGeneration(tupleArity = 3)
public final class Tuple3<T1, T2, T3> extends GeneratedTuple3<T1, T2, T3> implements Tuple {

  private Tuple3(T1 t1, T2 t2, T3 t3) {
    super(t1, t2, t3);
  }

  /**
   * Constructs a tuple with the provided values as elements. The values can be null.
   * @return a new tuple instance.
   */
  public static <S1, S2, S3> Tuple3<S1, S2, S3> of(S1 s1, S2 s2, S3 s3) {
    return new Tuple3<>(s1, s2, s3);
  }

  /**
   * Constructs a tuple with the provided values as elements. The values cannot be null.
   * @return a new tuple instance.
   * @throws NullPointerException if any of the provided values are null.
   */
  public static <S1, S2, S3> Tuple3<S1, S2, S3> ofNonNull(S1 s1, S2 s2, S3 s3) {
    TtObjects.requireNonNull("Illegal null elements.", s1, s2, s3);

    return new Tuple3<>(s1, s2, s3);
  }

  public static <S1, S2, S3> List<Tuple3<S1, S2, S3>> zip(List<S1> l1, List<S2> l2, List<S3> l3) {
    TtObjects.requireNonNull("Illegal null Lists.", l1, l2, l3);

    int shortestListSize = Stream.of(l1, l2, l3)
                                 .map(List::size)
                                 .min(Integer::compareTo).orElse(0);

    List<Tuple3<S1, S2, S3>> result = new ArrayList<>();
    for (int i = 0; i < shortestListSize; i++) {
      result.add(Tuple3.of(l1.get(i), l2.get(i), l3.get(i)));
    }
    return result;
  }

  public static <S1, S2, S3> Tuple3<List<S1>, List<S2>, List<S3>> unzip(List<Tuple3<S1, S2, S3>> listOfTuples) {
    Objects.requireNonNull(listOfTuples, "Illegal null List argument");

    var left = listOfTuples.stream().map(Tuple3::elem1).collect(Collectors.toList());
    var middle = listOfTuples.stream().map(Tuple3::elem2).collect(Collectors.toList());
    var right = listOfTuples.stream().map(Tuple3::elem3).collect(Collectors.toList());
    return Tuple3.of(left, middle, right);
  }

  public <T> Tuple4<T, T1, T2, T3> prepend(T t) {
    return Tuple4.of(t, t1, t2, t3);
  }

  public <T> Tuple4<T1, T2, T3, T> append(T t) {
    return Tuple4.of(t1, t2, t3, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple3<?, ?, ?> tuple3 = (Tuple3<?, ?, ?>) o;
    return Objects.deepEquals(t1, tuple3.t1) && Objects.deepEquals(t2, tuple3.t2) && Objects.deepEquals(t3, tuple3.t3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3);
  }
}
