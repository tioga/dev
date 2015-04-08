package org.tiogasolutions.dev.jackson;

/**
* Created by jacobp on 7/21/2014.
*/
public class TiogaJacksonInjectable {

  private final String key;
  private final Object value;

  public TiogaJacksonInjectable(String key, Object value) {
    this.key = key;
    this.value = value;
  }

  public TiogaJacksonInjectable(Class key, Object value) {
    this.key = key.getName();
    this.value = value;
  }

  public TiogaJacksonInjectable(Object value) {
    this.key = value.getClass().getName();
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public Object getValue() {
    return value;
  }
}
