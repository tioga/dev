package org.tiogasolutions.dev.common;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public class EqualsUtils {

  private EqualsUtils() {
  }

  /**
   * Helper method for testing equality of objects while handling the issue of nulls
   * @param <T> an type of object
   * @param valueA the first value to compare
   * @param valueB the second value to compare
   * @return true if the values are both equal.
   */
  public static <T> boolean objectsEqual(T valueA, T valueB) {
    if (valueA == valueB) {
      return true; // Same instance or both null
    } else if (valueA == null || valueB == null) {
      return false; // One but not both are null
    } else {
      return valueA.equals(valueB);
    }
  }

  public static <T> boolean objectsNotEqual(T valueA, T valueB) {
    return objectsEqual(valueA, valueB) == false;
  }

  static public boolean datesEqual(Date dateA, Date dateB) {
    if (dateA == null && dateB == null) {
      return true;
    } else if (dateA == null || dateB == null) {
      return false;
    } else {
      GregorianCalendar calA = new GregorianCalendar();
      calA.setTime(dateA);
      GregorianCalendar calB = new GregorianCalendar();
      calB.setTime(dateB);

      return (calA.get(Calendar.DATE) == calB.get(Calendar.DATE) &&
          calA.get(Calendar.MONTH) == calB.get(Calendar.MONTH) &&
          calA.get(Calendar.YEAR) == calB.get(Calendar.YEAR));
    }
  }

  static public boolean datesNotEqual(Date dateA, Date dateB) {
    return datesEqual(dateA, dateB) == false;
  }

  public static boolean bigDecimalsEqual(BigDecimal valueA, BigDecimal valueB) {
    return NumberUtils.bigDecimalsEqual(valueA, valueB);
  }
  public static boolean bigDecimalsNotEqual(BigDecimal valueA, BigDecimal valueB) {
    return NumberUtils.bigDecimalsNotEqual(valueA, valueB);
  }

  public static ComparisonResults compare(Object objectA, Object objectB) {
    ComparisonResults.Builder builder = ComparisonResults.builder();
    return compare(builder, null, objectA, objectB, "root").build();
  }

  private static ComparisonResults.Builder compare(ComparisonResults.Builder builder, Field field, Object objectA, Object objectB, final String beanName) {

    Object valueA = (field == null) ? objectA : ReflectUtils.getFieldValue(objectA, field);
    Object valueB = (field == null) ? objectB : ReflectUtils.getFieldValue(objectB, field);

    if (valueA == valueB) {
      return builder;

    } else if (valueA == null) {
      return builder.add(beanName, null, valueB, ComparisonResults.VALUE_A_IS_NULL);

    } else if (valueB == null) {
      return builder.add(beanName, valueA, null, ComparisonResults.VALUE_B_IS_NULL);
    }

    if (valueA instanceof Collection<?> && valueB instanceof Collection<?>) {
      Collection<?> collectionA = (Collection<?>)valueA;
      Collection<?> collectionB = (Collection<?>)valueB;
      return compareCollection(builder, collectionA, collectionB, beanName);

    } else if (valueA instanceof Map && valueB instanceof Map) {
      return compareMaps(builder, (Map)valueA, (Map)valueB, beanName);

    } else if (valueA.getClass().equals(valueB.getClass()) == false) {
      return builder.add(beanName, valueA.getClass(), valueB.getClass(), ComparisonResults.CLASSES_DIFFERENT);
    }

    Class<?> type = valueA.getClass();

    if (isSimple(type)) {
      boolean equal = EqualsUtils.objectsEqual(valueA, valueB);
      return (equal) ? builder : builder.add(beanName, valueA, valueB, ComparisonResults.NOT_EQUAL);

    } else if (valueA instanceof Object[] && valueB instanceof Object[]) {
      List<?> collectionA = Arrays.asList((Object[])valueA);
      List<?> collectionB = Arrays.asList((Object[])valueB);
      return compareCollection(builder, collectionA, collectionB, beanName);
    }

    List<Field> fields = ReflectUtils.getFields(valueA.getClass());
    for (Field nextField : fields) {
      boolean isStatic = Modifier.isStatic(nextField.getModifiers());
      boolean isTransient = Modifier.isTransient(nextField.getModifiers());
      if (isStatic == false && isTransient == false) {
        // no point in comparing static or transient values
        compare(builder, nextField, valueA, valueB, beanName + "." + nextField.getName());
      }
    }

    return builder;
  }

  private static ComparisonResults.Builder compareMaps(ComparisonResults.Builder builder, Map<?,?> mapA, Map<?,?> mapB, String beanName) {

    int countA = mapA.size();
    int countB = mapB.size();

    if (countA != countB) {
      builder.add(beanName+":count()", countA, countB, ComparisonResults.SIZE);
    }

    Collection<?> keysA = mapA.keySet();
    Collection<?> keysB = mapB.keySet();
    List<?> intersection = BeanUtils.intersection(keysA, keysB);

    for (Object key : intersection) {
      Object valueA = mapA.get(key);
      Object valueB = mapB.get(key);
      compare(builder, null, valueA, valueB, beanName+"["+key+"]");
    }

    for (Object key : mapA.keySet()) {
      if (intersection.contains(key) == false) {
        builder.add(beanName+"["+key+"]", mapA.get(key), null, ComparisonResults.MISSING_B);
      }
    }

    for (Object key : mapB.keySet()) {
      if (intersection.contains(key) == false) {
        builder.add(beanName+"["+key+"]", null, mapA.get(key), ComparisonResults.MISSING_A);
      }
    }

    return builder;
  }

  private static ComparisonResults.Builder compareCollection(ComparisonResults.Builder builder, Collection<?> collectionA, Collection<?> collectionB, String beanName) {

    List<?> listA = new ArrayList<>(collectionA);
    List<?> listB = new ArrayList<>(collectionB);

    int countA = listA.size();
    int countB = listB.size();

    if (countA != countB) {
      builder.add(beanName + ":count()", countA, countB, ComparisonResults.SIZE);
    }

    int max = Math.min(countA, countB);
    for (int i = 0; i < max; i++) {
      Object valueA = listA.get(i);
      Object valueB = listB.get(i);
      compare(builder, null, valueA, valueB, beanName + "[" + i + "]");
    }

    for (int i = max; i < listA.size(); i++) {
      builder.add(beanName+"["+i+"]", listA.get(i), null, ComparisonResults.MISSING_B);
    }

    for (int i = max; i < listB.size(); i++) {
      builder.add(beanName+"["+i+"]", null, listB.get(i), ComparisonResults.MISSING_A);
    }

    return builder;
  }

  /**
   * @param type the class to be evaluated.
   * @return true if the specified type is simple enough to be safely evaluated with it's equals(..) method.
   */
  private static boolean isSimple(Class<?> type) {

    List<Class<?>> types = Arrays.<Class<?>>asList(
        Boolean.class,
        Character.class, Byte.class, Short.class,
        Integer.class, Long.class, Float.class, Double.class,
        String.class,
        LocalDate.class, LocalTime.class, LocalDateTime.class
    );

    return type.isPrimitive() || types.contains(type);

  }
}
