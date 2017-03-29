package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.CONFLICT;

public class ApiConflictException extends Api4xxException {

    private static final long serialVersionUID = 1L;

    public ApiConflictException() {
        super(CONFLICT);
    }

    public ApiConflictException(String message) {
        super(CONFLICT, message);
    }

    public ApiConflictException(String message, String... traits) {
        super(CONFLICT, message, traits);
    }

    public ApiConflictException(Throwable ex, String... traits) {
        super(CONFLICT, ex, traits);
    }

    public ApiConflictException(String message, Throwable ex, String... traits) {
        super(CONFLICT, message, ex, traits);
    }

    public ApiConflictException(FineMessage richMessage) {
        super(CONFLICT, richMessage);
    }

    public ApiConflictException(FineMessage richMessage, Throwable ex) {
        super(CONFLICT, richMessage, ex);
    }

    public ApiConflictException(FineMessageSet messageSet) {
        super(CONFLICT, messageSet);
    }

    public ApiConflictException(FineMessageSet messageSet, Throwable ex) {
        super(CONFLICT, messageSet, ex);
    }
}
