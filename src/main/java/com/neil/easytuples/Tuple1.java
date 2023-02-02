package com.neil.easytuples;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class Tuple1<T1> implements Tuple {
  private final T1 t1;

  private Tuple1(T1 t1) {
    this.t1 = t1;
  }

  public static <S1> Tuple1<S1> of(S1 s1) {
    return new Tuple1<>(s1);
  }

  public T1 elem1() {
    return this.t1;
  }


  public <T> Tuple2<T, T1> prepend(T t) {
    return Tuple2.of(t, t1);
  }

  public <T> Tuple2<T1, T> append(T t) {
    return Tuple2.of(t1, t);
  }

  @Override
  public List<Object> toList() {
    return List.of(t1);
  }
  @Override
  public boolean containsAnyNulls() {
    return t1 == null;
  }
  public <R> Tuple1<R> mapElem1(Function<T1, R> function) {
    return Tuple1.of(
        function.apply(t1)
    );
  }
  public <R> Tuple1<R> withElem1(R newValue) {
    return Tuple1.of(
        newValue
    );
  }

  @Override
  public int getArity() {
    return 1;
  }

  @Override
  public String toString() {
    return String.format("(%s)", t1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple1<?> tuple1 = (Tuple1<?>) o;
    return Objects.equals(t1, tuple1.t1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1);
  }
}
