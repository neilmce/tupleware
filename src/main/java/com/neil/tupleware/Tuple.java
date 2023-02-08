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

package com.neil.tupleware;

import java.util.List;

/**
 * This interface defines a Tuple type. A tuple is an immutable sequence of element values.
 * The number of fields in a particular tuple is available from {@link #size()}.
 */
public interface Tuple {
  /**
   * @return the number of elements in this tuple.
   */
  int size();

  /**
   * Converts the sequence of elements in this tuple into a List of Objects.
   * Note that this method loses all type information associated with the elements
   * of the Tuple.
   *
   * @return the elements of this Tuple as a List of Objects.
   */
  List<Object> toList();

  /**
   * @return a flag indicating whether this tuple contains any elements whose value is null.
   */
  boolean containsAnyNulls();
}
