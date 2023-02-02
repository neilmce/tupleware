package com.neil.ntuples;

import java.util.List;

public interface Tuple {
  int getArity();

  List<Object> toList();

  boolean containsAnyNulls();
}
