package org.tiogasolutions.dev.common;

import java.time.*;
import java.util.*;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("Duplicates")
public class DateUtilsFactory {

  private final ZoneId zoneId;

  public DateUtilsFactory(ZoneId zoneId) {
    this.zoneId = (zoneId != null) ? zoneId : ZoneId.systemDefault();
  }

  public ZoneId getZoneId() {
    return zoneId;
  }

  public LocalTime currentLocalTime() {
    return LocalTime.now(zoneId);
  }
  public LocalDate currentLocalDate() {
    return LocalDate.now(zoneId);
  }
  public LocalDateTime currentLocalDateTime() {
    return LocalDateTime.now(zoneId);
  }
  public ZonedDateTime currentZonedDateTime() {
    return ZonedDateTime.now(zoneId);
  }

  public LocalDate toLocalDate(Object date) {
    if (date == null) {
      return null;

    } else if (date instanceof LocalDate) {
      return (LocalDate)date;

    } else if (date instanceof String) {
      return toLocalDate((String)date);

    } else if (date instanceof Long) {
      return toLocalDate((long)date);

    } else if (date instanceof java.util.Date) {
      return toLocalDate((java.util.Date)date);

    } else if (date instanceof Calendar) {
      return toLocalDate((Calendar)date);

    } else if (date instanceof LocalDateTime) {
      return toLocalDate((LocalDateTime)date);

    } else if (date instanceof ZonedDateTime) {
      return toLocalDate((ZonedDateTime)date);

    } else {
      String msg = String.format("Cannot convert objects of type %s to LocalDate.", date.getClass().getSimpleName());
      throw new IllegalArgumentException(msg);
    }
  }
  public LocalDate toLocalDate(String date) {
    try { return LocalDate.parse(date); }
    catch (Exception ignored) {}

    if (StringUtils.isBlank(date)) {
      return null;
    }

    date = sanitize(date);

    if (date.contains("T")) {
      date = date.substring(0, date.indexOf('T'));
      return LocalDate.parse(date);

    } else {
      return LocalDate.parse(date);
    }
  }
  public LocalDate toLocalDate(long date) {
    if (date == 0) {
      return null;

    } else if (date > 0) {
      Instant instant = Instant.ofEpochMilli(date);
      return LocalDate.from(instant.atZone(zoneId));

    } else {
      throw new IllegalArgumentException("The value \"date\" must be greater than or equal to zero.");
    }
  }
  public LocalDate toLocalDate(java.util.Date date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }

