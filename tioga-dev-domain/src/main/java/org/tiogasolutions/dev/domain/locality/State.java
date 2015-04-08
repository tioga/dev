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

package org.tiogasolutions.dev.domain.locality;

public enum State implements Province {
  AL("Alabama"),
  AK("Alaska"),
  AZ("Arizona"),
  AR("Arkansas"),
  CA("California"),
  CO("Colorado"),
  CT("Connecticut"),
  DE("Delaware"),
  FL("Florida"),
  GA("Georgia"),
  HI("Hawaii"),
  ID("Idaho"),
  IL("Illinois"),
  IN("Indiana"),
  IA("Iowa"),
  KS("Kansas"),
  KY("Kentucky"),
  LA("Louisiana"),
  ME("Maine"),
  MD("Maryland"),
  MA("Massachusetts"),
  MI("Michigan"),
  MN("Minnesota"),
  MS("Mississippi"),
  MO("Missouri"),
  MT("Montana"),
  NE("Nebraska"),
  NV("Nevada"),
  NH("New Hampshire"),
  NJ("New Jersey"),
  NM("New Mexico"),
  NY("New York"),
  NC("North Carolina"),
  ND("North Dakota"),
  OH("Ohio"),
  OK("Oklahoma"),
  OR("Oregon"),
  PA("Pennsylvania"),
  RI("Rhode Island"),
  SC("South Carolina"),
  SD("South Dakota"),
  TN("Tennessee"),
  TX("Texas"),
  UT("Utah"),
  VT("Vermont"),
  VA("Virginia"),
  WA("Washington"),
  WV("West Virginia"),
  WI("Wisconsin"),
  WY("Wyoming"),
  AS("American Samoa"),
  DC("District of Columbia"),
  FM("Federated States of Micronesia"),
  GU("Guam"),
  MH("Marshall Islands"),
  MP("Northern Mariana Islands"),
  PW("Palau"),
  PR("Puerto Rico"),
  VI("Virgin Islands");

  private String description;

  State(String description) {
    this.description = description;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getAbbreviation() {
    return name();
  }

  public String toString() {
    return description;
  }

  public static State toState(String descriptionOrAbbreviation) {
    if (descriptionOrAbbreviation == null){
      return null;
    }

    for (State state : values()) {
      if (state.getDescription().equals(descriptionOrAbbreviation) || state.getAbbreviation().equals(descriptionOrAbbreviation)) {
        return state;
      }
    }

    return null;
  }

  public static State valueOf(String code, State defaultValue) {
    try {
      return State.valueOf(code);
    } catch (IllegalArgumentException e) {
      return defaultValue;
    }
  }
}


