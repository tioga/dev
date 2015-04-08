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

package org.tiogasolutions.dev.common.id;

import java.util.UUID;

/**
 * User: harlan
 * Date: 11/5/12
 * Time: 10:03 PM
 * IdGenerator which generates the same sequence each time (used for testing)
 */
public class StaticIdGenerator implements IdGenerator {

    private String id;

    public StaticIdGenerator() {
        id = UUID.randomUUID().toString();
    }

    public StaticIdGenerator(String id) {
        this.id = id;
    }

    @Override
    public String newId() {
        return id;
    }

    @Override
    public String getLastId() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}