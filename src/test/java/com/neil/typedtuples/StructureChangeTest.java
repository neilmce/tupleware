package com.neil.typedtuples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.neil.typedtuples.TestData.tuple0;
import static com.neil.typedtuples.TestData.tuple1;
import static com.neil.typedtuples.TestData.tuple10;
import static com.neil.typedtuples.TestData.tuple2;
import static com.neil.typedtuples.TestData.tuple3;
import static com.neil.typedtuples.TestData.tuple4;
import static com.neil.typedtuples.TestData.tuple5;
import static com.neil.typedtuples.TestData.tuple6;
import static com.neil.typedtuples.TestData.tuple7;
import static com.neil.typedtuples.TestData.tuple8;
import static com.neil.typedtuples.TestData.tuple9;
import static java.time.Month.JANUARY;
import static java.time.ZoneOffset.UTC;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StructureChangeTest {
  /**
   * Set of sample Tuple objects that cover all arities.
   */
  private final List<Tuple> sampleTuples = List.of(
      tuple0, tuple1, tuple2, tuple3, tuple4, tuple5, tuple6, tuple7, tuple8, tuple9, tuple10
  );


  @Test
  void appendShouldWork() {
    assertEquals(tuple1, tuple0.append("Hi"));
    assertEquals(tuple2, tuple1.append(2));
    assertEquals(tuple3, tuple2.append(3.1));
    assertEquals(tuple4, tuple3.append(true));
    assertEquals(tuple5, tuple4.append(JANUARY));
    assertEquals(tuple6, tuple5.append(UTC));
    assertEquals(tuple7, tuple6.append('x'));
    assertEquals(tuple8, tuple7.append(List.of(1)));
    assertEquals(tuple9, tuple8.append(Set.of(1)));
    assertEquals(tuple10, tuple9.append(Map.of("One", 1)));
  }

  @Test
  void prependShouldWork() {
    assertEquals(Tuple1.of("Hi"), tuple0.prepend("Hi"));
    assertEquals(Tuple2.of(2, "Hi"), tuple1.prepend(2));
    assertEquals(Tuple3.of(3.1, "Hi", 2), tuple2.prepend(3.1));
    assertEquals(Tuple4.of(true, "Hi", 2, 3.1), tuple3.prepend(true));
    assertEquals(Tuple5.of(JANUARY, "Hi", 2, 3.1, true), tuple4.prepend(JANUARY));
    assertEquals(Tuple6.of(UTC, "Hi", 2, 3.1, true, JANUARY), tuple5.prepend(UTC));
    assertEquals(Tuple7.of('x', "Hi", 2, 3.1, true, JANUARY, UTC), tuple6.prepend('x'));
    assertEquals(Tuple8.of(List.of(1), "Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple7.prepend(List.of(1)));
    assertEquals(Tuple9.of(Set.of(1), "Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.prepend(Set.of(1)));
    assertEquals(Tuple10.of(Map.of("One", 1), "Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)),
                 tuple9.prepend(Map.of("One", 1)));
  }

  @Test
  void reverseShouldWork() {
    assertEquals(Tuple2.of(2, "Hi"), tuple2.reverse());
    assertEquals(Tuple3.of(3.1, 2, "Hi"), tuple3.reverse());
    assertEquals(Tuple4.of(true, 3.1, 2, "Hi"), tuple4.reverse());
    assertEquals(Tuple5.of(JANUARY, true, 3.1, 2, "Hi"), tuple5.reverse());
    assertEquals(Tuple6.of(UTC, JANUARY, true, 3.1, 2, "Hi"), tuple6.reverse());
    assertEquals(Tuple7.of('x', UTC, JANUARY, true, 3.1, 2, "Hi"), tuple7.reverse());
    assertEquals(Tuple8.of(List.of(1), 'x', UTC, JANUARY, true, 3.1, 2, "Hi"), tuple8.reverse());
    assertEquals(Tuple9.of(Set.of(1), List.of(1), 'x', UTC, JANUARY, true, 3.1, 2, "Hi"), tuple9.reverse());
    assertEquals(Tuple10.of(Map.of("One", 1), Set.of(1), List.of(1), 'x', UTC, JANUARY, true, 3.1, 2, "Hi"),
                 tuple10.reverse());
  }

  @Test void splitTuple() {
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple1.of(2)), tuple2.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple1.of(3.1)), tuple3.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple2.of(2, 3.1)), tuple3.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple3.of("Hi", 2, 3.1), Tuple1.of(true)), tuple4.splitAfterElement3());
    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple2.of(3.1, true)), tuple4.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple3.of(2, 3.1, true)), tuple4.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple4.of("Hi", 2, 3.1, true), Tuple1.of(JANUARY)), tuple5.splitAfterElement4());
    assertEquals(Tuple2.of(Tuple3.of("Hi", 2, 3.1), Tuple2.of(true, JANUARY)), tuple5.splitAfterElement3());
    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple3.of(3.1, true, JANUARY)), tuple5.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple4.of(2, 3.1, true, JANUARY)), tuple5.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple5.of("Hi", 2, 3.1, true, JANUARY), Tuple1.of(UTC)), tuple6.splitAfterElement5());
    assertEquals(Tuple2.of(Tuple4.of("Hi", 2, 3.1, true), Tuple2.of(JANUARY, UTC)), tuple6.splitAfterElement4());
    assertEquals(Tuple2.of(Tuple3.of("Hi", 2, 3.1), Tuple3.of(true, JANUARY, UTC)), tuple6.splitAfterElement3());
    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple4.of(3.1, true, JANUARY, UTC)), tuple6.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple5.of(2, 3.1, true, JANUARY, UTC)), tuple6.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), Tuple1.of('x')), tuple7.splitAfterElement6());
    assertEquals(Tuple2.of(Tuple5.of("Hi", 2, 3.1, true, JANUARY), Tuple2.of(UTC, 'x')), tuple7.splitAfterElement5());
    assertEquals(Tuple2.of(Tuple4.of("Hi", 2, 3.1, true), Tuple3.of(JANUARY, UTC, 'x')), tuple7.splitAfterElement4());
    assertEquals(Tuple2.of(Tuple3.of("Hi", 2, 3.1), Tuple4.of(true, JANUARY, UTC, 'x')), tuple7.splitAfterElement3());
    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple5.of(3.1, true, JANUARY, UTC, 'x')), tuple7.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple6.of(2, 3.1, true, JANUARY, UTC, 'x')), tuple7.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), Tuple1.of(List.of(1))), tuple8.splitAfterElement7());
    assertEquals(Tuple2.of(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), Tuple2.of('x', List.of(1))), tuple8.splitAfterElement6());
    assertEquals(Tuple2.of(Tuple5.of("Hi", 2, 3.1, true, JANUARY), Tuple3.of(UTC, 'x', List.of(1))), tuple8.splitAfterElement5());
    assertEquals(Tuple2.of(Tuple4.of("Hi", 2, 3.1, true), Tuple4.of(JANUARY, UTC, 'x', List.of(1))), tuple8.splitAfterElement4());
    assertEquals(Tuple2.of(Tuple3.of("Hi", 2, 3.1), Tuple5.of(true, JANUARY, UTC, 'x', List.of(1))), tuple8.splitAfterElement3());
    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple6.of(3.1, true, JANUARY, UTC, 'x', List.of(1))), tuple8.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple7.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1))), tuple8.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), Tuple1.of(Set.of(1))), tuple9.splitAfterElement8());
    assertEquals(Tuple2.of(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), Tuple2.of(List.of(1), Set.of(1))), tuple9.splitAfterElement7());
    assertEquals(Tuple2.of(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), Tuple3.of('x', List.of(1), Set.of(1))), tuple9.splitAfterElement6());
    assertEquals(Tuple2.of(Tuple5.of("Hi", 2, 3.1, true, JANUARY), Tuple4.of(UTC, 'x', List.of(1), Set.of(1))), tuple9.splitAfterElement5());
    assertEquals(Tuple2.of(Tuple4.of("Hi", 2, 3.1, true), Tuple5.of(JANUARY, UTC, 'x', List.of(1), Set.of(1))), tuple9.splitAfterElement4());
    assertEquals(Tuple2.of(Tuple3.of("Hi", 2, 3.1), Tuple6.of(true, JANUARY, UTC, 'x', List.of(1), Set.of(1))), tuple9.splitAfterElement3());
    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple7.of(3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1))), tuple9.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple8.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1))), tuple9.splitAfterElement1());

    assertEquals(Tuple2.of(Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), Tuple1.of(Map.of("One", 1))), tuple10.splitAfterElement9());
    assertEquals(Tuple2.of(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), Tuple2.of(Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement8());
    assertEquals(Tuple2.of(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), Tuple3.of(List.of(1), Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement7());
    assertEquals(Tuple2.of(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), Tuple4.of('x', List.of(1), Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement6());
    assertEquals(Tuple2.of(Tuple5.of("Hi", 2, 3.1, true, JANUARY), Tuple5.of(UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement5());
    assertEquals(Tuple2.of(Tuple4.of("Hi", 2, 3.1, true), Tuple6.of(JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement4());
    assertEquals(Tuple2.of(Tuple3.of("Hi", 2, 3.1), Tuple7.of(true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement3());
    assertEquals(Tuple2.of(Tuple2.of("Hi", 2), Tuple8.of(3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement2());
    assertEquals(Tuple2.of(Tuple1.of("Hi"), Tuple9.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1))), tuple10.splitAfterElement1());
  }

  @Test void dropElements() {
    assertEquals(Tuple0.of(), tuple1.dropElem1());

    assertEquals(Tuple1.of("Hi"), tuple2.dropElem2());
    assertEquals(Tuple1.of(2), tuple2.dropElem1());

    assertEquals(Tuple2.of("Hi", 2), tuple3.dropElem3());
    assertEquals(Tuple2.of("Hi", 3.1), tuple3.dropElem2());
    assertEquals(Tuple2.of(2, 3.1), tuple3.dropElem1());

    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple4.dropElem4());
    assertEquals(Tuple3.of("Hi", 2, true), tuple4.dropElem3());
    assertEquals(Tuple3.of("Hi", 3.1, true), tuple4.dropElem2());
    assertEquals(Tuple3.of(2, 3.1, true), tuple4.dropElem1());

    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple5.dropElem5());
    assertEquals(Tuple4.of("Hi", 2, 3.1, JANUARY), tuple5.dropElem4());
    assertEquals(Tuple4.of("Hi", 2, true, JANUARY), tuple5.dropElem3());
    assertEquals(Tuple4.of("Hi", 3.1, true, JANUARY), tuple5.dropElem2());
    assertEquals(Tuple4.of(2, 3.1, true, JANUARY), tuple5.dropElem1());

    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple6.dropElem6());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, UTC), tuple6.dropElem5());
    assertEquals(Tuple5.of("Hi", 2, 3.1, JANUARY, UTC), tuple6.dropElem4());
    assertEquals(Tuple5.of("Hi", 2, true, JANUARY, UTC), tuple6.dropElem3());
    assertEquals(Tuple5.of("Hi", 3.1, true, JANUARY, UTC), tuple6.dropElem2());
    assertEquals(Tuple5.of(2, 3.1, true, JANUARY, UTC), tuple6.dropElem1());

    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple7.dropElem7());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, 'x'), tuple7.dropElem6());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, UTC, 'x'), tuple7.dropElem5());
    assertEquals(Tuple6.of("Hi", 2, 3.1, JANUARY, UTC, 'x'), tuple7.dropElem4());
    assertEquals(Tuple6.of("Hi", 2, true, JANUARY, UTC, 'x'), tuple7.dropElem3());
    assertEquals(Tuple6.of("Hi", 3.1, true, JANUARY, UTC, 'x'), tuple7.dropElem2());
    assertEquals(Tuple6.of(2, 3.1, true, JANUARY, UTC, 'x'), tuple7.dropElem1());

    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple8.dropElem8());
    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, List.of(1)), tuple8.dropElem7());
    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, 'x', List.of(1)), tuple8.dropElem6());
    assertEquals(Tuple7.of("Hi", 2, 3.1, true, UTC, 'x', List.of(1)), tuple8.dropElem5());
    assertEquals(Tuple7.of("Hi", 2, 3.1, JANUARY, UTC, 'x', List.of(1)), tuple8.dropElem4());
    assertEquals(Tuple7.of("Hi", 2, true, JANUARY, UTC, 'x', List.of(1)), tuple8.dropElem3());
    assertEquals(Tuple7.of("Hi", 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.dropElem2());
    assertEquals(Tuple7.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.dropElem1());

    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple9.dropElem9());
    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', Set.of(1)), tuple9.dropElem8());
    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, List.of(1), Set.of(1)), tuple9.dropElem7());
    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, 'x', List.of(1), Set.of(1)), tuple9.dropElem6());
    assertEquals(Tuple8.of("Hi", 2, 3.1, true, UTC, 'x', List.of(1), Set.of(1)), tuple9.dropElem5());
    assertEquals(Tuple8.of("Hi", 2, 3.1, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.dropElem4());
    assertEquals(Tuple8.of("Hi", 2, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.dropElem3());
    assertEquals(Tuple8.of("Hi", 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.dropElem2());
    assertEquals(Tuple8.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.dropElem1());

    assertEquals(Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple10.dropElem10());
    assertEquals(Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Map.of("One", 1)), tuple10.dropElem9());
    assertEquals(Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', Set.of(1), Map.of("One", 1)), tuple10.dropElem8());
    assertEquals(Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, List.of(1), Set.of(1), Map.of("One", 1)), tuple10.dropElem7());
    assertEquals(Tuple9.of("Hi", 2, 3.1, true, JANUARY, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.dropElem6());
    assertEquals(Tuple9.of("Hi", 2, 3.1, true, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.dropElem5());
    assertEquals(Tuple9.of("Hi", 2, 3.1, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.dropElem4());
    assertEquals(Tuple9.of("Hi", 2, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.dropElem3());
    assertEquals(Tuple9.of("Hi", 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.dropElem2());
    assertEquals(Tuple9.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.dropElem1());
  }

  @Test void takeNElements() {
    assertEquals(Tuple1.of("Hi"), tuple2.take1());

    assertEquals(Tuple2.of("Hi", 2), tuple3.take2());
    assertEquals(Tuple1.of("Hi"), tuple3.take1());

    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple4.take3());
    assertEquals(Tuple2.of("Hi", 2), tuple4.take2());
    assertEquals(Tuple1.of("Hi"), tuple4.take1());

    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple5.take4());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple5.take3());
    assertEquals(Tuple2.of("Hi", 2), tuple5.take2());
    assertEquals(Tuple1.of("Hi"), tuple5.take1());

    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple6.take5());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple6.take4());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple6.take3());
    assertEquals(Tuple2.of("Hi", 2), tuple6.take2());
    assertEquals(Tuple1.of("Hi"), tuple6.take1());

    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple7.take6());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple7.take5());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple7.take4());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple7.take3());
    assertEquals(Tuple2.of("Hi", 2), tuple7.take2());
    assertEquals(Tuple1.of("Hi"), tuple7.take1());

    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple8.take7());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple8.take6());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple8.take5());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple8.take4());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple8.take3());
    assertEquals(Tuple2.of("Hi", 2), tuple8.take2());
    assertEquals(Tuple1.of("Hi"), tuple8.take1());

    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple9.take8());
    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple9.take7());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple9.take6());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple9.take5());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple9.take4());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple9.take3());
    assertEquals(Tuple2.of("Hi", 2), tuple9.take2());
    assertEquals(Tuple1.of("Hi"), tuple9.take1());

    assertEquals(Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple10.take9());
    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple10.take8());
    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple10.take7());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple10.take6());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple10.take5());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple10.take4());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple10.take3());
    assertEquals(Tuple2.of("Hi", 2), tuple10.take2());
    assertEquals(Tuple1.of("Hi"), tuple10.take1());
  }

  @Test void takeRightNElements() {
    assertEquals(Tuple1.of(2), tuple2.takeRight1());

    assertEquals(Tuple2.of(2, 3.1), tuple3.takeRight2());
    assertEquals(Tuple1.of(3.1), tuple3.takeRight1());

    assertEquals(Tuple3.of(2, 3.1, true), tuple4.takeRight3());
    assertEquals(Tuple2.of(3.1, true), tuple4.takeRight2());
    assertEquals(Tuple1.of(true), tuple4.takeRight1());

    assertEquals(Tuple4.of(2, 3.1, true, JANUARY), tuple5.takeRight4());
    assertEquals(Tuple3.of(3.1, true, JANUARY), tuple5.takeRight3());
    assertEquals(Tuple2.of(true, JANUARY), tuple5.takeRight2());
    assertEquals(Tuple1.of(JANUARY), tuple5.takeRight1());

    assertEquals(Tuple5.of(2, 3.1, true, JANUARY, UTC), tuple6.takeRight5());
    assertEquals(Tuple4.of(3.1, true, JANUARY, UTC), tuple6.takeRight4());
    assertEquals(Tuple3.of(true, JANUARY, UTC), tuple6.takeRight3());
    assertEquals(Tuple2.of(JANUARY, UTC), tuple6.takeRight2());
    assertEquals(Tuple1.of(UTC), tuple6.takeRight1());

    assertEquals(Tuple6.of(2, 3.1, true, JANUARY, UTC, 'x'), tuple7.takeRight6());
    assertEquals(Tuple5.of(3.1, true, JANUARY, UTC, 'x'), tuple7.takeRight5());
    assertEquals(Tuple4.of(true, JANUARY, UTC, 'x'), tuple7.takeRight4());
    assertEquals(Tuple3.of(JANUARY, UTC, 'x'), tuple7.takeRight3());
    assertEquals(Tuple2.of(UTC, 'x'), tuple7.takeRight2());
    assertEquals(Tuple1.of('x'), tuple7.takeRight1());

    assertEquals(Tuple7.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.takeRight7());
    assertEquals(Tuple6.of(3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.takeRight6());
    assertEquals(Tuple5.of(true, JANUARY, UTC, 'x', List.of(1)), tuple8.takeRight5());
    assertEquals(Tuple4.of(JANUARY, UTC, 'x', List.of(1)), tuple8.takeRight4());
    assertEquals(Tuple3.of(UTC, 'x', List.of(1)), tuple8.takeRight3());
    assertEquals(Tuple2.of('x', List.of(1)), tuple8.takeRight2());
    assertEquals(Tuple1.of(List.of(1)), tuple8.takeRight1());

    assertEquals(Tuple8.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.takeRight8());
    assertEquals(Tuple7.of(3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.takeRight7());
    assertEquals(Tuple6.of(true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.takeRight6());
    assertEquals(Tuple5.of(JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.takeRight5());
    assertEquals(Tuple4.of(UTC, 'x', List.of(1), Set.of(1)), tuple9.takeRight4());
    assertEquals(Tuple3.of('x', List.of(1), Set.of(1)), tuple9.takeRight3());
    assertEquals(Tuple2.of(List.of(1), Set.of(1)), tuple9.takeRight2());
    assertEquals(Tuple1.of(Set.of(1)), tuple9.takeRight1());

    assertEquals(Tuple9.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.takeRight9());
    assertEquals(Tuple8.of(3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.takeRight8());
    assertEquals(Tuple7.of(true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.takeRight7());
    assertEquals(Tuple6.of(JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.takeRight6());
    assertEquals(Tuple5.of(UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.takeRight5());
    assertEquals(Tuple4.of('x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.takeRight4());
    assertEquals(Tuple3.of(List.of(1), Set.of(1), Map.of("One", 1)), tuple10.takeRight3());
    assertEquals(Tuple2.of(Set.of(1), Map.of("One", 1)), tuple10.takeRight2());
    assertEquals(Tuple1.of(Map.of("One", 1)), tuple10.takeRight1());
  }

  @Test void dropNElements() {
    assertEquals(Tuple1.of(2), tuple2.drop1());

    assertEquals(Tuple2.of(2, 3.1), tuple3.drop1());
    assertEquals(Tuple1.of(3.1), tuple3.drop2());

    assertEquals(Tuple3.of(2, 3.1, true), tuple4.drop1());
    assertEquals(Tuple2.of(3.1, true), tuple4.drop2());
    assertEquals(Tuple1.of(true), tuple4.drop3());

    assertEquals(Tuple4.of(2, 3.1, true, JANUARY), tuple5.drop1());
    assertEquals(Tuple3.of(3.1, true, JANUARY), tuple5.drop2());
    assertEquals(Tuple2.of(true, JANUARY), tuple5.drop3());
    assertEquals(Tuple1.of(JANUARY), tuple5.drop4());

    assertEquals(Tuple5.of(2, 3.1, true, JANUARY, UTC), tuple6.drop1());
    assertEquals(Tuple4.of(3.1, true, JANUARY, UTC), tuple6.drop2());
    assertEquals(Tuple3.of(true, JANUARY, UTC), tuple6.drop3());
    assertEquals(Tuple2.of(JANUARY, UTC), tuple6.drop4());
    assertEquals(Tuple1.of(UTC), tuple6.drop5());

    assertEquals(Tuple6.of(2, 3.1, true, JANUARY, UTC, 'x'), tuple7.drop1());
    assertEquals(Tuple5.of(3.1, true, JANUARY, UTC, 'x'), tuple7.drop2());
    assertEquals(Tuple4.of(true, JANUARY, UTC, 'x'), tuple7.drop3());
    assertEquals(Tuple3.of(JANUARY, UTC, 'x'), tuple7.drop4());
    assertEquals(Tuple2.of(UTC, 'x'), tuple7.drop5());
    assertEquals(Tuple1.of('x'), tuple7.drop6());

    assertEquals(Tuple7.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.drop1());
    assertEquals(Tuple6.of(3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.drop2());
    assertEquals(Tuple5.of(true, JANUARY, UTC, 'x', List.of(1)), tuple8.drop3());
    assertEquals(Tuple4.of(JANUARY, UTC, 'x', List.of(1)), tuple8.drop4());
    assertEquals(Tuple3.of(UTC, 'x', List.of(1)), tuple8.drop5());
    assertEquals(Tuple2.of('x', List.of(1)), tuple8.drop6());
    assertEquals(Tuple1.of(List.of(1)), tuple8.drop7());

    assertEquals(Tuple8.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.drop1());
    assertEquals(Tuple7.of(3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.drop2());
    assertEquals(Tuple6.of(true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.drop3());
    assertEquals(Tuple5.of(JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.drop4());
    assertEquals(Tuple4.of(UTC, 'x', List.of(1), Set.of(1)), tuple9.drop5());
    assertEquals(Tuple3.of('x', List.of(1), Set.of(1)), tuple9.drop6());
    assertEquals(Tuple2.of(List.of(1), Set.of(1)), tuple9.drop7());
    assertEquals(Tuple1.of(Set.of(1)), tuple9.drop8());

    assertEquals(Tuple9.of(2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.drop1());
    assertEquals(Tuple8.of(3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.drop2());
    assertEquals(Tuple7.of(true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.drop3());
    assertEquals(Tuple6.of(JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.drop4());
    assertEquals(Tuple5.of(UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.drop5());
    assertEquals(Tuple4.of('x', List.of(1), Set.of(1), Map.of("One", 1)), tuple10.drop6());
    assertEquals(Tuple3.of(List.of(1), Set.of(1), Map.of("One", 1)), tuple10.drop7());
    assertEquals(Tuple2.of(Set.of(1), Map.of("One", 1)), tuple10.drop8());
    assertEquals(Tuple1.of(Map.of("One", 1)), tuple10.drop9());
  }
  
  @Test void dropRightNElements() {
    assertEquals(Tuple1.of("Hi"), tuple2.dropRight1());

    assertEquals(Tuple2.of("Hi", 2), tuple3.dropRight1());
    assertEquals(Tuple1.of("Hi"), tuple3.dropRight2());

    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple4.dropRight1());
    assertEquals(Tuple2.of("Hi", 2), tuple4.dropRight2());
    assertEquals(Tuple1.of("Hi"), tuple4.dropRight3());

    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple5.dropRight1());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple5.dropRight2());
    assertEquals(Tuple2.of("Hi", 2), tuple5.dropRight3());
    assertEquals(Tuple1.of("Hi"), tuple5.dropRight4());

    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple6.dropRight1());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple6.dropRight2());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple6.dropRight3());
    assertEquals(Tuple2.of("Hi", 2), tuple6.dropRight4());
    assertEquals(Tuple1.of("Hi"), tuple6.dropRight5());

    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple7.dropRight1());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple7.dropRight2());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple7.dropRight3());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple7.dropRight4());
    assertEquals(Tuple2.of("Hi", 2), tuple7.dropRight5());
    assertEquals(Tuple1.of("Hi"), tuple7.dropRight6());

    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple8.dropRight1());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple8.dropRight2());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple8.dropRight3());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple8.dropRight4());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple8.dropRight5());
    assertEquals(Tuple2.of("Hi", 2), tuple8.dropRight6());
    assertEquals(Tuple1.of("Hi"), tuple8.dropRight7());

    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple9.dropRight1());
    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple9.dropRight2());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple9.dropRight3());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple9.dropRight4());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple9.dropRight5());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple9.dropRight6());
    assertEquals(Tuple2.of("Hi", 2), tuple9.dropRight7());
    assertEquals(Tuple1.of("Hi"), tuple9.dropRight8());

    assertEquals(Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple10.dropRight1());
    assertEquals(Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple10.dropRight2());
    assertEquals(Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple10.dropRight3());
    assertEquals(Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple10.dropRight4());
    assertEquals(Tuple5.of("Hi", 2, 3.1, true, JANUARY), tuple10.dropRight5());
    assertEquals(Tuple4.of("Hi", 2, 3.1, true), tuple10.dropRight6());
    assertEquals(Tuple3.of("Hi", 2, 3.1), tuple10.dropRight7());
    assertEquals(Tuple2.of("Hi", 2), tuple10.dropRight8());
    assertEquals(Tuple1.of("Hi"), tuple10.dropRight9());
  }
}
