package org.tiogasolutions.dev.common;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.fail;

@Test
public class DateUtilsFactoryTest {

  private static String timeZoneId = "America/Los_Angeles";
  private static final ZoneId zoneId = ZoneId.of(timeZoneId);

  private String stringTime = "13:15:32.136";

  private String stringDateMidnightHy = "2014-06-17";
  private String stringDateMidnightFs = "2014/06/17";

  private String stringDateTimeHyT =    "2014-06-17" + "T" + stringTime;
  private String stringDateTimeFsT =    "2014/06/17" + "T" + stringTime;

  private String stringDateTimeHyZ =    "2014-06-17" + "T" + stringTime + "-07:00[America/Los_Angeles]";
  private String stringDateTimeFsZ =    "2014/06/17" + "T" + stringTime + "-07:00[America/Los_Angeles]";

  private String stringDateTimeHyS =   "2014-06-17" + " " + stringTime;
  private String stringDateTimeFsS =   "2014/06/17" + " " + stringTime;

  private LocalTime localTime = LocalTime.parse(stringTime);
  private LocalDate localDate = LocalDate.parse(stringDateMidnightHy);

  private LocalDateTime localDateTime = LocalDateTime.parse(stringDateTimeHyT);
  private LocalDateTime localDateTimeMidnight = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);

  private ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
  private ZonedDateTime zonedDateTimeMidnight = ZonedDateTime.of(localDateTimeMidnight, zoneId);

  private long longDateTime     = zonedDateTime.toInstant().toEpochMilli();
  private long longDateMidnight = zonedDateTimeMidnight.toInstant().toEpochMilli();

  private Timestamp utilDateMidnight = new Timestamp(longDateMidnight);
  private Timestamp utilDateTime = new Timestamp(longDateTime);

  private Calendar calendar;
  private Calendar calendarMidnight;

  private DateUtilsFactory factory;

  @BeforeClass
  public void beforeClass() throws Exception {
    factory = new DateUtilsFactory(zoneId);

    calendar = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
    calendar.set(Calendar.YEAR, 2014);
    calendar.set(Calendar.MONTH, 5);
    calendar.set(Calendar.DAY_OF_MONTH, 17);
    calendar.set(Calendar.HOUR_OF_DAY, 13);
    calendar.set(Calendar.MINUTE, 15);
    calendar.set(Calendar.SECOND, 32);
    calendar.set(Calendar.MILLISECOND, 0);

    calendarMidnight = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
    calendarMidnight.set(Calendar.YEAR, 2014);
    calendarMidnight.set(Calendar.MONTH, 5);
    calendarMidnight.set(Calendar.DAY_OF_MONTH, 17);
    calendarMidnight.set(Calendar.HOUR_OF_DAY, 0);
    calendarMidnight.set(Calendar.MINUTE, 0);
    calendarMidnight.set(Calendar.SECOND, 0);
    calendarMidnight.set(Calendar.MILLISECOND, 0);

    assertEquals(longDateTime, 1403036132136L);
    assertEquals(longDateMidnight, 1402988400000L);
  }

  public void currentLocalTime() {
    assertNotNull(DateUtils.currentLocalTime());
  }
  public void currentLocalDateTime() {
    assertNotNull(DateUtils.currentLocalDateTime());
  }
  public void currentLocalDate() {
    assertNotNull(DateUtils.currentLocalDate());
  }
  public void currentZonedDateTime() {
    assertNotNull(DateUtils.currentZonedDateTime());
  }

  public void toLocalDate_Object() {
    assertNull(factory.toLocalDate((Object) null));

    validate(factory.toLocalDate(        localDateTime.toLocalDate()));

    validate(factory.toLocalDate((Object)stringDateTimeHyT));
    validate(factory.toLocalDate((Object) stringDateTimeFsT));

    validate(factory.toLocalDate((Object)stringDateTimeHyZ));
    validate(factory.toLocalDate((Object)stringDateTimeFsZ));

    validate(factory.toLocalDate((Object)longDateTime));
    validate(factory.toLocalDate((Object)new java.util.Date(longDateTime)));
    validate(factory.toLocalDate((Object)calendar));
    validate(factory.toLocalDate((Object)localDateTime));
    validate(factory.toLocalDate((Object)zonedDateTime));

    try {
      factory.toLocalDate(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to LocalDate.");
    }
  }

  public void toLocalDate_String() {

    validate(factory.toLocalDate(stringDateMidnightHy));
    validate(factory.toLocalDate(stringDateMidnightFs));

    validate(factory.toLocalDate(stringDateTimeHyT));
    validate(factory.toLocalDate(stringDateTimeFsT));

    validate(factory.toLocalDate(stringDateTimeHyZ));
    validate(factory.toLocalDate(stringDateTimeFsZ));

    validate(factory.toLocalDate(stringDateTimeHyS));
    validate(factory.toLocalDate(stringDateTimeFsS));

    assertNull(factory.toLocalDate((String) null));
  }
  public void toLocalDate_Long() {

    validate(factory.toLocalDate(longDateTime));
    validate(factory.toLocalDate(longDateMidnight));

    assertNull(factory.toLocalDate(0));

    try {
      factory.toLocalDate(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toLocalDate_UtilDate() {
    validate(factory.toLocalDate(utilDateTime));
    validate(factory.toLocalDate(utilDateMidnight));

    assertNull(factory.toLocalDate((java.util.Date) null));
  }
  public void toLocalDate_GregorianCalendar() {
    validate(factory.toLocalDate(calendar));
    validate(factory.toLocalDate(calendarMidnight));

    assertNull(factory.toLocalDate((Calendar) null));
  }
  public void toLocalDate_LocalDateTime() {

    validate(factory.toLocalDate(localDateTime));

    assertNull(factory.toLocalDate((LocalDateTime) null));
  }
  public void toLocalDate_DateTime() {

    validate(factory.toLocalDate(zonedDateTime));

    assertEquals(factory.toLocalDate((ZonedDateTime) null), null);
  }

  public void
  toLocalDate_YMD() {
    validate(factory.toLocalDate(2014, 6, 17));
  }

  private void validate(LocalDate value) {
    assertNotNull(value);
    assertEquals(value.getYear(), 2014);
    assertEquals(value.getMonthValue(), 6);
    assertEquals(value.getDayOfMonth(), 17);
  }



  public void toLocalTime_Object() {
    assertEquals(factory.toLocalTime((Object) null), null);

    validate(factory.toLocalTime(         localTime), false, false);
    validate(factory.toLocalTime((Object) stringTime), false, false);
    validate(factory.toLocalTime((Object) longDateTime), false, false);
    validate(factory.toLocalTime((Object) new java.util.Date(longDateTime)), false, false);
    validate(factory.toLocalTime((Object) calendar), false, true);
    validate(factory.toLocalTime((Object) localDateTime), false, false);
    validate(factory.toLocalTime((Object) zonedDateTime), false, false);

    try {
      factory.toLocalTime(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to LocalTime.");
    }
  }
  public void toLocalTime_String() {

    validate(factory.toLocalTime(stringTime), false, false);

    validate(factory.toLocalTime(stringDateMidnightHy), true, false);
    validate(factory.toLocalTime(stringDateMidnightFs), true, false);

    validate(factory.toLocalTime(stringDateTimeHyT), false, false);
    validate(factory.toLocalTime(stringDateTimeFsT), false, false);

    validate(factory.toLocalTime(stringDateTimeHyZ), false, false);
    validate(factory.toLocalTime(stringDateTimeFsZ), false, false);

    validate(factory.toLocalTime(stringDateTimeHyS), false, false);
    validate(factory.toLocalTime(stringDateTimeFsS), false, false);

    assertNull(factory.toLocalTime((String) null));
  }
  public void toLocalTime_Long() {

    validate(factory.toLocalTime(longDateTime), false, false);
    validate(factory.toLocalTime(longDateMidnight), true, false);

    assertEquals(factory.toLocalTime(0), null);

    try {
      factory.toLocalTime(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toLocalTime_UtilDate() {
    validate(factory.toLocalTime(utilDateTime), false, false);
    validate(factory.toLocalTime(utilDateMidnight), true, false);

    assertNull(factory.toLocalTime((java.util.Date) null));
  }
  public void toLocalTime_GregorianCalendar() {
    validate(factory.toLocalTime(calendar), false, true);
    validate(factory.toLocalTime(calendarMidnight), true, true);

    assertNull(factory.toLocalTime((Calendar) null));
  }
  public void toLocalTime_LocalDateTime() {

    validate(factory.toLocalTime(localDateTime), false, false);

    assertNull(factory.toLocalTime((LocalDateTime) null));
  }
  public void toLocalTime_DateTime() {

    validate(factory.toLocalTime(zonedDateTime), false, false);

    assertNull(factory.toLocalTime((ZonedDateTime) null));
  }
  public void toLocalTime_HMSM() {
    assertEquals(factory.toLocalTime(0, 0, 0, 0), LocalTime.MIDNIGHT);
    validate(factory.toLocalTime(13, 15, 32, 136), false, false);
  }
  private void validate(LocalTime value, boolean midnight, boolean calendar) {
    assertNotNull(value);
    assertEquals(value.getHour(), (midnight ? 0 : 13));
    assertEquals(value.getMinute(), (midnight ? 0 : 15));
    assertEquals(value.getSecond(), (midnight ? 0 : 32));
    assertEquals(value.getNano(), (midnight || calendar ? 0 : 136000000));
  }



  public void toLocalDateTime_Object() {
    assertEquals(factory.toLocalDateTime((Object) null), null);

    validate(factory.toLocalDateTime(         localDateTime), false, false);

    validate(factory.toLocalDateTime((Object) stringDateTimeHyT), false, false);
    validate(factory.toLocalDateTime((Object) stringDateTimeFsT), false, false);

    validate(factory.toLocalDateTime((Object) stringDateTimeHyZ), false, false);
    validate(factory.toLocalDateTime((Object) stringDateTimeFsZ), false, false);

    validate(factory.toLocalDateTime((Object) longDateTime), false, false);
    validate(factory.toLocalDateTime((Object) new java.util.Date(longDateTime)), false, false);
    validate(factory.toLocalDateTime((Object) calendar), false, true);
    validate(factory.toLocalDateTime((Object) localDate), true, false);
    validate(factory.toLocalDateTime((Object) zonedDateTime), false, false);

    try {
      factory.toLocalDateTime(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to LocalDateTime.");
    }
  }
  public void toLocalDateTime_String() {

    validate(factory.toLocalDateTime(stringDateMidnightHy), true, false);
    validate(factory.toLocalDateTime(stringDateMidnightFs), true, false);

    validate(factory.toLocalDateTime(stringDateTimeHyT), false, false);
    validate(factory.toLocalDateTime(stringDateTimeFsT), false, false);

    validate(factory.toLocalDateTime(stringDateTimeHyZ), false, false);
    validate(factory.toLocalDateTime(stringDateTimeFsZ), false, false);

    validate(factory.toLocalDateTime(stringDateTimeHyS), false, false);
    validate(factory.toLocalDateTime(stringDateTimeFsS), false, false);

    assertNull(factory.toLocalDateTime((String) null));
  }
  public void toLocalDateTime_Long() {

    validate(factory.toLocalDateTime(longDateTime), false, false);
    validate(factory.toLocalDateTime(longDateMidnight), true, false);

    assertEquals(factory.toLocalDateTime(0), null);

    try {
      factory.toLocalDateTime(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toLocalDateTime_UtilDate() {
    validate(factory.toLocalDateTime(utilDateTime), false, false);
    validate(factory.toLocalDateTime(utilDateMidnight), true, false);

    assertNull(factory.toLocalDateTime((java.util.Date) null));
  }
  public void toLocalDateTime_GregorianCalendar() {
    validate(factory.toLocalDateTime(calendar), false, true);
    validate(factory.toLocalDateTime(calendarMidnight), true, true);

    assertEquals(factory.toLocalDateTime((Calendar) null), null);
  }
  public void toLocalDateTime_LocalDate() {

    validate(factory.toLocalDateTime(localDateTimeMidnight), true, false);

    assertNull(factory.toLocalDateTime((LocalDate) null));
  }
  public void toLocalDateTime_DateTime() {

    validate(factory.toLocalDateTime(zonedDateTime), false, false);

    assertEquals(factory.toLocalDateTime((ZonedDateTime) null), null);
  }
  public void toLocalDateTime_MDY_HMSM() {
    validate(factory.toLocalDateTime(2014, 6, 17, 13, 15, 32, 136), false, false);
  }
  private void validate(LocalDateTime value, boolean midnight, boolean calendar) {
    assertNotNull(value);
    assertEquals(value.getYear(), 2014);
    assertEquals(value.getMonthValue(), 6);
    assertEquals(value.getDayOfMonth(), 17);
    assertEquals(value.getHour(), (midnight ? 0 : 13));
    assertEquals(value.getMinute(), (midnight ? 0 : 15));
    assertEquals(value.getSecond(), (midnight ? 0 : 32));
    assertEquals(value.getNano(), (midnight || calendar ? 0 : 136000000));
  }



  public void toDateTime_Object() {
    assertEquals(factory.toZonedDateTime((Object) null), null);

    validate(factory.toZonedDateTime(         zonedDateTime), false, false);

    validate(factory.toZonedDateTime((Object) stringDateTimeHyT), false, false);
    validate(factory.toZonedDateTime((Object) stringDateTimeFsT), false, false);

    validate(factory.toZonedDateTime((Object) stringDateTimeHyZ), false, false);
    validate(factory.toZonedDateTime((Object) stringDateTimeFsZ), false, false);

    validate(factory.toZonedDateTime((Object) longDateTime), false, false);
    validate(factory.toZonedDateTime((Object) new java.util.Date(longDateTime)), false, false);
    validate(factory.toZonedDateTime((Object) calendar), false, true);
    validate(factory.toZonedDateTime((Object) localDateTimeMidnight), true, false);
    validate(factory.toZonedDateTime((Object) localDateTime), false, false);

    try {
      factory.toZonedDateTime(new TestBean());
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Cannot convert objects of type TestBean to DateTime.");
    }
  }
  public void toDateTime_String_Midnight() {
    validate(factory.toZonedDateTime(stringDateMidnightHy), true, false);
    validate(factory.toZonedDateTime(stringDateMidnightFs), true, false);
  }
  public void toDateTime_String_withT() {
    validate(factory.toZonedDateTime(stringDateTimeHyT), false, false);
    validate(factory.toZonedDateTime(stringDateTimeFsT), false, false);
  }
  public void toDateTime_String_withZ() {
    validate(factory.toZonedDateTime(stringDateTimeHyZ), false, false);
    validate(factory.toZonedDateTime(stringDateTimeFsZ), false, false);
  }
  public void toDateTime_String_withSp() {
    validate(factory.toZonedDateTime(stringDateTimeHyS), false, false);
    validate(factory.toZonedDateTime(stringDateTimeFsS), false, false);
  }
  public void toDateTime_String_null() {
    assertEquals(factory.toZonedDateTime((String) null), null);
  }
  public void toDateTime_Long() {

    validate(factory.toZonedDateTime(longDateTime), false, false);
    validate(factory.toZonedDateTime(longDateMidnight), true, false);

    assertEquals(factory.toZonedDateTime(0), null);

    try {
      factory.toZonedDateTime(Integer.MIN_VALUE);
      fail();
    } catch (Exception e) {
      assertEquals(e.getMessage(), "The value \"date\" must be greater than or equal to zero.");
    }
  }
  public void toDateTime_LocalDate() {

    validate(factory.toZonedDateTime(localDateTimeMidnight), true, false);

    assertEquals(factory.toZonedDateTime((LocalDate) null), null);
  }
  public void toDateTime_LocalDateTime() {

    validate(factory.toZonedDateTime(localDateTime), false, false);

    assertEquals(factory.toZonedDateTime((LocalDateTime) null), null);
  }
  public void toDateTime_GregorianCalendar() {
    validate(factory.toZonedDateTime(calendar), false, true);
    validate(factory.toZonedDateTime(calendarMidnight), true, true);

    assertEquals(factory.toZonedDateTime((Calendar) null), null);
  }
  public void toDateTime_UtilDate() {
    validate(factory.toZonedDateTime(utilDateTime), false, false);
    validate(factory.toZonedDateTime(utilDateMidnight), true, false);

    assertEquals(factory.toZonedDateTime((java.util.Date) null), null);
  }
  public void toDateTime_MDY_HMSM() {
    validate(factory.toZonedDateTimeWithMills(2014, 6, 17, 13, 15, 32, 136), false, false);
  }
  public void toDateTime_MDY_HMSN() {
    validate(factory.toZonedDateTimeWithNanos(2014, 6, 17, 13, 15, 32, 136*1000*1000), false, false);
  }
  private void validate(ZonedDateTime value, boolean midnight, boolean calendar) {
    assertNotNull(value);
    assertEquals(value.getYear(), 2014);
    assertEquals(value.getMonthValue(), 6);
    assertEquals(value.getDayOfMonth(), 17);
    assertEquals(value.getHour(), (midnight ? 0 : 13));
    assertEquals(value.getMinute(), (midnight ? 0 : 15));
    assertEquals(value.getSecond(), (midnight ? 0 : 32));
    assertEquals(value.getNano(), (midnight || calendar ? 0 : 136000000));
  }




  public void toUtilDate_Long() {
    java.util.Date date = factory.toUtilDate(longDateTime);
    LocalDateTime ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);
  }

  public void toUtilDate_String() {
    LocalDateTime ldt;
    java.util.Date date;

    date = factory.toUtilDate(stringDateTimeHyT);
    ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);

    date = factory.toUtilDate(stringDateTimeFsT);
    ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);

    date = factory.toUtilDate(stringDateTimeHyZ);
    ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);

    date = factory.toUtilDate(stringDateTimeFsZ);
    ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);
  }

  public void toUtilDate_UtilDate() {
    java.util.Date date = factory.toUtilDate(new java.util.Date(longDateTime));
    LocalDateTime ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);
  }

  public void toUtilDate_Calendar() {
    java.util.Date date = factory.toUtilDate(calendar);
    LocalDateTime ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, true);
  }

  public void toUtilDate_LocalDate() {
    java.util.Date date = factory.toUtilDate(localDate);
    LocalDateTime ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, true, false);
  }

  public void toUtilDate_LocalDateTime() {
    java.util.Date date = factory.toUtilDate(localDateTime);
    LocalDateTime ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);
  }

  public void toUtilDate_ZonedDateTime() {
    java.util.Date date = factory.toUtilDate(zonedDateTime);
    LocalDateTime ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, false, false);
  }

  public void toUtilDate_YMD() {
    java.util.Date date = factory.toUtilDate(2014, 6, 17);
    LocalDateTime ldt = DateUtils.toLocalDateTime(date);
    validate(ldt, true, false);
  }


  public void toYearMonth_LocalDate() {
    YearMonth yearMonth = factory.toYearMonth(localDate);
    assertEquals(yearMonth.getYear(), 2014);
    assertEquals(yearMonth.getMonthValue(), 6);
  }
  public void toYearMonth_LocalDateTime() {
    YearMonth yearMonth = factory.toYearMonth(localDateTime);
    assertEquals(yearMonth.getYear(), 2014);
    assertEquals(yearMonth.getMonthValue(), 6);
  }
  public void toYearMonth_ZonedDateTime() {
    YearMonth yearMonth = factory.toYearMonth(zonedDateTime);
    assertEquals(yearMonth.getYear(), 2014);
    assertEquals(yearMonth.getMonthValue(), 6);
  }


  public void toFirstDate_LocalDate() {
    LocalDate actual = factory.toFirstDate(localDate);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 1);
  }
  public void toFirstDate_LocalDateTime() {
    LocalDateTime actual = factory.toFirstDate(localDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 1);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }
  public void toFirstDate_ZonedDateTime() {
    ZonedDateTime actual = factory.toFirstDate(zonedDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 1);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }


  public void toLastDate_LocalDate() {
    LocalDate actual = factory.toLastDate(localDate);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 30);
  }
  public void toLastDate_LocalDateTime() {
    LocalDateTime actual = factory.toLastDate(localDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 30);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }
  public void toLastDate_ZonedDateTime() {
    ZonedDateTime actual = factory.toLastDate(zonedDateTime);
    assertEquals(actual.getYear(), 2014);
    assertEquals(actual.getMonthValue(), 6);
    assertEquals(actual.getDayOfMonth(), 30);
    assertEquals(actual.getHour(), 13);
    assertEquals(actual.getMinute(), 15);
    assertEquals(actual.getSecond(), 32);
    assertEquals(actual.getNano(), 136000000);
  }
}