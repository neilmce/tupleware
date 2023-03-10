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
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A tuple with 2 elements. A pair.
 *
 * @param <T1> the type of the 1st element.
 * @param <T2> the type of the 2nd element.
 */
@TupleGeneration(tupleSize = 2)
public final class Tuple2<T1, T2> extends GeneratedTuple2<T1, T2> implements Tuple {
  private Tuple2(T1 t1, T2 t2) {
    super(t1, t2);
  }

  /**
   * Constructs a tuple with the provided values as elements. The values can be null.
   * @return a new tuple instance.
   */
  public static <ST1, ST2> Tuple2<ST1, ST2> of(ST1 t1, ST2 t2) {
    return new Tuple2<>(t1, t2);
  }

  /**
   * Constructs a tuple with the provided values as elements. The values cannot be null.
   * @return a new tuple instance.
   * @throws NullPointerException if any of the provided values are null.
   */
  public static <S1, S2> Tuple2<S1, S2> ofNonNull(S1 s1, S2 s2) {
    TtObjects.requireNonNull("Illegal null elements.", s1, s2);

    return new Tuple2<>(s1, s2);
  }

  public static <K, V> Tuple2<K, V> from(Entry<K, V> entry) {
    return Tuple2.of(entry.getKey(), entry.getValue());
  }

  public static <S1, S2> List<Tuple2<S1, S2>> zip(List<S1> l1, List<S2> l2) {
    TtObjects.requireNonNull("Illegal null Lists.", l1, l2);

    int shortestListSize = Math.min(l1.size(), l2.size());

    List<Tuple2<S1, S2>> result = new ArrayList<>();
    for (int i = 0; i < shortestListSize; i++) {
      result.add(Tuple2.of(l1.get(i), l2.get(i)));
    }
    return result;
  }

  public static <S1, S2> Tuple2<List<S1>, List<S2>> unzip(List<Tuple2<S1, S2>> listOfTuples) {
    Objects.requireNonNull(listOfTuples, "Illegal null List argument");

    var left = listOfTuples.stream().map(Tuple2::elem1).collect(Collectors.toList());
    var right = listOfTuples.stream().map(Tuple2::elem2).collect(Collectors.toList());
    return Tuple2.of(left, right);
  }

  public Tuple2<T2, T1> swap() {
    return reverse();
  }

  public <T> Tuple3<T, T1, T2> prepend(T t) {
    return Tuple3.of(t, t1, t2);
  }

  public <T> Tuple3<T1, T2, T> append(T t) {
    return Tuple3.of(t1, t2, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
    return Objects.deepEquals(t1, tuple2.t1) && Objects.deepEquals(t2, tuple2.t2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2);
  }
}
