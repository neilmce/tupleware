package com.neil.easytuples;

import com.neil.easytuples.annotations.TupleGeneration;

import java.util.Objects;
import java.util.function.Function;

@TupleGeneration(tupleArity = 8)
public final class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>
    extends Tuple8Impl<T1, T2, T3, T4, T5, T6, T7, T8>
    implements Tuple {

  private Tuple8(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
    super(t1, t2, t3, t4, t5, t6, t7, t8);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8> Tuple8<S1, S2, S3, S4, S5, S6, S7, S8> of(S1 s1, S2 s2,
                                                                                           S3 s3, S4 s4,
                                                                                           S5 s5, S6 s6,
                                                                                           S7 s7, S8 s8) {
    return new Tuple8<>(s1, s2, s3, s4, s5, s6, s7, s8);
  }


  public Tuple8<T8, T7, T6, T5, T4, T3, T2, T1> reverse() {
    return Tuple8.of(t8, t7, t6, t5, t4, t3, t2, t1);
  }

  public <T> Tuple9<T, T1, T2, T3, T4, T5, T6, T7, T8> prepend(T t) {
    return Tuple9.of(t, t1, t2, t3, t4, t5, t6, t7, t8);
  }

  public <T> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T> append(T t) {
    return Tuple9.of(t1, t2, t3, t4, t5, t6, t7, t8, t);
  }

  public <R> Tuple8<R, T2, T3, T4, T5, T6, T7, T8> mapElem1(Function<T1, R> function) {
    return Tuple8.of(
        function.apply(t1), t2, t3, t4, t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, R, T3, T4, T5, T6, T7, T8> mapElem2(Function<T2, R> function) {
    return Tuple8.of(
        t1, function.apply(t2), t3, t4, t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, R, T4, T5, T6, T7, T8> mapElem3(Function<T3, R> function) {
    return Tuple8.of(
        t1, t2, function.apply(t3), t4, t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, R, T5, T6, T7, T8> mapElem4(Function<T4, R> function) {
    return Tuple8.of(
        t1, t2, t3, function.apply(t4), t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, R, T6, T7, T8> mapElem5(Function<T5, R> function) {
    return Tuple8.of(
        t1, t2, t3, t4, function.apply(t5), t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, T5, R, T7, T8> mapElem6(Function<T6, R> function) {
    return Tuple8.of(
        t1, t2, t3, t4, t5, function.apply(t6), t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, T5, T6, R, T8> mapElem7(Function<T7, R> function) {
    return Tuple8.of(
        t1, t2, t3, t4, t5, t6, function.apply(t7), t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, T5, T6, T7, R> mapElem8(Function<T8, R> function) {
    return Tuple8.of(
        t1, t2, t3, t4, t5, t6, t7, function.apply(t8)
    );
  }

  public <R> Tuple8<R, T2, T3, T4, T5, T6, T7, T8> withElem1(R newValue) {
    return Tuple8.of(
        newValue, t2, t3, t4, t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, R, T3, T4, T5, T6, T7, T8> withElem2(R newValue) {
    return Tuple8.of(
        t1, newValue, t3, t4, t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, R, T4, T5, T6, T7, T8> withElem3(R newValue) {
    return Tuple8.of(
        t1, t2, newValue, t4, t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, R, T5, T6, T7, T8> withElem4(R newValue) {
    return Tuple8.of(
        t1, t2, t3, newValue, t5, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, R, T6, T7, T8> withElem5(R newValue) {
    return Tuple8.of(
        t1, t2, t3, t4, newValue, t6, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, T5, R, T7, T8> withElem6(R newValue) {
    return Tuple8.of(
        t1, t2, t3, t4, t5, newValue, t7, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, T5, T6, R, T8> withElem7(R newValue) {
    return Tuple8.of(
        t1, t2, t3, t4, t5, t6, newValue, t8
    );
  }

  public <R> Tuple8<T1, T2, T3, T4, T5, T6, T7, R> withElem8(R newValue) {
    return Tuple8.of(
        t1, t2, t3, t4, t5, t6, t7, newValue
    );
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
