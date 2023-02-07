// Copyright 2023 Neil Mc Erlean.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

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