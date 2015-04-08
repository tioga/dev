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

import java.io.Serializable;
import java.time.*;
import java.util.*;
import org.tiogasolutions.dev.common.StringUtils;

/**
 * User: harlan
 * Date: 3/20/12
 * Time: 12:00 AM
 * <br>
 * Considerations -
 * prefixLifeInSeconds should not be greater than the time it takes to reset the server.
 * REVIEW - consider having the generator ensure it has existed at a minimum of prefixLifeInSeconds before generating the first sequence.
 * REVIEW - concurrency
 */
/**
 * User: harlan
 * Date: 3/20/12
 * Time: 12:00 AM
 * Considerations -
 * prefixLifeInSeconds should not be greater than the time it takes to reset the server.
 * REVIEW - consider having the generator ensure it has existed at a minimum of prefixLifeInSeconds before generating the first sequence.
 * REVIEW - concurrency
 */
public class TwoPartIdGenerator implements IdGenerator, Serializable {
    private static final int MAX_ATTEMPTS = 10000000; // 10 million

    private final Object idLock = new Object();
    private final Set<Integer> rememberedRandomSet = new TreeSet<>();
    private final Random random = new Random(System.currentTimeMillis());
    private final String idFormat;
    private final ZoneId zoneId;
    private final ZonedDateTime prefixBegin;
    private final char[] prefixChars;
    private final int prefixLifeInSeconds;
    private final int minPrefixLength;
    private final int suffixLength;
    private final int suffixMaxInt;

    private String currentPrefix;
    private int currentPrefixNumber;
    private String lastId;


    public TwoPartIdGenerator(String idFormat, ZonedDateTime prefixBegin, String prefixChars, int prefixLifeInSeconds, int minPrefixLength, int suffixLength) {
        this.idFormat = idFormat;
        this.prefixBegin = prefixBegin;
        this.zoneId = prefixBegin.getZone();
        this.prefixLifeInSeconds = prefixLifeInSeconds;
        this.prefixChars = prefixChars.toCharArray();
        this.minPrefixLength = minPrefixLength;
        this.suffixLength = suffixLength;
        this.suffixMaxInt = Integer.valueOf(StringUtils.padRight("", suffixLength, '9'));
        this.currentPrefix = "";
    }

    public TwoPartIdGenerator() {
        this("%s-%s",
            ZonedDateTime.of(2013, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC")),
            "ABCDFGHJKLMNPQRSTVWXYZ",
            20,
            5,
            7);
    }

    /**
     * Generate a new sequence.
     *
     * @return generated sequence
     */
    // CRITICAL should this method be synchronized since we use idLock
    @Override
    public synchronized String newId() {
        // We perform this in a loop so that if we find a duplicate we can regenerate the entire number.
        int attempts = 0;
        while (attempts++ < MAX_ATTEMPTS) {
            // Generate the prefix.
            String lastPrefix = currentPrefix;
            String prefix = generatePrefix();

            // If our prefix has changed we need to clear the rememberedRandomSet
            if (!lastPrefix.equals(prefix)) {
                rememberedRandomSet.clear();
            }

            // Create the suffixInt and ensure it's unique.
            int suffixInt = random.nextInt(suffixMaxInt);
            if (rememberedRandomSet.contains(suffixInt)) {
                // This is not a unique number, generate another.
                continue;
            }

            // We have a unique randomInt, so generate the suffix which needs to be padded to suffixLength.
            rememberedRandomSet.add(suffixInt);
            String suffix = StringUtils.padRight(String.valueOf(suffixInt), suffixLength, '0');

            // Return the sequence.
            String sequence = String.format(idFormat, prefix, suffix);
            synchronized (idLock) {
                lastId = sequence;
            }
            return sequence;

        }

        // We reached max attempts but never successfully generated sequence, throw exception.
        throw new RuntimeException("Exceeded number of attempts at generating a sequence.");

    }

    public String getLastId() {
        synchronized (idLock) {
            return lastId;
        }
    }

    private String generatePrefix() {
        // Duration will be used to generate the prefix characters, which are time based.
        Duration duration = Duration.between(prefixBegin, ZonedDateTime.now(zoneId));

        // Safe to cast to int here as will never exceed MAX_INT.
        int newPrefixNumber = (int) duration.getSeconds() / prefixLifeInSeconds;
        if (newPrefixNumber == currentPrefixNumber) {
            // Our prefix number has not changed so just return the previous prefix.
            return currentPrefix;
        }

        // We have a new currentPrefixNumber;
        currentPrefixNumber = newPrefixNumber;

        // Construct prefix, which is representation of prefixNumber based on the prefix chars.
        int base = prefixChars.length;
        StringBuilder prefixSb = new StringBuilder();
        while (newPrefixNumber != 0) {
            int mod = newPrefixNumber % base;
            prefixSb.insert(0, prefixChars[mod]);
            newPrefixNumber = newPrefixNumber / base;
        }
        currentPrefix = StringUtils.padLeft(prefixSb.toString(), minPrefixLength, this.prefixChars[0]);
        return currentPrefix;

    }
}
