package com.neil.typedtuples.util;

import java.util.ArrayList;

public class TtObjects {
  public static void requireNonNull(String message, Object... args) {
    if (args == null) {
      throw new NullPointerException("Illegal null args");
    }
    else {
      var nullElemPositions = new ArrayList<Integer>();
      for (int i = 1; i <= args.length; i++) {
        if (args[i - 1] == null) {
          nullElemPositions.add(i);
        }
      }
      if (!nullElemPositions.isEmpty()) {
        throw new NullPointerException(String.format("%s (Nulls at positions %s)", message, nullElemPositions));
      }
    }
  }
}