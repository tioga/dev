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

import org.tiogasolutions.dev.common.exceptions.ApiException;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.fine.FineMessageSetBuilder;
import org.tiogasolutions.dev.common.fine.TraitMap;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * User: harlann
 * Date: 8/26/13
 * Time: 9:40 PM
 */
public class Jsr349BeanValidator implements BeanValidator {

    private final Validator validator;

    public Jsr349BeanValidator(Validator validator) {
        this.validator = validator;
    }

    public Jsr349BeanValidator(ValidatorFactory validatorFactory) {
        this.validator = validatorFactory.getValidator();
    }

    @Override
    public FineMessageSet validate(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object, groups);
        return newFineMessageSet(violations);
    }

    @Override
    public void validateWithThrow(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object, groups);
        FineMessageSet messageSet = newFineMessageSet(violations);
        if (messageSet.isNotEmpty()) {
            throw ApiException.badRequest(messageSet);
        }
    }

    @Override
    public void validateWithThrow(Object object, HttpStatusCode statusCode, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object, groups);
        FineMessageSet messageSet = newFineMessageSet(violations);
        if (messageSet.isNotEmpty()) {
            throw new ApiException(statusCode, messageSet);
        }
    }

    @Override
    public FineMessageSet validateProperty(Object object, String propertyName, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validateProperty(object, propertyName, groups);
        return newFineMessageSet(violations);
    }

    protected FineMessageSet newFineMessageSet(Set<ConstraintViolation<Object>> violations) {
        FineMessageSetBuilder builder = new FineMessageSetBuilder();
        for (ConstraintViolation<?> violation : violations) {
            String text = violation.getMessage();
            String propertyPath = violation.getPropertyPath().toString();
            if (propertyPath != null && !propertyPath.isEmpty()) {
                builder.withAll(text, propertyPath, new TraitMap("property:" + propertyPath));
            } else {
                builder.withText(text);
            }
        }
        return builder.build();
    }

    public Validator getValidator() {
        return validator;
    }

}
