package com.neil.typedtuples;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapRelatedTest {

  @Test
  void tuple2FromMapEntry() {
    var map = Map.of("Hello", 42);
    Tuple2<String, Integer> t2 = Tuple2.from(map.entrySet().iterator().next());

    assertEquals(Tuple2.of("Hello", 42), t2);
  }
}