    return LocalDateTime.ofInstant(date.toInstant(), zoneId).toLocalDate();
  }
  public LocalDate toLocalDate(Calendar date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    return LocalDate.of(
      date.get(Calendar.YEAR),
      date.get(Calendar.MONTH) + 1,
      date.get(Calendar.DAY_OF_MONTH));
  }
  public LocalDate toLocalDate(LocalDateTime date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    return date.toLocalDate();
  }
  public LocalDate toLocalDate(ZonedDateTime date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    return date.toLocalDate();
  }
  public LocalDate toLocalDate(int year, int monthOfYear, int dayOfMonth) {
    return LocalDate.of(year, monthOfYear, dayOfMonth);
  }



  public LocalTime toLocalTime(Object date) {
    if (date == null) {
      return null;

    } else if (date instanceof LocalTime) {
      return (LocalTime)date;

    } else if (date instanceof String) {
      return toLocalTime((String)date);

    } else if (date instanceof Long) {
      return toLocalTime((long)date);

    } else if (date instanceof java.util.Date) {
      return toLocalTime((java.util.Date)date);

    } else if (date instanceof Calendar) {
      return toLocalTime((Calendar)date);

    } else if (date instanceof LocalDateTime) {
      return toLocalTime((LocalDateTime)date);

    } else if (date instanceof ZonedDateTime) {
      return toLocalTime((ZonedDateTime)date);

    } else {
      String msg = String.format("Cannot convert objects of type %s to LocalTime.", date.getClass().getSimpleName());
      throw new IllegalArgumentException(msg);
    }
  }
  public LocalTime toLocalTime(String date) {
    try { return LocalTime.parse(date); }
    catch (Exception ignored) {}

    if (date == null) {
      return null;
    }

    date = sanitize(date);

    if (date.contains("-") == false) {
      return LocalTime.parse(date);

    } else if (date.contains("T")) {
      LocalDateTime ldt = toLocalDateTime(date);
      return ldt.toLocalTime();

    } else {
      return LocalTime.MIDNIGHT;
    }
  }
  public LocalTime toLocalTime(long date) {
    if (date == 0) {
      return null;

    } else if (date > 0) {
      Instant instant = Instant.ofEpochMilli(date);
      return LocalTime.from(instant.atZone(zoneId));

    } else {
      throw new IllegalArgumentException("The value \"date\" must be greater than or equal to zero.");
    }
  }
  public LocalTime toLocalTime(java.util.Date date) {
    if (date == null) {
      return null;
    }
    return toLocalTime(date.getTime());
  }
  public LocalTime toLocalTime(Calendar date) {
    if (date == null) {
      return null;
    }
    return LocalTime.of(
      date.get(Calendar.HOUR_OF_DAY),
      date.get(Calendar.MINUTE),
      date.get(Calendar.SECOND),
      date.get(Calendar.MILLISECOND) * 1000 * 1000);
  }
  public LocalTime toLocalTime(LocalDateTime date) {
    if (date == null) {
      return null;
    }
    return date.toLocalTime();
  }
  public LocalTime toLocalTime(ZonedDateTime date) {
    if (date == null) {
      return null;
    }
    return date.toLocalTime();
  }
  public LocalTime toLocalTimeWithMills(int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
    return LocalTime.of(hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond * 1000 * 1000);
  }
  public LocalTime toLocalTimeWithNanos(int hourOfDay, int minuteOfHour, int secondOfMinute, int nanoOfSecond) {
    return LocalTime.of(hourOfDay, minuteOfHour, secondOfMinute, nanoOfSecond);
  }



  public LocalDateTime toLocalDateTime(Object date) {
    if (date == null) {
      return null;

    } else if (date instanceof LocalDateTime) {
      return (LocalDateTime)date;

    } else if (date instanceof String) {
      return toLocalDateTime((String)date);

    } else if (date instanceof Long) {
      return toLocalDateTime((long)date);

    } else if (date instanceof java.util.Date) {
      return toLocalDateTime((java.util.Date)date);

    } else if (date instanceof Calendar) {
      return toLocalDateTime((Calendar)date);

    } else if (date instanceof LocalDate) {
      return toLocalDateTime((LocalDate)date);

    } else if (date instanceof ZonedDateTime) {
      return toLocalDateTime((ZonedDateTime)date);

    } else {
      String msg = String.format("Cannot convert objects of type %s to LocalDateTime.", date.getClass().getSimpleName());
      throw new IllegalArgumentException(msg);
    }
  }

  public LocalDateTime toLocalDateTime(String date) {
    try { return LocalDateTime.parse(date); }
    catch (Exception ignored) {}

    if (date == null) {
      return null;
    }

    date = sanitize(date);

    if (date.contains("T") == false) {
      date = date + "T" + LocalTime.MIDNIGHT;
    }

    int pos = date.indexOf("T");
    int zonePos = date.indexOf("-", pos);

    if (zonePos >= 0 || date.toUpperCase().endsWith("Z")) {
      return toZonedDateTime(date).toLocalDateTime();

    } else {
      return LocalDateTime.parse(date);
    }
  }

  public LocalDateTime toLocalDateTime(long date) {
    if (date == 0) {
      return null;

    } else if (date > 0) {
      Instant instant = Instant.ofEpochMilli(date);
      return LocalDateTime.from(instant.atZone(zoneId));

    } else {
      throw new IllegalArgumentException("The value \"date\" must be greater than or equal to zero.");
    }
  }
  public LocalDateTime toLocalDateTime(java.util.Date date) {
    if (date == null) {
      return null;
    }
    return LocalDateTime.ofInstant(date.toInstant(), zoneId);
  }
  public LocalDateTime toLocalDateTime(Calendar date) {
    if (date == null) {
      return null;
    }
    return LocalDateTime.of(
      date.get(Calendar.YEAR),
      date.get(Calendar.MONTH) + 1,
      date.get(Calendar.DAY_OF_MONTH),
      date.get(Calendar.HOUR_OF_DAY),
      date.get(Calendar.MINUTE),
      date.get(Calendar.SECOND),
      date.get(Calendar.MILLISECOND) * 1000 * 1000);
  }
  public LocalDateTime toLocalDateTime(LocalDate date) {
    if (date == null) {
      return null;
    }
    return LocalDateTime.of(date, LocalTime.MIDNIGHT);
  }
  public LocalDateTime toLocalDateTime(ZonedDateTime date) {
    if (date == null) {
      return null;
    }
    return date.toLocalDateTime();
  }
  public LocalDateTime toLocalDateTimeWithMills(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
    return LocalDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond * 1000 * 1000);
  }
  public LocalDateTime toLocalDateTimeWithNanos(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int nanoOfSecond) {
    return LocalDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, nanoOfSecond);
  }


  public ZonedDateTime toZonedDateTime(Object date) {

    if (date == null) {
      return null;

    } else if (date instanceof ZonedDateTime) {
      return (ZonedDateTime)date;

    } else if (date instanceof String) {
      return toZonedDateTime((String) date);

    } else if (date instanceof Long) {
      return toZonedDateTime((long) date);

    } else if (date instanceof LocalDate) {
      return toZonedDateTime((LocalDate) date);

    } else if (date instanceof LocalDateTime) {
      return toZonedDateTime((LocalDateTime) date);

    } else if (date instanceof java.util.Date) {
      return toZonedDateTime((java.util.Date) date);

    } else if (date instanceof Calendar) {
      return toZonedDateTime((Calendar) date);

    } else {
      String msg = String.format("Cannot convert objects of type %s to DateTime.", date.getClass().getSimpleName());
      throw new IllegalArgumentException(msg);
    }
  }
  @Deprecated
  public ZonedDateTime toDateTime(String date) {
    return toZonedDateTime(date);
  }

  public ZonedDateTime toZonedDateTime(String date) {
    try { return ZonedDateTime.parse(date); }
    catch (Exception ignored) {}

    if (date == null) {
      return null;
    }

    date = sanitize(date);
    int posT = date.indexOf("T");

    if (posT >= 0) {
      int posZ = date.indexOf("-", posT);

      if (posZ >= 0) {
        String zone = date.substring(posZ);
        date = date.substring(0, posZ);
        date += zone;
        return ZonedDateTime.parse(date);

      } else if (date.toUpperCase().endsWith("Z")) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, getFormatter(date));
        ZoneId zoneId = ZoneId.of("Z");
        ZonedDateTime value = ZonedDateTime.of(localDateTime, zoneId);
        return value;

      } else {
        LocalDateTime localDateTime = LocalDateTime.parse(date, getFormatter(date));
        return ZonedDateTime.of(localDateTime, zoneId);
      }

    } else {
      LocalDate localDate = LocalDate.parse(date, getFormatter(date));
      return ZonedDateTime.of(localDate, LocalTime.MIDNIGHT, zoneId);

    }
  }

  private DateTimeFormatter getFormatter(String date) {

    int posT = date.indexOf("T");

    if (posT >= 0) {
      if (date.indexOf("-", posT) >= 0) {
        return DateTimeFormatter.ISO_ZONED_DATE_TIME;
      } else {
        return DateTimeFormatter.ISO_DATE_TIME;
      }
    } else if (date.contains(" ") || date.contains("T")) {
      return DateTimeFormatter.ISO_DATE_TIME;

    } else if (date.contains(":")) {
      return DateTimeFormatter.ISO_LOCAL_TIME;

    } else {
      return DateTimeFormatter.ISO_LOCAL_DATE;
    }
  }

  @Deprecated
  public ZonedDateTime toDateTime(long date) {
    return toZonedDateTime(date);
  }
  public ZonedDateTime toZonedDateTime(long date) {
    if (date == 0) {
      return null;

    } else if (date > 0) {
      Instant instant = Instant.ofEpochMilli(date);
      return ZonedDateTime.from(instant.atZone(zoneId));

    } else {
      throw new IllegalArgumentException("The value \"date\" must be greater than or equal to zero.");
    }
  }

  @Deprecated
  public ZonedDateTime toDateTime(LocalDate date) {
    return toZonedDateTime(date);
  }
  public ZonedDateTime toZonedDateTime(LocalDate date) {
    if (date == null) {
      return null;
    }
    return ZonedDateTime.of(date, LocalTime.MIDNIGHT, zoneId);
  }

  @Deprecated
  public ZonedDateTime toDateTime(LocalDateTime date) {
    return toZonedDateTime(date);
  }
  public ZonedDateTime toZonedDateTime(LocalDateTime date) {
    if (date == null) {
      return null;
    }
    return ZonedDateTime.of(date, zoneId);
  }

  @Deprecated
  public ZonedDateTime toDateTime(java.util.Date date) {
    return toZonedDateTime(date);
  }
  public ZonedDateTime toZonedDateTime(java.util.Date date) {
    if (date == null) {
      return null;
    }
    return ZonedDateTime.ofInstant(date.toInstant(), zoneId);
  }

  @Deprecated
  public ZonedDateTime toDateTime(Calendar date) {
    return toZonedDateTime(date);
  }
  public ZonedDateTime toZonedDateTime(Calendar date) {
    if (date == null) {
      return null;
    }
    return ZonedDateTime.of(
        date.get(Calendar.YEAR),
        date.get(Calendar.MONTH) + 1,
        date.get(Calendar.DAY_OF_MONTH),
        date.get(Calendar.HOUR_OF_DAY),
        date.get(Calendar.MINUTE),
        date.get(Calendar.SECOND),
        date.get(Calendar.MILLISECOND) * 1000 * 1000,
      zoneId);
  }

  public ZonedDateTime toZonedDateTimeWithMills(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
    return ZonedDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond*1000*1000, zoneId);
  }
  public ZonedDateTime toZonedDateTimeWithNanos(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, int nanoOfSecond) {
    return ZonedDateTime.of(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, nanoOfSecond, zoneId);
  }

  public java.util.Date toUtilDate(long date) {
    return (date <= 0) ? null : new java.util.Date(date);
  }

  public Date toUtilDate(String date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    ZonedDateTime zdt = toZonedDateTime(date);
    return java.util.Date.from(zdt.toInstant());
  }

  public Date toUtilDate(Date date) {
    return date;
  }

  public Date toUtilDate(Calendar date) {
    return (date == null) ? null : date.getTime();
  }

  public Date toUtilDate(LocalDate date) {
    if (date == null) {
      return null;
    }
    Instant instant = toZonedDateTime(date).toInstant();
    return java.util.Date.from(instant);
  }

  public Date toUtilDate(LocalDateTime date) {
    if (date == null) {
      return null;
    }
    Instant instant = toZonedDateTime(date).toInstant();
    return java.util.Date.from(instant);
  }

  public Date toUtilDate(ZonedDateTime date) {
    if (date == null) {
      return null;
    }
    Instant instant = date.toInstant();
    return java.util.Date.from(instant);
  }

  public Date toUtilDate(int year, int month, int day) {
    return toUtilDate(ZonedDateTime.of(year, month, day, 0, 0, 0, 0, zoneId));
  }

  private static String sanitize(String date) {

    date = date.replace(' ', 'T');

    int posT = date.indexOf('T');
    if (posT < 0) {
      // No time component.
      date = date.replace('/', '-');
      return date;
    }

    int posZ = date.indexOf('-', posT);
    if (posZ < 0) {
      // No zone component.
      date = date.replace('/', '-');
      return date;
    }

    String zone = date.substring(posZ);
    date = date.substring(0, posZ);
    date = date.replace('/', '-');

    return date + zone;
  }

  public String toAbbreviatedMonth(Month month) {
    return (month == null) ? null : toAbbreviatedMonth(month.getValue());
  }

  public String toAbbreviatedMonth(YearMonth yearMonth) {
    return (yearMonth == null) ? null : toAbbreviatedMonth(yearMonth.getMonthValue());
  }

  public String toAbbreviatedMonth(int month) {
    switch(month) {
      case 1: return "Jan";
      case 2: return "Feb";
      case 3: return "Mar";
      case 4: return "Apr";
      case 5: return "May";
      case 6: return "Jun";
      case 7: return "Jul";
      case 8: return "Aug";
      case 9: return "Sep";
      case 10: return "Oct";
      case 11: return "Nov";
      case 12: return "Dec";
      default:
        String msg = String.format("The value \"%s\" does not represented a valid month.", month);
        throw new IllegalArgumentException(msg);
    }
  }

  public String toMonthName(Month month) {
    return (month == null) ? null : toMonthName(month.getValue());
  }

  public String toMonthName(YearMonth yearMonth) {
    return (yearMonth == null) ? null : toMonthName(yearMonth.getMonthValue());
  }

  public String toMonthName(int month) {
    switch(month) {
      case 1: return "January";
      case 2: return "February";
      case 3: return "March";
      case 4: return "April";
      case 5: return "May";
      case 6: return "June";
      case 7: return "July";
      case 8: return "August";
      case 9: return "September";
      case 10: return "October";
      case 11: return "November";
      case 12: return "December";
      default:
        String msg = String.format("The value \"%s\" does not represented a valid month.", month);
        throw new IllegalArgumentException(msg);
    }
  }

  public YearMonth toYearMonth(LocalDate date) {
    return (date == null) ? null : YearMonth.of(date.getYear(), date.getMonthValue());
  }
  public YearMonth toYearMonth(LocalDateTime date) {
    return (date == null) ? null : YearMonth.of(date.getYear(), date.getMonthValue());
  }
  public YearMonth toYearMonth(ZonedDateTime date) {
    return (date == null) ? null : YearMonth.of(date.getYear(), date.getMonthValue());
  }

  public LocalDate toLastDate(LocalDate date) {
    YearMonth yearMonth = toYearMonth(date);
    int lastDayOfMonth = yearMonth.lengthOfMonth();
    return (date == null) ? null : LocalDate.of(date.getYear(), date.getMonthValue(), lastDayOfMonth);
  }
  public LocalDateTime toLastDate(LocalDateTime date) {
    YearMonth yearMonth = toYearMonth(date);
    int lastDayOfMonth = yearMonth.lengthOfMonth();
    return (date == null) ? null : LocalDateTime.of(date.getYear(), date.getMonthValue(), lastDayOfMonth,
                                                    date.getHour(), date.getMinute(), date.getSecond(), date.getNano());
  }
  public ZonedDateTime toLastDate(ZonedDateTime date) {
    YearMonth yearMonth = toYearMonth(date);
    int lastDayOfMonth = yearMonth.lengthOfMonth();
    return (date == null) ? null : ZonedDateTime.of(date.getYear(), date.getMonthValue(), lastDayOfMonth,
                                                    date.getHour(), date.getMinute(), date.getSecond(), date.getNano(), date.getZone());
  }

  public LocalDate toFirstDate(LocalDate date) {
    return (date == null) ? null : LocalDate.of(date.getYear(), date.getMonthValue(), 1);
  }
  public LocalDateTime toFirstDate(LocalDateTime date) {
    return (date == null) ? null : LocalDateTime.of(date.getYear(), date.getMonthValue(), 1,
                                                    date.getHour(), date.getMinute(), date.getSecond(), date.getNano());
  }
  public ZonedDateTime toFirstDate(ZonedDateTime date) {
    return (date == null) ? null : ZonedDateTime.of(date.getYear(), date.getMonthValue(), 1,
                                                    date.getHour(), date.getMinute(), date.getSecond(), date.getNano(), date.getZone());
  }
}
