package org.tiogasolutions.dev.common;

import java.time.LocalDateTime;
import java.util.*;

import org.testng.annotations.Test;
import org.tiogasolutions.dev.common.compare.Boondoggle;
import org.tiogasolutions.dev.common.compare.Note;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

@Test
public class EqualsUtilsTest {

  private final Boondoggle boondoggleA = new Boondoggle((byte)1, (short)2, 3, 4L, 5f, 6d, 'x', true, "Mickey", null,
      new Note("2012-03-15T12:45", "I was just created."),
      new Note("2013-04-15T13:46", "Something else happened."));

  private final Boondoggle boondoggleB = new Boondoggle((byte)1, (short)2, 3, 4L, 5f, 6d, 'x', true, "Mickey", null,
      new Note("2012-03-15T12:45", "I was just created."),
      new Note("2013-04-15T13:46", "Something else happened."));

  private final Boondoggle boondoggleC = new Boondoggle((byte)2, (short)3, 4, 5L, 6f, 7d, 'y', false, "Donald", "something",
      new Note("2002-05-17T14:48", "Someone created this."),
      new Note("2003-06-18T15:49", "And then they really screwed up.."));

  private final Boondoggle boondoggleD = new Boondoggle((byte)2, (short)3, 4, 5L, 6f, 7d, 'y', false, "Donald", "something",
      new Note("2002-05-17T14:48", "Someone created this."),
      new Note("2003-06-18T15:49", "And then they really screwed up.."),
      new Note("2004-07-19T16:50", "But then they covered it up."));


  public void testObjectEquals() {

    assertEquals(boondoggleA, boondoggleA);
    assertTrue(EqualsUtils.objectsEqual(boondoggleA, boondoggleA));

    assertEquals(boondoggleA, boondoggleB);
    assertTrue(EqualsUtils.objectsEqual(boondoggleA, boondoggleB));

    assertNotEquals(boondoggleB, boondoggleC);
    assertFalse(EqualsUtils.objectsEqual(boondoggleB, boondoggleC));

    assertFalse(EqualsUtils.objectsEqual(boondoggleA, null));
    assertFalse(EqualsUtils.objectsEqual(null, boondoggleB));
  }

  public void testBothNull() {
    ComparisonResults results = EqualsUtils.compare(null, null);
    assertTrue(results.isEmpty());
  }

  public void testBothEqual() {
    ComparisonResults results = EqualsUtils.compare(boondoggleA, boondoggleB);
    assertTrue(results.isEmpty());
  }

  public void testBothSame() {
    ComparisonResults results = EqualsUtils.compare(boondoggleA, boondoggleA);
    assertTrue(results.isEmpty());
  }

  public void testNullRight() throws Exception {
    ComparisonResults results = EqualsUtils.compare(boondoggleA, null);
    results.assertError("root", ComparisonResults.VALUE_B_IS_NULL);
    results.assertValidationComplete();

    results = EqualsUtils.compare(boondoggleA, null);
    results.assertError("root", ComparisonResults.VALUE_B_IS_NULL, boondoggleA, null);
    results.assertValidationComplete();
  }

  public void testNullLeft() throws Exception {
    ComparisonResults results = EqualsUtils.compare(null, boondoggleB);
    results.assertError("root", ComparisonResults.VALUE_A_IS_NULL);
    results.assertValidationComplete();

    results = EqualsUtils.compare(null, boondoggleB);
    results.assertError("root", ComparisonResults.VALUE_A_IS_NULL, null, boondoggleB);
    results.assertValidationComplete();
  }

  public void testBadClass() throws Exception {
    ComparisonResults results = EqualsUtils.compare("A String", boondoggleB);
    results.assertError("root", ComparisonResults.CLASSES_DIFFERENT);
    results.assertValidationComplete();

    results = EqualsUtils.compare("A String", boondoggleB);
    results.assertError("root", ComparisonResults.CLASSES_DIFFERENT, String.class, Boondoggle.class);
    results.assertValidationComplete();
  }

