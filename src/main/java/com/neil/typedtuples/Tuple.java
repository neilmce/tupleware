package com.neil.typedtuples;

import java.util.List;

/**
 * This interface defines a Tuple type. A tuple is an immutable sequence of element values.
 * The number of fields in a particular tuple is available from {@link #getArity()}.
 */
public interface Tuple {
  /**
   * @return the number of elements in this tuple.
   */
  int getArity();

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
