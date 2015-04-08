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

package org.tiogasolutions.dev.common;

import java.text.*;

public class Formats {

  public static java.text.DecimalFormat Money() {
    return new java.text.DecimalFormat("###,###,##0.00");
  }
  public static String money(Object value) {
    return (value == null) ? null : Money().format(value);
  }

  public static java.text.DecimalFormat Percent() {
    return new java.text.DecimalFormat("###,###,##0%");
  }
  public static String percent(Object value) {
    return (value == null) ? null : Percent().format(value);
  }

  public static java.text.DecimalFormat PercentTwo() {
    return new java.text.DecimalFormat("###,###,##0.00%");
  }
  public static String percentTwo(Object value) {
    return (value == null) ? null : PercentTwo().format(value);
  }

  public static SimpleDateFormat DefaultDate() {
    return new SimpleDateFormat("M-d-yyyy");
  }
  public static String defaultDate(Object value) {
    return (value == null) ? null : DefaultDate().format(value);
  }

  public static SimpleDateFormat DefaultTime() {
    return new SimpleDateFormat("h:mm a");
  }
  public static String defaultTime(Object value) {
    return (value == null) ? null : DefaultTime().format(value);
  }

  public static SimpleDateFormat DefaultStamp() {
    return new SimpleDateFormat("M-d-yyyy 'at' h:mm a");
  }
  public static String defaultStamp(Object value) {
    return (value == null) ? null : DefaultStamp().format(value);
  }

  public static SimpleDateFormat MilitaryTime() {
    return new SimpleDateFormat("HH:mm");
  }
  public static String militaryTime(Object value) {
    return (value == null) ? null : MilitaryTime().format(value);
  }

  public static SimpleDateFormat FourYearDate() {
    return new SimpleDateFormat("MM-dd-yyyy");
  }
  public static String fourYearDate(Object value) {
    return (value == null) ? null : FourYearDate().format(value);
  }

  public static SimpleDateFormat LongDate() {
    return new SimpleDateFormat("MMMM d, yyyy");
  }
  public static String longDate(Object value) {
    return (value == null) ? null : LongDate().format(value);
  }

  public static SimpleDateFormat MediumDate() {
    return new SimpleDateFormat("MMM d, yyyy");
  }
  public static String mediumDate(Object value) {
    return (value == null) ? null : MediumDate().format(value);
    }

  public static SimpleDateFormat ShortDate() {
    return new SimpleDateFormat("MM-dd-yy");
  }
  public static String shortDate(Object value) {
    return (value == null) ? null : ShortDate().format(value);
  }

  public static SimpleDateFormat DBDate() {
    return new SimpleDateFormat("yyyy-MM-dd");
  }
  public static String dbDate(Object value) {
    return (value == null) ? null : DBDate().format(value);
  }

  public static SimpleDateFormat DBTime() {
    return new SimpleDateFormat("HH:mm:ss:SSSS");
  }
  public static String dbTime(Object value) {
    return (value == null) ? null : DBTime().format(value);
  }

  public static SimpleDateFormat DBStamp() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
  }
  public static String dbStamp(Object value) {
    return (value == null) ? null : DBStamp().format(value);
  }

  public static SimpleDateFormat CompactDate() {
    return new SimpleDateFormat("yyyyMMdd");
  }
  public static String compactDate(Object value) {
    return (value == null) ? null : CompactDate().format(value);
  }

  private Formats() {
  }
}
