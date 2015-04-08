package org.tiogasolutions.dev.testing.domain;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.fine.FineMessageSetImpl;
import org.tiogasolutions.dev.common.fine.TraitMap;
import org.tiogasolutions.dev.domain.locality.LatLng;
import org.tiogasolutions.dev.domain.money.Money;

/**
 * This class is used by unit tests in this module as well as
 * by the demo app and the jersey-client module. It's made
 * public so that these apps do not require inclusion of test
 * dependencies.
 */
public class FreeBird {

  private final String injected;
  private final String missing;

  private final String stringValue;
  private final long longValue;
  private final int intValue;

  private final LatLng latLng;

  private final Money tiogaMoney;

  private final java.time.LocalTime javaLocalTime;
  private final java.time.LocalDate javaLocalDate;
  private final java.time.LocalDateTime javaLocalDateTime;
  private final java.time.ZonedDateTime javaZonedDateTime;

  private final TraitMap traitMap;
  private final FineMessage fineMessage;
  private final FineMessageSet messageSet;

  public FreeBird(String stringValue,
                  long longValue,
                  int intValue,
                  LatLng latLng,
                  Money tiogaMoney,

                  java.time.LocalTime javaLocalTime,
                  java.time.LocalDate javaLocalDate,
                  java.time.LocalDateTime javaLocalDateTime,
                  java.time.ZonedDateTime javaZonedDateTime,

                  TraitMap traitMap,
                  FineMessage fineMessage,
                  FineMessageSetImpl messageSet) {

    this.injected = null;
    this.missing = null;
    this.stringValue = stringValue;
    this.longValue = longValue;
    this.intValue = intValue;
    this.latLng = latLng;

    this.tiogaMoney = tiogaMoney;

    this.javaLocalTime = javaLocalTime;
    this.javaLocalDate = javaLocalDate;
    this.javaLocalDateTime = javaLocalDateTime;
    this.javaZonedDateTime = javaZonedDateTime;

    this.traitMap = traitMap;
    this.fineMessage = fineMessage;
    this.messageSet = messageSet;
  }

  @JsonCreator
  private FreeBird(@JacksonInject("injected-string") String injected,
                   @JacksonInject("missing-string") String missing,
                   @JsonProperty("stringValue") String stringValue,
                   @JsonProperty("longValue") long longValue,
                   @JsonProperty("intValue") int intValue,
                   @JsonProperty("latLng") LatLng latLng,

                   @JsonProperty("tiogaMoney") Money tiogaMoney,

                   @JsonProperty("javaLocalTime") java.time.LocalTime javaLocalTime,
                   @JsonProperty("javaLocalDate") java.time.LocalDate javaLocalDate,
                   @JsonProperty("javaLocalDateTime") java.time.LocalDateTime javaLocalDateTime,
                   @JsonProperty("javaZonedDateTime") java.time.ZonedDateTime javaZonedDateTime,

                   @JsonProperty("traitMap") TraitMap traitMap,
                   @JsonProperty("fineMessage") FineMessage fineMessage,
                   @JsonProperty("messageSet") FineMessageSet messageSet) {

    this.injected = injected;
    this.missing = missing;
    this.stringValue = stringValue;
    this.longValue = longValue;
    this.intValue = intValue;
    this.latLng = latLng;

    this.tiogaMoney = tiogaMoney;

    this.javaLocalTime = javaLocalTime;
    this.javaLocalDate = javaLocalDate;
    this.javaLocalDateTime = javaLocalDateTime;
    this.javaZonedDateTime = javaZonedDateTime;

    this.traitMap = traitMap;
    this.fineMessage = fineMessage;
    this.messageSet = messageSet;
  }

  public String getInjected() {
    return injected;
  }

  public String getMissing() {
    return missing;
  }

  public String getStringValue() {
    return stringValue;
  }

  public long getLongValue() {
    return longValue;
  }

  public int getIntValue() {
    return intValue;
  }

  public LatLng getLatLng() {
    return latLng;
  }

  public Money getTiogaMoney() {
    return tiogaMoney;
  }

  public java.time.LocalTime getJavaLocalTime() {
    return javaLocalTime;
  }

  public java.time.LocalDate getJavaLocalDate() {
    return javaLocalDate;
  }

  public java.time.LocalDateTime getJavaLocalDateTime() {
    return javaLocalDateTime;
  }

  public ZonedDateTime getJavaZonedDateTime() {
    return javaZonedDateTime;
  }

  public TraitMap getTraitMap() {
    return traitMap;
  }

  public FineMessage getFineMessage() {
    return fineMessage;
  }

  public FineMessageSet getMessageSet() {
    return messageSet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof FreeBird)) return false;

    FreeBird freeBird = (FreeBird) o;

    if (intValue != freeBird.intValue) return false;
    if (longValue != freeBird.longValue) return false;
    if (fineMessage != null ? !fineMessage.equals(freeBird.fineMessage) : freeBird.fineMessage != null) return false;
    if (injected != null ? !injected.equals(freeBird.injected) : freeBird.injected != null) return false;
    if (latLng != null ? !latLng.equals(freeBird.latLng) : freeBird.latLng != null) return false;

    if (tiogaMoney != null ? !tiogaMoney.equals(freeBird.tiogaMoney) : freeBird.tiogaMoney != null) return false;

    if (javaLocalDate != null ? !javaLocalDate.equals(freeBird.javaLocalDate) : freeBird.javaLocalDate != null) return false;
    if (javaLocalDateTime != null ? !javaLocalDateTime.equals(freeBird.javaLocalDateTime) : freeBird.javaLocalDateTime != null) return false;
    if (javaLocalTime != null ? !javaLocalTime.equals(freeBird.javaLocalTime) : freeBird.javaLocalTime != null) return false;
    if (javaZonedDateTime != null ? !javaZonedDateTime.equals(freeBird.javaZonedDateTime) : freeBird.javaZonedDateTime != null) return false;

    if (messageSet != null ? !messageSet.equals(freeBird.messageSet) : freeBird.messageSet != null) return false;
    if (missing != null ? !missing.equals(freeBird.missing) : freeBird.missing != null) return false;
    if (stringValue != null ? !stringValue.equals(freeBird.stringValue) : freeBird.stringValue != null) return false;
    if (traitMap != null ? !traitMap.equals(freeBird.traitMap) : freeBird.traitMap != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = injected != null ? injected.hashCode() : 0;
    result = 31 * result + (missing != null ? missing.hashCode() : 0);
    result = 31 * result + (stringValue != null ? stringValue.hashCode() : 0);
    result = 31 * result + (int) (longValue ^ (longValue >>> 32));
    result = 31 * result + intValue;
    result = 31 * result + (latLng != null ? latLng.hashCode() : 0);

    result = 31 * result + (tiogaMoney != null ? tiogaMoney.hashCode() : 0);

    result = 31 * result + (javaLocalTime != null ? javaLocalTime.hashCode() : 0);
    result = 31 * result + (javaLocalDate != null ? javaLocalDate.hashCode() : 0);
    result = 31 * result + (javaLocalDateTime != null ? javaLocalDateTime.hashCode() : 0);
    result = 31 * result + (javaZonedDateTime != null ? javaZonedDateTime.hashCode() : 0);

    result = 31 * result + (traitMap != null ? traitMap.hashCode() : 0);
    result = 31 * result + (fineMessage != null ? fineMessage.hashCode() : 0);
    result = 31 * result + (messageSet != null ? messageSet.hashCode() : 0);
    return result;
  }
}
