package com.neil.easytuples;

import com.neil.easytuples.annotations.TupleGeneration;

import java.util.Objects;

@TupleGeneration(tupleArity = 4)
public final class Tuple4<T1, T2, T3, T4>
    extends Tuple4Impl<T1, T2, T3, T4>
    implements Tuple {
  private Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
    super(t1, t2, t3, t4);
  }

  public static <S1, S2, S3, S4> Tuple4<S1, S2, S3, S4> of(S1 s1, S2 s2,
                                                           S3 s3, S4 s4) {
    return new Tuple4<>(s1, s2, s3, s4);
  }

  public Tuple4<T4, T3, T2, T1> reverse() {
    return Tuple4.of(t4, t3, t2, t1);
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
    return Objects.equals(t1, tuple4.t1) && Objects.equals(t2, tuple4.t2) && Objects.equals(t3, tuple4.t3) && Objects.equals(t4, tuple4.t4);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4);
  }
}
