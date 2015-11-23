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

package org.tiogasolutions.dev.jackson;

import com.fasterxml.jackson.databind.*;
import java.util.*;

public class TiogaJacksonObjectMapper extends ObjectMapper {

  private static final long serialVersionUID = 1L;

  private final Collection<? extends Module> modules;
  private final Collection<? extends TiogaJacksonInjectable> injectables;

  public static Collection<? extends Module> defaultModules() {
    return Arrays.asList(
        new TiogaJacksonModule());
  }

  public TiogaJacksonObjectMapper() {
    this(defaultModules(), Collections.<TiogaJacksonInjectable>emptyList());
  }

  public TiogaJacksonObjectMapper(Collection<? extends TiogaJacksonInjectable> injectables) {
    this(defaultModules(), injectables);
  }

  public TiogaJacksonObjectMapper(Collection<? extends Module> modules, Collection<? extends TiogaJacksonInjectable> injectables) {

    this.modules = modules;
    this.injectables = injectables;

    // Stops LocalDateTime from being rendered as array.
    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    // Pretty printing
    configure(SerializationFeature.INDENT_OUTPUT, true);

    // Empty strings to null
    configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);

    // Allows for extra data.
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    for (Module module : modules) {
      registerModule(module);
    }

    TiogaJacksonInjectableValues injectableValues = new TiogaJacksonInjectableValues();
    for (TiogaJacksonInjectable injectable : injectables) {
      injectableValues.addValue(injectable.getKey(), injectable.getValue());
    }
    setInjectableValues(injectableValues);
  }

  @Override
  public ObjectMapper copy() {
    _checkInvalidCopy(TiogaJacksonObjectMapper.class);
    return new TiogaJacksonObjectMapper(getModules(), getInjectables());
  }

  public Collection<? extends Module> getModules() {
    return Collections.unmodifiableCollection(modules);
  }

  public Collection<? extends TiogaJacksonInjectable> getInjectables() {
    return Collections.unmodifiableCollection(injectables);
  }
}
