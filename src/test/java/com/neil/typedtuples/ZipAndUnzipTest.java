package com.neil.typedtuples;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.Month.FEBRUARY;
import static java.time.Month.JANUARY;
import static java.time.Month.MARCH;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZipAndUnzipTest {
  private enum TestEnum {
    LEFT, MIDDLE, RIGHT
  }

  @Test void zip2ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);

    var expected = List.of(
        Tuple2.of("One", 1),
        Tuple2.of("Two", 2),
        Tuple2.of("Three", 3)
    );

    assertEquals(expected, Tuple2.zip(l1, l2));
  }

  @Test void zip2WithMismatchedListsShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3, 4, 5, 6);

    var expected = List.of(
        Tuple2.of("One", 1),
        Tuple2.of("Two", 2),
        Tuple2.of("Three", 3)
    );

    assertEquals(expected, Tuple2.zip(l1, l2));

    // And with the long list on the other side:
    assertEquals(expected, Tuple2.zip(List.of("One", "Two", "Three", "Four"), List.of(1, 2, 3)));
  }

  @Test void unzip2ShouldWork() {
    var listOfTuples = List.of(
        Tuple2.of("One", 1),
        Tuple2.of("Two", 2),
        Tuple2.of("Three", 3)
    );

    Tuple2<List<String>, List<Integer>> ans = Tuple2.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
  }

  @Test void zip3ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);

    var expected = List.of(
        Tuple3.of("One", 1, 1.0),
        Tuple3.of("Two", 2, 2.0),
        Tuple3.of("Three", 3, 3.0)
    );

    assertEquals(expected, Tuple3.zip(l1, l2, l3));
  }

  @Test void unzip3ShouldWork() {
    var listOfTuples = List.of(
        Tuple3.of("One", 1, 1.0),
        Tuple3.of("Two", 2, 2.0),
        Tuple3.of("Three", 3, 3.0)
    );

    Tuple3<List<String>, List<Integer>, List<Double>> ans = Tuple3.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
  }

  @Test void zip4ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);
    var l4 = List.of(MONDAY, TUESDAY, WEDNESDAY);

    var expected = List.of(
        Tuple4.of("One", 1, 1.0, MONDAY),
        Tuple4.of("Two", 2, 2.0, TUESDAY),
        Tuple4.of("Three", 3, 3.0, WEDNESDAY)
    );

    assertEquals(expected, Tuple4.zip(l1, l2, l3, l4));
  }

  @Test void unzip4ShouldWork() {
    var listOfTuples = List.of(
        Tuple4.of("One", 1, 1.0, MONDAY),
        Tuple4.of("Two", 2, 2.0, TUESDAY),
        Tuple4.of("Three", 3, 3.0, WEDNESDAY)
    );

    Tuple4<List<String>, List<Integer>, List<Double>, List<DayOfWeek>> ans = Tuple4.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
    assertEquals(List.of(MONDAY, TUESDAY, WEDNESDAY), ans.elem4());
  }

  @Test void zip5ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);
    var l4 = List.of(MONDAY, TUESDAY, WEDNESDAY);
    var l5 = List.of(JANUARY, FEBRUARY, MARCH);

    var expected = List.of(
        Tuple5.of("One", 1, 1.0, MONDAY, JANUARY),
        Tuple5.of("Two", 2, 2.0, TUESDAY, FEBRUARY),
        Tuple5.of("Three", 3, 3.0, WEDNESDAY, MARCH)
    );

    assertEquals(expected, Tuple5.zip(l1, l2, l3, l4, l5));
  }

  @Test void unzip5ShouldWork() {
    var listOfTuples = List.of(
        Tuple5.of("One", 1, 1.0, MONDAY, JANUARY),
        Tuple5.of("Two", 2, 2.0, TUESDAY, FEBRUARY),
        Tuple5.of("Three", 3, 3.0, WEDNESDAY, MARCH)
    );

    Tuple5<List<String>, List<Integer>, List<Double>, List<DayOfWeek>, List<Month>> ans = Tuple5.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
    assertEquals(List.of(MONDAY, TUESDAY, WEDNESDAY), ans.elem4());
    assertEquals(List.of(JANUARY, FEBRUARY, MARCH), ans.elem5());
  }

  @Test void zip6ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);
    var l4 = List.of(MONDAY, TUESDAY, WEDNESDAY);
    var l5 = List.of(JANUARY, FEBRUARY, MARCH);
    var l6 = List.of('a', 'b', 'c');

    var expected = List.of(
        Tuple6.of("One", 1, 1.0, MONDAY, JANUARY, 'a'),
        Tuple6.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b'),
        Tuple6.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c')
    );

    assertEquals(expected, Tuple6.zip(l1, l2, l3, l4, l5, l6));
  }

  @Test void unzip6ShouldWork() {
    var listOfTuples = List.of(
        Tuple6.of("One", 1, 1.0, MONDAY, JANUARY, 'a'),
        Tuple6.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b'),
        Tuple6.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c')
    );

    Tuple6<List<String>, List<Integer>, List<Double>, List<DayOfWeek>, List<Month>, List<Character>> ans = Tuple6.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
    assertEquals(List.of(MONDAY, TUESDAY, WEDNESDAY), ans.elem4());
    assertEquals(List.of(JANUARY, FEBRUARY, MARCH), ans.elem5());
    assertEquals(List.of('a', 'b', 'c'), ans.elem6());
  }

  @Test void zip7ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);
    var l4 = List.of(MONDAY, TUESDAY, WEDNESDAY);
    var l5 = List.of(JANUARY, FEBRUARY, MARCH);
    var l6 = List.of('a', 'b', 'c');
    var l7 = List.of(-1L, 0L, 1L);

    var expected = List.of(
        Tuple7.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L),
        Tuple7.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L),
        Tuple7.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L)
    );

    assertEquals(expected, Tuple7.zip(l1, l2, l3, l4, l5, l6, l7));
  }

  @Test void unzip7ShouldWork() {
    var listOfTuples = List.of(
        Tuple7.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L),
        Tuple7.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L),
        Tuple7.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L)
    );

    Tuple7<List<String>, List<Integer>, List<Double>, List<DayOfWeek>, List<Month>, List<Character>, List<Long>> ans = Tuple7.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
    assertEquals(List.of(MONDAY, TUESDAY, WEDNESDAY), ans.elem4());
    assertEquals(List.of(JANUARY, FEBRUARY, MARCH), ans.elem5());
    assertEquals(List.of('a', 'b', 'c'), ans.elem6());
    assertEquals(List.of(-1L, 0L, 1L), ans.elem7());
  }

  @Test void zip8ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);
    var l4 = List.of(MONDAY, TUESDAY, WEDNESDAY);
    var l5 = List.of(JANUARY, FEBRUARY, MARCH);
    var l6 = List.of('a', 'b', 'c');
    var l7 = List.of(-1L, 0L, 1L);
    var l8 = List.of(TestEnum.LEFT, TestEnum.MIDDLE, TestEnum.RIGHT);

    var expected = List.of(
        Tuple8.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L, TestEnum.LEFT),
        Tuple8.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L, TestEnum.MIDDLE),
        Tuple8.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L, TestEnum.RIGHT)
    );

    assertEquals(expected, Tuple8.zip(l1, l2, l3, l4, l5, l6, l7, l8));
  }

  @Test void unzip8ShouldWork() {
    var listOfTuples = List.of(
        Tuple8.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L, TestEnum.LEFT),
        Tuple8.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L, TestEnum.MIDDLE),
        Tuple8.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L, TestEnum.RIGHT)
    );

    Tuple8<List<String>, List<Integer>, List<Double>, List<DayOfWeek>, List<Month>, List<Character>, List<Long>, List<TestEnum>> ans = Tuple8.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
    assertEquals(List.of(MONDAY, TUESDAY, WEDNESDAY), ans.elem4());
    assertEquals(List.of(JANUARY, FEBRUARY, MARCH), ans.elem5());
    assertEquals(List.of('a', 'b', 'c'), ans.elem6());
    assertEquals(List.of(-1L, 0L, 1L), ans.elem7());
    assertEquals(List.of(TestEnum.LEFT, TestEnum.MIDDLE, TestEnum.RIGHT), ans.elem8());
  }

  @Test void zip9ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);
    var l4 = List.of(MONDAY, TUESDAY, WEDNESDAY);
    var l5 = List.of(JANUARY, FEBRUARY, MARCH);
    var l6 = List.of('a', 'b', 'c');
    var l7 = List.of(-1L, 0L, 1L);
    var l8 = List.of(TestEnum.LEFT, TestEnum.MIDDLE, TestEnum.RIGHT);
    var l9 = List.of(1F, 2F, 3F);

    var expected = List.of(
        Tuple9.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L, TestEnum.LEFT, 1F),
        Tuple9.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L, TestEnum.MIDDLE, 2F),
        Tuple9.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L, TestEnum.RIGHT, 3F)
    );

    assertEquals(expected, Tuple9.zip(l1, l2, l3, l4, l5, l6, l7, l8, l9));
  }

  @Test void unzip9ShouldWork() {
    var listOfTuples = List.of(
        Tuple9.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L, TestEnum.LEFT, 1F),
        Tuple9.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L, TestEnum.MIDDLE, 2F),
        Tuple9.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L, TestEnum.RIGHT, 3F)
    );

    Tuple9<List<String>, List<Integer>, List<Double>, List<DayOfWeek>, List<Month>, List<Character>, List<Long>, List<TestEnum>, List<Float>> ans = Tuple9.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
    assertEquals(List.of(MONDAY, TUESDAY, WEDNESDAY), ans.elem4());
    assertEquals(List.of(JANUARY, FEBRUARY, MARCH), ans.elem5());
    assertEquals(List.of('a', 'b', 'c'), ans.elem6());
    assertEquals(List.of(-1L, 0L, 1L), ans.elem7());
    assertEquals(List.of(TestEnum.LEFT, TestEnum.MIDDLE, TestEnum.RIGHT), ans.elem8());
    assertEquals(List.of(1F, 2F, 3F), ans.elem9());
  }

  @Test void zip10ShouldWork() {
    var l1 = List.of("One", "Two", "Three");
    var l2 = List.of(1, 2, 3);
    var l3 = List.of(1.0, 2.0, 3.0);
    var l4 = List.of(MONDAY, TUESDAY, WEDNESDAY);
    var l5 = List.of(JANUARY, FEBRUARY, MARCH);
    var l6 = List.of('a', 'b', 'c');
    var l7 = List.of(-1L, 0L, 1L);
    var l8 = List.of(TestEnum.LEFT, TestEnum.MIDDLE, TestEnum.RIGHT);
    var l9 = List.of(1F, 2F, 3F);
    var l10 = List.of(new int[] {1}, new int[] {2}, new int[] {3});

    var expected = List.of(
        Tuple10.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L, TestEnum.LEFT, 1F, new int[]{1}),
        Tuple10.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L, TestEnum.MIDDLE, 2F, new int[]{2}),
        Tuple10.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L, TestEnum.RIGHT, 3F, new int[]{3})
    );

    assertEquals(expected, Tuple10.zip(l1, l2, l3, l4, l5, l6, l7, l8, l9, l10));
  }

  @Test void unzip10ShouldWork() {
    var listOfTuples = List.of(
        Tuple10.of("One", 1, 1.0, MONDAY, JANUARY, 'a', -1L, TestEnum.LEFT, 1F, new int[]{1}),
        Tuple10.of("Two", 2, 2.0, TUESDAY, FEBRUARY, 'b', 0L, TestEnum.MIDDLE, 2F, new int[]{2}),
        Tuple10.of("Three", 3, 3.0, WEDNESDAY, MARCH, 'c', 1L, TestEnum.RIGHT, 3F, new int[]{3})
    );

    Tuple10<List<String>, List<Integer>, List<Double>, List<DayOfWeek>, List<Month>, List<Character>, List<Long>, List<TestEnum>, List<Float>, List<int[]>> ans = Tuple10.unzip(listOfTuples);

    assertEquals(List.of("One", "Two", "Three"), ans.elem1());
    assertEquals(List.of(1, 2, 3), ans.elem2());
    assertEquals(List.of(1.0, 2.0, 3.0), ans.elem3());
    assertEquals(List.of(MONDAY, TUESDAY, WEDNESDAY), ans.elem4());
    assertEquals(List.of(JANUARY, FEBRUARY, MARCH), ans.elem5());
    assertEquals(List.of('a', 'b', 'c'), ans.elem6());
    assertEquals(List.of(-1L, 0L, 1L), ans.elem7());
    assertEquals(List.of(TestEnum.LEFT, TestEnum.MIDDLE, TestEnum.RIGHT), ans.elem8());
    assertEquals(List.of(1F, 2F, 3F), ans.elem9());
    // Arrays are weird.
    var expected = List.of(new int[]{1}, new int[]{2}, new int[]{3});
    assertEquals(expected.size(), ans.elem10().size());
    for (int i = 0; i < expected.size(); i++) {
      assertArrayEquals(expected.get(i), ans.elem10().get(i));
    }
  }

  @Test
  void unzippingNullListOfTuplesShouldFailGracefully() {
    NullPointerException exception = assertThrows(NullPointerException.class, () -> Tuple2.unzip(null));
    assertEquals("Illegal null List argument", exception.getMessage());
  }
}
