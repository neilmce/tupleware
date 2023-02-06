package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;
import com.neil.typedtuples.util.TtObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A tuple with 9 elements.
 *
 * @param <T1> the type of the 1st element.
 * @param <T2> the type of the 2nd element.
 * @param <T3> the type of the 3rd element.
 * @param <T4> the type of the 4th element.
 * @param <T5> the type of the 5th element.
 * @param <T6> the type of the 6th element.
 * @param <T7> the type of the 7th element.
 * @param <T8> the type of the 8th element.
 * @param <T9> the type of the 9th element.
 */
@TupleGeneration(tupleArity = 9)
public final class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends Tuple9Impl<T1, T2, T3, T4, T5, T6, T7, T8, T9>
                                                              implements Tuple {

  private Tuple9(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
    super(t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9> Tuple9<S1, S2, S3, S4, S5, S6, S7, S8, S9> of(S1 s1, S2 s2,
                                                                                                   S3 s3, S4 s4,
                                                                                                   S5 s5, S6 s6,
                                                                                                   S7 s7, S8 s8,
                                                                                                   S9 s9) {
    return new Tuple9<>(s1, s2, s3, s4, s5, s6, s7, s8, s9);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9>
    Tuple9<S1, S2, S3, S4, S5, S6, S7, S8, S9> ofNonNull(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5,
                                                         S6 s6, S7 s7, S8 s8, S9 s9) {
    TtObjects.requireNonNull("Illegal null elements.", s1, s2, s3, s4, s5, s6, s7, s8, s9);

    return new Tuple9<>(s1, s2, s3, s4, s5, s6, s7, s8, s9);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9> List<Tuple9<S1, S2, S3, S4, S5, S6, S7, S8, S9>> zip(List<S1> l1, List<S2> l2, List<S3> l3, List<S4> l4, List<S5> l5, List<S6> l6, List<S7> l7, List<S8> l8, List<S9> l9) {
    TtObjects.requireNonNull("Illegal null Lists.", l1, l2, l3, l4, l5, l6, l7, l8, l9);

    int shortestListSize = Stream.of(l1, l2, l3, l4, l5, l6, l7, l8, l9)
                                 .map(List::size)
                                 .min(Integer::compareTo).orElse(0);

    List<Tuple9<S1, S2, S3, S4, S5, S6, S7, S8, S9>> result = new ArrayList<>();
    for (int i = 0; i < shortestListSize; i++) {
      result.add(Tuple9.of(l1.get(i), l2.get(i), l3.get(i), l4.get(i), l5.get(i), l6.get(i), l7.get(i), l8.get(i), l9.get(i)));
    }
    return result;
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9> Tuple9<List<S1>, List<S2>, List<S3>, List<S4>, List<S5>, List<S6>, List<S7>, List<S8>, List<S9>> unzip(List<Tuple9<S1, S2, S3, S4, S5, S6, S7, S8, S9>> listOfTuples) {
    Objects.requireNonNull(listOfTuples, "Illegal null List argument");

    var l1 = listOfTuples.stream().map(Tuple9::elem1).collect(Collectors.toList());
    var l2 = listOfTuples.stream().map(Tuple9::elem2).collect(Collectors.toList());
    var l3 = listOfTuples.stream().map(Tuple9::elem3).collect(Collectors.toList());
    var l4 = listOfTuples.stream().map(Tuple9::elem4).collect(Collectors.toList());
    var l5 = listOfTuples.stream().map(Tuple9::elem5).collect(Collectors.toList());
    var l6 = listOfTuples.stream().map(Tuple9::elem6).collect(Collectors.toList());
    var l7 = listOfTuples.stream().map(Tuple9::elem7).collect(Collectors.toList());
    var l8 = listOfTuples.stream().map(Tuple9::elem8).collect(Collectors.toList());
    var l9 = listOfTuples.stream().map(Tuple9::elem9).collect(Collectors.toList());
    return Tuple9.of(l1, l2, l3, l4, l5, l6, l7, l8, l9);
  }

  public <T> Tuple10<T, T1, T2, T3, T4, T5, T6, T7, T8, T9> prepend(T t) {
    return Tuple10.of(t, t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  public <T> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T> append(T t) {
    return Tuple10.of(t1, t2, t3, t4, t5, t6, t7, t8, t9, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple9<?, ?, ?, ?, ?, ?, ?, ?, ?> tuple9 = (Tuple9<?, ?, ?, ?, ?, ?, ?, ?, ?>) o;
    return Objects.deepEquals(t1, tuple9.t1) && Objects.deepEquals(t2, tuple9.t2) && Objects.deepEquals(t3, tuple9.t3) &&
        Objects.deepEquals(t4, tuple9.t4) && Objects.deepEquals(t5, tuple9.t5) && Objects.deepEquals(t6, tuple9.t6) &&
        Objects.deepEquals(t7, tuple9.t7) && Objects.deepEquals(t8, tuple9.t8) && Objects.deepEquals(t9, tuple9.t9);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }
}
