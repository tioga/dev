package org.tiogasolutions.dev.common;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test
public class BeanUtilsTest {

  public void testUnion() throws Exception {

    Collection<String> listA = Arrays.asList("one", "two", "three", "four");
    Collection<String> listB = Arrays.asList("three", "four", "five", "six");
    List<String> result = BeanUtils.union(listA, listB);

    assertTrue(result.remove("one"));
    assertTrue(result.remove("two"));
    assertTrue(result.remove("three"));
    assertTrue(result.remove("four"));
    assertTrue(result.remove("five"));
    assertTrue(result.remove("six"));

    assertEquals(result.size(), 0);
  }

  public void testIntersection() throws Exception {

    Collection<String> listA = Arrays.asList("one", "two", "three", "four");
    Collection<String> listB = Arrays.asList("three", "four", "five", "six");
    List<String> result = BeanUtils.intersection(listA, listB);

    assertTrue(result.remove("three"));
    assertTrue(result.remove("four"));

    assertEquals(result.size(), 0);
  }

  public void testCompare() throws Exception {
    try {
      // noinspection ConstantConditions
      assertEquals("asdf".compareTo(null), -1);
    } catch (NullPointerException e) {
      assertEquals(e.getMessage(), null);
    }

    assertEquals(BeanUtils.compare(null, null), 0);
    assertEquals(BeanUtils.compare("asdf", null), -1);
    assertEquals(BeanUtils.compare(null, "asdf"), 1);

    assertEquals(BeanUtils.compare("asdf", "asdf"), 0);
    assertEquals(BeanUtils.compare("asdf", "fdsa"), -5);
    assertEquals(BeanUtils.compare("fdsa", "asdf"), 5);
  }

  public void testToMap() throws Exception {
    Map<String,String> map = BeanUtils.toMap();
    assertEquals(map.size(), 0);

    String[] nullArray = null;
    // noinspection ConstantConditions
    map = BeanUtils.toMap(nullArray);
    assertEquals(map.size(), 0);

    map = BeanUtils.toMap("red:ff0000", "yellow", "green:", null, "blue:0000ff");
    validateMapEntry(map, "red", "ff0000");
    validateMapEntry(map, "yellow", null);
    validateMapEntry(map, "green", "");
    validateMapEntry(map, null, null);
    validateMapEntry(map, "blue", "0000ff");
  }

  private void validateMapEntry(Map<String,String> map, String key, String value) {
    assertTrue(map.containsKey(key));
    assertEquals(map.get(key), value);
  }
}