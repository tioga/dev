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

package org.tiogasolutions.dev.jackson.trait;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.tiogasolutions.dev.common.fine.TraitMap;

import java.io.IOException;
import java.util.Map;

/**
 * User: harlan
 * Date: 7/21/12
 * Time: 10:36 PM
 */
public final class TraitMapDeserializer extends StdDeserializer<TraitMap> {

  public TraitMapDeserializer() {
      super(TraitMap.class);
  }

  @Override
  public TraitMap deserialize(JsonParser jp, DeserializationContext context) throws IOException {
    Map map = jp.readValueAs(Map.class);
    return new TraitMap(map);
  }
}

