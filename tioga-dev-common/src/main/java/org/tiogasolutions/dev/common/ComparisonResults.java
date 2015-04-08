package org.tiogasolutions.dev.common;

import java.util.*;

public class ComparisonResults {

  private static enum Type {
    SIZE,
    NOT_EQUAL,
    MISSING_A,
    MISSING_B,
    VALUE_A_IS_NULL,
    VALUE_B_IS_NULL,
    CLASSES_DIFFERENT,
  }

  public static Type SIZE = Type.SIZE;
  public static Type NOT_EQUAL = Type.NOT_EQUAL;
  public static Type MISSING_A = Type.MISSING_A;
  public static Type MISSING_B = Type.MISSING_B;
  public static Type VALUE_A_IS_NULL = Type.VALUE_A_IS_NULL;
  public static Type VALUE_B_IS_NULL = Type.VALUE_B_IS_NULL;
  public static Type CLASSES_DIFFERENT = Type.CLASSES_DIFFERENT;

  private final List<Result> results;
  private final Map<String,Result> validationMap = new HashMap<>();

  public ComparisonResults(List<Result> results) {
    this.results = Collections.unmodifiableList(new ArrayList<>(results));
    this.validationMap.putAll(toMap());
  }

  public static Builder builder() {
    return new Builder();
  }

  public boolean isEmpty() {
    return results.isEmpty();
  }

  public boolean isNotEmpty() {
    return results.isEmpty() == false;
  }

  public List<Result> toList() {
    return new ArrayList<>(results);
  }

  public Map<String,Result> toMap() {
    Map<String,Result> map = new HashMap<>();
    for (Result result : results) {
      map.put(result.getBeanName(), result);
    }
    return map;
  }

  public int getCount() {
    return results.size();
  }
  public int getValidatableCount() {
    return validationMap.size();
  }

  public Result get(int index) {
    return results.get(index);
  }

  public static class Builder {

    private final List<Result> results = new ArrayList<>();

    public Builder add(String beanName, Object valueA, Object valueB, Type type) {
      results.add(new Result(beanName, valueA, valueB, type));
      return this;
    }

    public ComparisonResults build() {
      return new ComparisonResults(this.results);
    }
  }

  public void ignore(String... beanNames) {
    ignore(Arrays.asList(beanNames));
  }

  public void ignore(Collection<String> beanNames) {
    for (String beanName : beanNames) {
      validationMap.remove(beanName);
    }
  }

  public void assertError(String beanName, Type type, Object valueA, Object valueB) throws ComparisonException {

    Result result = assertError(beanName, type); // start by validating the basics

    if (BeanUtils.objectsNotEqual(valueA, result.getValueA())) {
      String expectedName = ReflectUtils.getName(valueA);
      String actualName = ReflectUtils.getName(result.getValueA());
      String msg = String.format("Value A is not \"%s\" (%s) as expected but rather \"%s\" (%s).", valueA, expectedName, result.getValueA(), actualName);
      throw new ComparisonException(msg);
    }

    if (BeanUtils.objectsNotEqual(valueB, result.getValueB())) {
      String expectedName = ReflectUtils.getName(valueB);
      String actualName = ReflectUtils.getName(result.getValueB());
      String msg = String.format("Value B is not \"%s\" (%s) as expected but rather \"%s\" (%s).", valueB, expectedName, result.getValueB(), actualName);
      throw new ComparisonException(msg);
    }
  }

  public Result assertError(String beanName, Type type) throws ComparisonException {
    if (validationMap.containsKey(beanName) == false) {
      String msg = String.format("An error for the property \"%s\" does not exist: %s", beanName, validationMap.keySet());
      throw new ComparisonException(msg);
    }

    Result result = validationMap.remove(beanName);
    if (result.getType() != type) {
      String msg = String.format("The error %s does not exist for the property \"%s\", found %s.", type, beanName, result.getType());
      throw new ComparisonException(msg);
    }

    return result;
  }

  public void assertValidationComplete() throws ComparisonException {
    if (validationMap.isEmpty() == false) {
      String msg = String.format("Validation is not complete, %s errors remaining: %s", validationMap.size(), validationMap.keySet());
      throw new ComparisonException(msg);
    }
  }

  public static class Result {

    private final String beanName;
    private final Object valueA;
    private final Object valueB;
    private final Type type;

    private Result(String beanName, Object valueA, Object valueB, Type type) {
      this.beanName = beanName;
      this.valueA = valueA;
      this.valueB = valueB;
      this.type = type;
    }

    public String getBeanName() {
      return beanName;
    }

    public Object getValueA() {
      return valueA;
    }

    public Object getValueB() {
      return valueB;
    }

    public Type getType() {
      return type;
    }
  }

  public static class ComparisonException extends Exception {
    public ComparisonException(String message) {
      super(message);
    }
  }
}
