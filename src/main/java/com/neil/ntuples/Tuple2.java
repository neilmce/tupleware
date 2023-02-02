package com.neil.ntuples;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class Tuple2<T1, T2> implements Tuple {
  private final T1 t1;
  private final T2 t2;

  private Tuple2(T1 t1, T2 t2) {
    // TODO reject nulls
    this.t1 = t1;
    this.t2 = t2;
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

  public T1 elem1() {
    return this.t1;
  }

  public T2 elem2() {
    return this.t2;
  }

  public Tuple2<T2, T1> reverse() {
    return Tuple2.of(t2, t1);
  }

  @Override
  public List<Object> toList() {
    return List.of(t1, t2);
  }
  @Override
  public boolean containsAnyNulls() {
    return t1 == null || t2 == null;
  }
  public <R> Tuple2<R, T2> mapElem1(Function<T1, R> function) {
    return Tuple2.of(
        function.apply(t1), t2
    );
  }

  public <R> Tuple2<T1, R> mapElem2(Function<T2, R> function) {
    return Tuple2.of(
        t1, function.apply(t2)
    );
  }
  public <R> Tuple2<R, T2> withElem1(R newValue) {
    return Tuple2.of(
        newValue, t2
    );
  }

  public <R> Tuple2<T1, R> withElem2(R newValue) {
    return Tuple2.of(
        t1, newValue
    );
  }

  public Object[] toArray() {
    return new Object[]{t1, t2};
  }

  public <S1, S2, R1, R2> Tuple2<R1, R2> zipWith(Tuple2<S1, S2> that, BiFunction<T1, S1, R1> combine1, BiFunction<T2,
      S2, R2> combine2) {
    return Tuple2.of(combine1.apply(this.t1, that.t1), combine2.apply(this.t2, that.t2));
  }

  @Override
  public int getArity() {
    return 2;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", t1, t2);
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
