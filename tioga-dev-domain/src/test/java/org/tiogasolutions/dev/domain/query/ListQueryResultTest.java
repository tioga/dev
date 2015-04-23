package org.tiogasolutions.dev.domain.query;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

@Test
public class ListQueryResultTest {

  public void testNewEmpty() {
    ListQueryResult<String> result = ListQueryResult.newEmpty(String.class);
    assertEquals(result.getSize(), 0);
    assertTrue(result.isTotalExact());
  }

  public void testNewSingle() {
    ListQueryResult<String> result = ListQueryResult.newSingle(String.class, "kitten");
    assertEquals(result.getSize(), 1);
    assertEquals(result.getFirst(), "kitten");
    assertTrue(result.isTotalExact());
  }

  public void testNewComplete_Collection() {
    ListQueryResult<String> result = ListQueryResult.newComplete(String.class, Arrays.asList("kitten", "puppy"));
    assertEquals(result.getSize(), 2);
    assertEquals(result.getFirst(), "kitten");
    assertEquals(result.getLast(), "puppy");
    assertTrue(result.isTotalExact());
  }

  public void testNewComplete_Array() {
    ListQueryResult<String> result = ListQueryResult.newComplete(String.class, new String[]{"kitten", "puppy"});
    assertEquals(result.getSize(), 2);
    assertEquals(result.getFirst(), "kitten");
    assertEquals(result.getLast(), "puppy");
    assertTrue(result.isTotalExact());
  }

  public void testNewResult_Collection() {
    ListQueryResult<String> result = ListQueryResult.newResult(String.class, 2, 3, 20, true, Arrays.asList("kitten", "puppy"));
    assertEquals(result.getSize(), 2);
    assertEquals(result.getFirst(), "kitten");
    assertEquals(result.getLast(), "puppy");
    assertTrue(result.isTotalExact());
  }

  public void testNewResult_Array() {
    ListQueryResult<String> result = ListQueryResult.newResult(String.class, 2, 3, 20, true, new String[]{"kitten", "puppy"});
    assertEquals(result.getSize(), 2);
    assertEquals(result.getFirst(), "kitten");
    assertEquals(result.getLast(), "puppy");
    assertTrue(result.isTotalExact());
  }
}