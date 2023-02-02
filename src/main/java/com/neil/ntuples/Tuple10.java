package com.neil.ntuples;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> implements Tuple {
  private final T1 t1;
  private final T2 t2;
  private final T3 t3;
  private final T4 t4;
  private final T5 t5;
  private final T6 t6;
  private final T7 t7;
  private final T8 t8;
  private final T9 t9;
  private final T10 t10;

  private Tuple10(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
    this.t6 = t6;
    this.t7 = t7;
    this.t8 = t8;
    this.t9 = t9;
    this.t10 = t10;
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9, S10> Tuple10<S1, S2, S3, S4, S5, S6, S7, S8, S9, S10> of(S1 s1,
                                                                                                              S2 s2,
                                                                                                              S3 s3,
                                                                                                              S4 s4,
                                                                                                              S5 s5,
                                                                                                              S6 s6,
                                                                                                              S7 s7,
                                                                                                              S8 s8,
                                                                                                              S9 s9,
                                                                                                              S10 s10) {
    return new Tuple10<>(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
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

  public T7 elem7() {
    return this.t7;
  }

  public T8 elem8() {
    return this.t8;
  }

  public T9 elem9() {
    return this.t9;
  }

  public T10 elem10() {
    return this.t10;
  }

  public Tuple10<T10, T9, T8, T7, T6, T5, T4, T3, T2, T1> reverse() {
    return Tuple10.of(t10, t9, t8, t7, t6, t5, t4, t3, t2, t1);
  }


  @Override
  public List<Object> toList() {
    return List.of(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
  }

  @Override
  public boolean containsAnyNulls() {
    return t1 == null || t2 == null || t3 == null || t4 == null || t5 == null || t6 == null || t7 == null || t8 == null || t9 == null || t10 == null;
  }

  public <R> Tuple10<R, T2, T3, T4, T5, T6, T7, T8, T9, T10> mapElem1(Function<T1, R> function) {
    return Tuple10.of(
        function.apply(t1), t2, t3, t4, t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, R, T3, T4, T5, T6, T7, T8, T9, T10> mapElem2(Function<T2, R> function) {
    return Tuple10.of(
        t1, function.apply(t2), t3, t4, t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, R, T4, T5, T6, T7, T8, T9, T10> mapElem3(Function<T3, R> function) {
    return Tuple10.of(
        t1, t2, function.apply(t3), t4, t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, R, T5, T6, T7, T8, T9, T10> mapElem4(Function<T4, R> function) {
    return Tuple10.of(
        t1, t2, t3, function.apply(t4), t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, R, T6, T7, T8, T9, T10> mapElem5(Function<T5, R> function) {
    return Tuple10.of(
        t1, t2, t3, t4, function.apply(t5), t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, R, T7, T8, T9, T10> mapElem6(Function<T6, R> function) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, function.apply(t6), t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, R, T8, T9, T10> mapElem7(Function<T7, R> function) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, function.apply(t7), t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, T7, R, T9, T10> mapElem8(Function<T8, R> function) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, t7, function.apply(t8), t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, R, T10> mapElem9(Function<T9, R> function) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, t7, t8, function.apply(t9), t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> mapElem10(Function<T10, R> function) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, t7, t8, t9, function.apply(t10)
    );
  }
  public <R> Tuple10<R, T2, T3, T4, T5, T6, T7, T8, T9, T10> withElem1(R newValue) {
    return Tuple10.of(
        newValue, t2, t3, t4, t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, R, T3, T4, T5, T6, T7, T8, T9, T10> withElem2(R newValue) {
    return Tuple10.of(
        t1, newValue, t3, t4, t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, R, T4, T5, T6, T7, T8, T9, T10> withElem3(R newValue) {
    return Tuple10.of(
        t1, t2, newValue, t4, t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, R, T5, T6, T7, T8, T9, T10> withElem4(R newValue) {
    return Tuple10.of(
        t1, t2, t3, newValue, t5, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, R, T6, T7, T8, T9, T10> withElem5(R newValue) {
    return Tuple10.of(
        t1, t2, t3, t4, newValue, t6, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, R, T7, T8, T9, T10> withElem6(R newValue) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, newValue, t7, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, R, T8, T9, T10> withElem7(R newValue) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, newValue, t8, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, T7, R, T9, T10> withElem8(R newValue) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, t7, newValue, t9, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, R, T10> withElem9(R newValue) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, t7, t8, newValue, t10
    );
  }

  public <R> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> withElem10(R newValue) {
    return Tuple10.of(
        t1, t2, t3, t4, t5, t6, t7, t8, t9, newValue
    );
  }

  public <S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, R1, R2, R3, R4, R5, R6, R7, R8, R9, R10> Tuple10<R1, R2, R3, R4,
      R5, R6, R7, R8, R9, R10> zipWith(Tuple10<S1, S2, S3, S4, S5, S6, S7, S8, S9, S10> that,
                                       BiFunction<T1, S1, R1> combine1,
                                       BiFunction<T2, S2, R2> combine2,
                                       BiFunction<T3, S3, R3> combine3,
                                       BiFunction<T4, S4, R4> combine4,
                                       BiFunction<T5, S5, R5> combine5,
                                       BiFunction<T6, S6, R6> combine6,
                                       BiFunction<T7, S7, R7> combine7,
                                       BiFunction<T8, S8, R8> combine8,
                                       BiFunction<T9, S9, R9> combine9,
                                       BiFunction<T10, S10, R10> combine10) {
    return Tuple10.of(
        combine1.apply(this.t1, that.t1),
        combine2.apply(this.t2, that.t2),
        combine3.apply(this.t3, that.t3),
        combine4.apply(this.t4, that.t4),
        combine5.apply(this.t5, that.t5),
        combine6.apply(this.t6, that.t6),
        combine7.apply(this.t7, that.t7),
        combine8.apply(this.t8, that.t8),
        combine9.apply(this.t9, that.t9),
        combine10.apply(this.t10, that.t10)
    );
  }

  @Override
  public String toString() {
    return String.format("(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
  }

  @Override
  public int getArity() {
    return 10;
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
