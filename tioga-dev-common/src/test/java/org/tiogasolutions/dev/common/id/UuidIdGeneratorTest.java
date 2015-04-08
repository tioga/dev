package org.tiogasolutions.dev.common.id;

import java.util.UUID;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class UuidIdGeneratorTest {

  public void testUUID() {
    UUID uuid = UUID.randomUUID();
    Assert.assertEquals(uuid.version(), 4);
  }

}