package org.tiogasolutions.dev.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.*;
import java.util.*;

public class DateUtils {

  private static final Object LOCK = new Object();

  public static final ZoneId PDT = ZoneId.of("America/Los_Angeles");

  private static DateUtilsFactory factory = new DateUtilsFactory(null);
  
  private DateUtils() {
  }

  public static DateUtilsFactory newFactory(ZoneId timeZone) {
    return new DateUtilsFactory(timeZone);
  }

  @Deprecated
  public static LocalTime currentTime() {
    return factory.currentLocalTime();
  }
  public static LocalTime currentLocalTime() {
    return factory.currentLocalTime();
  }

  @Deprecated
  public static LocalDateTime currentDateTime() {
    return factory.currentLocalDateTime();
  }
  public static LocalDateTime currentLocalDateTime() {
    return factory.currentLocalDateTime();
  }

  @Deprecated
  public static LocalDate currentDate() {
    return factory.currentLocalDate();
  }
  public static LocalDate currentLocalDate() {
    return factory.currentLocalDate();
  }

  public static ZonedDateTime currentZonedDateTime() {
    return factory.currentZonedDateTime();
  }



  public static LocalDate toLocalDate(Object date) {
    return factory.toLocalDate(date);
  }
  public static LocalDate toLocalDate(String date) {
    return factory.toLocalDate(date);
  }
  public static LocalDate toLocalDate(long date) {
    return factory.toLocalDate(date);
  }
  public static LocalDate toLocalDate(Date date) {
    return factory.toLocalDate(date);
  }
  public static LocalDate toLocalDate(Calendar date) {
    return factory.toLocalDate(date);
  }
  public static LocalDate toLocalDate(LocalDateTime date) {
    return factory.toLocalDate(date);
  }
  public static LocalDate toLocalDate(ZonedDateTime date) {
    return factory.toLocalDate(date);
  }
  public static LocalDate toLocalDate(int year, int month, int day) {
    return factory.toLocalDate(year, month, day);
  }



  public static java.util.Date toUtilDate(String date) {
    return factory.toUtilDate(date);
  }
  public static java.util.Date toUtilDate(long date) {
    return factory.toUtilDate(date);
  }
  public static java.util.Date toUtilDate(Date date) {
    return factory.toUtilDate(date);
  }
//  public static java.util.Date toUtilDate(Calendar date) {
//    return factory.toUtilDate(date);
//  }
  public static java.util.Date toUtilDate(LocalDate date) {
    return factory.toUtilDate(date);
  }
  public static java.util.Date toUtilDate(LocalDateTime date) {
    return factory.toUtilDate(date);
  }
//  public static java.util.Date toUtilDate(ZonedDateTime date) {
//    return factory.toUtilDate(date);
//  }
  public static java.util.Date toUtilDate(int year, int month, int day) {
    return factory.toUtilDate(year, month, day);
  }



