package io.github.neilmce.tupleware.annotations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThymeleafHelperTest {
  private final ThymeleafHelper helper = new ThymeleafHelper();

  @Test
  void testRangeOneToken() {
    assertEquals("XT1, XT2, XT3", helper.rangeOneToken(1, 3, "X%s%d", "T"));
  }

  @Test
  void testRangeTwoTokens() {
    assertEquals("Foo ab yz, Foo ab yz, Foo ab yz", helper.rangeTwoTokens(1, 3, "Foo %s %s", "ab", "yz"));
  }

  @Test
  void testCharNumberPairs() {
    assertEquals("T1, T2, T3", helper.charNumberPairs(1, 3, 'T'));
  }

  @Test
  void testGetTypeParams() {
    assertEquals("T1 t1, T2 t2, T3 t3", helper.getTypeParams(1, 3));
  }

  @Test
  void testSampleArgs() {
    assertEquals("\"Hi\", 2, 3.1", helper.sampleArgs(1, 3));
  }
}
