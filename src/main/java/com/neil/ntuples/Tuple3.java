package com.neil.ntuples;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class Tuple3<T1, T2, T3> implements Tuple {
  private final T1 t1;
  private final T2 t2;
  private final T3 t3;

  private Tuple3(T1 t1, T2 t2, T3 t3) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
  }

  public static <S1, S2, S3> Tuple3<S1, S2, S3> of(S1 s1, S2 s2,
                                                   S3 s3) {
    return new Tuple3<>(s1, s2, s3);
  }

  public T1 elem1() {
    return this.t1;
  }

  public T2 elem2() {
    return this.t2;
  }

  public T3 elem3() {
    return this.t3;
  }

  public Tuple3<T3, T2, T1> reverse() {
    return Tuple3.of(t3, t2, t1);
  }

  public <T> Tuple4<T, T1, T2, T3> prepend(T t) {
    return Tuple4.of(t, t1, t2, t3);
  }

  public <T> Tuple4<T1, T2, T3, T> append(T t) {
    return Tuple4.of(t1, t2, t3, t);
  }

  @Override
  public List<Object> toList() {
    return List.of(t1, t2, t3);
  }
  @Override
  public boolean containsAnyNulls() {
    return t1 == null || t2 == null || t3 == null;
  }
  public <R> Tuple3<R, T2, T3> mapElem1(Function<T1, R> function) {
    return Tuple3.of(
        function.apply(t1), t2, t3
    );
  }

  public <R> Tuple3<T1, R, T3> mapElem2(Function<T2, R> function) {
    return Tuple3.of(
        t1, function.apply(t2), t3
    );
  }

  public <R> Tuple3<T1, T2, R> mapElem3(Function<T3, R> function) {
    return Tuple3.of(
        t1, t2, function.apply(t3)
    );
  }
  public <R> Tuple3<R, T2, T3> withElem1(R newValue) {
    return Tuple3.of(
        newValue, t2, t3
    );
  }

  public <R> Tuple3<T1, R, T3> withElem2(R newValue) {
    return Tuple3.of(
        t1, newValue, t3
    );
  }

  public <R> Tuple3<T1, T2, R> withElem3(R newValue) {
    return Tuple3.of(
        t1, t2, newValue
    );
  }

  @Override
  public int getArity() {
    return 3;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s, %s)", t1, t2, t3);
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
