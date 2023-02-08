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

package io.github.neilmce.tupleware.util;

import java.util.ArrayList;

/** Utility class. */
public class TtObjects {
  /**
   * Each of the provided objects must be non-null, or else an NPE with the provided message be thrown.
   * @throws NullPointerException if any of the provided objects are null.
   */
  public static void requireNonNull(String message, Object... objects) {
    if (objects == null) {
      throw new NullPointerException("Illegal null objects");
    }
    else {
      var nullElemPositions = new ArrayList<Integer>();
      for (int i = 1; i <= objects.length; i++) {
        if (objects[i - 1] == null) {
          nullElemPositions.add(i);
        }
      }
      if (!nullElemPositions.isEmpty()) {
        throw new NullPointerException(String.format("%s (Nulls at positions %s)", message, nullElemPositions));
      }
    }
  }
}