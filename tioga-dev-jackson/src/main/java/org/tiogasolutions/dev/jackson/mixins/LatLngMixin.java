/*
 * Copyright 2014 Harlan Noonkester
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

package org.tiogasolutions.dev.jackson.mixins;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.tiogasolutions.dev.domain.locality.Country;
import org.tiogasolutions.dev.domain.locality.State;

public abstract class LatLngMixin {

  @JsonCreator
  public LatLngMixin(@JsonProperty("latitude") String latitude,
                     @JsonProperty("longitude") String longitude,
                     @JsonProperty("city") String city,
                     @JsonProperty("state") State state,
                     @JsonProperty("country") Country country) {
  }

  @JsonIgnore
  public abstract String getLabel();

  @JsonIgnore
  public abstract double getLatitudeDouble();

  @JsonIgnore
  public abstract double getLongitudeDouble();
}

