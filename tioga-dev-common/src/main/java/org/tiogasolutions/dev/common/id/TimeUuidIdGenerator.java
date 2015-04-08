package org.tiogasolutions.dev.common.id;

import org.tiogasolutions.dev.common.id.uuid.TimeUuid;

public class TimeUuidIdGenerator implements IdGenerator {

    private String lastId;

    public TimeUuidIdGenerator() {
    }

    @Override
    public synchronized String newId() {
        String newId = new TimeUuid().toString();
        lastId = newId;
        return newId;
    }

    @Override
    public synchronized String getLastId() {
        return lastId;
    }

}