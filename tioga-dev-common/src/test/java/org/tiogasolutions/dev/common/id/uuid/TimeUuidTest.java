package org.tiogasolutions.dev.common.id.uuid;

import java.util.*;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class TimeUuidTest {

  public void testCreation() {

    int MAX = 1000 * 1000;
    Set<TimeUuid> idSet = new TreeSet<>();
    List<TimeUuid> listA = new ArrayList<>();

    for (int i = 0; i < MAX; i++) {
      TimeUuid uuid = new TimeUuid();
      idSet.add(uuid);
      listA.add(uuid);
    }

    List<TimeUuid> listB = new ArrayList<>(idSet);

    for (int i = 0; i < MAX; i++) {
      TimeUuid idA = listA.get(i);
      TimeUuid idB = listB.get(i);
      assertEquals(idA, idB);
    }
  }

}