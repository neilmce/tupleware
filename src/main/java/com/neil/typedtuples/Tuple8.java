package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;

import java.util.ArrayList;
import java.util.Objects;

@TupleGeneration(tupleArity = 8)
public final class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> extends Tuple8Impl<T1, T2, T3, T4, T5, T6, T7, T8> implements Tuple {

  private Tuple8(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
    super(t1, t2, t3, t4, t5, t6, t7, t8);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8> Tuple8<S1, S2, S3, S4, S5, S6, S7, S8> of(S1 s1, S2 s2,
                                                                                           S3 s3, S4 s4,
                                                                                           S5 s5, S6 s6,
                                                                                           S7 s7, S8 s8) {
    return new Tuple8<>(s1, s2, s3, s4, s5, s6, s7, s8);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8>
    Tuple8<S1, S2, S3, S4, S5, S6, S7, S8> ofNonNull(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5, S6 s6, S7 s7, S8 s8) {
    var args = new ArrayList<>();
    args.add(s1);
    args.add(s2);
    args.add(s3);
    args.add(s4);
    args.add(s5);
    args.add(s6);
    args.add(s7);
    args.add(s8);
    var nullElemPositions = new ArrayList<Integer>();
    for (int i = 1; i <= 8; i++) {
      if (args.get(i - 1) == null) {
        nullElemPositions.add(i);
      }
    }
    if (!nullElemPositions.isEmpty()) {
      throw new NullPointerException(String.format("Illegal null elements at positions %s", nullElemPositions));
    }

    return new Tuple8<>(s1, s2, s3, s4, s5, s6, s7, s8);
  }

  public <T> Tuple9<T, T1, T2, T3, T4, T5, T6, T7, T8> prepend(T t) {
    return Tuple9.of(t, t1, t2, t3, t4, t5, t6, t7, t8);
  }

  public <T> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T> append(T t) {
    return Tuple9.of(t1, t2, t3, t4, t5, t6, t7, t8, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple8<?, ?, ?, ?, ?, ?, ?, ?> tuple8 = (Tuple8<?, ?, ?, ?, ?, ?, ?, ?>) o;
    return Objects.equals(t1, tuple8.t1) && Objects.equals(t2, tuple8.t2) && Objects.equals(t3, tuple8.t3) && Objects.equals(t4, tuple8.t4) && Objects.equals(t5, tuple8.t5) && Objects.equals(t6, tuple8.t6) && Objects.equals(t7, tuple8.t7) && Objects.equals(t8, tuple8.t8);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4, t5, t6, t7, t8);
  }
}
