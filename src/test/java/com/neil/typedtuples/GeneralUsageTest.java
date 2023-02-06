package com.neil.typedtuples;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.APRIL;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static java.time.ZoneOffset.UTC;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneralUsageTest {
  private enum TestEnum {
    LEFT, MIDDLE, RIGHT
  }

  private final Tuple0 tuple0 = Tuple0.of();
  private final Tuple1<String> tuple1 = Tuple1.of("Hi");
  private final Tuple2<String, Integer> tuple2 = Tuple2.of("Hi", 2);
  private final Tuple3<String, Integer, Double> tuple3 = Tuple3.of("Hi", 2, 3.1);
  private final Tuple4<String, Integer, Double, Boolean> tuple4 = Tuple4.of("Hi", 2, 3.1, true);
  private final Tuple5<String, Integer, Double, Boolean, Month> tuple5 = Tuple5.of("Hi", 2, 3.1, true, JANUARY);
  private final Tuple6<String, Integer, Double, Boolean, Month, ZoneOffset> tuple6 = Tuple6.of("Hi", 2, 3.1, true,
                                                                                               JANUARY, UTC);
  private final Tuple7<String, Integer, Double, Boolean, Month, ZoneOffset, Character> tuple7 = Tuple7.of("Hi", 2,
                                                                                                          3.1, true,
                                                                                                          JANUARY,
                                                                                                          UTC, 'x');
  private final Tuple8<String, Integer, Double, Boolean, Month, ZoneOffset, Character, List<Integer>> tuple8 =
      Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1));
  private final Tuple9<String, Integer, Double, Boolean, Month, ZoneOffset, Character, List<Integer>, Set<Integer>> tuple9 = Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1));
  private final Tuple10<String, Integer, Double, Boolean, Month, ZoneOffset, Character, List<Integer>, Set<Integer>,
      Map<String, Integer>> tuple10 = Tuple10.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1),
                                                 Map.of("One", 1));

  /**
   * Set of sample Tuple objects that cover all arities.
   */
  private final List<Tuple> sampleTuples = List.of(
      tuple0, tuple1, tuple2, tuple3, tuple4, tuple5, tuple6, tuple7, tuple8, tuple9, tuple10
  );

  @Test
  void aritiesShouldBeCorrect() {
    Iterator<Tuple> tupleIterator = sampleTuples.iterator();
    for (int expectedArity = 0; expectedArity <= 10; expectedArity++) {
      assertEquals(expectedArity, tupleIterator.next().getArity());
    }
  }

  @Test void nullArgs() {
    var exception10 = assertThrows(NullPointerException.class,
                                 () -> Tuple10.ofNonNull(null, null, null, 4, null, 6, 7, null, 9, 10));
    assertEquals("Illegal null elements at positions [1, 2, 3, 5, 8]", exception10.getMessage());

    var exception9 = assertThrows(NullPointerException.class,
                                 () -> Tuple9.ofNonNull(null, null, null, 4, null, 6, 7, null, 9));
    assertEquals("Illegal null elements at positions [1, 2, 3, 5, 8]", exception9.getMessage());

    var exception8 = assertThrows(NullPointerException.class,
                                 () -> Tuple8.ofNonNull(null, null, null, 4, null, 6, 7, null));
    assertEquals("Illegal null elements at positions [1, 2, 3, 5, 8]", exception8.getMessage());

    var exception7 = assertThrows(NullPointerException.class,
                                 () -> Tuple7.ofNonNull(null, null, null, 4, null, 6, 7));
    assertEquals("Illegal null elements at positions [1, 2, 3, 5]", exception7.getMessage());

    var exception6 = assertThrows(NullPointerException.class,
                                 () -> Tuple6.ofNonNull(null, null, null, 4, null, 6));
    assertEquals("Illegal null elements at positions [1, 2, 3, 5]", exception6.getMessage());

    var exception5 = assertThrows(NullPointerException.class,
                                 () -> Tuple5.ofNonNull(null, null, null, 4, null));
    assertEquals("Illegal null elements at positions [1, 2, 3, 5]", exception5.getMessage());

    var exception4 = assertThrows(NullPointerException.class,
                                 () -> Tuple4.ofNonNull(null, null, null, 4));
    assertEquals("Illegal null elements at positions [1, 2, 3]", exception4.getMessage());

    var exception3 = assertThrows(NullPointerException.class,
                                 () -> Tuple3.ofNonNull(null, null, null));
    assertEquals("Illegal null elements at positions [1, 2, 3]", exception3.getMessage());

    var exception2 = assertThrows(NullPointerException.class,
                                 () -> Tuple2.ofNonNull(null, null));
    assertEquals("Illegal null elements at positions [1, 2]", exception2.getMessage());

    var exception1 = assertThrows(NullPointerException.class,
                                 () -> Tuple1.ofNonNull(null));
    assertEquals("Illegal null element at position 1", exception1.getMessage());
  }

  @Test
  void getElementsShouldWork() {
    assertEquals("Hi", tuple10.elem1());
    assertEquals(2, tuple10.elem2());
    assertEquals(3.1, tuple10.elem3());
    assertEquals(true, tuple10.elem4());
    assertEquals(JANUARY, tuple10.elem5());
    assertEquals(UTC, tuple10.elem6());
    assertEquals('x', tuple10.elem7());
    assertEquals(List.of(1), tuple10.elem8());
    assertEquals(Set.of(1), tuple10.elem9());
    assertEquals(Map.of("One", 1), tuple10.elem10());

    assertEquals("Hi", tuple9.elem1());
    assertEquals(2, tuple9.elem2());
    assertEquals(3.1, tuple9.elem3());
    assertEquals(true, tuple9.elem4());
    assertEquals(JANUARY, tuple9.elem5());
    assertEquals(UTC, tuple9.elem6());
    assertEquals('x', tuple9.elem7());
    assertEquals(List.of(1), tuple9.elem8());
    assertEquals(Set.of(1), tuple9.elem9());

    assertEquals("Hi", tuple8.elem1());
    assertEquals(2, tuple8.elem2());
    assertEquals(3.1, tuple8.elem3());
    assertEquals(true, tuple8.elem4());
    assertEquals(JANUARY, tuple8.elem5());
    assertEquals(UTC, tuple8.elem6());
    assertEquals('x', tuple8.elem7());
    assertEquals(List.of(1), tuple8.elem8());

    assertEquals("Hi", tuple7.elem1());
    assertEquals(2, tuple7.elem2());
    assertEquals(3.1, tuple7.elem3());
    assertEquals(true, tuple7.elem4());
    assertEquals(JANUARY, tuple7.elem5());
    assertEquals(UTC, tuple7.elem6());
    assertEquals('x', tuple7.elem7());

    assertEquals("Hi", tuple6.elem1());
    assertEquals(2, tuple6.elem2());
    assertEquals(3.1, tuple6.elem3());
    assertEquals(true, tuple6.elem4());
    assertEquals(JANUARY, tuple6.elem5());
    assertEquals(UTC, tuple6.elem6());

    assertEquals("Hi", tuple5.elem1());
    assertEquals(2, tuple5.elem2());
    assertEquals(3.1, tuple5.elem3());
    assertEquals(true, tuple5.elem4());
    assertEquals(JANUARY, tuple5.elem5());

    assertEquals("Hi", tuple4.elem1());
    assertEquals(2, tuple4.elem2());
    assertEquals(3.1, tuple4.elem3());
    assertEquals(true, tuple4.elem4());

    assertEquals("Hi", tuple3.elem1());
    assertEquals(2, tuple3.elem2());
    assertEquals(3.1, tuple3.elem3());

    assertEquals("Hi", tuple2.elem1());
    assertEquals(2, tuple2.elem2());

    assertEquals("Hi", tuple1.elem1());

  }

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

  @Test
  void toListShouldWork() {
    assertEquals(List.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1)),
                 tuple10.toList());
    assertEquals(List.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1)), tuple9.toList());
    assertEquals(List.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1)), tuple8.toList());
    assertEquals(List.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x'), tuple7.toList());
    assertEquals(List.of("Hi", 2, 3.1, true, JANUARY, UTC), tuple6.toList());
    assertEquals(List.of("Hi", 2, 3.1, true, JANUARY), tuple5.toList());
    assertEquals(List.of("Hi", 2, 3.1, true), tuple4.toList());
    assertEquals(List.of("Hi", 2, 3.1), tuple3.toList());
    assertEquals(List.of("Hi", 2), tuple2.toList());
    assertEquals(List.of("Hi"), tuple1.toList());
    assertEquals(List.of(), tuple0.toList());
  }

  @Test
  void mappingElementsShouldWork() {
    assertEquals(2, tuple1.mapElem1(String::length).elem1());

    assertEquals(2, tuple2.mapElem1(String::length).elem1());
    assertEquals(3, tuple2.mapElem2(i -> i + 1).elem2());

    assertEquals(2, tuple3.mapElem1(String::length).elem1());
    assertEquals(3, tuple3.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple3.mapElem3(d -> d * 2).elem3());

    assertEquals(2, tuple4.mapElem1(String::length).elem1());
    assertEquals(3, tuple4.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple4.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple4.mapElem4(Object::toString).elem4());

    assertEquals(2, tuple5.mapElem1(String::length).elem1());
    assertEquals(3, tuple5.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple5.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple5.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple5.mapElem5(Enum::toString).elem5());

    assertEquals(2, tuple6.mapElem1(String::length).elem1());
    assertEquals(3, tuple6.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple6.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple6.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple6.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple6.mapElem6(ZoneOffset::getId).elem6());

    assertEquals(2, tuple7.mapElem1(String::length).elem1());
    assertEquals(3, tuple7.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple7.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple7.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple7.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple7.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple7.mapElem7(Character::toUpperCase).elem7());

    assertEquals(2, tuple8.mapElem1(String::length).elem1());
    assertEquals(3, tuple8.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple8.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple8.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple8.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple8.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple8.mapElem7(Character::toUpperCase).elem7());
    assertEquals("[1]", tuple8.mapElem8(Object::toString).elem8());

    assertEquals(2, tuple9.mapElem1(String::length).elem1());
    assertEquals(3, tuple9.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple9.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple9.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple9.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple9.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple9.mapElem7(Character::toUpperCase).elem7());
    assertEquals("[1]", tuple9.mapElem8(Object::toString).elem8());
    assertEquals(1, tuple9.mapElem9(Set::size).elem9());

    assertEquals(2, tuple10.mapElem1(String::length).elem1());
    assertEquals(3, tuple10.mapElem2(i -> i + 1).elem2());
    assertEquals(6.2, tuple10.mapElem3(d -> d * 2).elem3());
    assertEquals("true", tuple10.mapElem4(Object::toString).elem4());
    assertEquals("JANUARY", tuple10.mapElem5(Enum::toString).elem5());
    assertEquals("Z", tuple10.mapElem6(ZoneOffset::getId).elem6());
    assertEquals('X', tuple10.mapElem7(Character::toUpperCase).elem7());
    assertEquals("[1]", tuple10.mapElem8(Object::toString).elem8());
    assertEquals(1, tuple10.mapElem9(Set::size).elem9());
    assertEquals(2, tuple10.mapElem10(map -> map.get("One") + 1).elem10());
  }

  @Test
  void replacementShouldWork() {
    assertEquals(APRIL, tuple1.withElem1(APRIL).elem1());

    assertEquals(APRIL, tuple2.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple2.withElem2(APRIL).elem2());

    assertEquals(APRIL, tuple3.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple3.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple3.withElem3(APRIL).elem3());

    assertEquals(APRIL, tuple4.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple4.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple4.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple4.withElem4(APRIL).elem4());

    assertEquals(APRIL, tuple5.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple5.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple5.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple5.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple5.withElem5(APRIL).elem5());

    assertEquals(APRIL, tuple6.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple6.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple6.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple6.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple6.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple6.withElem6(APRIL).elem6());

    assertEquals(APRIL, tuple7.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple7.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple7.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple7.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple7.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple7.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple7.withElem7(APRIL).elem7());

    assertEquals(APRIL, tuple8.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple8.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple8.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple8.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple8.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple8.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple8.withElem7(APRIL).elem7());
    assertEquals(APRIL, tuple8.withElem8(APRIL).elem8());

    assertEquals(APRIL, tuple9.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple9.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple9.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple9.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple9.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple9.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple9.withElem7(APRIL).elem7());
    assertEquals(APRIL, tuple9.withElem8(APRIL).elem8());
    assertEquals(APRIL, tuple9.withElem9(APRIL).elem9());

    assertEquals(APRIL, tuple10.withElem1(APRIL).elem1());
    assertEquals(APRIL, tuple10.withElem2(APRIL).elem2());
    assertEquals(APRIL, tuple10.withElem3(APRIL).elem3());
    assertEquals(APRIL, tuple10.withElem4(APRIL).elem4());
    assertEquals(APRIL, tuple10.withElem5(APRIL).elem5());
    assertEquals(APRIL, tuple10.withElem6(APRIL).elem6());
    assertEquals(APRIL, tuple10.withElem7(APRIL).elem7());
    assertEquals(APRIL, tuple10.withElem8(APRIL).elem8());
    assertEquals(APRIL, tuple10.withElem9(APRIL).elem9());
    assertEquals(APRIL, tuple10.withElem10(APRIL).elem10());
  }

  @Test
  void containsAnyNullsShouldWork() {
    assertFalse(Tuple0.of().containsAnyNulls());
    assertTrue(tuple1.withElem1(null).containsAnyNulls());
    assertTrue(tuple2.withElem2(null).containsAnyNulls());
    assertTrue(tuple3.withElem3(null).containsAnyNulls());
    assertTrue(tuple4.withElem4(null).containsAnyNulls());
    assertTrue(tuple5.withElem5(null).containsAnyNulls());
    assertTrue(tuple6.withElem6(null).containsAnyNulls());
    assertTrue(tuple7.withElem7(null).containsAnyNulls());
    assertTrue(tuple8.withElem8(null).containsAnyNulls());
    assertTrue(tuple9.withElem9(null).containsAnyNulls());
    assertTrue(tuple10.withElem10(null).containsAnyNulls());
  }

  @Test
  void tuple2FromMapEntry() {
    var map = Map.of("Hello", 42);
    Tuple2<String, Integer> t2 = Tuple2.from(map.entrySet().iterator().next());

    assertEquals(Tuple2.of("Hello", 42), t2);

    // map to list of tuples.
    List<Tuple2<String, Integer>> entryTuples = map.entrySet().stream().map(Tuple2::from).collect(toList());
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
}
