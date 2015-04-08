package org.tiogasolutions.dev.common.fine;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * User: harlann
 * Date: 8/22/13
 * Time: 11:08 PM
 */
public class FineMessageSetTest {

  private FineMessageSet messageSet;

  @BeforeClass
  public void beforeClass() {
    FineMessageSetBuilder builder = new FineMessageSetBuilder();
    Assert.assertNotNull(builder);

    messageSet = builder.withText("One").withTraits("Two", "keyOne:valOne", "keyTwo:valTwo").withId("Three", "3").withAll("Four", "4", new TraitMap("keyOne:valeOne", "keyFour:valFour")).build();
  }

  @Test
  public void basicsTest() {
    // Basics
    Assert.assertTrue(messageSet.isNotEmpty());
    Assert.assertFalse(messageSet.isEmpty());
    Assert.assertEquals(messageSet.size(), 4);

    // Has text
    Assert.assertTrue(messageSet.hasText("One"));
    Assert.assertTrue(messageSet.hasText("Two"));
    Assert.assertTrue(messageSet.hasText("Three"));
    Assert.assertTrue(messageSet.hasText("Four"));
    Assert.assertFalse(messageSet.hasText("Cat"));

    // Find
    Assert.assertEquals(messageSet.findFirstWithId("3"), FineMessage.withId("Three", "3"));
    FineMessageSet foundList = messageSet.findWithTrait("keyOne");
    Assert.assertEquals(foundList.size(), 2);
    foundList = messageSet.findWithId(null);
    Assert.assertEquals(foundList.size(), 2);
  }
}
