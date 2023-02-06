package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TupleGeneration(tupleArity = 10)
public final class Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    extends Tuple10Impl<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    implements Tuple {

  private Tuple10(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
    super(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9, S10>
    Tuple10<S1, S2, S3, S4, S5, S6, S7, S8, S9, S10> of(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5,
                                                        S6 s6, S7 s7, S8 s8, S9 s9, S10 s10) {
    return new Tuple10<>(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9, S10>
    Tuple10<S1, S2, S3, S4, S5, S6, S7, S8, S9, S10> ofNonNull(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5,
                                                               S6 s6, S7 s7, S8 s8, S9 s9, S10 s10) {
    var args = new ArrayList<>();
    args.add(s1);
    args.add(s2);
    args.add(s3);
    args.add(s4);
    args.add(s5);
    args.add(s6);
    args.add(s7);
    args.add(s8);
    args.add(s9);
    args.add(s10);
    var nullElemPositions = new ArrayList<Integer>();
    for (int i = 1; i <= 10; i++) {
      if (args.get(i - 1) == null) {
        nullElemPositions.add(i);
      }
    }
    if (!nullElemPositions.isEmpty()) {
      throw new NullPointerException(String.format("Illegal null elements at positions %s", nullElemPositions));
    }

    return new Tuple10<>(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9, S10> List<Tuple10<S1, S2, S3, S4, S5, S6, S7, S8, S9, S10>> zip(List<S1> l1, List<S2> l2, List<S3> l3, List<S4> l4, List<S5> l5, List<S6> l6, List<S7> l7, List<S8> l8, List<S9> l9, List<S10> l10) {
    // TODO null check the lists.
    int shortestListSize = Stream.of(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10)
                                 .map(List::size)
                                 .min(Integer::compareTo).orElse(0);

    List<Tuple10<S1, S2, S3, S4, S5, S6, S7, S8, S9, S10>> result = new ArrayList<>();
    for (int i = 0; i < shortestListSize; i++) {
      result.add(Tuple10.of(l1.get(i), l2.get(i), l3.get(i), l4.get(i), l5.get(i), l6.get(i), l7.get(i), l8.get(i), l9.get(i), l10.get(i)));
    }
    return result;
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9, S10> Tuple10<List<S1>, List<S2>, List<S3>, List<S4>, List<S5>, List<S6>, List<S7>, List<S8>, List<S9>, List<S10>> unzip(List<Tuple10<S1, S2, S3, S4, S5, S6, S7, S8, S9, S10>> listOfTuples) {
    var l1 = listOfTuples.stream().map(Tuple10::elem1).collect(Collectors.toList());
    var l2 = listOfTuples.stream().map(Tuple10::elem2).collect(Collectors.toList());
    var l3 = listOfTuples.stream().map(Tuple10::elem3).collect(Collectors.toList());
    var l4 = listOfTuples.stream().map(Tuple10::elem4).collect(Collectors.toList());
    var l5 = listOfTuples.stream().map(Tuple10::elem5).collect(Collectors.toList());
    var l6 = listOfTuples.stream().map(Tuple10::elem6).collect(Collectors.toList());
    var l7 = listOfTuples.stream().map(Tuple10::elem7).collect(Collectors.toList());
    var l8 = listOfTuples.stream().map(Tuple10::elem8).collect(Collectors.toList());
    var l9 = listOfTuples.stream().map(Tuple10::elem9).collect(Collectors.toList());
    var l10 = listOfTuples.stream().map(Tuple10::elem10).collect(Collectors.toList());
    return Tuple10.of(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> tuple10 = (Tuple10<?, ?, ?, ?, ?, ?, ?, ?, ?, ?>) o;
    return Objects.equals(t1, tuple10.t1) && Objects.equals(t2, tuple10.t2) && Objects.equals(t3, tuple10.t3) && Objects.equals(t4, tuple10.t4) && Objects.equals(t5, tuple10.t5) && Objects.equals(t6, tuple10.t6) && Objects.equals(t7, tuple10.t7) && Objects.equals(t8, tuple10.t8) && Objects.equals(t9, tuple10.t9) && Objects.equals(t10, tuple10.t10);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
  }
}
