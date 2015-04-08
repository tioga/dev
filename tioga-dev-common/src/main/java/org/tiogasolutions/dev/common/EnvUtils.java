package org.tiogasolutions.dev.common;

import java.util.Map;

public class EnvUtils {

  private EnvUtils() {
  }

  public static String getEnvironmentProperty(String propertyName) {
    return System.getenv(propertyName);
  }

  public static String getEnvironmentProperty(String propertyName, String defaultValue) {
    String actualValue = System.getenv(propertyName);
    return (StringUtils.isBlank(actualValue)) ? defaultValue : actualValue;
  }

  public static String getSystemProperty(String propertyName) {
    return System.getProperty(propertyName);
  }

  public static String getSystemProperty(String propertyName, String defaultValue) {
    String actualValue = System.getProperty(propertyName);
    return (StringUtils.isBlank(actualValue)) ? defaultValue : actualValue;
  }

  public static String requireProperty(String propertyName) {
    String value = findProperty(propertyName, null);
    if (StringUtils.isBlank(value)) {
      String msg = String.format("The system or environment property \"%s\" must be specified.", propertyName);
      throw new IllegalArgumentException(msg);
    }
    return value;
  }

  public static String findProperty(String propertyName) {
    return findProperty(propertyName, null);
  }

  public static String findProperty(String propertyName, String defaultValue) {
    String value = getSystemProperty(propertyName);
    if (StringUtils.isNotBlank(value)) {
      return value;
    } else {
      return findEnvironmentProperty(System.getenv(), propertyName, defaultValue);
    }
  }

  /** Broken out for testing, this method should not be called directly. */
  /*local*/ static String findEnvironmentProperty(final Map<String,String> envMap, final String name, final String defaultValue) {

    String key = name;
    String value = envMap.get(name);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }

    // The remaining cases check the environment with a modified
    // version of the property name to account for case and delimiters.


    // Same delimiter with upper case.
    key = name.toUpperCase();
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }

    // Same delimiter with lower case
    key = name.toLowerCase();
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }


    // Period->Underscore, natural case
    key = name.replaceAll("\\.","_");
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }

    // Period->Underscore, upper case
    key = name.replaceAll("\\.","_").toUpperCase();
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }

    // Period->Underscore, lower case
    key = name.replaceAll("\\.","_").toLowerCase();
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }


    // Underscore->Period, natural case
    key = name.replaceAll("_","\\.");
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }

    // Underscore->Period, upper case
    key = name.replaceAll("_","\\.").toUpperCase();
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }

    // Underscore->Period, lower case
    key = name.replaceAll("_","\\.").toLowerCase();
    value = envMap.get(key);
    if (StringUtils.isNotBlank(value)) {
      return value;
    }

    return defaultValue;
  }
}
