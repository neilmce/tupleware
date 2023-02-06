package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TupleGeneration(tupleArity = 5)
public final class Tuple5<T1, T2, T3, T4, T5> extends Tuple5Impl<T1, T2, T3, T4, T5> implements Tuple {
  private Tuple5(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
    super(t1, t2, t3, t4, t5);
  }

  public static <S1, S2, S3, S4, S5> Tuple5<S1, S2, S3, S4, S5> of(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5) {
    return new Tuple5<>(s1, s2, s3, s4, s5);
  }

  public static <S1, S2, S3, S4, S5> Tuple5<S1, S2, S3, S4, S5> ofNonNull(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5) {
    var args = new ArrayList<>();
    args.add(s1);
    args.add(s2);
    args.add(s3);
    args.add(s4);
    args.add(s5);
    var nullElemPositions = new ArrayList<Integer>();
    for (int i = 1; i <= 5; i++) {
      if (args.get(i - 1) == null) {
        nullElemPositions.add(i);
      }
    }
    if (!nullElemPositions.isEmpty()) {
      throw new NullPointerException(String.format("Illegal null elements at positions %s", nullElemPositions));
    }

    return new Tuple5<>(s1, s2, s3, s4, s5);
  }

  public static <S1, S2, S3, S4, S5> List<Tuple5<S1, S2, S3, S4, S5>> zip(List<S1> l1, List<S2> l2, List<S3> l3, List<S4> l4, List<S5> l5) {
    // TODO null check the lists.
    int shortestListSize = Stream.of(l1, l2, l3, l4, l5)
                                 .map(List::size)
                                 .min(Integer::compareTo).orElse(0);

    List<Tuple5<S1, S2, S3, S4, S5>> result = new ArrayList<>();
    for (int i = 0; i < shortestListSize; i++) {
      result.add(Tuple5.of(l1.get(i), l2.get(i), l3.get(i), l4.get(i), l5.get(i)));
    }
    return result;
  }

  public static <S1, S2, S3, S4, S5> Tuple5<List<S1>, List<S2>, List<S3>, List<S4>, List<S5>> unzip(List<Tuple5<S1, S2, S3, S4, S5>> listOfTuples) {
    var l1 = listOfTuples.stream().map(Tuple5::elem1).collect(Collectors.toList());
    var l2 = listOfTuples.stream().map(Tuple5::elem2).collect(Collectors.toList());
    var l3 = listOfTuples.stream().map(Tuple5::elem3).collect(Collectors.toList());
    var l4 = listOfTuples.stream().map(Tuple5::elem4).collect(Collectors.toList());
    var l5 = listOfTuples.stream().map(Tuple5::elem5).collect(Collectors.toList());
    return Tuple5.of(l1, l2, l3, l4, l5);
  }

  public <T> Tuple6<T, T1, T2, T3, T4, T5> prepend(T t) {
    return Tuple6.of(t, t1, t2, t3, t4, t5);
  }

  public <T> Tuple6<T1, T2, T3, T4, T5, T> append(T t) {
    return Tuple6.of(t1, t2, t3, t4, t5, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple5<?, ?, ?, ?, ?> tuple5 = (Tuple5<?, ?, ?, ?, ?>) o;
    return Objects.equals(t1, tuple5.t1) && Objects.equals(t2, tuple5.t2) && Objects.equals(t3, tuple5.t3) && Objects.equals(t4, tuple5.t4) && Objects.equals(t5, tuple5.t5);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4, t5);
  }
}
