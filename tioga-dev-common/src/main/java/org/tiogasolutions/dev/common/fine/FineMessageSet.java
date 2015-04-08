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

import java.util.*;

public interface FineMessageSet extends Iterable<FineMessage> {

  public boolean hasText(String text);

  public boolean hasId(String id);

  public FineMessage findFirstWithId(String id);

  public FineMessageSet findWithId(String id);

  public FineMessage findFirstWithTrait(String trait);

  public FineMessageSet findWithTrait(String trait);

  public boolean isEmpty();

  public boolean isNotEmpty();

  public int size();

  public List<FineMessage> getMessages();

  public String toString(String delim);
}