  public void testInvalidDiffNotes() throws Exception {

    ComparisonResults results = EqualsUtils.compare(boondoggleC, boondoggleD);

    results.assertError("root.notesArray:count()", ComparisonResults.SIZE, 2, 3);
    results.assertError("root.notesArray[2]", ComparisonResults.MISSING_A);

    results.assertError("root.notesList:count()", ComparisonResults.SIZE, 2, 3);
    results.assertError("root.notesList[2]", ComparisonResults.MISSING_A);

    results.assertError("root.notesMap:count()", ComparisonResults.SIZE, 2, 3);
    results.assertError("root.notesMap[key-2]", ComparisonResults.MISSING_A);
    results.assertValidationComplete();


    results = EqualsUtils.compare(boondoggleD, boondoggleC);

    results.assertError("root.notesArray:count()", ComparisonResults.SIZE, 3, 2);
    results.assertError("root.notesArray[2]", ComparisonResults.MISSING_B);

    results.assertError("root.notesList:count()", ComparisonResults.SIZE, 3, 2);
    results.assertError("root.notesList[2]", ComparisonResults.MISSING_B);

    results.assertError("root.notesMap:count()", ComparisonResults.SIZE, 3, 2);
    results.assertError("root.notesMap[key-2]", ComparisonResults.MISSING_B);
    results.assertValidationComplete();
  }

  public void testInvalid() throws Exception {

    ComparisonResults results = EqualsUtils.compare(boondoggleA, boondoggleC);

    results.assertError("root.byteValue", ComparisonResults.NOT_EQUAL, (byte)1, (byte)2);
    results.assertError("root.shortValue", ComparisonResults.NOT_EQUAL, (short)2, (short)3);
    results.assertError("root.intValue", ComparisonResults.NOT_EQUAL, 3, 4);
    results.assertError("root.longValue", ComparisonResults.NOT_EQUAL, 4L, 5L);
    results.assertError("root.floatValue", ComparisonResults.NOT_EQUAL, 5.0f, 6.0f);
    results.assertError("root.doubleValue", ComparisonResults.NOT_EQUAL, 6.0d, 7.0d);
    results.assertError("root.charValue", ComparisonResults.NOT_EQUAL, 'x', 'y');
    results.assertError("root.stringValue", ComparisonResults.NOT_EQUAL, "Mickey", "Donald");
    results.assertError("root.booleanValue", ComparisonResults.NOT_EQUAL, true, false);
    results.assertError("root.nullValue", ComparisonResults.VALUE_A_IS_NULL);

    results.assertError("root.firstNote.message", ComparisonResults.NOT_EQUAL, "I was just created.", "Someone created this.");
    results.assertError("root.firstNote.createdAt", ComparisonResults.NOT_EQUAL,
        LocalDateTime.parse("2012-03-15T12:45:00.000"),
        LocalDateTime.parse("2002-05-17T14:48:00.000"));

    results.assertError("root.notesArray[0].message", ComparisonResults.NOT_EQUAL, "I was just created.", "Someone created this.");
    results.assertError("root.notesArray[0].createdAt", ComparisonResults.NOT_EQUAL,
        LocalDateTime.parse("2012-03-15T12:45:00.000"),
        LocalDateTime.parse("2002-05-17T14:48:00.000"));

    results.assertError("root.notesArray[1].message", ComparisonResults.NOT_EQUAL, "Something else happened.", "And then they really screwed up..");
    results.assertError("root.notesArray[1].createdAt", ComparisonResults.NOT_EQUAL,
        LocalDateTime.parse("2013-04-15T13:46:00.000"),
        LocalDateTime.parse("2003-06-18T15:49:00.000"));

    results.assertError("root.notesList[0].message", ComparisonResults.NOT_EQUAL, "I was just created.", "Someone created this.");
    results.assertError("root.notesList[0].createdAt", ComparisonResults.NOT_EQUAL,
        LocalDateTime.parse("2012-03-15T12:45:00.000"),
        LocalDateTime.parse("2002-05-17T14:48:00.000"));

    results.assertError("root.notesList[1].message", ComparisonResults.NOT_EQUAL, "Something else happened.", "And then they really screwed up..");
    results.assertError("root.notesList[1].createdAt", ComparisonResults.NOT_EQUAL,
        LocalDateTime.parse("2013-04-15T13:46:00.000"),
        LocalDateTime.parse("2003-06-18T15:49:00.000"));

    results.assertError("root.notesMap[key-0].message", ComparisonResults.NOT_EQUAL, "I was just created.", "Someone created this.");
    results.assertError("root.notesMap[key-0].createdAt", ComparisonResults.NOT_EQUAL,
        LocalDateTime.parse("2012-03-15T12:45:00.000"),
        LocalDateTime.parse("2002-05-17T14:48:00.000"));

    results.assertError("root.notesMap[key-1].message", ComparisonResults.NOT_EQUAL, "Something else happened.", "And then they really screwed up..");
    results.assertError("root.notesMap[key-1].createdAt", ComparisonResults.NOT_EQUAL,
        LocalDateTime.parse("2013-04-15T13:46:00.000"),
        LocalDateTime.parse("2003-06-18T15:49:00.000"));

    results.assertValidationComplete();
  }

