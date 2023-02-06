package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;
import com.neil.typedtuples.util.TtObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TupleGeneration(tupleArity = 7)
public final class Tuple7<T1, T2, T3, T4, T5, T6, T7> extends Tuple7Impl<T1, T2, T3, T4, T5, T6, T7> implements Tuple {

  private Tuple7(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
    super(t1, t2, t3, t4, t5, t6, t7);
  }

  public static <S1, S2, S3, S4, S5, S6, S7> Tuple7<S1, S2, S3, S4, S5, S6, S7> of(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5, S6 s6, S7 s7) {
    return new Tuple7<>(s1, s2, s3, s4, s5, s6, s7);
  }

  public static <S1, S2, S3, S4, S5, S6, S7>
  Tuple7<S1, S2, S3, S4, S5, S6, S7> ofNonNull(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5, S6 s6, S7 s7) {
    TtObjects.requireNonNull("Illegal null elements.", s1, s2, s3, s4, s5, s6, s7);

    return new Tuple7<>(s1, s2, s3, s4, s5, s6, s7);
  }

  public static <S1, S2, S3, S4, S5, S6, S7> List<Tuple7<S1, S2, S3, S4, S5, S6, S7>> zip(List<S1> l1, List<S2> l2, List<S3> l3, List<S4> l4, List<S5> l5, List<S6> l6, List<S7> l7) {
    // TODO null check the lists.
    int shortestListSize = Stream.of(l1, l2, l3, l4, l5, l6, l7)
                                 .map(List::size)
                                 .min(Integer::compareTo).orElse(0);

    List<Tuple7<S1, S2, S3, S4, S5, S6, S7>> result = new ArrayList<>();
    for (int i = 0; i < shortestListSize; i++) {
      result.add(Tuple7.of(l1.get(i), l2.get(i), l3.get(i), l4.get(i), l5.get(i), l6.get(i), l7.get(i)));
    }
    return result;
  }

  public static <S1, S2, S3, S4, S5, S6, S7> Tuple7<List<S1>, List<S2>, List<S3>, List<S4>, List<S5>, List<S6>, List<S7>> unzip(List<Tuple7<S1, S2, S3, S4, S5, S6, S7>> listOfTuples) {
    Objects.requireNonNull(listOfTuples, "Illegal null List argument");

    var l1 = listOfTuples.stream().map(Tuple7::elem1).collect(Collectors.toList());
    var l2 = listOfTuples.stream().map(Tuple7::elem2).collect(Collectors.toList());
    var l3 = listOfTuples.stream().map(Tuple7::elem3).collect(Collectors.toList());
    var l4 = listOfTuples.stream().map(Tuple7::elem4).collect(Collectors.toList());
    var l5 = listOfTuples.stream().map(Tuple7::elem5).collect(Collectors.toList());
    var l6 = listOfTuples.stream().map(Tuple7::elem6).collect(Collectors.toList());
    var l7 = listOfTuples.stream().map(Tuple7::elem7).collect(Collectors.toList());
    return Tuple7.of(l1, l2, l3, l4, l5, l6, l7);
  }

  public <T> Tuple8<T, T1, T2, T3, T4, T5, T6, T7> prepend(T t) {
    return Tuple8.of(t, t1, t2, t3, t4, t5, t6, t7);
  }

  public <T> Tuple8<T1, T2, T3, T4, T5, T6, T7, T> append(T t) {
    return Tuple8.of(t1, t2, t3, t4, t5, t6, t7, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple7<?, ?, ?, ?, ?, ?, ?> tuple7 = (Tuple7<?, ?, ?, ?, ?, ?, ?>) o;
    return Objects.deepEquals(t1, tuple7.t1) && Objects.deepEquals(t2, tuple7.t2) && Objects.deepEquals(t3, tuple7.t3) &&
        Objects.deepEquals(t4, tuple7.t4) && Objects.deepEquals(t5, tuple7.t5) && Objects.deepEquals(t6, tuple7.t6) &&
        Objects.deepEquals(t7, tuple7.t7);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4, t5, t6, t7);
  }
}
