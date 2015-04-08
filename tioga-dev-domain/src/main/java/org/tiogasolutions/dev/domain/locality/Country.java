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

import java.util.*;

public enum Country {

  AF("Afghanistan"), 
  AX("Aland Islands"), 
  AL("Albania"), 
  DZ("Algeria"), 
  AS("American Samoa"), 
  AD("Andorra"), 
  AO("Angola"), 
  AI("Anguilla"), 
  AQ("Antarctica"), 
  AG("Antigua and Barbuda"), 
  AR("Argentina"), 
  AM("Armenia"), 
  AW("Aruba"), 
  AU("Australia"), 
  AT("Austria"), 
  AZ("Azerbaijan"), 
  BS("Bahamas, The"), 
  BH("Bahrain"), 
  BD("Bangladesh"), 
  BB("Barbados"), 
  BY("Belarus"), 
  BE("Belgium"), 
  BZ("Belize"), 
  BJ("Benin"), 
  BM("Bermuda"), 
  BT("Bhutan"), 
  BO("Bolivia"), 
  BA("Bosnia and Herzegovina"), 
  BW("Botswana"), 
  BV("Bouvet Island"), 
  BR("Brazil"), 
  IO("British Indian Ocean Territory"), 
  BN("Brunei Darussalam"), 
  BG("Bulgaria"), 
  BF("Burkina Faso"), 
  BI("Burundi"), 
  KH("Cambodia"), 
  CM("Cameroon"), 
  CA("Canada"), 
  CV("Cape Verde"), 
  KY("Cayman Islands"), 
  CF("Central African Republic"), 
  TD("Chad"), 
  CL("Chile"), 
  CN("China"), 
  CX("Christmas Island"), 
  CC("Cocos (Keeling) Islands"), 
  CO("Colombia"), 
  KM("Comoros"), 
  CG("Congo"), 
  CD("Congo, The Democratic Republic Of The"), 
  CK("Cook Islands"), 
  CR("Costa Rica"), 
  CI("Cote D'ivoire"), 
  HR("Croatia"), 
  CY("Cyprus"), 
  CZ("Czech Republic"), 
  DK("Denmark"), 
  DJ("Djibouti"), 
  DM("Dominica"), 
  DO("Dominican Republic"), 
  EC("Ecuador"), 
  EG("Egypt"), 
  SV("El Salvador"), 
  GQ("Equatorial Guinea"), 
  ER("Eritrea"), 
  EE("Estonia"), 
  ET("Ethiopia"), 
  FK("Falkland Islands (Malvinas)"), 
  FO("Faroe Islands"), 
  FJ("Fiji"), 
  FI("Finland"), 
  FR("France"), 
  GF("French Guiana"), 
  PF("French Polynesia"), 
  TF("French Southern Territories"), 
  GA("Gabon"), 
  GM("Gambia, The"), 
  GE("Georgia"), 
  DE("Germany"), 
  GH("Ghana"), 
  GI("Gibraltar"), 
  GR("Greece"), 
  GL("Greenland"), 
  GD("Grenada"), 
  GP("Guadeloupe"), 
  GU("Guam"), 
  GT("Guatemala"), 
  GG("Guernsey"), 
  GN("Guinea"), 
  GW("Guinea-Bissau"), 
  GY("Guyana"), 
  HT("Haiti"), 
  HM("Heard Island and the McDonald Islands"),
  VA("Holy See"), 
  HN("Honduras"), 
  HK("Hong Kong"), 
  HU("Hungary"), 
  IS("Iceland"), 
  IN("India"), 
  ID("Indonesia"), 
  IQ("Iraq"), 
  IE("Ireland"), 
  IM("Isle Of Man"), 
  IL("Israel"), 
  IT("Italy"), 
  JM("Jamaica"), 
  JP("Japan"), 
  JE("Jersey"), 
  JO("Jordan"), 
  KZ("Kazakhstan"), 
  KE("Kenya"), 
  KI("Kiribati"), 
  KR("Korea, Republic Of"), 
  KW("Kuwait"), 
  KG("Kyrgyzstan"), 
  LA("Lao People's Democratic Republic"), 
  LV("Latvia"), 
  LB("Lebanon"), 
  LS("Lesotho"), 
  LR("Liberia"), 
  LY("Libya"), 
  LI("Liechtenstein"), 
  LT("Lithuania"), 
  LU("Luxembourg"), 
  MO("Macao"), 
  MK("Macedonia, The Former Yugoslav Republic Of"),
  MG("Madagascar"), 
  MW("Malawi"), 
  MY("Malaysia"), 
  MV("Maldives"), 
  ML("Mali"), 
  MT("Malta"), 
  MH("Marshall Islands"), 
  MQ("Martinique"), 
  MR("Mauritania"), 
  MU("Mauritius"), 
  YT("Mayotte"), 
  MX("Mexico"), 
  FM("Micronesia, Federated States Of"),
  MD("Moldova, Republic Of"),
  MC("Monaco"), 
  MN("Mongolia"), 
  ME("Montenegro"), 
  MS("Montserrat"), 
  MA("Morocco"), 
  MZ("Mozambique"), 
  MM("Myanmar"), 
  NA("Namibia"), 
  NR("Nauru"), 
  NP("Nepal"), 
  NL("Netherlands"), 
  AN("Netherlands Antilles"), 
  NC("New Caledonia"), 
  NZ("New Zealand"), 
  NI("Nicaragua"), 
  NE("Niger"), 
  NG("Nigeria"), 
  NU("Niue"), 
  NF("Norfolk Island"), 
  MP("Northern Mariana Islands"), 
  NO("Norway"), 
  OM("Oman"), 
  PK("Pakistan"), 
  PW("Palau"), 
  PS("Palestinian Territories"), 
  PA("Panama"), 
  PG("Papua New Guinea"), 
  PY("Paraguay"), 
  PE("Peru"), 
  PH("Philippines"), 
  PN("Pitcairn"), 
  PL("Poland"), 
  PT("Portugal"), 
  PR("Puerto Rico"), 
  QA("Qatar"), 
  RE("Reunion"), 
  RO("Romania"), 
  RU("Russian Federation"), 
  RW("Rwanda"), 
  BL("Saint Barthelemy"), 
  SH("Saint Helena"), 
  KN("Saint Kitts and Nevis"), 
  LC("Saint Lucia"), 
  MF("Saint Martin"), 
  PM("Saint Pierre and Miquelon"), 
  VC("Saint Vincent and The Grenadines"), 
  WS("Samoa"), 
  SM("San Marino"), 
  ST("Sao Tome and Principe"), 
  SA("Saudi Arabia"), 
  SN("Senegal"), 
  RS("Serbia"), 
  SC("Seychelles"), 
  SL("Sierra Leone"), 
  SG("Singapore"), 
  SK("Slovakia"), 
  SI("Slovenia"), 
  SB("Solomon Islands"), 
  SO("Somalia"), 
  ZA("South Africa"), 
  GS("South Georgia and the South Sandwich Islands"), 
  ES("Spain"), 
  LK("Sri Lanka"), 
  SR("Suriname"), 
  SJ("Svalbard and Jan Mayen"), 
  SZ("Swaziland"), 
  SE("Sweden"), 
  CH("Switzerland"), 
  TW("Taiwan"), 
  TJ("Tajikistan"), 
  TZ("Tanzania, United Republic Of"), 
  TH("Thailand"), 
  TL("Timor-leste"), 
  TG("Togo"), 
  TK("Tokelau"), 
  TO("Tonga"), 
  TT("Trinidad and Tobago"), 
  TN("Tunisia"), 
  TR("Turkey"), 
  TM("Turkmenistan"), 
  TC("Turks and Caicos Islands"), 
  TV("Tuvalu"), 
  UG("Uganda"), 
  UA("Ukraine"), 
  AE("United Arab Emirates"), 
  GB("United Kingdom"), 
  US("United States of America", State.values()),
  UM("United States Minor Outlying Islands"), 
  UY("Uruguay"), 
  UZ("Uzbekistan"), 
  VU("Vanuatu"), 
  VE("Venezuela"), 
  VN("Vietnam"), 
  VG("Virgin Islands, British"), 
  VI("Virgin Islands, U.S."), 
  WF("Wallis and Futuna"), 
  EH("Western Sahara"), 
  YE("Yemen"), 
  ZM("Zambia"), 
  ZW("Zimbabwe"); 

