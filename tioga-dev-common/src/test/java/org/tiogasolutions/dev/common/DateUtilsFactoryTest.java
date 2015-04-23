package org.tiogasolutions.dev.common;

import java.time.*;
import java.util.*;
import org.testng.annotations.*;

import static java.lang.String.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

@Test(dataProvider="timeZones")
public class DateUtilsFactoryTest {

  @DataProvider(name="timeZones")
  public static Iterator<Object[]> getTimeZones() throws Exception {

    List<Object[]> list = new ArrayList<>();

    list.add(new Object[]{new Config("GMT")});
    list.add(new Object[]{new Config("America/Los_Angeles")});
    list.add(new Object[]{new Config("America/New_York")});

    return list.iterator();
  }

  public void currentLocalTime(Config config) {
    assertNotNull(config.factory.currentLocalTime());
  }
  public void currentLocalDateTime(Config config) {
    assertNotNull(config.factory.currentLocalDateTime());
  }
  public void currentLocalDate(Config config) {
    assertNotNull(config.factory.currentLocalDate());
  }
  public void currentZonedDateTime(Config config) {
    ZonedDateTime value = config.factory.currentZonedDateTime();
    assertNotNull(value);
    assertEquals(value.getZone(), config.zoneId);
  }

  public void toLocalDate_Object(Config config) {
    assertNull(config.factory.toLocalDate((Object) null));

    validate(config, config.factory.toLocalDate(config.localDateTime.toLocalDate()));

    validate(config, config.factory.toLocalDate((Object)config.stringDateTimeHyT));
    validate(config, config.factory.toLocalDate((Object)config.stringDateTimeFsT));

    validate(config, config.factory.toLocalDate((Object)config.stringDateTimeHyZ));
    validate(config, config.factory.toLocalDate((Object)config.stringDateTimeFsZ));

    validate(config, config.factory.toLocalDate((Object)config.utcDateTime));
    validate(config, config.factory.toLocalDate((Object)new java.util.Date(config.utcDateTime)));
    validate(config, config.factory.toLocalDate((Object)config.calendar));
    validate(config, config.factory.toLocalDate((Object) config.localDateTime));
    validate(config, config.factory.toLocalDate((Object) config.zonedDateTime));

    try {
      config.factory.toLocalDate(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to LocalDate.");
    }
  }

  public void toLocalDate_String(Config config) {

    validate(config, config.factory.toLocalDate(config.stringDateMidnightHy));
    validate(config, config.factory.toLocalDate(config.stringDateMidnightFs));

    validate(config, config.factory.toLocalDate(config.stringDateTimeHyT));
    validate(config, config.factory.toLocalDate(config.stringDateTimeFsT));

    validate(config, config.factory.toLocalDate(config.stringDateTimeHyZ));
    validate(config, config.factory.toLocalDate(config.stringDateTimeFsZ));

    validate(config, config.factory.toLocalDate(config.stringDateTimeHyS));
    validate(config, config.factory.toLocalDate(config.stringDateTimeFsS));

    assertNull(config.factory.toLocalDate((String) null));
  }
  public void toLocalDate_Long(Config config) {

    validate(config, config.factory.toLocalDate(config.utcDateTime));
    validate(config, config.factory.toLocalDate(config.utcDateMidnight));

    assertNull(config.factory.toLocalDate(0));

    try {
      config.factory.toLocalDate(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toLocalDate_UtilDate(Config config) {
    LocalDate localDate = config.factory.toLocalDate(config.utilDateTime);
    validate(config, localDate);
  }
  public void toLocalDate_UtilDate_Midnight(Config config) {
    LocalDate localDate = config.factory.toLocalDate(config.utilDateMidnight);
    validate(config, localDate);
  }
  public void toLocalDate_UtilDate_Null(Config config) {
    LocalDate localDate = config.factory.toLocalDate((java.util.Date) null);
    assertNull(localDate);
  }
  public void toLocalDate_GregorianCalendar(Config config) {
    validate(config, config.factory.toLocalDate(config.calendar));
    validate(config, config.factory.toLocalDate(config.calendarMidnight));

    assertNull(config.factory.toLocalDate((Calendar) null));
  }
  public void toLocalDate_LocalDateTime(Config config) {

    validate(config, config.factory.toLocalDate(config.localDateTime));

    assertNull(config.factory.toLocalDate((LocalDateTime) null));
  }
  public void toLocalDate_DateTime(Config config) {

    LocalDate localDate = config.factory.toLocalDate(config.zonedDateTime);
    validate(config, localDate);

    assertEquals(config.factory.toLocalDate((ZonedDateTime) null), null);
  }

  public void
  toLocalDate_YMD(Config config) {
    validate(config, config.factory.toLocalDate(2014, 6, 17));
  }

  private void validate(Config config, LocalDate value) {
    assertNotNull(value);
    assertEquals(value.getYear(), 2014, format("Year (%s)", config.zoneId));
    assertEquals(value.getMonthValue(), 6, format("Month of Year (%s)", value));
    assertEquals(value.getDayOfMonth(), 17, format("Day of Month (%s)", value));
  }



  public void toLocalTime_Object(Config config) {
    assertEquals(config.factory.toLocalTime((Object) null), null);

    validate(config, config.factory.toLocalTime(        config.localTime), false);
    validate(config, config.factory.toLocalTime((Object)config.stringTime), false);
    validate(config, config.factory.toLocalTime((Object)config.utcDateTime), false);
    validate(config, config.factory.toLocalTime((Object) new java.util.Date(config.utcDateTime)), false);
    validate(config, config.factory.toLocalTime((Object) config.calendar), false);
    validate(config, config.factory.toLocalTime((Object) config.localDateTime), false);
    validate(config, config.factory.toLocalTime((Object) config.zonedDateTime), false);

    try {
      config.factory.toLocalTime(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to LocalTime.");
    }
  }
  public void toLocalTime_String(Config config) {

    validate(config, config.factory.toLocalTime(config.stringTime), false);

    validate(config, config.factory.toLocalTime(config.stringDateMidnightHy), true);
    validate(config, config.factory.toLocalTime(config.stringDateMidnightFs), true);

    validate(config, config.factory.toLocalTime(config.stringDateTimeHyT), false);
    validate(config, config.factory.toLocalTime(config.stringDateTimeFsT), false);

    validate(config, config.factory.toLocalTime(config.stringDateTimeHyZ), false);
    validate(config, config.factory.toLocalTime(config.stringDateTimeFsZ), false);

    validate(config, config.factory.toLocalTime(config.stringDateTimeHyS), false);
    validate(config, config.factory.toLocalTime(config.stringDateTimeFsS), false);

    assertNull(config.factory.toLocalTime((String) null));
  }
  public void toLocalTime_Long(Config config) {

    validate(config, config.factory.toLocalTime(config.utcDateTime), false);
    validate(config, config.factory.toLocalTime(config.utcDateMidnight), true);

    assertEquals(config.factory.toLocalTime(0), null);

    try {
      config.factory.toLocalTime(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toLocalTime_UtilDate(Config config) {
    validate(config, config.factory.toLocalTime(config.utilDateTime), false);
    validate(config, config.factory.toLocalTime(config.utilDateMidnight), true);

    assertNull(config.factory.toLocalTime((java.util.Date) null));
  }
  public void toLocalTime_GregorianCalendar(Config config) {
    validate(config, config.factory.toLocalTime(config.calendar), false);
    validate(config, config.factory.toLocalTime(config.calendarMidnight), true);

    assertNull(config.factory.toLocalTime((Calendar) null));
  }
  public void toLocalTime_LocalDateTime(Config config) {

    validate(config, config.factory.toLocalTime(config.localDateTime), false);

    assertNull(config.factory.toLocalTime((LocalDateTime) null));
  }
  public void toLocalTime_DateTime(Config config) {

    validate(config, config.factory.toLocalTime(config.zonedDateTime), false);

    assertNull(config.factory.toLocalTime((ZonedDateTime) null));
  }
  public void toLocalTime_HMS_Nano(Config config) {
    assertEquals(config.factory.toLocalTimeWithNanos(0, 0, 0, 0), LocalTime.MIDNIGHT);
    validate(config, config.factory.toLocalTimeWithNanos(13, 15, 32, 136_000_000), false);
  }
  public void toLocalTime_HMS_Mili(Config config) {
    assertEquals(config.factory.toLocalTimeWithMills(0, 0, 0, 0), LocalTime.MIDNIGHT);
    validate(config, config.factory.toLocalTimeWithMills(13, 15, 32, 136), false);
  }
  private void validate(Config config, LocalTime value, boolean midnight) {
    assertNotNull(value);
    assertEquals(value.getHour(), (midnight ? 0 : 13), format("Hour of Day (%s)", config.zoneId));
    assertEquals(value.getMinute(), (midnight ? 0 : 15), format("Minute of Hour (%s)", config.zoneId));
    assertEquals(value.getSecond(), (midnight ? 0 : 32), format("Second of Minute (%s)", config.zoneId));
    assertEquals(value.getNano(), (midnight ? 0 : 136000000), format("Nano of Second (%s)", config.zoneId));
  }



  public void toLocalDateTime_Object(Config config) {
    assertEquals(config.factory.toLocalDateTime((Object) null), null);

    validate(config, config.factory.toLocalDateTime(        config.localDateTime), false);

    validate(config, config.factory.toLocalDateTime((Object)config.stringDateTimeHyT), false);
    validate(config, config.factory.toLocalDateTime((Object)config.stringDateTimeFsT), false);

    validate(config, config.factory.toLocalDateTime((Object)config.stringDateTimeHyZ), false);
    validate(config, config.factory.toLocalDateTime((Object)config.stringDateTimeFsZ), false);

    validate(config, config.factory.toLocalDateTime((Object) config.utcDateTime), false);
    validate(config, config.factory.toLocalDateTime((Object) new java.util.Date(config.utcDateTime)), false);
    validate(config, config.factory.toLocalDateTime((Object) config.calendar), false);
    validate(config, config.factory.toLocalDateTime((Object) config.localDate), true);
    validate(config, config.factory.toLocalDateTime((Object) config.zonedDateTime), false);

    try {
      config.factory.toLocalDateTime(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to LocalDateTime.");
    }
  }
  public void toLocalDateTime_String(Config config) {

    validate(config, config.factory.toLocalDateTime(config.stringDateMidnightHy), true);
    validate(config, config.factory.toLocalDateTime(config.stringDateMidnightFs), true);

    validate(config, config.factory.toLocalDateTime(config.stringDateTimeHyT), false);
    validate(config, config.factory.toLocalDateTime(config.stringDateTimeFsT), false);

    validate(config, config.factory.toLocalDateTime(config.stringDateTimeHyZ), false);
    validate(config, config.factory.toLocalDateTime(config.stringDateTimeFsZ), false);

    validate(config, config.factory.toLocalDateTime(config.stringDateTimeHyS), false);
    validate(config, config.factory.toLocalDateTime(config.stringDateTimeFsS), false);

    assertNull(config.factory.toLocalDateTime((String) null));
  }
  public void toLocalDateTime_Long(Config config) {

    validate(config, config.factory.toLocalDateTime(config.utcDateTime), false);
    validate(config, config.factory.toLocalDateTime(config.utcDateMidnight), true);

    assertEquals(config.factory.toLocalDateTime(0), null);

    try {
      config.factory.toLocalDateTime(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toLocalDateTime_UtilDate(Config config) {
    LocalDateTime localDateTime = config.factory.toLocalDateTime(config.utilDateTime);
    validate(config, localDateTime, false);
  }
  public void toLocalDateTime_UtilDate_Midnight(Config config) {
    LocalDateTime localDateTime = config.factory.toLocalDateTime(config.utilDateMidnight);
    validate(config, localDateTime, true);
  }
  public void toLocalDateTime_UtilDate_Null(Config config) {
    LocalDateTime localDateTime = config.factory.toLocalDateTime((java.util.Date) null);
    assertNull(localDateTime);
  }
  public void toLocalDateTime_GregorianCalendar(Config config) {
    validate(config, config.factory.toLocalDateTime(config.calendar), false);
    validate(config, config.factory.toLocalDateTime(config.calendarMidnight), true);

    assertEquals(config.factory.toLocalDateTime((Calendar) null), null);
  }
  public void toLocalDateTime_LocalDate(Config config) {

    validate(config, config.factory.toLocalDateTime(config.localDateTimeMidnight), true);

    assertNull(config.factory.toLocalDateTime((LocalDate) null));
  }
  public void toLocalDateTime_DateTime(Config config) {

    validate(config, config.factory.toLocalDateTime(config.zonedDateTime), false);

    assertEquals(config.factory.toLocalDateTime((ZonedDateTime) null), null);
  }
  public void toLocalDateTime_MDY_HMSM(Config config) {
    validate(config, config.factory.toLocalDateTimeWithMills(2014, 6, 17, 13, 15, 32, 136), false);
  }
  public void toLocalDateTime_MDY_HMSN(Config config) {
    validate(config, config.factory.toLocalDateTimeWithNanos(2014, 6, 17, 13, 15, 32, 136_000_000), false);
  }
  private void validate(Config config, LocalDateTime value, boolean midnight) {
    assertNotNull(value);
    assertEquals(value.getYear(), 2014, format("Year (%s)", config.zoneId));
    assertEquals(value.getMonthValue(), 6, format("Month of Year (%s)", config.zoneId));
    assertEquals(value.getDayOfMonth(), 17, format("Day of Month (%s)", config.zoneId));
    assertEquals(value.getHour(), (midnight ? 0 : 13), format("Hour of Day (%s)", config.zoneId));
    assertEquals(value.getMinute(), (midnight ? 0 : 15), format("Minute of Hour (%s)", config.zoneId));
    assertEquals(value.getSecond(), (midnight ? 0 : 32), format("Second of Minute (%s)", config.zoneId));
    assertEquals(value.getNano(), (midnight ? 0 : 136000000), format("Nano of Second (%s)", config.zoneId));
  }

  public void toDateTime_Object(Config config) {
    assertEquals(config.factory.toZonedDateTime((Object) null), null);

    validate(config, config.factory.toZonedDateTime(        config.zonedDateTime), false);

    validate(config, config.factory.toZonedDateTime((Object)config.stringDateTimeHyT), false);
    validate(config, config.factory.toZonedDateTime((Object) config.stringDateTimeFsT), false);

    validate(config, config.factory.toZonedDateTime((Object) config.stringDateTimeHyZ), false);
    validate(config, config.factory.toZonedDateTime((Object) config.stringDateTimeFsZ), false);

    validate(config, config.factory.toZonedDateTime((Object) config.utcDateTime), false);
    validate(config, config.factory.toZonedDateTime((Object) new java.util.Date(config.utcDateTime)), false);
    validate(config, config.factory.toZonedDateTime((Object) config.calendar), false);
    validate(config, config.factory.toZonedDateTime((Object) config.localDateTimeMidnight), true);
    validate(config, config.factory.toZonedDateTime((Object) config.localDateTime), false);

    try {
      config.factory.toZonedDateTime(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to DateTime.");
    }
  }
  public void toDateTime_String_Midnight(Config config) {
    validate(config, config.factory.toZonedDateTime(config.stringDateMidnightHy), true);
    validate(config, config.factory.toZonedDateTime(config.stringDateMidnightFs), true);
  }
  public void toDateTime_String_withT(Config config) {
    validate(config, config.factory.toZonedDateTime(config.stringDateTimeHyT), false);
    validate(config, config.factory.toZonedDateTime(config.stringDateTimeFsT), false);
  }
  public void toDateTime_String_withZ(Config config) {
    validate(config, config.factory.toZonedDateTime(config.stringDateTimeHyZ), false);
    validate(config, config.factory.toZonedDateTime(config.stringDateTimeFsZ), false);
  }
  public void toDateTime_String_withSp(Config config) {
    validate(config, config.factory.toZonedDateTime(config.stringDateTimeHyS), false);
    validate(config, config.factory.toZonedDateTime(config.stringDateTimeFsS), false);
  }
  public void toDateTime_String_null(Config config) {
    assertEquals(config.factory.toZonedDateTime((String) null), null);
  }
  public void toDateTime_Long(Config config) {

    validate(config, config.factory.toZonedDateTime(config.utcDateTime), false);
    validate(config, config.factory.toZonedDateTime(config.utcDateMidnight), true);

    assertEquals(config.factory.toZonedDateTime(0), null);

    try {
      config.factory.toZonedDateTime(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toDateTime_LocalDate(Config config) {

    validate(config, config.factory.toZonedDateTime(config.localDateTimeMidnight), true);

    assertEquals(config.factory.toZonedDateTime((LocalDate) null), null);
  }
  public void toDateTime_LocalDateTime(Config config) {

    validate(config, config.factory.toZonedDateTime(config.localDateTime), false);

    assertEquals(config.factory.toZonedDateTime((LocalDateTime) null), null);
  }
  public void toDateTime_GregorianCalendar(Config config) {
    validate(config, config.factory.toZonedDateTime(config.calendar), false);
    validate(config, config.factory.toZonedDateTime(config.calendarMidnight), true);

    assertEquals(config.factory.toZonedDateTime((Calendar) null), null);
  }
  public void toDateTime_UtilDate(Config config) {
    validate(config, config.factory.toZonedDateTime(config.utilDateTime), false);
    validate(config, config.factory.toZonedDateTime(config.utilDateMidnight), true);

    assertEquals(config.factory.toZonedDateTime((java.util.Date) null), null);
  }
  public void toDateTime_MDY_HMSM(Config config) {
    validate(config, config.factory.toZonedDateTimeWithMills(2014, 6, 17, 13, 15, 32, 136), false);
  }
  public void toDateTime_MDY_HMSN(Config config) {
    validate(config, config.factory.toZonedDateTimeWithNanos(2014, 6, 17, 13, 15, 32, 136_000_000), false);
  }
  private void validate(Config config, ZonedDateTime value, boolean midnight) {
    assertNotNull(value);
    assertEquals(value.getYear(), 2014, format("Year (%s)", config.zoneId));
    assertEquals(value.getMonthValue(), 6, format("Month of Year (%s)",config.zoneId));
    assertEquals(value.getDayOfMonth(), 17, format("Day of Month (%s)", config.zoneId));
    assertEquals(value.getHour(), (midnight ? 0 : 13), format("Hour of Day (%s)", config.zoneId));
    assertEquals(value.getMinute(), (midnight ? 0 : 15), format("Minute of Hour (%s)", config.zoneId));
    assertEquals(value.getSecond(), (midnight ? 0 : 32), format("Second of Minute (%s)", config.zoneId));
    assertEquals(value.getNano(), (midnight ? 0 : 136000000), format("Nano of Second (%s)", config.zoneId));
  }




  public void toUtilDate_Long(Config config) {
    java.util.Date date = config.factory.toUtilDate(config.utcDateTime);
    LocalDateTime ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);
  }

  public void toUtilDate_String(Config config) {
    LocalDateTime ldt;
    java.util.Date date;

    date = config.factory.toUtilDate(config.stringDateTimeHyT);
    ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);

    date = config.factory.toUtilDate(config.stringDateTimeFsT);
    ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);

    date = config.factory.toUtilDate(config.stringDateTimeHyZ);
    ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);

    date = config.factory.toUtilDate(config.stringDateTimeFsZ);
    ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);
  }

  public void toUtilDate_UtilDate(Config config) {
    java.util.Date date = config.factory.toUtilDate(new java.util.Date(config.utcDateTime));
    LocalDateTime ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);
  }

  public void toUtilDate_Calendar(Config config) {
    java.util.Date date = config.factory.toUtilDate(config.calendar);
    LocalDateTime ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);
  }

  public void toUtilDate_LocalDate(Config config) {
    java.util.Date date = config.factory.toUtilDate(config.localDate);
    LocalDateTime ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, true);
  }

  public void toUtilDate_LocalDateTime(Config config) {
    java.util.Date date = config.factory.toUtilDate(config.localDateTime);
    LocalDateTime ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);
  }

  public void toUtilDate_ZonedDateTime(Config config) {
    java.util.Date date = config.factory.toUtilDate(config.zonedDateTime);
    LocalDateTime ldt = config.factory.toLocalDateTime(date);
    validate(config, ldt, false);
  }

  public void toUtilDate_YMD(Config config) {
    java.util.Date date = config.factory.toUtilDate(2014, 6, 17);
    validate(config, date, true);
  }


  public void toYearMonth_LocalDate(Config config) {
    YearMonth yearMonth = config.factory.toYearMonth(config.localDate);
    assertEquals(yearMonth.getYear(), 2014);
    assertEquals(yearMonth.getMonthValue(), 6);
  }
  public void toYearMonth_LocalDateTime(Config config) {
    YearMonth yearMonth = config.factory.toYearMonth(config.localDateTime);
    assertEquals(yearMonth.getYear(), 2014);
    assertEquals(yearMonth.getMonthValue(), 6);
  }
  public void toYearMonth_ZonedDateTime(Config config) {
    YearMonth yearMonth = config.factory.toYearMonth(config.zonedDateTime);
    assertEquals(yearMonth.getYear(), 2014);
    assertEquals(yearMonth.getMonthValue(), 6);
  }


  public void toFirstDate_LocalDate(Config config) {
    LocalDate actual = config.factory.toFirstDate(config.localDate);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 1);
  }
  public void toFirstDate_LocalDateTime(Config config) {
    LocalDateTime actual = config.factory.toFirstDate(config.localDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 1);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }
  public void toFirstDate_ZonedDateTime(Config config) {
    ZonedDateTime actual = config.factory.toFirstDate(config.zonedDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 1);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }


  public void toLastDate_LocalDate(Config config) {
    LocalDate actual = config.factory.toLastDate(config.localDate);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 30);
  }
  public void toLastDate_LocalDateTime(Config config) {
    LocalDateTime actual = config.factory.toLastDate(config.localDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 30);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }
  public void toLastDate_ZonedDateTime(Config config) {
    ZonedDateTime actual = config.factory.toLastDate(config.zonedDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 30);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }




  public void testConfig_ZonedDateTime(Config config) {
    validate(config, config.zonedDateTime, false);
  }
  public void testConfig_ZonedDateTime_Midnight(Config config) {
    validate(config, config.zonedDateTimeMidnight, true);
  }
  public void testConfig_Calendar(Config config) {
    validate(config, config.calendar, false);
  }
  public void testConfig_Calendar_Midnight(Config config) {
    validate(config, config.calendarMidnight, true);
  }
  public void testConfig_UtilDate(Config config) {
    validate(config, config.utilDateTime, false);
  }
  public void testConfig_UtilDate_Midnight(Config config) {
    validate(config, config.utilDateMidnight, true);
  }
  private void validate(Config config, Calendar value, boolean midnight) {
    assertNotNull(value);

    long offsetMin = value.get(Calendar.ZONE_OFFSET) / 1000 / 60;
    long expectedOffset = TimeZone.getTimeZone(config.zoneId).getRawOffset() / 1000 / 60;

    assertEquals(offsetMin, expectedOffset, format("Timezone Offset (%s)", value));

    assertEquals(value.get(Calendar.YEAR), 2014, format("Year (%s)", value));
    assertEquals(value.get(Calendar.MONTH) + 1, 6, format("Month of Year (%s)", value));
    assertEquals(value.get(Calendar.DAY_OF_MONTH), 17, format("Day of Month (%s)", value));
    assertEquals(value.get(Calendar.HOUR_OF_DAY),   (midnight ? 0 : 13), format("Hour of Day (%s)", value));
    assertEquals(value.get(Calendar.MINUTE),        (midnight ? 0 : 15), format("Minute of Hour (%s)", value));
    assertEquals(value.get(Calendar.SECOND),        (midnight ? 0 : 32), format("Second of Minute (%s)", value));
    assertEquals(value.get(Calendar.MILLISECOND),   (midnight ? 0 : 136), format("Mills of Second (%s)", value));
  }
  @SuppressWarnings("deprecation")
  private void validate(Config config, java.util.Date value, boolean midnight) {
    assertNotNull(value);

    long thisOffset = TimeZone.getDefault().getOffset(value.getTime());
    long thatOffset = TimeZone.getTimeZone(config.zoneId).getOffset(value.getTime());
    long offset = (thatOffset - thisOffset);

    java.util.Date adjusted = new java.util.Date(value.getTime() + offset);

    assertEquals(adjusted.getYear()+1900, 2014, format("Year (%s)", value));
    assertEquals(adjusted.getMonth()+1,   6, format("Month of Year (%s)", value));
    assertEquals(adjusted.getDate(),      17, format("Day of Month (%s)", value));
    assertEquals(adjusted.getHours(),     (midnight ? 0 : 13), format("Hour of Day (%s)", value));
    assertEquals(adjusted.getMinutes(),   (midnight ? 0 : 15), format("Minute of Hour (%s)", value));
    assertEquals(adjusted.getSeconds(),   (midnight ? 0 : 32), format("Second of Minute (%s)", value));
  }



  public static class Config {
    public final String timeZone;
    public final ZoneId zoneId;

    public final String stringTime = "13:15:32.136";

    public final String stringDateMidnightHy = "2014-06-17";
    public final String stringDateMidnightFs = "2014/06/17";

    public final String stringDateTimeHyT =    stringDateMidnightHy + "T" + stringTime;
    public final String stringDateTimeFsT =    stringDateMidnightFs + "T" + stringTime;

    public final String stringDateTimeHyZ;
    public final String stringDateTimeFsZ;

    public final String stringDateTimeHyS =   stringDateMidnightHy + " " + stringTime;
    public final String stringDateTimeFsS =   stringDateMidnightFs + " " + stringTime;

    public final LocalTime localTime = LocalTime.parse(stringTime);
    public final LocalDate localDate = LocalDate.parse(stringDateMidnightHy);

    public final LocalDateTime localDateTime = LocalDateTime.parse(stringDateTimeHyT);
    public final LocalDateTime localDateTimeMidnight = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);

    public final ZonedDateTime zonedDateTime;
    public final ZonedDateTime zonedDateTimeMidnight;

    public final Calendar calendar;
    public final Calendar calendarMidnight;

    public final long utcDateTime;
    public final long utcDateMidnight;

    public final java.util.Date utilDateTime;
    public final java.util.Date utilDateMidnight;

    public DateUtilsFactory factory;

    public Config(String timeZone) throws Exception {
      this.timeZone = timeZone;
      this.zoneId = ZoneId.of(timeZone);

      zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
      zonedDateTimeMidnight = ZonedDateTime.of(localDateTimeMidnight, zoneId);

      calendar = GregorianCalendar.from(zonedDateTime);
      calendarMidnight = GregorianCalendar.from(zonedDateTimeMidnight);

      stringDateTimeHyZ =    stringDateMidnightHy + "T" + stringTime + "-07:00["+timeZone+"]";
      stringDateTimeFsZ =    stringDateMidnightFs + "T" + stringTime + "-07:00["+timeZone+"]";

      utcDateTime = zonedDateTime.toInstant().toEpochMilli();
      utcDateMidnight = zonedDateTimeMidnight.toInstant().toEpochMilli();

      utilDateTime = java.util.Date.from(zonedDateTime.toInstant());
      utilDateMidnight = java.util.Date.from(zonedDateTimeMidnight.toInstant());

      factory = new DateUtilsFactory(zoneId);
    }

    public String toString() {
      return timeZone;
    }
  }
}