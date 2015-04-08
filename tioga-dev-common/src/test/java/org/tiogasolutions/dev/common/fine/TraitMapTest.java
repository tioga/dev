package org.tiogasolutions.dev.common.fine;

import org.tiogasolutions.dev.common.BeanUtils;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

/**
 * User: harlann
 * Date: 8/22/13
 * Time: 11:08 PM
 */
@Test
public class TraitMapTest {

  private final Map<String,String> initMap = BeanUtils.toMap("Key1:valOne", "Key2:", "Key3", null);

  public void beforeClass() {
  }

  public void basicsTest() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getSize(), 3);
    assertFalse(traitMap.isEmpty());
    assertTrue(traitMap.isNotEmpty());

    traitMap = new TraitMap((TraitMap)null);
    assertEquals(traitMap.getSize(), 0);

    traitMap = new TraitMap((Map)null);
    assertEquals(traitMap.getSize(), 0);

    traitMap = new TraitMap((String[])null);
    assertEquals(traitMap.getSize(), 0);

    traitMap = new TraitMap((Collection<String>)null);
    assertEquals(traitMap.getSize(), 0);
  }

  public void emptyTest() {
    TraitMap emptyMap = TraitMap.empty();
    assertEquals(emptyMap.getSize(), 0);
    assertTrue(emptyMap.isEmpty());
    assertFalse(emptyMap.isNotEmpty());

    TraitMap traitMap = new TraitMap(initMap);
    assertNotEquals(emptyMap, traitMap);

    traitMap = new TraitMap(initMap);
    assertEquals(traitMap, traitMap);

    assertEquals(new TraitMap(initMap), new TraitMap(initMap));
    assertEquals(new TraitMap(initMap), new TraitMap("Key1:valOne", "Key2:", "Key3"));
    assertEquals(new TraitMap(initMap), new TraitMap(Arrays.asList("Key1:valOne", "Key2:", "Key3")));
  }

  public void equalsTest() {
    TraitMap traitMapA = new TraitMap(initMap);
    TraitMap traitMapB = new TraitMap(initMap);
    assertEquals(traitMapA, traitMapB);

    traitMapB = new TraitMap("Key1:valOne", "Key2:", "Key3");
    assertEquals(traitMapA, traitMapB);

    // Different key cases
    traitMapB = new TraitMap("KEY1:valOne", "KEY2:", "KEY3");
    assertEquals(traitMapA, traitMapB);

    // Different key cases
    traitMapB = new TraitMap("key1:valOne", "key2:", "key3");
    assertEquals(traitMapA, traitMapB);

    // Different values
    traitMapB = new TraitMap("Key1:x", "Key2:", "Key3");
    assertNotEquals(traitMapA, traitMapB);

    // Extra key
    traitMapB = new TraitMap(initMap).add("Key4:four");
    assertNotEquals(traitMapA, traitMapB);

    // Missing a key
    traitMapB = new TraitMap(initMap).remove("Key1");
    assertNotEquals(traitMapA, traitMapB);

    // Different Order
    traitMapB = new TraitMap(initMap).remove("Key1").add("key1:valOne");
    assertNotEquals(traitMapA, traitMapB);
  }

  public void testAdd() {
    TraitMap traitMap = new TraitMap(initMap);
    validateAdd(traitMap, null);

    TraitMap newMap = traitMap.add("Key4:four", "Key5:", "Key6");
    validateAdd(traitMap, newMap);

    newMap = traitMap.add(Arrays.asList("Key4:four", "Key5:", "Key6"));
    validateAdd(traitMap, newMap);

    newMap = traitMap.add(BeanUtils.toMap("Key4:four", "Key5:", "Key6"));
    validateAdd(traitMap, newMap);

    newMap = traitMap.add(new TraitMap("Key4:four", "Key5:", "Key6"));
    validateAdd(traitMap, newMap);

    newMap = traitMap.add((TraitMap)null);
    validateAdd(traitMap, null);
    validateAdd(newMap, null);

    newMap = traitMap.add((Map)null);
    validateAdd(traitMap, null);
    validateAdd(newMap, null);

    newMap = traitMap.add((String[])null);
    validateAdd(traitMap, null);
    validateAdd(newMap, null);

    newMap = traitMap.add((Collection<String>)null);
    validateAdd(traitMap, null);
    validateAdd(newMap, null);
  }

  private void validateAdd(TraitMap traitMap, TraitMap newMap) {
    assertEquals(traitMap.getValue("Key1"), "valOne");
    assertEquals(traitMap.getValue("Key2"), "");
    assertEquals(traitMap.getValue("Key3"), null);
    assertEquals(traitMap.getSize(), 3);

    if (newMap != null) {
      assertEquals(newMap.getValue("Key1"), "valOne");
      assertEquals(newMap.getValue("Key2"), "");
      assertEquals(newMap.getValue("Key3"), null);
      assertEquals(newMap.getValue("Key4"), "four");
      assertEquals(newMap.getValue("Key5"), "");
      assertEquals(newMap.getValue("Key6"), null);
      assertEquals(newMap.getSize(), 6);
    }
  }

  public void testRemove() {
    TraitMap traitMap = new TraitMap("Key1:one", "Key2:two", "Key3:three", "Key4:four", "Key5:five", "Key6:six");
    validateRemove(traitMap, null);

    TraitMap newMap = traitMap.remove("Key2", "Key4");
    validateRemove(traitMap, newMap);

    newMap = traitMap.remove(Arrays.asList("Key2", "Key4"));
    validateRemove(traitMap, newMap);

    newMap = traitMap.remove((String[])null);
    validateRemove(traitMap, null);
    validateRemove(newMap, null);

    newMap = traitMap.remove((Collection<String>) null);
    validateRemove(traitMap, null);
    validateRemove(newMap, null);
  }

  private void validateRemove(TraitMap traitMap, TraitMap newMap) {

    assertEquals(traitMap.getValue("Key1"), "one");
    assertEquals(traitMap.getValue("Key2"), "two");
    assertEquals(traitMap.getValue("Key3"), "three");
    assertEquals(traitMap.getValue("Key4"), "four");
    assertEquals(traitMap.getValue("Key5"), "five");
    assertEquals(traitMap.getValue("Key6"), "six");
    assertEquals(traitMap.getSize(), 6);

    if (newMap != null) {
      assertEquals(newMap.getValue("Key1"), "one");
      assertEquals(newMap.getValue("Key2"), null);
      assertEquals(newMap.getValue("Key3"), "three");
      assertEquals(newMap.getValue("Key4"), null);
      assertEquals(newMap.getValue("Key5"), "five");
      assertEquals(newMap.getValue("Key6"), "six");
      assertEquals(newMap.getSize(), 4);
    }
  }

  public void traitStringTest() {
    TraitMap map = new TraitMap("1:one", "2:", "3", null, "4 :four", "5: five", "6:six ");

    // The null entry should be excluded.
    assertEquals(map.getSize(), 6);

    assertTrue(map.hasTrait("1"));
    assertTrue(map.hasValue("1", "one"));

    assertTrue(map.hasTrait("2"));
    assertTrue(map.hasValue("2", ""));

    assertTrue(map.hasTrait("3"));
    assertTrue(map.hasValue("3", null));

    assertTrue(map.hasTrait("4 "));
    assertTrue(map.hasValue("4 ", "four"));

    assertTrue(map.hasTrait("5"));
    assertTrue(map.hasValue("5", " five"));

    assertTrue(map.hasTrait("6"));
    assertTrue(map.hasValue("6", "six "));
  }

  public void hasTraitTest() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getSize(), 3);

    assertTrue(traitMap.hasTrait("Key1"));
    assertTrue(traitMap.hasTrait("Key2"));
    assertTrue(traitMap.hasTrait("Key3"));
    assertFalse(traitMap.hasTrait("Junk"));
  }

  public void hasValueTest_NORMAL() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getSize(), 3);

    assertTrue(traitMap.hasValue("Key1", "valOne"));
    assertTrue(traitMap.hasValue("Key2", ""));
    assertTrue(traitMap.hasValue("Key3", null));

    assertFalse(traitMap.hasValue("Key1", "valone"));
    assertFalse(traitMap.hasValue("Key1", "VALONE"));
    assertFalse(traitMap.hasValue("Key1", null));
    assertFalse(traitMap.hasValue("Key1", ""));
    assertFalse(traitMap.hasValue("Key1", "XYZ"));

    assertFalse(traitMap.hasValue("Key2", "valone"));
    assertFalse(traitMap.hasValue("Key2", "VALONE"));
    assertFalse(traitMap.hasValue("Key2", null));
    // Assert.assertFalse(traitMap.hasValue("key2", ""));
    assertFalse(traitMap.hasValue("Key2", "XYZ"));

    assertFalse(traitMap.hasValue("Key3", "valone"));
    assertFalse(traitMap.hasValue("Key3", "VALONE"));
    // Assert.assertFalse(traitMap.hasValue("key3", null));
    assertFalse(traitMap.hasValue("Key3", ""));
    assertFalse(traitMap.hasValue("Key3", "XYZ"));
  }

  public void hasValueTest_UPPER() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getSize(), 3);

    assertTrue(traitMap.hasValue("KEY1", "valOne"));
    assertTrue(traitMap.hasValue("KEY2", ""));
    assertTrue(traitMap.hasValue("KEY3", null));

    assertFalse(traitMap.hasValue("KEY1", "valone"));
    assertFalse(traitMap.hasValue("KEY1", "VALONE"));
    assertFalse(traitMap.hasValue("KEY1", null));
    assertFalse(traitMap.hasValue("KEY1", ""));
    assertFalse(traitMap.hasValue("KEY1", "XYZ"));

    assertFalse(traitMap.hasValue("KEY2", "valone"));
    assertFalse(traitMap.hasValue("KEY2", "VALONE"));
    assertFalse(traitMap.hasValue("KEY2", null));
    // Assert.assertFalse(traitMap.hasValue("key2", ""));
    assertFalse(traitMap.hasValue("KEY2", "XYZ"));

    assertFalse(traitMap.hasValue("KEY3", "valone"));
    assertFalse(traitMap.hasValue("KEY3", "VALONE"));
    // Assert.assertFalse(traitMap.hasValue("key3", null));
    assertFalse(traitMap.hasValue("KEY3", ""));
    assertFalse(traitMap.hasValue("KEY3", "XYZ"));
  }

  public void hasValueTest_LOWER() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getSize(), 3);

    assertTrue(traitMap.hasValue("key1", "valOne"));
    assertTrue(traitMap.hasValue("key2", ""));
    assertTrue(traitMap.hasValue("key3", null));

    assertFalse(traitMap.hasValue("key1", "valone"));
    assertFalse(traitMap.hasValue("key1", "VALONE"));
    assertFalse(traitMap.hasValue("key1", null));
    assertFalse(traitMap.hasValue("key1", ""));
    assertFalse(traitMap.hasValue("key1", "XYZ"));

    assertFalse(traitMap.hasValue("key2", "valone"));
    assertFalse(traitMap.hasValue("key2", "VALONE"));
    assertFalse(traitMap.hasValue("key2", null));
    // Assert.assertFalse(traitMap.hasValue("key2", ""));
    assertFalse(traitMap.hasValue("key2", "XYZ"));

    assertFalse(traitMap.hasValue("key3", "valone"));
    assertFalse(traitMap.hasValue("key3", "VALONE"));
    // Assert.assertFalse(traitMap.hasValue("key3", null));
    assertFalse(traitMap.hasValue("key3", ""));
    assertFalse(traitMap.hasValue("key3", "XYZ"));
  }

  public void getValueTest_NATURAL() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getValue("Key1"), "valOne");
    assertEquals(traitMap.getValue("Key2"), "");
    assertEquals(traitMap.getValue("Key3"), null);
    assertEquals(traitMap.getValue("Junk"), null);
  }

  public void getValueTest_UPPER() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getValue("KEY1"), "valOne");
    assertEquals(traitMap.getValue("KEY2"), "");
    assertEquals(traitMap.getValue("KEY3"), null);
    assertEquals(traitMap.getValue("JUNK"), null);
  }

  public void getValueTest_LOWER() {
    TraitMap traitMap = new TraitMap(initMap);
    assertEquals(traitMap.getValue("key1"), "valOne");
    assertEquals(traitMap.getValue("key2"), "");
    assertEquals(traitMap.getValue("key3"), null);
    assertEquals(traitMap.getValue("junk"), null);
  }

  public void getTextTest() {
    assertEquals(new TraitMap(initMap).getText(), "Key1:\"valOne\", Key2:\"\", Key3:null");
  }

  public void testHash() {
    int hash = new TraitMap(initMap).hashCode();
    assertEquals(hash, 2055886536);
  }
}
