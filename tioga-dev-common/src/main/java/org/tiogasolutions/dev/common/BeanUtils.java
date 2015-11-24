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

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.reflect.Modifier.*;

public class BeanUtils {

  public static List<String> getValuesFromCollection(Collection<?> objects, String propertyName) {
    try {
      final Object[] args = new Object[0];
      List<String> retVal = new ArrayList<>();
      for (Object object : objects) {
        if (object == null) {
          retVal.add(null);
        } else {
          Method method = ReflectUtils.getReadMethod(object.getClass(), propertyName);
          if (method == null) {
            throw new IllegalArgumentException("The accessor method for the property \"" + propertyName + "\" does not exist.");
          }
          Object value = method.invoke(object, args);
          if (value instanceof String || value instanceof Number) {
            retVal.add(value.toString());
          } else if (value instanceof Boolean) {
            retVal.add((Boolean) value ? "1" : "0");
          } else {
            retVal.add(null);
          }
        }
      }
      return retVal;

    } catch (Exception ex) {
      throw new RuntimeException("Exception building list", ex);
    }
  }

  static public StringBuffer getObjectAsString(Object object, boolean newline) {
    Class<?> type = object.getClass();
    final String space = "     ";
    StringBuffer buffer = new StringBuffer();
    buffer.append(type.getName());
    buffer.append(":");
    buffer.append(space);

    HashMap<String, String> map = new HashMap<>();

    while (type != null) {
      java.lang.reflect.Field fields[] = type.getDeclaredFields();
      for (Field field : fields) {
        try {
          if (isStatic(field.getModifiers()) && isFinal(field.getModifiers())) {
            continue;
          }

          if (map.get(field.getName()) == null) {
            map.put(field.getName(), field.getName());

            field.setAccessible(true);
            Object value = field.get(object);
            buffer.append("(");

            if (value == null) {
              buffer.append(field.getName());
              buffer.append("=null");

            } else if (field.getType().isArray()) {
              buffer.append("[");
              for (int arrayDex = 0; arrayDex < Array.getLength(value); arrayDex++) {
                buffer.append(getObjectAsString(Array.get(value, arrayDex), false));
                if (arrayDex < Array.getLength(value) - 1) {
                  buffer.append(",");
                  buffer.append(space);
                }
              }
              buffer.append("]");

            } else {
              buffer.append(field.getName());
              buffer.append("=");
              buffer.append(value.toString());
            }

            buffer.append(")");

            if (newline) {
              buffer.append("\n");
            } else if (field != fields[fields.length - 1]) {
              buffer.append(",");
            }
            buffer.append(space);
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
          System.exit(0);
        }
      }
      type = type.getSuperclass();
    }
    return buffer;
  }

  @Deprecated
  public static boolean objectsNotEqual(Object valueA, Object valueB) {
    return EqualsUtils.objectsNotEqual(valueA, valueB);
  }

  /**
   * @see EqualsUtils#objectsEqual(Object, Object)
   * @deprecated
   * @param <T> an type of object
   * @param valueA the first value to compare
   * @param valueB the second value to compare
   * @return true if the values are both equal.
   */
  @Deprecated
  public static <T> boolean objectsEqual(T valueA, T valueB) {
    return EqualsUtils.objectsEqual(valueA, valueB);
  }

  /**
   * @see EqualsUtils#datesEqual(java.util.Date, java.util.Date)
   * @deprecated
   * @param dateA the first value to compare
   * @param dateB the second value to compare
   * @return true if the values are both equal to the day.
   */
  @Deprecated
  static public boolean datesEqual(Date dateA, Date dateB) {
    return EqualsUtils.datesEqual(dateA, dateB);
  }

  public static <T> int compare(Comparable<T> objectA, T objectB) {

    if (objectA == objectB) {
      return 0;
    }

    int result = compareForNull(objectA, objectB);
    if (result != 0) return result;

    // noinspection unchecked
    return objectA.compareTo(objectB);
  }

  private static int compareForNull(Object objectA, Object objectB) {
    if (objectA == null && objectB != null) {
      return 1;
    } else if (objectA != null && objectB == null) {
      return -1;
    } else {
      return 0;
    }
  }

  @SafeVarargs
  public static <T> T[] addToArray(T[] array, T... items) {
    List<T> list = new ArrayList<>(array.length + items.length);
    Collections.addAll(list, array);
    // Rewritten to remove warnings about potential heap pollution.
    // noinspection ManualArrayToCollectionCopy
    for (T item : items) list.add(item);
    return list.toArray(array);
  }

  @SafeVarargs
  public static <T> T[] removeFromArray(T[] array, T... items) {
    List<T> list = new ArrayList<>(array.length);
    Collections.addAll(list);
    for (T item : items) {
      list.remove(item);
    }
    return list.toArray(array);
  }

  @SafeVarargs
  public static <T> T[] replaceInArray(T[] array, T... items) {
    List<T> list = new ArrayList<>(array.length);
    Collections.addAll(list);

    // Rewritten to remove warnings about potential heap pollution.
    for (T item : items) {
      list.remove(item);
    }

    // noinspection ManualArrayToCollectionCopy
    for (T item : items) {
      list.add(item);
    }

    return list.toArray(array);
  }

  public static LinkedHashMap<String, String> toMap(String... keyValuePairs) {
    List<String> list = new ArrayList<>();
    if (keyValuePairs != null) {
      Collections.addAll(list, keyValuePairs);
    }
    return toMap(list);
  }

  public static LinkedHashMap<String, String> toMap(Collection<String> keyValuePairs) {

    LinkedHashMap<String, String> map = new LinkedHashMap<>();

    if (keyValuePairs == null) {
      return map;
    }

    for (String pair : keyValuePairs) {
      int pos = (pair == null) ? -1 : pair.indexOf(":");

      if (pair == null) {
        map.put(null, null);

      } else if (pos < 0) {
        map.put(pair, null);

      } else {
        String key = pair.substring(0, pos);
        String value = pair.substring(pos + 1);
        map.put(key, value);
      }
    }
    return map;
  }

  public static <T> List<T> union(Collection<T> collectionA, Collection<T> collectionB) {
    List<T> union = new ArrayList<>();

    union.addAll(collectionA);
    for (T entry : collectionB) {
      if (collectionA.contains(entry) == false) {
        union.add(entry);
      }
    }

    return union;
  }

  public static <T> List<T> intersection(Collection<? extends T> collectionA, Collection<? extends T> collectionB) {
    int size = Math.max(collectionA.size(), collectionB.size());
    List<T> list = new ArrayList<>(size);

    for (T object : collectionA) {
      if (collectionB.contains(object)) {
        list.add(object);
      }
    }

    return list;
  }
}
