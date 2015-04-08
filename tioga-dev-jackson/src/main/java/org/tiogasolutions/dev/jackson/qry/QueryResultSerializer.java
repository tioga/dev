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

package org.tiogasolutions.dev.jackson.qry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.tiogasolutions.dev.domain.query.QueryResult;

import java.io.IOException;

/**
 * User: harlan
 * Date: 7/21/12
 * Time: 10:36 PM
 */

public final class QueryResultSerializer extends StdSerializer<QueryResult> {

  public QueryResultSerializer() {
      super(QueryResult.class);
  }

  @Override
  public void serialize(QueryResult results, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();

    gen.writeFieldName("containsType");
    gen.writeObject(results.getContainsType().getName());

    gen.writeFieldName("empty");
    gen.writeObject(results.isEmpty());

    gen.writeFieldName("limit");
    gen.writeObject(results.getLimit());

    gen.writeFieldName("size");
    gen.writeObject(results.getSize());

    gen.writeFieldName("totalFound");
    gen.writeObject(results.getTotalFound());

    gen.writeFieldName("totalExact");
    gen.writeObject(results.isTotalExact());

    gen.writeFieldName("offset");
    gen.writeObject(results.getOffset());

    gen.writeFieldName("hasPrevious");
    gen.writeObject(results.getHasPrevious());

    gen.writeFieldName("hasNext");
    gen.writeObject(results.getHasNext());

    gen.writeFieldName("results");
    gen.writeStartArray();
    for (Object resultItem : results.getResults()) {
      gen.writeObject(resultItem);
    }
    gen.writeEndArray();
    gen.writeEndObject();
  }
}

