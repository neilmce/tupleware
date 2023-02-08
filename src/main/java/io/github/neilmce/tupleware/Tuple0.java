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

package io.github.neilmce.tupleware;

import java.util.List;

/** This class represents the empty tuple. */
public final class Tuple0 implements Tuple {
  private static final Tuple0 INSTANCE = new Tuple0();

  /**
   * @return an instance of the empty tuple.
   */
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
    return size();
  }

  @Override
  public String toString() {
    return "()";
  }

  @Override
  public int size() {
    return 0;
  }
}
