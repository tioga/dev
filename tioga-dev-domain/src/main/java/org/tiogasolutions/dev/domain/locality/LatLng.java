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

import org.tiogasolutions.dev.common.StringUtils;
import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;

public class LatLng {

  private String latitude;
  private String longitude;
  private String city;
  private State state;
  private Country country;
  private String label;

  public LatLng(String latitude, String longitude) {
    this(latitude, longitude, null, null, null);
  }

  public LatLng(String latitude, String longitude, String city, State state, Country country) {
    this(latitude, longitude, city, state, country, null);
  }

  public LatLng(String latitude, String longitude, String city, State state, Country country, String label) {
    ExceptionUtils.assertNotNull(latitude, "latitude");
    ExceptionUtils.assertNotNull(longitude, "longitude");

    this.latitude = latitude;
    this.longitude = longitude;

    this.city = city;
    this.state = state;
    this.country = country;

    this.label = (label != null) ? label : buildLabel(latitude, longitude, city, state, country);
  }

  public static String buildLabel(String latitude, String longitude, String city, State state, Country country) {
    if (StringUtils.isNotBlank(city) && StringUtils.isNotBlank(state)) {
      if (StringUtils.isNotBlank(country) || Country.US.equals(country)) {
        return city+", "+state;
      } else {
        return city+", "+state + " " + country;
      }
    } else {
      return latitude + ", " + longitude;
    }
  }

  public String getLabel() {
    return label;
  }

  public String getCity() {
    return city;
  }

  public State getState() {
    return state;
  }

  public Country getCountry() {
    return country;
  }

  public String getLatitude() {
    return latitude;
  }
  public String getLongitude() {
    return longitude;
  }

  public double getLatitudeDouble() {
    return Double.valueOf(latitude);
  }
  public double getLongitudeDouble() {
    return Double.valueOf(longitude);
  }

  public double between(LatLng that) {
    double earthRadius = 3958.75;
    double dLat = Math.toRadians(that.getLatitudeDouble()-this.getLatitudeDouble());
    double dLng = Math.toRadians(that.getLongitudeDouble()-this.getLongitudeDouble());
    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
               Math.cos(Math.toRadians(this.getLatitudeDouble())) * Math.cos(Math.toRadians(that.getLatitudeDouble())) *
               Math.sin(dLng/2) * Math.sin(dLng/2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    return earthRadius * c;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof LatLng) {
      LatLng that = (LatLng)object;
      return this.latitude.equals(that.latitude) &&
             this.longitude.equals(that.longitude);
    }
    return false;
  }

  public String toString() {
    return label;
  }
}
