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

package org.tiogasolutions.dev.domain.validation;

import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

public interface BeanValidator {

    FineMessageSet validate(Object object, Class<?>... groups);

    void validateWithThrow(Object object, Class<?>... groups);

    void validateWithThrow(Object object, HttpStatusCode statusCode, Class<?>... groups);

    FineMessageSet validateProperty(Object object, String propertyName, Class<?>... groups);

}
