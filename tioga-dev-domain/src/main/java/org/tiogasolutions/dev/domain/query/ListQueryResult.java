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

package org.tiogasolutions.dev.domain.query;

import java.util.*;

/**
 * User: harlan
 * Date: 3/3/12
 * Time: 12:18 AM
 */
public final class ListQueryResult<T> implements QueryResult<T> {
  private final Class<? extends T> containsType;
  private final int limit;
  private final int offset;
  private int totalFound;
  private boolean totalExact;
  private final List<T> results;

  public static <T> ListQueryResult<T> newEmpty(Class<T> type) {
    return new ListQueryResult<>(type, 0, 0, 0, true, Collections.<T>emptyList());
  }

  public static <T> ListQueryResult<T> newSingle(Class<T> type, T result) {
    if (result == null) {
      throw new NullPointerException("Null value argument given to newSingle");
    }
    return new ListQueryResult<>(type, 0, 0, 1, true, Collections.singleton(result));
  }

  public static <T> ListQueryResult<T> newComplete(Class<T> type, Collection<T> results) {
    return new ListQueryResult<>(type, 0, 0, results.size(), true, results);
  }

  public static <T> ListQueryResult<T> newComplete(Class<T> type, T[] results) {

    return new ListQueryResult<>(type, 0, 0, results.length, true, Arrays.asList(results));
  }

  public static <T> ListQueryResult<T> newResult(Class<? extends T> type,
                                                 int limit,
                                                 int offset,
                                                 int totalFound,
                                                 boolean totalExact,
                                                 Collection<T> results) {
    return new ListQueryResult<>(type, limit, offset, totalFound, totalExact, results);
  }

  public static <T> ListQueryResult<T> newResult(Class<T> type,
                                                 int limit,
                                                 int offset,
                                                 int totalFound,
                                                 boolean totalExact,
                                                 T[] results) {
    return new ListQueryResult<>(type, limit, offset, totalFound, totalExact, Arrays.asList(results));
  }

  private ListQueryResult(Class<? extends T> containsType,
                          int limit,
                          int offset,
                          int totalFound,
                          boolean totalExact,
                          Collection<T> results) {
    this.containsType = containsType;
    this.limit = limit;
    this.offset = offset;
    this.totalFound = totalFound;
    this.totalExact = totalExact;
    List<T> localList = new ArrayList<>(results);
    this.results = Collections.unmodifiableList(localList);
  }

  @Override
  public Class<? extends T> getContainsType() {
    return containsType;
  }

  @Override
  public boolean isContainsType(Class<?> containsType) {
    return this.containsType.isAssignableFrom(containsType);
  }

  @Override
  public boolean isEmpty() {
    return results.isEmpty();
  }

  @Override
  public boolean isNotEmpty() {
    return !results.isEmpty();
  }

  @Override
  public int getLimit() {
    return limit;
  }

  @Override
  public int getSize() {
    return results.size();
  }

  @Override
  public int getTotalFound() {
    return totalFound;
  }

  @Override
  public boolean isTotalExact() {
    return totalExact;
  }

  @Override
  public int getOffset() {
    return offset;
  }

  @Override
  public T getFirst() {
    return results.get(0);
  }

  @Override
  public T getLast() {
    return results.get(results.size() - 1);
  }

  @Override
  public T getAt(int index) {
    return results.get(index);
  }

  @Override
  public List<T> getResults() {
    return results;
  }

  @Override
  public Iterator<T> iterator() {
    return results.iterator();
  }

  @Override
  public boolean getHasPrevious() {
    return offset != 0;
  }

  @Override
  public boolean getHasNext() {
    return offset + results.size() < totalFound;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ListQueryResult<?> that = (ListQueryResult)o;

    if (limit != that.limit) return false;
    if (offset != that.offset) return false;
    if (totalFound != that.totalFound) return false;
    if (results.size() != that.results.size()) return false;
    if (!results.equals(that.results)) return false;
    if (!containsType.equals(that.containsType)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = containsType.hashCode();
    result = 31 * result + offset;
    result = 31 * result + limit;
    result = 31 * result + totalFound;
    result = 31 * result + results.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "ListQueryResult{" +
        "type=" + containsType +
        ", limit=" + limit +
        ", offset=" + offset +
        ", totalFound=" + totalFound +
        ", results=" + results +
        '}';
  }
}
