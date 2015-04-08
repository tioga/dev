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

package org.tiogasolutions.dev.jackson.msg;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.fine.FineMessageSetBuilder;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: harlan
 * Date: 7/21/12
 * Time: 10:36 PM
 */
public final class FineMessageSetDeserializer extends StdDeserializer<FineMessageSet> {

  public FineMessageSetDeserializer() {
      super(FineMessageSet.class);
  }

  @Override
  public FineMessageSet deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    FineMessageSetBuilder builder = new FineMessageSetBuilder();
    parser.nextToken();
    String name = parser.getCurrentName();
    parser.nextToken();
    if ("messages".equals(name)) {
      parser.nextToken();
      Iterator<FineMessage> it = parser.readValuesAs(FineMessage.class);
      while(it.hasNext()) {
        builder.withMessage(it.next());
      }
    } else {
      throw ApiException.internalServerError("Error during RichMessageSet translation, expecting \"message\" but node is " + name);
    }
    return builder.build();
  }
}

