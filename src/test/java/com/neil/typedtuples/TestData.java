package com.neil.typedtuples;

import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.time.Month.JANUARY;
import static java.time.ZoneOffset.UTC;

public class TestData {
  public static Tuple0                                          tuple0 = Tuple0.of();
  public static Tuple1<String>                                  tuple1 = Tuple1.of("Hi");
  public static Tuple2<String, Integer>                         tuple2 = Tuple2.of("Hi", 2);
  public static Tuple3<String, Integer, Double>                 tuple3 = Tuple3.of("Hi", 2, 3.1);
  public static Tuple4<String, Integer, Double, Boolean>        tuple4 = Tuple4.of("Hi", 2, 3.1, true);
  public static Tuple5<String, Integer, Double, Boolean, Month> tuple5 = Tuple5.of("Hi", 2, 3.1, true, JANUARY);
  public static Tuple6<String, Integer, Double, Boolean, Month, ZoneOffset>
      tuple6 = Tuple6.of("Hi", 2, 3.1, true, JANUARY, UTC);
  public static Tuple7<String, Integer, Double, Boolean, Month, ZoneOffset, Character>
      tuple7 = Tuple7.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x');
  public static Tuple8<String, Integer, Double, Boolean, Month, ZoneOffset, Character, List<Integer>>
      tuple8 = Tuple8.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1));
  public static Tuple9<String, Integer, Double, Boolean, Month, ZoneOffset, Character, List<Integer>, Set<Integer>>
      tuple9 = Tuple9.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1));
  public static Tuple10<String, Integer, Double, Boolean, Month, ZoneOffset, Character, List<Integer>, Set<Integer>, Map<String, Integer>>
      tuple10 = Tuple10.of("Hi", 2, 3.1, true, JANUARY, UTC, 'x', List.of(1), Set.of(1), Map.of("One", 1));
}