  public static LocalTime toLocalTime(Object date) {
    return factory.toLocalTime(date);
  }
  public static LocalTime toLocalTime(String date) {
    return factory.toLocalTime(date);
  }
  public static LocalTime toLocalTime(long date) {
    return factory.toLocalTime(date);
  }
  public static LocalTime toLocalTime(Date date) {
    return factory.toLocalTime(date);
  }
  public static LocalTime toLocalTime(Calendar date) {
    return factory.toLocalTime(date);
  }
  public static LocalTime toLocalTime(LocalDateTime date) {
    return factory.toLocalTime(date);
  }
  public static LocalTime toLocalTime(ZonedDateTime date) {
    return factory.toLocalTime(date);
  }
  public static LocalTime toLocalTime(int hourOfDay, int minuteOfHour) {
    return factory.toLocalTimeWithMills(hourOfDay, minuteOfHour, 0, 0);
  }
  public static LocalTime toLocalTime(int hourOfDay, int minuteOfHour, int secondOfMinute) {
    return factory.toLocalTimeWithMills(hourOfDay, minuteOfHour, secondOfMinute, 0);
  }
  public static LocalTime toLocalTimeWithMills(int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
    return factory.toLocalTimeWithMills(hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
  }
  public static LocalTime toLocalTimeWithNanos(int hourOfDay, int minuteOfHour, int secondOfMinute, int nanoOfSecond) {
    return factory.toLocalTimeWithNanos(hourOfDay, minuteOfHour, secondOfMinute, nanoOfSecond);
  }



  public static LocalDateTime toLocalDateTime(Object date) {
    return factory.toLocalDateTime(date);
  }
  public static LocalDateTime toLocalDateTime(String date) {
    return factory.toLocalDateTime(date);
  }
  public static LocalDateTime toLocalDateTime(long date) {
    return factory.toLocalDateTime(date);
  }
  public static LocalDateTime toLocalDateTime(Date date) {
    return factory.toLocalDateTime(date);
  }
  public static LocalDateTime toLocalDateTime(Calendar date) {
    return factory.toLocalDateTime(date);
  }
  public static LocalDateTime toLocalDateTime(LocalDate date) {
    return factory.toLocalDateTime(date);
  }
  public static LocalDateTime toLocalDateTime(ZonedDateTime date) {
    return factory.toLocalDateTime(date);
  }
  public static LocalDateTime toLocalDateTime(int year, int monthOfYear, int dayOfMonth) {
    return factory.toLocalDateTimeWithMills(year, monthOfYear, dayOfMonth, 0, 0, 0, 0);
  }
  public static LocalDateTime toLocalDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour) {
    return factory.toLocalDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0);
  }
  public static LocalDateTime toLocalDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute) {
    return factory.toLocalDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, 0);
  }
  public static LocalDateTime toLocalDateTimeWithMills(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
    return factory.toLocalDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
  }
  public static LocalDateTime toLocalDateTimeWithNanos(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int nanosOfSecond) {
    return factory.toLocalDateTimeWithNanos(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, nanosOfSecond);
  }



  @Deprecated
  public static ZonedDateTime toDateTime(Object date) {
    return factory.toZonedDateTime(date);
  }
  public static ZonedDateTime toZonedDateTime(Object date) {
    return factory.toZonedDateTime(date);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(String date) {
    return factory.toZonedDateTime(date);
  }
  public static ZonedDateTime toZonedDateTime(String date) {
    return factory.toZonedDateTime(date);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(long date) {
    return factory.toZonedDateTime(date);
  }
  public static ZonedDateTime toZonedDateTime(long date) {
    return factory.toZonedDateTime(date);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(LocalDate date) {
    return factory.toZonedDateTime(date);
  }
  public static ZonedDateTime toZonedDateTime(LocalDate date) {
    return factory.toZonedDateTime(date);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(LocalDateTime date) {
    return factory.toZonedDateTime(date);
  }
  public static ZonedDateTime toZonedDateTime(LocalDateTime date) {
    return factory.toZonedDateTime(date);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(Date date) {
    return factory.toZonedDateTime(date);
  }
  public static ZonedDateTime toZonedDateTime(Date date) {
    return factory.toZonedDateTime(date);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(Calendar date) {
    return factory.toZonedDateTime(date);
  }
  public static ZonedDateTime toZonedDateTime(Calendar date) {
    return factory.toZonedDateTime(date);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(int year, int monthOfYear, int dayOfMonth) {
    return factory.toZonedDateTimeWithMills(year, monthOfYear, dayOfMonth, 0, 0, 0, 0);
  }
  public static ZonedDateTime toZonedDateTime(int year, int monthOfYear, int dayOfMonth) {
    return factory.toZonedDateTimeWithMills(year, monthOfYear, dayOfMonth, 0, 0, 0, 0);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour) {
    return factory.toZonedDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0);
  }
  public static ZonedDateTime toZonedDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour) {
    return factory.toZonedDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 0, 0);
  }

  @Deprecated
  public static ZonedDateTime toDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute) {
    return factory.toZonedDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, 0);
  }
  public static ZonedDateTime toZonedDateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute) {
    return factory.toZonedDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, 0);
  }

  public static ZonedDateTime toZonedDateTimeWithMills(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
    return factory.toZonedDateTimeWithMills(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond);
  }
  public static ZonedDateTime toZonedDateTimeWithNanos(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int nanosOfSecond) {
    return factory.toZonedDateTimeWithNanos(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, nanosOfSecond);
  }

  public static boolean datesEqual(Date dateA, Date dateB) {
    return EqualsUtils.datesEqual(dateA, dateB);
  }
  public static boolean datesNotEqual(Date dateA, Date dateB) {
    return EqualsUtils.datesNotEqual(dateA, dateB);
  }

  public static String toAbbreviatedMonth(Month month) {
    return factory.toAbbreviatedMonth(month);
  }
  public static String toAbbreviatedMonth(YearMonth yearMonth) {
    return factory.toAbbreviatedMonth(yearMonth);
  }
  public static String toAbbreviatedMonth(int month) {
    return factory.toAbbreviatedMonth(month);
  }

  public static String toMonthName(Month month) {
    return factory.toMonthName(month);
  }
  public static String toMonthName(YearMonth yearMonth) {
    return factory.toMonthName(yearMonth);
  }
  public static String toMonthName(int month) {
    return factory.toMonthName(month);
  }

  public static YearMonth toYearMonth(LocalDate date) {
    return factory.toYearMonth(date);
  }
  public static YearMonth toYearMonth(LocalDateTime date) {
    return factory.toYearMonth(date);
  }
  public static YearMonth toYearMonth(ZonedDateTime date) {
    return factory.toYearMonth(date);
  }

  public static LocalDate toLastDate(LocalDate date) {
    return factory.toLastDate(date);
  }
  public static LocalDateTime toLastDate(LocalDateTime date) {
    return factory.toLastDate(date);
  }
  public static ZonedDateTime toLastDate(ZonedDateTime date) {
    return factory.toLastDate(date);
  }

  public static LocalDate toFirstDate(LocalDate date) {
    return factory.toFirstDate(date);
  }
  public static LocalDateTime toFirstDate(LocalDateTime date) {
    return factory.toFirstDate(date);
  }
  public static ZonedDateTime toFirstDate(ZonedDateTime date) {
    return factory.toFirstDate(date);
  }

//  public static java.util.Date setTimeZone(java.util.Date date, ZoneId zoneId) {
//    TimeZone timeZone = TimeZone.getTimeZone(zoneId);
//    return setTimeZone(date, timeZone);
//  }
//  public static java.util.Date setTimeZone(java.util.Date date, TimeZone timeZone) {
//    synchronized (LOCK) {
//      TimeZone originalTimeZone = TimeZone.getDefault();
//      try {
//        TimeZone.setDefault(timeZone);
//
//        date.setTime(date.getTime());
//
//        Method method = java.util.Date.class.getDeclaredMethod("normalize");
//        method.setAccessible(true);
//        method.invoke(date);
//
//        return date;
//
//      } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//        throw new RuntimeException("Exception normalizing java.util.Date", e);
//      } finally {
//        TimeZone.setDefault(originalTimeZone);
//      }
//    }
//  }
}
