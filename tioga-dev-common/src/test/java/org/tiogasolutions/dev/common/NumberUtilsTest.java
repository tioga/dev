package org.tiogasolutions.dev.common;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;

import static org.testng.Assert.*;

@Test
public class NumberUtilsTest {

  public void testBigDecimalsEqual() {

    BigDecimal valueA = new BigDecimal("1.2");
    BigDecimal valueB = new BigDecimal("1.2");

    assertEquals(valueA.scale(), 1);
    assertEquals(valueB.scale(), 1);
    assertEquals(valueA, valueB);
    assertTrue(NumberUtils.bigDecimalsEqual(valueA, valueB));
    assertTrue(EqualsUtils.bigDecimalsEqual(valueA, valueB));

    valueA = new BigDecimal("1.3");
    valueB = new BigDecimal("1.30");

    assertEquals(valueA.scale(), 1);
    assertEquals(valueB.scale(), 2);
    assertNotEquals(valueA, valueB);
    assertTrue(NumberUtils.bigDecimalsEqual(valueA, valueB));
    assertTrue(EqualsUtils.bigDecimalsEqual(valueA, valueB));

    valueA = new BigDecimal("310923811.323");
    valueB = new BigDecimal("310923811.32300000000");

    assertEquals(valueA.scale(), 3);
    assertEquals(valueB.scale(), 11);
    assertNotEquals(valueA, valueB);
    assertTrue(NumberUtils.bigDecimalsEqual(valueA, valueB));
    assertTrue(EqualsUtils.bigDecimalsEqual(valueA, valueB));

    valueA = new BigDecimal("310923811.323");
    valueB = new BigDecimal("310923811.323000000001");

    assertEquals(valueA.scale(), 3);
    assertEquals(valueB.scale(), 12);
    assertNotEquals(valueA, valueB);
    assertTrue(NumberUtils.bigDecimalsNotEqual(valueA, valueB));
    assertTrue(EqualsUtils.bigDecimalsNotEqual(valueA, valueB));
  }

  public static void testMultiply() {
    try {
      NumberUtils.multiply(null, Collections.emptyList());
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"firstNumber\" cannot be null.");
    }

    try {
      // noinspection RedundantArrayCreation
      NumberUtils.multiply(null, new Object[0]);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"firstNumber\" cannot be null.");
    }

    try {
      NumberUtils.multiply("2", (Object[])null);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"numbers\" cannot be null.");
    }

    try {
      NumberUtils.multiply("3", (Collection)null);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"numbers\" cannot be null.");
    }

    BigDecimal result = NumberUtils.multiply("2");
    assertEquals(result, new BigDecimal("2"));

    result = NumberUtils.multiply("2", "3");
    assertEquals(result, new BigDecimal("6"));

    result = NumberUtils.multiply("2", "3", 5);
    assertEquals(result, new BigDecimal("30"));

    result = NumberUtils.multiply("2", "3", 5, new BigDecimal("0.5"));
    assertEquals(result, new BigDecimal("15.0"));
  }

  public static void testToBigDecimal() {

    assertEquals(NumberUtils.toBigDecimal((char)256), new BigDecimal("256"));

    assertEquals(NumberUtils.toBigDecimal("19.95".toCharArray()), new BigDecimal("19.95"));
    assertEquals(NumberUtils.toBigDecimal("19.95"), new BigDecimal("19.95"));

    assertEquals(NumberUtils.toBigDecimal(19.95f), new BigDecimal("19.950000762939453125"));
    assertEquals(NumberUtils.toBigDecimal(19.95d), new BigDecimal("19.949999999999999289457264239899814128875732421875"));

    assertEquals(NumberUtils.toBigDecimal(1995), new BigDecimal("1995"));
    assertEquals(NumberUtils.toBigDecimal(1995L), new BigDecimal("1995"));
    assertEquals(NumberUtils.toBigDecimal(new BigInteger("1995")), new BigDecimal("1995"));

    assertEquals(NumberUtils.toBigDecimal(new BigDecimal("19950")), new BigDecimal("19950"));
  }
}