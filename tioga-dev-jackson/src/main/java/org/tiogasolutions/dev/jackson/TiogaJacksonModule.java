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

package org.tiogasolutions.dev.jackson;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.lang.reflect.*;
import java.time.*;

import org.tiogasolutions.dev.domain.account.AccountStatus;
import org.tiogasolutions.dev.domain.locality.LatLng;
import org.tiogasolutions.dev.domain.money.Money;
import org.tiogasolutions.dev.domain.query.QueryResult;
import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.fine.TraitMap;
import org.tiogasolutions.dev.jackson.mixins.AccountStatusMixin;
import org.tiogasolutions.dev.jackson.mixins.LatLngMixin;
import org.tiogasolutions.dev.jackson.money.TiogaMoneyDeserializer;
import org.tiogasolutions.dev.jackson.money.TiogaMoneySerializer;
import org.tiogasolutions.dev.jackson.msg.FineMessageMixin;
import org.tiogasolutions.dev.jackson.msg.FineMessageSetDeserializer;
import org.tiogasolutions.dev.jackson.msg.FineMessageSetSerializer;
import org.tiogasolutions.dev.jackson.qry.QueryResultDeserializer;
import org.tiogasolutions.dev.jackson.qry.QueryResultSerializer;
import org.tiogasolutions.dev.jackson.time.*;
import org.tiogasolutions.dev.jackson.trait.TraitMapDeserializer;
import org.tiogasolutions.dev.jackson.trait.TraitMapSerializer;

public class TiogaJacksonModule extends SimpleModule {

  public TiogaJacksonModule() {

    add(Money.class,          new TiogaMoneySerializer(),     new TiogaMoneyDeserializer());
    add(TraitMap.class,       new TraitMapSerializer(),       new TraitMapDeserializer());
    add(FineMessageSet.class, new FineMessageSetSerializer(), new FineMessageSetDeserializer());
    add(QueryResult.class,    new QueryResultSerializer(),    new QueryResultDeserializer());

    add(LocalDate.class,      new LocalDateSerializer(),      new LocalDateDeserializer());
    add(LocalTime.class,      new LocalTimeSerializer(),      new LocalTimeDeserializer());
    add(LocalDateTime.class,  new LocalDateTimeSerializer(),  new LocalDateTimeDeserializer());
    add(ZonedDateTime.class,  new ZonedDateTimeSerializer(),  new ZonedDateTimeDeserializer());

    setMixInAnnotation(LatLng.class, LatLngMixin.class);
    setMixInAnnotation(FineMessage.class, FineMessageMixin.class);
    setMixInAnnotation(AccountStatus.class, AccountStatusMixin.class);

    add("org.joda.money.Money", "org.tiogasolutions.lib.joda.jackson.JodaMoneySerializer", "org.tiogasolutions.lib.joda.jackson.JodaMoneyDeserializer");

    add("org.joda.time.DateMidnight",   "com.fasterxml.jackson.datatype.joda.ser.DateMidnightSerializer",   "com.fasterxml.jackson.datatype.joda.deser.DateMidnightDeserializer");
    add("org.joda.time.DateTime",       "com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer",       "com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer");
    add("org.joda.time.DateTimeZone",   "com.fasterxml.jackson.datatype.joda.ser.DateTimeZoneSerializer",   "com.fasterxml.jackson.datatype.joda.deser.DateTimeZoneDeserializer");
    add("org.joda.time.Duration",       "com.fasterxml.jackson.datatype.joda.ser.DurationSerializer",       "com.fasterxml.jackson.datatype.joda.deser.DurationDeserializer");
    add("org.joda.time.Instant",        "com.fasterxml.jackson.datatype.joda.ser.InstantSerializer",        "com.fasterxml.jackson.datatype.joda.deser.InstantDeserializer");
    add("org.joda.time.LocalDateTime",  "com.fasterxml.jackson.datatype.joda.ser.LocalDateTimeSerializer",  "com.fasterxml.jackson.datatype.joda.deser.LocalDateTimeDeserializer");
    add("org.joda.time.LocalDate",      "com.fasterxml.jackson.datatype.joda.ser.LocalDateSerializer",      "com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer");
    add("org.joda.time.LocalTime",      "com.fasterxml.jackson.datatype.joda.ser.LocalTimeSerializer",      "com.fasterxml.jackson.datatype.joda.deser.LocalTimeDeserializer");
    add("org.joda.time.Period",         "com.fasterxml.jackson.datatype.joda.ser.PeriodSerializer",         "com.fasterxml.jackson.datatype.joda.deser.PeriodDeserializer");
    add("org.joda.time.Interval",       "com.fasterxml.jackson.datatype.joda.ser.IntervalSerializer",       "com.fasterxml.jackson.datatype.joda.deser.IntervalDeserializer");
    add("org.joda.time.MonthDay",       "com.fasterxml.jackson.databind.ser.std.ToStringSerializer",       "com.fasterxml.jackson.datatype.joda.deser.MonthDayDeserializer");
    add("org.joda.time.YearMonth",      "com.fasterxml.jackson.databind.ser.std.ToStringSerializer",       "com.fasterxml.jackson.datatype.joda.deser.YearMonthDeserializer");
  }

  private void add(Class type, JsonSerializer serializer, JsonDeserializer deserializer) {
    addSerializer(type, serializer);
    addDeserializer(type, deserializer);
  }

  private void add(String typeName, String serializerClassName, String deserializerClassName) {
    try {
      Class type = Class.forName(typeName);

      Class jsonSerializerClass = Class.forName(serializerClassName);
      JsonSerializer<?> serializer = (JsonSerializer<?>)jsonSerializerClass.newInstance();
      // noinspection unchecked
      addSerializer(type, serializer);

      Class jsonDeserializerClass = Class.forName(deserializerClassName);
      JsonDeserializer deserializer;
      if (deserializerClassName.equals("com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer")) {
        Class dateTimeType = Class.forName("org.joda.time.DateTime");
        // noinspection unchecked
        Constructor constructor = jsonDeserializerClass.getConstructor(Class.class);
        deserializer = (JsonDeserializer)constructor.newInstance(dateTimeType);
        addDeserializer(type, deserializer);

      } else {
        deserializer = (JsonDeserializer)jsonDeserializerClass.newInstance();
        addDeserializer(type, deserializer);
      }

    } catch (InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException ignored) {
      /* ignore this */
    }
  }
}
