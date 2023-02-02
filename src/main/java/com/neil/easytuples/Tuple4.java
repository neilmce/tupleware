package com.neil.easytuples;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class Tuple4<T1, T2, T3, T4> implements Tuple {
  private final T1 t1;
  private final T2 t2;
  private final T3 t3;
  private final T4 t4;

  private Tuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
  }

  public static <S1, S2, S3, S4> Tuple4<S1, S2, S3, S4> of(S1 s1, S2 s2,
                                                           S3 s3, S4 s4) {
    return new Tuple4<>(s1, s2, s3, s4);
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

  public T4 elem4() {
    return this.t4;
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
  public List<Object> toList() {
    return List.of(t1, t2, t3, t4);
  }
  @Override
  public boolean containsAnyNulls() {
    return t1 == null || t2 == null || t3 == null || t4 == null;
  }
  public <R> Tuple4<R, T2, T3, T4> mapElem1(Function<T1, R> function) {
    return Tuple4.of(
        function.apply(t1), t2, t3, t4
    );
  }

  public <R> Tuple4<T1, R, T3, T4> mapElem2(Function<T2, R> function) {
    return Tuple4.of(
        t1, function.apply(t2), t3, t4
    );
  }

  public <R> Tuple4<T1, T2, R, T4> mapElem3(Function<T3, R> function) {
    return Tuple4.of(
        t1, t2, function.apply(t3), t4
    );
  }

  public <R> Tuple4<T1, T2, T3, R> mapElem4(Function<T4, R> function) {
    return Tuple4.of(
        t1, t2, t3, function.apply(t4)
    );
  }
  public <R> Tuple4<R, T2, T3, T4> withElem1(R newValue) {
    return Tuple4.of(
        newValue, t2, t3, t4
    );
  }

  public <R> Tuple4<T1, R, T3, T4> withElem2(R newValue) {
    return Tuple4.of(
        t1, newValue, t3, t4
    );
  }

  public <R> Tuple4<T1, T2, R, T4> withElem3(R newValue) {
    return Tuple4.of(
        t1, t2, newValue, t4
    );
  }

  public <R> Tuple4<T1, T2, T3, R> withElem4(R newValue) {
    return Tuple4.of(
        t1, t2, t3, newValue
    );
  }

  @Override
  public int getArity() {
    return 4;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s, %s, %s)", t1, t2, t3, t4);
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
