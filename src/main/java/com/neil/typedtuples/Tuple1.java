package com.neil.typedtuples;

import com.neil.typedtuples.annotations.TupleGeneration;

import java.util.Objects;

/**
 * A tuple with a single element.
 *
 * @param <T1> the type of the element.
 */
@TupleGeneration(tupleArity = 1)
public final class Tuple1<T1> extends Tuple1Impl<T1> implements Tuple {
  private Tuple1(T1 t1) {
    super(t1);
  }

  public static <S1> Tuple1<S1> of(S1 s1) {
    return new Tuple1<>(s1);
  }

  public static <S1> Tuple1<S1> ofNonNull(S1 s1) {
    if (s1 == null) {
      throw new NullPointerException("Illegal null element at position 1");
    }
    else {
      return new Tuple1<>(s1);
    }
  }

  public <T> Tuple2<T, T1> prepend(T t) {
    return Tuple2.of(t, t1);
  }

  public <T> Tuple2<T1, T> append(T t) {
    return Tuple2.of(t1, t);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple1<?> tuple1 = (Tuple1<?>) o;
    return Objects.deepEquals(t1, tuple1.t1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(t1);
  }
}
