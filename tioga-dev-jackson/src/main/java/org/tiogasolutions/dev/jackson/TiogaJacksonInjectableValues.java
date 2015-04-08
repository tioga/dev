/*
 * Copyright 2012 Harlan Noonkester
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

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;

public class TiogaJacksonInjectableValues extends InjectableValues.Std {

  public TiogaJacksonInjectableValues() {
  }

  @Override
  public Object findInjectableValue(Object valueId, DeserializationContext context, BeanProperty forProperty, Object beanInstance) {
    if (!(valueId instanceof String)) {
      String type = (valueId == null) ? "[null]" : valueId.getClass().getName();
      throw new IllegalArgumentException("Unrecognized inject value id type ("+type+"), expecting String");
    }
    String key = (String) valueId;
    return _values.get(key);
  }
}
