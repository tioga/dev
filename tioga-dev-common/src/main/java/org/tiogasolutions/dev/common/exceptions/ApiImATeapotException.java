package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.IM_A_TEAPOT;

public class ApiImATeapotException extends Api4xxException {

    private static final long serialVersionUID = 1L;

    public ApiImATeapotException() {
        super(IM_A_TEAPOT);
    }

    public ApiImATeapotException(String message) {
        super(IM_A_TEAPOT, message);
    }

    public ApiImATeapotException(String message, String... traits) {
        super(IM_A_TEAPOT, message, traits);
    }

    public ApiImATeapotException(Throwable ex, String... traits) {
        super(IM_A_TEAPOT, ex, traits);
    }

    public ApiImATeapotException(String message, Throwable ex, String... traits) {
        super(IM_A_TEAPOT, message, ex, traits);
    }

    public ApiImATeapotException(FineMessage richMessage) {
        super(IM_A_TEAPOT, richMessage);
    }

    public ApiImATeapotException(FineMessage richMessage, Throwable ex) {
        super(IM_A_TEAPOT, richMessage, ex);
    }

    public ApiImATeapotException(FineMessageSet messageSet) {
        super(IM_A_TEAPOT, messageSet);
    }

    public ApiImATeapotException(FineMessageSet messageSet, Throwable ex) {
        super(IM_A_TEAPOT, messageSet, ex);
    }
}
