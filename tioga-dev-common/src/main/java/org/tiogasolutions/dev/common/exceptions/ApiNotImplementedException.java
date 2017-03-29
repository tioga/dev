package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.NOT_IMPLEMENTED;

public class ApiNotImplementedException extends Api5xxException {

    private static final long serialVersionUID = 1L;

    public ApiNotImplementedException() {
        super(NOT_IMPLEMENTED);
    }

    public ApiNotImplementedException(String message) {
        super(NOT_IMPLEMENTED, message);
    }

    public ApiNotImplementedException(String message, String... traits) {
        super(NOT_IMPLEMENTED, message, traits);
    }

    public ApiNotImplementedException(Throwable ex, String... traits) {
        super(NOT_IMPLEMENTED, ex, traits);
    }

    public ApiNotImplementedException(String message, Throwable ex, String... traits) {
        super(NOT_IMPLEMENTED, message, ex, traits);
    }

    public ApiNotImplementedException(FineMessage richMessage) {
        super(NOT_IMPLEMENTED, richMessage);
    }

    public ApiNotImplementedException(FineMessage richMessage, Throwable ex) {
        super(NOT_IMPLEMENTED, richMessage, ex);
    }

    public ApiNotImplementedException(FineMessageSet messageSet) {
        super(NOT_IMPLEMENTED, messageSet);
    }

    public ApiNotImplementedException(FineMessageSet messageSet, Throwable ex) {
        super(NOT_IMPLEMENTED, messageSet, ex);
    }
}
