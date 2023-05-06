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
  void testGenerateRepeatingTokenWithIndex() {
    assertEquals("that.elem1(), that.elem2(), that.elem3()", helper.generateRepeatingTokenWithIndex(1, 3, "that.elem%d()"));
  }

  @Test
  void testGenerateSampleArgs() {
    assertEquals("\"Hi\", 2, 3.1", helper.generateSampleArgs(1, 3));
  }

  @Test
  void testGenerateSampleArgsReplacing() {
    assertEquals("42, 2, 3.1", helper.generateSampleArgsReplacing(1, 3, 1, "42"));
  }

  @Test
  void testGenerateSampleArgsDropping() {
    assertEquals("2, 3.1", helper.generateSampleArgsDropping(1, 3, 1));
  }

  @Test
  void testGenerateAngledTypeParamsDropping() {
    assertEquals("<T2, T3>", helper.angledTypeParamsDropping(1, 3, 1));
  }

  @Test
  void testGenerateParamsDropping() {
    assertEquals("t2, t3", helper.paramsDropping(1, 3, 1));
  }

  @Test
  void testGenerateAngledTypeParamsReplacing() {
    assertEquals("<R, T2, T3>", helper.angledTypeParamsReplacing(1, 3, 1, "R"));
  }

  @Test
  void testGenerateParamsReplacing() {
    assertEquals("foo, t2, t3", helper.paramsReplacing(1, 3, 1, "foo"));
  }

  @Test
  void testGenerateParamsReplacingPuke() {
    assertEquals("foo1bar, t2, t3", helper.paramsReplacing_(1, 3, 1, "fooXXXbar"));
  }
}
