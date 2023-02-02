package com.neil.easytuples;

import com.neil.easytuples.annotations.TupleGeneration;

import java.util.Objects;

@TupleGeneration(tupleArity = 2)
public final class Tuple2<T1, T2> extends Tuple2Impl<T1, T2> implements Tuple {
  private Tuple2(T1 t1, T2 t2) {
    super(t1, t2);
  }

  public static <ST1, ST2> Tuple2<ST1, ST2> of(ST1 t1, ST2 t2) {
    return new Tuple2<>(t1, t2);
  }

  public <T> Tuple3<T, T1, T2> prepend(T t) {
    return Tuple3.of(t, t1, t2);
  }

  public <T> Tuple3<T1, T2, T> append(T t) {
    return Tuple3.of(t1, t2, t);
  }

  public Tuple2<T2, T1> reverse() {
    return Tuple2.of(t2, t1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
    return Objects.equals(t1, tuple2.t1) && Objects.equals(t2, tuple2.t2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1, t2);
  }
}
