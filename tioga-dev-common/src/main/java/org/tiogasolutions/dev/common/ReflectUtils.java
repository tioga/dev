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

package org.tiogasolutions.dev.common;

import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ReflectUtils {

  private ReflectUtils() {
    int i = 2;
  }

  public static Field getField(Class<?> type, final String name) {
    while (type != null) {
      Field[] fields = type.getDeclaredFields();
      for (Field field : fields) {
        if (name.equals(field.getName())) {
          return field;
        }
      }
      type = type.getSuperclass();
    }
    return null;
  }

  public static Object getFieldValue(final Object entity, final Field field) {
    ExceptionUtils.assertNotNull(entity, "entity");
    ExceptionUtils.assertNotNull(field, "field");

    if (entity == null) {
      return null;
    }
    try {
      field.setAccessible(true);
      return field.get(entity);

    } catch (Exception ex) {
      // I don't want to have to code to this.
      throw new RuntimeException("Exception reflecting field's value", ex);
    }
  }

  /**
   * Uses the specified entity, field and value to identify a traditional setter method. If
   * one cannot be used, then setFieldValue(..) will be used instead.
   * @param entity The entity containing the field.
   * @param field The field to update
   * @param value The value to set the field to
   */
  public static void setPropertyValue(final Object entity, final Field field, Object value) {
    ExceptionUtils.assertNotNull(field, "field");

    Method setter = ReflectUtils.getWriteMethod(field.getDeclaringClass(), field.getName(), field.getType());

    if (setter == null) {
      setFieldValue(entity, field, value);
      return;
    }

    try {
      setter.setAccessible(true);
      setter.invoke(entity, value);

    } catch (Exception ex) {
      // I don't want to have to code to this.
      throw new RuntimeException("Exception reflecting field's value", ex);
    }
  }

  public static void setFieldValue(final Object entity, final Field field, Object value) {
    ExceptionUtils.assertNotNull(field, "field");

    try {
      field.setAccessible(true);
      field.set(entity, value);

    } catch (Exception ex) {
      // I don't want to have to code to this.
      throw new RuntimeException("Exception reflecting field's value", ex);
    }
  }

  public static Method getMethod(Class<?> type, String methodName, Class...parameterTypes) {
    try {
      return type.getMethod(methodName, parameterTypes);
    } catch (NoSuchMethodException e) {
      // not found, keep trying
    }
    return null;
  }

  public static Method getWriteMethod(Class<?> type, String propertyName, Class argType) {
    String methodName = "set" + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
    try {
      return type.getMethod(methodName, argType);
    } catch (NoSuchMethodException e) {/* not found, keep trying */}
    
    return null;
  }
  
  public static Method getReadMethod(Class<?> type, String propertyName) {
    String[] prefixes = {"get", "is", "was", "has"};
    for (String prefix : prefixes) {
      String methodName = prefix + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
      try {
        return type.getMethod(methodName);
      } catch (NoSuchMethodException e) {/* not found, keep trying */}
    }
    return null;
  }

  /**
   * @param object the object for which a name is desired.
   * @return the simple class name of the object, correctly dealing with null.
   */
  public static String getName(Object object) {
    return (object == null) ? null : object.getClass().getSimpleName();
  }

  /**
   * @param offset the offset into the stack trace.
   * @return the qualified name of the current method.
   */
  public static String getQualifiedMethodName(int offset) {
    StackTraceElement[] elements = Thread.currentThread().getStackTrace();
    StackTraceElement element = elements[offset + 2];
    return element.getClassName()+"."+element.getMethodName();
  }
  
  /**
   * @param offset the offset into the stack trace.
   * @return the name of the current method.
   */
  public static String getMethodName(int offset) {
    return getElement(offset+1).getMethodName();
  }
  
  public static String getClassName(int offset) {
    return getElement(offset+1).getClassName();
  }

  public static StackTraceElement getElement(int offset) {
    StackTraceElement[] elements = Thread.currentThread().getStackTrace();
    return elements[offset + 2];
  }
  
  public static <T> T[] toArray(Class<T> type, Collection<T> collection) {
    
    // noinspection unchecked
    T[] array = (T[]) Array.newInstance(type, collection.size());
    
    int i = 0;
    for (T t : collection) {
      array[i] = t;
      i++;
    }
    
    return array;
  }

  public static <T> T getValueOf(Class<T> type, String stringValue) {
    try {
      if (stringValue == null) {
        return null;
      }

      String methodName = "valueOf";
      Method method = ReflectUtils.getMethod(type, methodName, String.class);

      if (method == null) {
        String msg = String.format("The method %s.%s does not exist.", type.getName(), "valueOf");
        throw new IllegalArgumentException(msg);
      }
      // noinspection unchecked
      return (T)method.invoke(null, stringValue);

    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Field> getFields(Class type) {
    List<Field> fields = new ArrayList<Field>();

    while (type != null) {
      Collections.addAll(fields, type.getDeclaredFields());
      type = type.getSuperclass();
    }

    return fields;
  }

  public static List<Field> getNonStaticFields(Class type) {
    List<Field> fields = new ArrayList<Field>();

    while (type != null) {
      for (Field field : type.getDeclaredFields()) {
        if (Modifier.isStatic(field.getModifiers()) == false) {
          fields.add(field);
        }
      }
      type = type.getSuperclass();
    }

    return fields;
  }

  public static List<Field> getStaticFields(Class type) {
    List<Field> fields = new ArrayList<Field>();

    while (type != null) {
      for (Field field : type.getDeclaredFields()) {
        if (Modifier.isStatic(field.getModifiers())) {
          fields.add(field);
        }
      }
      type = type.getSuperclass();
    }

    return fields;
  }

  public static Field getAnnotatedField(Object entity, Class<? extends Annotation> annotation) {
    for (Field field : getFields(entity.getClass())) {
      if (field.isAnnotationPresent(annotation)) {
        return field;
      }
    }
    return null;
  }
}







