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

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * User: harlann
 * Date: 8/26/13
 * Time: 10:53 PM
 */
public class Person {
    public final String firstName;
    public final String lastName;
    public final int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    @Range(min=0)
    public int getAge() {
        return age;
    }
}
