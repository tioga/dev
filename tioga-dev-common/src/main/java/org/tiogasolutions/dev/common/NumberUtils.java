package org.tiogasolutions.dev.common;

import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;

public class NumberUtils {

  public static boolean bigDecimalsEqual(BigDecimal valueA, BigDecimal valueB) {
    // noinspection NumberEquality
    if (valueA == valueB) {
      return true;
    } if (valueA == null || valueB == null) {
      return true;
    }

    int commonScale = Math.max(valueA.scale(), valueB.scale());
    valueA = valueA.setScale(commonScale, RoundingMode.HALF_EVEN);
    valueB = valueB.setScale(commonScale, RoundingMode.HALF_EVEN);

    return valueA.equals(valueB);
  }

  public static boolean bigDecimalsNotEqual(BigDecimal valueA, BigDecimal valueB) {
    return bigDecimalsEqual(valueA, valueB) == false;
  }

  public static BigDecimal multiply(Object firstNumber, Object...numbers) {
    ExceptionUtils.assertNotNull(firstNumber, "firstNumber");
    ExceptionUtils.assertNotNull(numbers, "numbers");
    return multiply(firstNumber, Arrays.asList(numbers));
  }

  public static BigDecimal multiply(Object firstNumber, Collection<?> numbers) {
    ExceptionUtils.assertNotNull(firstNumber, "firstNumber");
    ExceptionUtils.assertNotNull(numbers, "numbers");

    BigDecimal retVal = toBigDecimal(firstNumber);
    for (Object next : numbers) {
      BigDecimal nextValue = toBigDecimal(next);
      retVal = retVal.multiply(nextValue);
    }
    return retVal;
  }

  public static BigDecimal toBigDecimal(Object value) {
    ExceptionUtils.assertNotNull(value, "value");

    if (value instanceof char[]) {
      return new BigDecimal((char[])value);
    } else if (value instanceof String) {
      return new BigDecimal((String)value);
    } else if (value instanceof Character) {
      return new BigDecimal((Character)value);
    } else if (value instanceof Float) {
      return new BigDecimal((Float)value);
    } else if (value instanceof Double) {
      return new BigDecimal((Double)value);
    } else if (value instanceof Integer) {
      return new BigDecimal((Integer)value);
    } else if (value instanceof Long) {
      return new BigDecimal((Long)value);
    } else if (value instanceof BigInteger) {
      return new BigDecimal((BigInteger)value);
    } else if (value instanceof BigDecimal) {
      return (BigDecimal)value;
    } else {
      String msg = String.format("Cannot convert the values \"%s\" (%s) to the type %s.", value, value.getClass().getName(), BigDecimal.class.getName());
      throw new IllegalArgumentException(msg);
    }
  }

  private NumberUtils() {
  }
}
