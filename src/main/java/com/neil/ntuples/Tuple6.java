package com.neil.ntuples;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class Tuple6<T1, T2, T3, T4, T5, T6> implements Tuple {
  private final T1 t1;
  private final T2 t2;
  private final T3 t3;
  private final T4 t4;
  private final T5 t5;
  private final T6 t6;

  private Tuple6(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
    this.t6 = t6;
  }

  public static <S1, S2, S3, S4, S5, S6> Tuple6<S1, S2, S3, S4, S5, S6> of(S1 s1, S2 s2,
                                                                           S3 s3, S4 s4,
                                                                           S5 s5, S6 s6) {
    return new Tuple6<>(s1, s2, s3, s4, s5, s6);
  }

  public Tuple6<T6, T5, T4, T3, T2, T1> reverse() {
    return Tuple6.of(t6, t5, t4, t3, t2, t1);
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

  public T5 elem5() {
    return this.t5;
  }

  public T6 elem6() {
    return this.t6;
  }


  public <T> Tuple7<T, T1, T2, T3, T4, T5, T6> prepend(T t) {
    return Tuple7.of(t, t1, t2, t3, t4, t5, t6);
  }

  public <T> Tuple7<T1, T2, T3, T4, T5, T6, T> append(T t) {
    return Tuple7.of(t1, t2, t3, t4, t5, t6, t);
  }

  @Override
  public List<Object> toList() {
    return List.of(t1, t2, t3, t4, t5, t6);
  }
  @Override
  public boolean containsAnyNulls() {
    return t1 == null || t2 == null || t3 == null || t4 == null || t5 == null || t6 == null;
  }
  public <R> Tuple6<R, T2, T3, T4, T5, T6> mapElem1(Function<T1, R> function) {
    return Tuple6.of(
        function.apply(t1), t2, t3, t4, t5, t6
    );
  }

  public <R> Tuple6<T1, R, T3, T4, T5, T6> mapElem2(Function<T2, R> function) {
    return Tuple6.of(
        t1, function.apply(t2), t3, t4, t5, t6
    );
  }

  public <R> Tuple6<T1, T2, R, T4, T5, T6> mapElem3(Function<T3, R> function) {
    return Tuple6.of(
        t1, t2, function.apply(t3), t4, t5, t6
    );
  }

  public <R> Tuple6<T1, T2, T3, R, T5, T6> mapElem4(Function<T4, R> function) {
    return Tuple6.of(
        t1, t2, t3, function.apply(t4), t5, t6
    );
  }

  public <R> Tuple6<T1, T2, T3, T4, R, T6> mapElem5(Function<T5, R> function) {
    return Tuple6.of(
        t1, t2, t3, t4, function.apply(t5), t6
    );
  }

  public <R> Tuple6<T1, T2, T3, T4, T5, R> mapElem6(Function<T6, R> function) {
    return Tuple6.of(
        t1, t2, t3, t4, t5, function.apply(t6)
    );
  }
  public <R> Tuple6<R, T2, T3, T4, T5, T6> withElem1(R newValue) {
    return Tuple6.of(
        newValue, t2, t3, t4, t5, t6
    );
  }

  public <R> Tuple6<T1, R, T3, T4, T5, T6> withElem2(R newValue) {
    return Tuple6.of(
        t1, newValue, t3, t4, t5, t6
    );
  }

  public <R> Tuple6<T1, T2, R, T4, T5, T6> withElem3(R newValue) {
    return Tuple6.of(
        t1, t2, newValue, t4, t5, t6
    );
  }

  public <R> Tuple6<T1, T2, T3, R, T5, T6> withElem4(R newValue) {
    return Tuple6.of(
        t1, t2, t3, newValue, t5, t6
    );
  }

  public <R> Tuple6<T1, T2, T3, T4, R, T6> withElem5(R newValue) {
    return Tuple6.of(
        t1, t2, t3, t4, newValue, t6
    );
  }

  public <R> Tuple6<T1, T2, T3, T4, T5, R> withElem6(R newValue) {
    return Tuple6.of(
        t1, t2, t3, t4, t5, newValue
    );
  }

  @Override
  public int getArity() {
    return 6;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s, %s, %s, %s, %s)", t1, t2, t3, t4, t5, t6);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple6<?, ?, ?, ?, ?, ?> tuple6 = (Tuple6<?, ?, ?, ?, ?, ?>) o;
    return Objects.equals(t1, tuple6.t1) && Objects.equals(t2, tuple6.t2) && Objects.equals(t3, tuple6.t3) && Objects.equals(t4, tuple6.t4) && Objects.equals(t5, tuple6.t5) && Objects.equals(t6, tuple6.t6);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4, t5, t6);
  }
}
