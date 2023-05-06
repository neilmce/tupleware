package io.github.neilmce.tupleware.annotations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThymeleafHelperTest {
  private final ThymeleafHelper helper = new ThymeleafHelper();

  @Test
  void testGenerateGenericTypeNames() {
    assertEquals("T1, T2, T3", helper.generateGenericTypeNames(1, 3, 'T'));
  }

  @Test
  void testGenerateGenericTypeParams() {
    assertEquals("T1 t1, T2 t2, T3 t3", helper.generateGenericTypeParams(1, 3));
  }

  @Test
  void testRangeOneTokenPuke() {
    assertEquals("that.elem1(), that.elem2(), that.elem3()", helper.generateRepeatingTokenWithIndex(1, 3, "that.elem%d()"));
  }

  @Test
  void testGenerateSampleArgs() {
    assertEquals("\"Hi\", 2, 3.1", helper.generateSampleArgs(1, 3));
  }
}
