package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.IM_A_TEAPOT;

public class ApiImATeapotException extends Api4xxException {

  /*default*/ ApiImATeapotException() {
    super(IM_A_TEAPOT);
  }

  /*default*/ ApiImATeapotException(String message, String... traits) {
    super(IM_A_TEAPOT, message, traits);
  }

  /*default*/ ApiImATeapotException(Throwable ex, String... traits) {
    super(IM_A_TEAPOT, ex, traits);
  }

  /*default*/ ApiImATeapotException(String message, Throwable ex, String... traits) {
    super(IM_A_TEAPOT, message, ex, traits);
  }

  /*default*/ ApiImATeapotException(FineMessage richMessage) {
    super(IM_A_TEAPOT, richMessage);
  }

  /*default*/ ApiImATeapotException(FineMessage richMessage, Throwable ex) {
    super(IM_A_TEAPOT, richMessage, ex);
  }

  /*default*/ ApiImATeapotException(FineMessageSet messageSet) {
    super(IM_A_TEAPOT, messageSet);
  }

  /*default*/ ApiImATeapotException(FineMessageSet messageSet, Throwable ex) {
    super(IM_A_TEAPOT, messageSet, ex);
  }
}
