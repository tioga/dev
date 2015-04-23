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

package org.tiogasolutions.dev.common.fine;

import org.tiogasolutions.dev.common.BeanUtils;
import org.tiogasolutions.dev.common.EqualsUtils;

import java.io.Serializable;
import java.util.*;

public class TraitMap implements Serializable {

  private static final TraitMap empty = new TraitMap();
  public static TraitMap empty() {
    return empty;
  }

  private final Map<String, String> m;

  public TraitMap(TraitMap givenMap) {
    this(givenMap == null ? null : givenMap.getMap());
  }

  public TraitMap(Map<?, ?> givenMap) {

    if (givenMap == null) {
      this.m = Collections.emptyMap();
      return;
    }

    SortedMap<String, String> localMap = new TreeMap<>();
    for (Map.Entry<?,?> entry : givenMap.entrySet()) {

      if (entry.getKey() == null) {
        continue;
      }

      String key = fixCase(entry.getKey().toString());
      Object value = entry.getValue();

      if (value == null) {
        localMap.put(key, null);
      } else {
        localMap.put(key, value.toString());
      }
    }

    this.m = Collections.unmodifiableSortedMap(localMap);
  }

  public TraitMap(String...traitStrings) {
    this(BeanUtils.toMap(traitStrings));
  }

  public TraitMap(Collection<String> traitStrings) {
    this(BeanUtils.toMap(traitStrings));
  }

  public TraitMap add(String...traits) {
    return add(traits == null ? Collections.emptyMap() : BeanUtils.toMap(traits));
  }

  public TraitMap add(Collection<String> traits) {
    return add(traits == null ? Collections.emptyMap() : BeanUtils.toMap(traits));
  }

  public TraitMap add(TraitMap map) {
    return add(map == null ? Collections.emptyMap() : map.getMap());
  }

  public TraitMap add(Map<?,?> traitMapArg) {
      if (traitMapArg == null) {
          return this;
      }
      Map<String, String> localMap = new HashMap<>(m);

      for (Map.Entry<?,?> entry : traitMapArg.entrySet()) {
        if (entry.getKey() == null) {
          // skip it...
        } else if (entry.getValue() == null) {
          localMap.put(entry.getKey().toString(), null);
        } else {
          localMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
      }

      return new TraitMap(localMap);
  }

  public TraitMap remove(String...traits) {
    return remove (traits == null ? Collections.<String>emptyList() : Arrays.asList(traits));
  }

  public TraitMap remove(Collection<String> traits) {
      if (traits == null) return this;

      Map<String, String> newMap = new HashMap<>();

      for (Map.Entry<String,String> entry : m.entrySet()) {
        if (containsKey(traits, entry.getKey()) == false) {
          newMap.put(entry.getKey(), entry.getValue());
        }
      }

      return new TraitMap(newMap);
  }

  protected boolean containsKey(Map<String,String> map, String key) {
    return containsKey(map.keySet(), key);
  }

  protected boolean containsKey(Collection<String> collection, String key) {
    for (String entry : collection) {
      if (keysEqual(entry, key)) {
        return true;
      }
    }
    return false;
  }

  protected boolean keysEqual(String keyA, String keyB) {
    if (keyA == null && keyB == null) return true;
    if (keyA == null || keyB == null) return false;
    return keyA.equalsIgnoreCase(keyB);
  }

  public Map<String, String> getMap() {
  return m;
  }

  public boolean isEmpty() {
    return m.isEmpty();
  }

  public boolean isNotEmpty() {
    return !m.isEmpty();
  }

  public int getSize() {
    return m.size();
  }

  public boolean hasTrait(String key) {
    if (key == null) return false;

    return containsKey(m.keySet(), key);
  }

  public boolean hasValue(String key, String checkValue) {
    if (key == null) return false;

    String value = getValue(key);
    return EqualsUtils.objectsEqual(checkValue, value);
  }

  public String getValue(String key) {
    for (Map.Entry<String,String> entry : m.entrySet()) {
      if (keysEqual(entry.getKey(), key)) {
        return entry.getValue();
      }
    }
    return null;
  }

  public String getText() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> entry : m.entrySet()) {
      sb.append(entry.getKey());
      sb.append(":");

      if (entry.getValue() == null) {
        sb.append("null");
      } else {
        sb.append("\"");
        sb.append(entry.getValue());
        sb.append("\"");
      }

      sb.append(", ");
    }
    if (sb.length() > 2) {
      sb.delete(sb.length() - 2, sb.length());
    }
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TraitMap that = (TraitMap) o;

    int thisCount = this.getSize();
    int thatCount = that.getSize();
    if (thisCount != thatCount) {
      return false;
    }

    List<String> thisKeys = new ArrayList<>(this.getMap().keySet());
    List<String> thatKeys = new ArrayList<>(that.getMap().keySet());

    for (int i = 0; i < thisKeys.size(); i++) {
      String thisKey = thisKeys.get(i);
      String thatKey = thatKeys.get(i);
      if (thisKey.equalsIgnoreCase(thatKey) == false) {
        return false;
      }

      String thisValue = this.getValue(thisKey);
      String thatValue = that.getValue(thatKey);
      if (EqualsUtils.objectsNotEqual(thisValue, thatValue)) {
        return false;
      }
    }

    return true;

  }

  @Override
  public int hashCode() {
    int result = getClass().getName().hashCode();
    result = 31 * result + m.hashCode();
    return result;
  }

  public String toString() {
    return getText();
  }

  protected String fixCase(String value) {
    return value;
  }
}
