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

import java.io.Serializable;
import java.util.Map;

/**
 * User: harlann
 * Date: 8/22/13
 * Time: 10:45 PM
 */
public class FineMessage implements Serializable {

  private final String text;
  private final String id;
  private final TraitMap traitMap;

  public static FineMessage withText(String text) {
    return new FineMessage(text, null, TraitMap.empty());
  }

  public static FineMessage withId(String text, String id) {
    return new FineMessage(text, id, TraitMap.empty());
  }

  public static FineMessage withTraits(String text, TraitMap traitMap) {
    return new FineMessage(text, null, traitMap);
  }

  public static FineMessage withTraits(String text, Map<String, String> traitMap) {
    return new FineMessage(text, null, new TraitMap(traitMap));
  }

  public static FineMessage withTraits(String text, String... traits) {
    return new FineMessage(text, null, new TraitMap(traits));
  }

  public static FineMessage withAll(String text, String id, String...traits) {
    return new FineMessage(text, id, new TraitMap(traits));
  }
  public static FineMessage withAll(String text, String id, TraitMap traitMap) {
    return new FineMessage(text, id, traitMap);
  }


  protected FineMessage(String text, String id, TraitMap traitMap) {
    this.text = (text != null) ? text : "";
    this.id = id;

    if (traitMap == null) {
      this.traitMap = TraitMap.empty();
    } else {
      Map map = traitMap.getMap();
      this.traitMap = new TraitMap(map);
    }
  }

  public boolean isId(String id) {
    return (this.id == null) ? id == null : this.id.equals(id);
  }

  public boolean isText(String givenText) {
    return text.equalsIgnoreCase(givenText);
  }

  public String getDisplay() {
    StringBuilder sb = new StringBuilder();
    if (id != null) {
      sb.append(id);
      sb.append(": ");
    }
    sb.append(text);
    if (traitMap.isNotEmpty()) {
      sb.append(" [");
      sb.append(traitMap);
      sb.append("]");
    }
    return sb.toString();
  }

  public String getText() {
    return text;
  }

  public String getId() {
    return id;
  }

  public TraitMap getTraitMap() {
    return traitMap;
  }

  public boolean hasTraits() {
    return traitMap.isNotEmpty();
  }

  public boolean hasTrait(String key) {
    return traitMap.hasTrait(key);
  }

  public boolean hasValue(String key, String checkValue) {
    return traitMap.hasValue(key, checkValue);
  }

  public String getTraitValue(String traitName) {
    return traitMap.getValue(traitName);
  }

  public int getTraitCount() {
    return getTraitMap().getSize();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FineMessage that = (FineMessage) o;

    if (id != null ? !id.equals(that.id) : that.id != null) {
      return false;
    } else if (!text.equals(that.text)) {
      return false;
    } else if (!traitMap.equals(that.traitMap)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = getClass().hashCode();
    result = 31 * result + text.hashCode();
    result = 31 * result + (id != null ? id.hashCode() : 0);
    result = 31 * result + traitMap.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return getDisplay();
  }

  public FineMessageSet toSet() {
    return new FineMessageSetBuilder().withMessage(this).build();
  }
}
