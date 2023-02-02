package com.neil.ntuples;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> implements Tuple {
  private final T1 t1;
  private final T2 t2;
  private final T3 t3;
  private final T4 t4;
  private final T5 t5;
  private final T6 t6;
  private final T7 t7;
  private final T8 t8;
  private final T9 t9;

  private Tuple9(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
    this.t1 = t1;
    this.t2 = t2;
    this.t3 = t3;
    this.t4 = t4;
    this.t5 = t5;
    this.t6 = t6;
    this.t7 = t7;
    this.t8 = t8;
    this.t9 = t9;
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9> Tuple9<S1, S2, S3, S4, S5, S6, S7, S8, S9> of(S1 s1, S2 s2,
                                                                                                   S3 s3, S4 s4,
                                                                                                   S5 s5, S6 s6,
                                                                                                   S7 s7, S8 s8,
                                                                                                   S9 s9) {
    return new Tuple9<>(s1, s2, s3, s4, s5, s6, s7, s8, s9);
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

  public Tuple9<T9, T8, T7, T6, T5, T4, T3, T2, T1> reverse() {
    return Tuple9.of(t9, t8, t7, t6, t5, t4, t3, t2, t1);
  }

  @Override
  public List<Object> toList() {
    return List.of(t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  public <T> Tuple10<T, T1, T2, T3, T4, T5, T6, T7, T8, T9> prepend(T t) {
    return Tuple10.of(t, t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  public <T> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T> append(T t) {
    return Tuple10.of(t1, t2, t3, t4, t5, t6, t7, t8, t9, t);
  }

//  public Tuple2<Tuple4<T1, T2, T3, T4>, Tuple5<T5, T6, T7, T8, T9>> splitAfterElem4() {
//
//  }

  public boolean containsAnyNulls() {
    return t1 == null ||
        t2 == null ||
        t3 == null ||
        t4 == null ||
        t5 == null ||
        t6 == null ||
        t7 == null ||
        t8 == null ||
        t9 == null;
  }
  public <R> Tuple9<R, T2, T3, T4, T5, T6, T7, T8, T9> mapElem1(Function<T1, R> function) {
    return Tuple9.of(
        function.apply(t1), t2, t3, t4, t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, R, T3, T4, T5, T6, T7, T8, T9> mapElem2(Function<T2, R> function) {
    return Tuple9.of(
        t1, function.apply(t2), t3, t4, t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, R, T4, T5, T6, T7, T8, T9> mapElem3(Function<T3, R> function) {
    return Tuple9.of(
        t1, t2, function.apply(t3), t4, t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, R, T5, T6, T7, T8, T9> mapElem4(Function<T4, R> function) {
    return Tuple9.of(
        t1, t2, t3, function.apply(t4), t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, R, T6, T7, T8, T9> mapElem5(Function<T5, R> function) {
    return Tuple9.of(
        t1, t2, t3, t4, function.apply(t5), t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, R, T7, T8, T9> mapElem6(Function<T6, R> function) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, function.apply(t6), t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, T6, R, T8, T9> mapElem7(Function<T7, R> function) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, t6, function.apply(t7), t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, T6, T7, R, T9> mapElem8(Function<T8, R> function) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, t6, t7, function.apply(t8), t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, R> mapElem9(Function<T9, R> function) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, t6, t7, t8, function.apply(t9)
    );
  }

  public <R> Tuple9<R, T2, T3, T4, T5, T6, T7, T8, T9> withElem1(R newValue) {
    return Tuple9.of(
        newValue, t2, t3, t4, t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, R, T3, T4, T5, T6, T7, T8, T9> withElem2(R newValue) {
    return Tuple9.of(
        t1, newValue, t3, t4, t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, R, T4, T5, T6, T7, T8, T9> withElem3(R newValue) {
    return Tuple9.of(
        t1, t2, newValue, t4, t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, R, T5, T6, T7, T8, T9> withElem4(R newValue) {
    return Tuple9.of(
        t1, t2, t3, newValue, t5, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, R, T6, T7, T8, T9> withElem5(R newValue) {
    return Tuple9.of(
        t1, t2, t3, t4, newValue, t6, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, R, T7, T8, T9> withElem6(R newValue) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, newValue, t7, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, T6, R, T8, T9> withElem7(R newValue) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, t6, newValue, t8, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, T6, T7, R, T9> withElem8(R newValue) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, t6, t7, newValue, t9
    );
  }

  public <R> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, R> withElem9(R newValue) {
    return Tuple9.of(
        t1, t2, t3, t4, t5, t6, t7, t8, newValue
    );
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple9<?, ?, ?, ?, ?, ?, ?, ?, ?> tuple9 = (Tuple9<?, ?, ?, ?, ?, ?, ?, ?, ?>) o;
    return Objects.equals(t1, tuple9.t1) && Objects.equals(t2, tuple9.t2) && Objects.equals(t3, tuple9.t3) && Objects.equals(t4, tuple9.t4) && Objects.equals(t5, tuple9.t5) && Objects.equals(t6, tuple9.t6) && Objects.equals(t7, tuple9.t7) && Objects.equals(t8, tuple9.t8) && Objects.equals(t9, tuple9.t9);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }


//  public <S1, S2, R1, R2> Tuple11<R1, R2> zipWith(Tuple11<S1, S2> that,
//                                                  BiFunction<T1, S1, R1> combine1,
//                                                  BiFunction<T2, S2, R2> combine2) {
//    return Tuple11.of(combine1.apply(this.t1, that.t1), combine2.apply(this.t2, that.t2));
//  }

  @Override
  public String toString() {
    return String.format("(%s, %s, %s, %s, %s, %s, %s, %s, %s)", t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  @Override
  public int getArity() {
    return 9;
  }
}
