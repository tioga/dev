package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.FORBIDDEN;

public class ApiForbiddenException extends Api4xxException {

    private static final long serialVersionUID = 1L;

    public ApiForbiddenException() {
        super(FORBIDDEN);
    }

    public ApiForbiddenException(String message) {
        super(FORBIDDEN, message);
    }

    public ApiForbiddenException(String message, String... traits) {
        super(FORBIDDEN, message, traits);
    }

    public ApiForbiddenException(Throwable ex, String... traits) {
        super(FORBIDDEN, ex, traits);
    }

    public ApiForbiddenException(String message, Throwable ex, String... traits) {
        super(FORBIDDEN, message, ex, traits);
    }

    public ApiForbiddenException(FineMessage richMessage) {
        super(FORBIDDEN, richMessage);
    }

    public ApiForbiddenException(FineMessage richMessage, Throwable ex) {
        super(FORBIDDEN, richMessage, ex);
    }

    public ApiForbiddenException(FineMessageSet messageSet) {
        super(FORBIDDEN, messageSet);
    }

    public ApiForbiddenException(FineMessageSet messageSet, Throwable ex) {
        super(FORBIDDEN, messageSet, ex);
    }
}
