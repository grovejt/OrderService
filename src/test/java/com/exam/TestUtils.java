package com.exam;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public final class TestUtils
{
  
  //prevent instantiation since this is a static collection of utility functions:
  private TestUtils() {};
  
  /**
   * Test that two big decimals represent the same number without regard to scale or precision differences.
   * @param expected the expected value of the big decimal being tested.
   * @param actual the actual value of the big decimal being tested.
   */
  public static void assertBigDecimalsAreEquivalent(BigDecimal expected, BigDecimal actual)
  {
    String formattedMessage = String.format("Incorrect order total was calculated.  Expected: %s, Actual: %s", expected.toString(), actual.toString());
    assertEquals(formattedMessage, 0, expected.compareTo(actual));
  }
}
