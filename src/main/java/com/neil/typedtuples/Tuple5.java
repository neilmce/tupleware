package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;

import java.util.Objects;

@TupleGeneration(tupleArity = 5)
public final class Tuple5<T1, T2, T3, T4, T5> extends Tuple5Impl<T1, T2, T3, T4, T5> implements Tuple {
  private Tuple5(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
    super(t1, t2, t3, t4, t5);
  }

  public static <S1, S2, S3, S4, S5> Tuple5<S1, S2, S3, S4, S5> of(S1 s1, S2 s2, S3 s3, S4 s4, S5 s5) {
    return new Tuple5<>(s1, s2, s3, s4, s5);
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
