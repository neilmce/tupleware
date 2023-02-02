package com.neil.easytuples;

import java.util.List;

public interface Tuple {
  int getArity();

  List<Object> toList();

  boolean containsAnyNulls();
}
