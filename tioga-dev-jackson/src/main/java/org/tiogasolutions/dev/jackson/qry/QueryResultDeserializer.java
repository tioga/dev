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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.domain.query.ListQueryResult;
import org.tiogasolutions.dev.domain.query.QueryResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: harlan
 * Date: 7/21/12
 * Time: 10:36 PM
 */
@SuppressWarnings("rawtypes")
public final class QueryResultDeserializer extends StdDeserializer<QueryResult> {

  private static final long serialVersionUID = 1L;

  public QueryResultDeserializer() {
    super(QueryResult.class);
  }

  @Override
  public QueryResult deserialize(JsonParser parser, DeserializationContext context) throws IOException {

    String containsTypeName = null;
    Class<?> containsType = null;
    Integer limit = null;
    Integer offset = null;
    Integer totalFound = null;
    Boolean totalExact = null;
    List<Object> results = new ArrayList<>();

    try {

      while(parser.nextToken() != null) {
        String name = parser.getCurrentName();
        if ("containsType".equalsIgnoreCase(name)) {
          parser.nextToken();
          containsTypeName = parser.getValueAsString();
          containsType = Class.forName(containsTypeName);

        } else if ("offset".equalsIgnoreCase(name)) {
          parser.nextToken();
          offset = parser.getIntValue();

        } else if ("limit".equalsIgnoreCase(name)) {
          parser.nextToken();
          limit = parser.getIntValue();

        } else if ("totalFound".equalsIgnoreCase(name)) {
          parser.nextToken();
          totalFound = parser.getIntValue();

        } else if ("totalExact".equalsIgnoreCase(name)) {
          parser.nextToken();
          totalExact = parser.getBooleanValue();

        } else if ("results".equalsIgnoreCase(name)) {
          // Start of results
          parser.nextToken();
          if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
            while (parser.nextToken()!=JsonToken.END_ARRAY) {
              Object item = parser.readValueAs(containsType);
              results.add(item);
            }
          }

        } else {
          // Field we don't care about, skip it.
          parser.nextToken();
        }

      }

    } catch (ClassNotFoundException ex) {
      String msg = String.format("Error instantiating result containsType %s", containsTypeName);
      throw ApiException.internalServerError(msg, ex);
    }

    // Verify all key values were found.
    if (containsType == null) {
      throw ApiException.internalServerError("QueryResult JSON does not specify containsType");
    }
    if (limit == null) {
      throw ApiException.internalServerError("QueryResult JSON does not specify limit");
    }
    if (offset == null) {
      throw ApiException.internalServerError("QueryResult JSON does not specify offset");
    }
    if (totalFound == null) {
      throw ApiException.internalServerError("QueryResult JSON does not specify totalFound");
    }
    if (totalExact == null) {
      throw ApiException.internalServerError("QueryResult JSON does not specify totalExact");
    }

    return ListQueryResult.newResult(containsType,
        limit,
        offset,
        totalFound,
        totalExact,
        results);
  }
}