  private String description;

  private final List<Province> provinces;

  Country(String description, Province...provinces) {
    this.description = description;

    List<Province> list = Arrays.asList(provinces);
    this.provinces = Collections.unmodifiableList(list);
  }

  public String getCode() {
    return name();
  }
  
  public String getDescription() {
    return description;
  }

  public List<Province> getProvinces() {
    return provinces;
  }

  public String toString() {
    return description;
  }

  public Province getProvince(final State state) {
    return getProvince(state == null ? null : state.getAbbreviation());
  }

  public Province getProvince(final String name) {
    if (provinces.isEmpty()) {
      return new Province() {
        @Override public String getAbbreviation() {
          return name;
        }
        @Override public String getDescription() {
          return name;
        }
      };
    }

    for (Province province : provinces) {
      if (province.getAbbreviation().equalsIgnoreCase(name)) return province;
      if (province.getDescription().equalsIgnoreCase(name)) return province;
    }
    
    return null;
  }

  public String validate(String state) {
    if (state == null || state.isEmpty()) {
      return null;
    }
    Province province = getProvince(state);
    return (province == null) ? null : province.getAbbreviation();
  }

  public static Country valueOf(String code, Country defaultValue) {
    try {
      return Country.valueOf(code);
    } catch (IllegalArgumentException e) {
      return defaultValue;
    }
  }
}
