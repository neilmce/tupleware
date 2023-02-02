package com.neil.typedtuples;

import java.util.List;

public interface Tuple {
  int getArity();

  List<Object> toList();

  boolean containsAnyNulls();
}
