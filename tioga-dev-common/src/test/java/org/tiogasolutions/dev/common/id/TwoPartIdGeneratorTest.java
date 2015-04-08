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

package org.tiogasolutions.dev.common.id;

import java.util.*;

/**
 * User: harlan
 * Date: 3/20/12
 * Time: 10:50 PM
 */
public class TwoPartIdGeneratorTest {

    public static void main(String[] args) {
        generateSequences();
    }

    public static void generateSequences() {
        int numberToGenerate = 1000000;
        long startMilli = System.currentTimeMillis();
        TwoPartIdGenerator sequenceGenerator = new TwoPartIdGenerator();

        Set<String> orderNumberSet = new HashSet<>();
        String first = sequenceGenerator.newId();
        for (int i = 0; i < numberToGenerate; i++) {
            String orderNumber = sequenceGenerator.newId();
            if (orderNumberSet.contains(orderNumber)) {
              String msg = String.format("We found a duplicate order number (%s at index %s after %s seconds", orderNumber, i, (System.currentTimeMillis() - startMilli) / 1000);
              throw new RuntimeException(msg);
            }
            //println("${i}) ${orderNumber}")
            if ((i % 1000) == 0) {
              System.out.printf("%s) %s\n", i, orderNumber);
            }
            if ((i % 10000000) == 0) {
                orderNumberSet.clear();
            }
//            Thread.sleep(1)
        }
        String last = sequenceGenerator.newId();

      System.out.printf("First: %s\n", first);
      System.out.printf("Last: %s\n", last);

    }

}