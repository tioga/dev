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

package org.tiogasolutions.dev.jackson.time;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.time.ZonedDateTime;
import org.tiogasolutions.dev.common.DateUtils;

public final class ZonedDateTimeDeserializer extends StdScalarDeserializer<ZonedDateTime> {

  private static final long serialVersionUID = 1L;

  public ZonedDateTimeDeserializer() {
    super(ZonedDateTime.class);
  }

  @Override
  public ZonedDateTime deserialize(JsonParser jp, DeserializationContext context) throws IOException {
    String value = jp.readValueAs(String.class);
    ZonedDateTime retVal =  DateUtils.toZonedDateTime(value);
    return retVal;
  }
}

