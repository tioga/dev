package org.tiogasolutions.dev.common.fine;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

/**
 * User: harlann
 * Date: 8/22/13
 * Time: 11:08 PM
 */
@Test
public class FineMessageTest {

  private TraitMap traitMap;

  @BeforeClass
  public void beforeClass() {
    LinkedHashMap<String, String> map = new LinkedHashMap<>(3);
    map.put("keyOne", "valOne");
    map.put("keyTwo", "valTwo");
    map.put("keyThree", null);
    traitMap = new TraitMap(map);
  }

  public void withTextTest() {
    FineMessage msg = FineMessage.withText("some message");
    Assert.assertEquals(msg.getText(), "some message");
    Assert.assertNull(msg.getId());
    Assert.assertFalse(msg.hasTraits());
    Assert.assertTrue(msg.isText("some message"));
    Assert.assertFalse(msg.isText("ZZZZ"));
  }

  public void withIdTest() {
    FineMessage msg = FineMessage.withId("some message", "99");
    Assert.assertEquals(msg.getText(), "some message");
    Assert.assertEquals(msg.getId(), "99");
    Assert.assertFalse(msg.hasTraits());
    Assert.assertTrue(msg.isText("some message"));
    Assert.assertFalse(msg.isText("ZZZZ"));
  }

  public void withTraitsTest() {
    FineMessage msg = FineMessage.withTraits("some message", traitMap);
    Assert.assertEquals(msg.getText(), "some message");
    Assert.assertNull(msg.getId());
    Assert.assertTrue(msg.hasTraits());
    Assert.assertTrue(msg.isText("some message"));
    Assert.assertFalse(msg.isText("ZZZZ"));
  }
}
