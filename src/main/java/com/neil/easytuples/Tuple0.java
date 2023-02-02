package com.neil.easytuples;

import java.util.List;

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
