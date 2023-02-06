package com.neil.typedtuples;

import java.util.List;

/** This class represents the empty tuple. */
public final class Tuple0 implements Tuple {
  private static final Tuple0 INSTANCE = new Tuple0();

  public static Tuple0 of() {
    return INSTANCE;
  }

  public <T> Tuple1<T> prepend(T t) {
    return Tuple1.of(t);
  }

  public <T> Tuple1<T> append(T t) {
    return prepend(t);
  }

  /**
   * {@inheritDoc}
   *
   * @return false. An empty tuple cannot contain any values, null or otherwise.
   */
  public boolean containsAnyNulls() {
    return false;
  }

  @Override
  public List<Object> toList() {
    return List.of();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Tuple0;
  }

  @Override
  public int hashCode() {
    return getArity();
  }

  @Override
  public String toString() {
    return "()";
  }

  @Override
  public int getArity() {
    return 0;
  }
}
