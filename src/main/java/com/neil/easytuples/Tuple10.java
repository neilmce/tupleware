package com.neil.easytuples;

import com.neil.easytuples.annotations.TupleGeneration;

import java.util.Objects;
import java.util.function.BiFunction;

@TupleGeneration(tupleArity = 10)
public final class Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    extends Tuple10Impl<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10>
    implements Tuple {

  private Tuple10(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
    super(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
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


  public Tuple10<T10, T9, T8, T7, T6, T5, T4, T3, T2, T1> reverse() {
    return Tuple10.of(t10, t9, t8, t7, t6, t5, t4, t3, t2, t1);
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