  public void testIgnoringErrors() throws Exception {

    ComparisonResults results = EqualsUtils.compare(boondoggleA, boondoggleC);
    assertEquals(results.getCount(), 24);
    assertEquals(results.getValidatableCount(), 24);

    results.ignore("root.firstNote.message",
        "root.firstNote.createdAt",
        "root.notesArray[0].message",
        "root.notesArray[0].createdAt",
        "root.notesArray[1].message",
        "root.notesArray[1].createdAt",
        "root.notesList[0].message",
        "root.notesList[0].createdAt",
        "root.notesList[1].message",
        "root.notesList[1].createdAt",
        "root.notesMap[key-0].message",
        "root.notesMap[key-0].createdAt",
        "root.notesMap[key-1].message",
        "root.notesMap[key-1].createdAt");

    assertEquals(results.getCount(), 24);
    assertEquals(results.getValidatableCount(), 10);

    results.assertError("root.byteValue", ComparisonResults.NOT_EQUAL, (byte) 1, (byte) 2);
    results.assertError("root.shortValue", ComparisonResults.NOT_EQUAL, (short) 2, (short) 3);
    results.assertError("root.intValue", ComparisonResults.NOT_EQUAL, 3, 4);
    results.assertError("root.longValue", ComparisonResults.NOT_EQUAL, 4L, 5L);
    results.assertError("root.floatValue", ComparisonResults.NOT_EQUAL, 5.0f, 6.0f);
    results.assertError("root.doubleValue", ComparisonResults.NOT_EQUAL, 6.0d, 7.0d);
    results.assertError("root.charValue", ComparisonResults.NOT_EQUAL, 'x', 'y');
    results.assertError("root.stringValue", ComparisonResults.NOT_EQUAL, "Mickey", "Donald");
    results.assertError("root.booleanValue", ComparisonResults.NOT_EQUAL, true, false);
    results.assertError("root.nullValue", ComparisonResults.VALUE_A_IS_NULL);

    results.assertValidationComplete();
  }

  public void testCollections() throws Exception {

    Collection<String> collectionA = Arrays.asList("one", "two", "three");
    Collection<String> collectionB = new TreeSet<>(Arrays.asList("two", "three", "four", "five"));

    ComparisonResults results = EqualsUtils.compare(collectionA, collectionB);

    results.assertError("root:count()", ComparisonResults.SIZE, 3, 4);
    results.assertError("root[0]", ComparisonResults.NOT_EQUAL, "one", "five");
    results.assertError("root[1]", ComparisonResults.NOT_EQUAL, "two", "four");
    results.assertError("root[3]", ComparisonResults.MISSING_A, null, "two");

    results.assertValidationComplete();
  }

  public void testMap() throws Exception {

    Map<String,String> mapA = new TreeMap<>(BeanUtils.toMap("one:1", "two:2", "three:", "four"));
    Map<String,String> mapB = new LinkedHashMap<>();
    mapB.put("two", "2");
    mapB.put("three", "3");
    mapB.put("four", "4");
    mapB.put("five", "5");
    mapB.put("color", "green");

    ComparisonResults results = EqualsUtils.compare(mapA, mapB);

    results.assertError("root:count()", ComparisonResults.SIZE, 4, 5);
    results.assertError("root[one]", ComparisonResults.MISSING_B, "1", null);
    results.assertError("root[three]", ComparisonResults.NOT_EQUAL, "", "3");
    results.assertError("root[four]", ComparisonResults.VALUE_A_IS_NULL, null, "4");
    results.assertError("root[five]", ComparisonResults.MISSING_A, null, null);
    results.assertError("root[color]", ComparisonResults.MISSING_A, null, null);

    results.assertValidationComplete();
  }
}