package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;

import java.util.Objects;

@TupleGeneration(tupleArity = 3)
public final class Tuple3<T1, T2, T3> extends Tuple3Impl<T1, T2, T3> implements Tuple {

  private Tuple3(T1 t1, T2 t2, T3 t3) {
    super(t1, t2, t3);
  }

  public static <S1, S2, S3> Tuple3<S1, S2, S3> of(S1 s1, S2 s2, S3 s3) {
    return new Tuple3<>(s1, s2, s3);
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
    return Objects.equals(t1, tuple3.t1) && Objects.equals(t2, tuple3.t2) && Objects.equals(t3, tuple3.t3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3);
  }
}
