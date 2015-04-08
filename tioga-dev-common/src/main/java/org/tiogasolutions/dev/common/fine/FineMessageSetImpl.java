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
import java.util.*;

/**
 * User: harlann
 * Date: 8/23/13
 * Time: 9:34 PM
 */
public class FineMessageSetImpl implements FineMessageSet, Serializable {

  private final List<FineMessage> messages;

  public FineMessageSetImpl() {
    this(Collections.<FineMessage>emptySet());
  }

  public FineMessageSetImpl(FineMessage... messages) {
    this(Arrays.asList(messages));
  }

  public FineMessageSetImpl(FineMessageSet messageSet) {
    this(messageSet.getMessages());
  }

  public FineMessageSetImpl(Collection<FineMessage> messages) {
    // We want an immutable list - first make a defensive copy
    // so we are not effected by changes to the original and
    // then we initialize ourselves with unmodifiable list.
    List<FineMessage> mutableList = new ArrayList<>(messages);
    this.messages = Collections.unmodifiableList(mutableList);
  }

  @Override
  public boolean hasText(String text) {
    for (FineMessage message : messages) {
      if (message.isText(text)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean hasId(String id) {
    for (FineMessage message : messages) {
      if (message.isId(id)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public FineMessage findFirstWithId(String id) {
    for (FineMessage message : messages) {
      if (message.isId(id)) {
        return message;
      }
    }
    return null;
  }

  @Override
  public FineMessageSet findWithId(String id) {
    List<FineMessage> foundList = new ArrayList<>();
    for (FineMessage message : messages) {
      if (message.isId(id)) {
        foundList.add(message);
      }
    }
    return new FineMessageSetImpl(foundList);
  }

  @Override
  public FineMessage findFirstWithTrait(String trait) {
    for (FineMessage message : messages) {
      if (message.hasTrait(trait)) {
        return message;
      }
    }
    return null;
  }

  @Override
  public FineMessageSet findWithTrait(String trait) {
    List<FineMessage> foundList = new ArrayList<>();
    for (FineMessage message : messages) {
      if (message.hasTrait(trait)) {
        foundList.add(message);
      }
    }
    return new FineMessageSetImpl(foundList);
  }

  @Override
  public String toString(String deliminator) {
    if (isEmpty()) {
      return String.format("%s is empty.", getClass().getSimpleName());
    }

    StringBuilder text = new StringBuilder();
    for (FineMessage message : messages) {
      text.append(message.getText());
      text.append(deliminator);
    }

    int length = text.length() - deliminator.length();
    return text.substring(0, Math.max(length, 0));
  }

  @Override
  public boolean isEmpty() {
    return messages.isEmpty();
  }

  @Override
  public boolean isNotEmpty() {
    return !messages.isEmpty();
  }

  @Override
  public int size() {
    return messages.size();
  }

  @Override
  public Iterator<FineMessage> iterator() {
    return messages.iterator();
  }

  @Override
  public List<FineMessage> getMessages() {
    return messages;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FineMessageSetImpl that = (FineMessageSetImpl) o;

    if (!messages.equals(that.messages)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return messages.hashCode();
  }

  @Override
  public String toString() {
    return toString(", ");
  }

}
