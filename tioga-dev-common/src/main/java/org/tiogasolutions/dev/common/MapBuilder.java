package org.tiogasolutions.dev.common;

import java.util.*;

public class MapBuilder<K,V> {

  private final LinkedHashMap<K,V> map = new LinkedHashMap<>();

  public MapBuilder(Class<K> keyType, Class<V> valueType) {
  }

  public HashMap<K,V> buildHashMap() {
    return new HashMap<>(map);
  }

  public Hashtable<K,V> buildHashtable() {
    return new Hashtable<>(map);
  }

  public TreeMap<K,V> buildTreeMap() {
    return new TreeMap<>(map);
  }

  public LinkedHashMap<K,V> buildLinkedHashMap() {
    return map;
  }

  public MapBuilder<K,V> put(K key, V value) {
    map.put(key, value);
    return this;
  }
}
