/*
 * Copyright 2012 Jacob D Parr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tiogasolutions.dev.domain.money;

import java.io.Serializable;
import java.math.*;

import static java.lang.String.format;

public class Money extends Number implements Serializable, Comparable<Money> {

  private static final String errorTemplate = format("The value %%s could not be converted to a %s: %%s", BigDecimal.class.getSimpleName());
  private static final java.text.DecimalFormat DollarFormat = new java.text.DecimalFormat("0.00");

  private long internalValue;

  public static Money ZERO() {
    return new Money("0.00");
  }
  public static Money MIN_VALUE() {
    return new Money(String.valueOf(Long.MIN_VALUE));
  }
  public static Money MAX_VALUE() {
    return new Money(String.valueOf(Long.MAX_VALUE));
  }

  /** @deprecated Use Money.ZERO() instead. */
  @Deprecated
  public Money() {
    internalValue = 0;
  }
  
  /**
   * Creates a new money value from a double value.
   * @param value the value to initialize from
   * @deprecated Use {@link #Money(String)} or {@link #Money(java.math.BigDecimal)} instead in that double value are inherently unsafe.
   */
  @Deprecated
  public Money(double value) {
    this(convert(value));
  }

  /**
   * Creates a new money value from a double value.
   * @param value the value to initialize from
   * @param roundingMode the rounding mode used in the conversion.
   * @deprecated Use {@link #Money(String)} or {@link #Money(java.math.BigDecimal)} instead in that double value are inherently unsafe.
   */
  @Deprecated
  public Money(double value, RoundingMode roundingMode) {
    this(convert(value), roundingMode);
  }

  public Money(String value) {
    this(convert(value));
  }

  public Money(String value, RoundingMode roundingMode) {
    this(convert(value), roundingMode);
  }

  public Money(final BigDecimal value) {
    this(value, RoundingMode.UNNECESSARY);
  }

  public Money(final BigDecimal value, RoundingMode roundingMode) {
    try {
      BigDecimal newValue = value.setScale(2, roundingMode);
      newValue = newValue.multiply(new BigDecimal(100));
      newValue = newValue.setScale(2, roundingMode);
      this.internalValue = newValue.longValueExact();

    } catch (Throwable ex) {
      throw new MoneyException(format(errorTemplate, value, ex.getMessage()), ex);
    }
  }

  public Money(Money copy) {
    this.internalValue = copy.internalValue;
  }

  private static BigDecimal convert(String value) {
    try {
      value = value.replace(",", "").replace("$", "");
      return new BigDecimal(value);

    } catch (Throwable ex) {
      throw new MoneyException(format(errorTemplate, value), ex);
    }
  }

  private static BigDecimal convert(double value) {
    try {
      return BigDecimal.valueOf(value);

    } catch (Throwable ex) {
      throw new MoneyException(format(errorTemplate, value), ex);
    }
  }

  private Money(long internalValue) {
    this.internalValue = internalValue;
  }

  public long getInternalValue() {
    return internalValue;
  }

  public String getShortForm() {

    String retVal = toString();
    if (retVal.endsWith(".00")) {
      retVal = retVal.substring(0, retVal.length()-3);
    }

    return retVal;
  }

  public String toString() {
    return DollarFormat.format(doubleValue());
  }

  public Money getAbsolute() {
    return isPositive() ? this : multiply(-1);
  }

  public Money add(Money amount) {
    return plus(amount);
  }

  public Money plus(Money amount) {
    return new Money(internalValue + amount.getInternalValue());
  }

  public Money subtract(Money amount) {
    return minus(amount);
  }
  
  public Money minus(Money amount) {
    return new Money(internalValue - amount.getInternalValue());
  }

  public Money multiply(long multiplier) {
    long longValue = (internalValue * multiplier);
    return new Money(longValue);
  }

  public Money multiply(double multiplier) {
    long longValue = (long)(internalValue * multiplier);
    return new Money(longValue);
  }

  public Money multiply(BigDecimal multiplier) {
    BigDecimal value = new BigDecimal(internalValue).divide(new BigDecimal(100));
    value = value.multiply(multiplier);
    return new Money(value, RoundingMode.HALF_EVEN);
  }

  public Money divide (double divisor) {
    long longValue = (long)(internalValue / divisor);
    return new Money(longValue);
  }

  public boolean equals(Object object) {
    if (object instanceof Money) {
      Money amount = (Money)object;
      return internalValue == amount.getInternalValue();
    }
    return false;
  }

  public boolean greaterThan(Money that) {
    return this.internalValue > that.getInternalValue();
  }

  public boolean lessThan(Money that) {
    return this.internalValue < that.getInternalValue();
  }

  public boolean isNegative() {
    return internalValue < 0;
  }
  public boolean isNonNegative() {
    return isNegative() == false;
  }

  public boolean isPositive() {
    return internalValue > 0;
  }
  public boolean isNonPositive() {
    return isPositive() == false;
  }

  public boolean isZero() {
    return internalValue == 0;
  }

  public boolean isNonZero() {
    return internalValue != 0;
  }

  public BigDecimal getWholeNumber() {
    BigDecimal value = new BigDecimal(internalValue/100);
    value = value.setScale(0, RoundingMode.HALF_EVEN);
    return value;
  }

  public BigDecimal getBigDecimalValue() {
    BigDecimal value = new BigDecimal(internalValue/100d);
    value = value.setScale(2, RoundingMode.HALF_EVEN);
    return value;
  }
  
  @Override
  public double doubleValue() {
    return getBigDecimalValue().doubleValue();
  }

  @Override
  public int intValue() {
    return getBigDecimalValue().intValue();
  }

  @Override
  public long longValue() {
    return getBigDecimalValue().longValue();
  }

  @Override
  public float floatValue() {
    return getBigDecimalValue().floatValue();
  }

  @Override
  public int compareTo(Money amount) {
    return getBigDecimalValue().compareTo(amount.getBigDecimalValue());
  }

  private static BigDecimal toBigDecimal(String value) {
    try {
      return new BigDecimal(value);
    } catch (NumberFormatException ex) {
      String msg = format("Cannot convert \"%s\" to an instance of %s.", value, Money.class.getSimpleName());
      throw new NumberFormatException(msg);
    }
  }

  public Money negate() {
    return this.multiply(new BigDecimal(-1));
  }
}
