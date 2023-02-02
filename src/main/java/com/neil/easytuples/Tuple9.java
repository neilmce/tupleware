package com.neil.easytuples;

import com.neil.easytuples.annotations.TupleGeneration;

import java.util.Objects;

@TupleGeneration(tupleArity = 9)
public final class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>
    extends Tuple9Impl<T1, T2, T3, T4, T5, T6, T7, T8, T9>
    implements Tuple {

  private Tuple9(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
    super(t1, t2, t3, t4, t5, t6, t7, t8, t9);
  }

  public static <S1, S2, S3, S4, S5, S6, S7, S8, S9> Tuple9<S1, S2, S3, S4, S5, S6, S7, S8, S9> of(S1 s1, S2 s2,
                                                                                                   S3 s3, S4 s4,
                                                                                                   S5 s5, S6 s6,
                                                                                                   S7 s7, S8 s8,
                                                                                                   S9 s9) {
    return new Tuple9<>(s1, s2, s3, s4, s5, s6, s7, s8, s9);
  }


  public Tuple9<T9, T8, T7, T6, T5, T4, T3, T2, T1> reverse() {
    return Tuple9.of(t9, t8, t7, t6, t5, t4, t3, t2, t1);
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
}
