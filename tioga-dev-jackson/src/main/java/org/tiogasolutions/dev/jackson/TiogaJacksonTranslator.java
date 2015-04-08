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

package org.tiogasolutions.dev.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.common.json.JsonTranslator;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * User: harlann
 * Date: 8/23/13
 * Time: 11:13 PM
 */
public class TiogaJacksonTranslator implements JsonTranslator {

  private final ObjectMapper jacksonMapper;

  public TiogaJacksonTranslator() {
    this.jacksonMapper = createObjectMapper(TiogaJacksonObjectMapper.defaultModules(), Collections.<TiogaJacksonInjectable>emptyList());
  }

  public TiogaJacksonTranslator(Collection<? extends TiogaJacksonInjectable> injectables) {
    this.jacksonMapper = createObjectMapper(TiogaJacksonObjectMapper.defaultModules(), injectables);
  }

  public TiogaJacksonTranslator(Collection<? extends Module> modules, Collection<? extends TiogaJacksonInjectable> injectables) {
    this.jacksonMapper = createObjectMapper(modules, injectables);
  }

  public TiogaJacksonTranslator(ObjectMapper jacksonMapper) {
    this.jacksonMapper = jacksonMapper;
  }

  protected ObjectMapper createObjectMapper(Collection<? extends Module> modules, Collection<? extends TiogaJacksonInjectable> injectables) {
    return new TiogaJacksonObjectMapper(modules, injectables);
  }

  @Override
  public String toJson(Object domain) {
    try {
      return jacksonMapper.writeValueAsString(domain).replaceAll("\r", "");

    } catch (JsonProcessingException e) {
      throw ApiException.internalServerError(e);
    }
  }

  @Override
  public <T> T fromJson(Class<T> type, String json, Class<?>... parameterTypes) {

    if (parameterTypes != null && parameterTypes.length > 0) {
      try {
        JavaType javaType = jacksonMapper.getTypeFactory().constructParametricType(type, parameterTypes);
        return jacksonMapper.readValue(json, javaType);

      } catch (IOException e) {
        String msg = String.format("Exception translating %s from json.", type.getName());
        throw new TiogaJacksonTranslatorException(msg, e);
      }
    } else {
      try {
        return jacksonMapper.readValue(json, type);

      } catch (IOException e) {
        String msg = String.format("Exception translating %s from json.", type.getName());
        throw new TiogaJacksonTranslatorException(msg, e);
      }
    }
  }

}
