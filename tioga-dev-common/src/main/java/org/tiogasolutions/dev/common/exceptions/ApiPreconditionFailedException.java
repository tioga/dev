package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.PRECONDITION_FAILED;

public class ApiPreconditionFailedException extends Api4xxException {

    private static final long serialVersionUID = 1L;

    public ApiPreconditionFailedException() {
        super(PRECONDITION_FAILED);
    }

    public ApiPreconditionFailedException(String message) {
        super(PRECONDITION_FAILED, message);
    }

    public ApiPreconditionFailedException(String message, String... traits) {
        super(PRECONDITION_FAILED, message, traits);
    }

    public ApiPreconditionFailedException(Throwable ex, String... traits) {
        super(PRECONDITION_FAILED, ex, traits);
    }

    public ApiPreconditionFailedException(String message, Throwable ex, String... traits) {
        super(PRECONDITION_FAILED, message, ex, traits);
    }

    public ApiPreconditionFailedException(FineMessage richMessage) {
        super(PRECONDITION_FAILED, richMessage);
    }

    public ApiPreconditionFailedException(FineMessage richMessage, Throwable ex) {
        super(PRECONDITION_FAILED, richMessage, ex);
    }

    public ApiPreconditionFailedException(FineMessageSet messageSet) {
        super(PRECONDITION_FAILED, messageSet);
    }

    public ApiPreconditionFailedException(FineMessageSet messageSet, Throwable ex) {
        super(PRECONDITION_FAILED, messageSet, ex);
    }
}
