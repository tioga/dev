package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.BAD_REQUEST;

public class ApiBadRequestException extends Api4xxException {

  /*default*/ ApiBadRequestException() {
    super(BAD_REQUEST);
  }

  /*default*/ ApiBadRequestException(String message, String... traits) {
    super(BAD_REQUEST, message, traits);
  }

  /*default*/ ApiBadRequestException(Throwable ex, String... traits) {
    super(BAD_REQUEST, ex, traits);
  }

  /*default*/ ApiBadRequestException(String message, Throwable ex, String... traits) {
    super(BAD_REQUEST, message, ex, traits);
  }

  /*default*/ ApiBadRequestException(FineMessage richMessage) {
    super(BAD_REQUEST, richMessage);
  }

  /*default*/ ApiBadRequestException(FineMessage richMessage, Throwable ex) {
    super(BAD_REQUEST, richMessage, ex);
  }

  /*default*/ ApiBadRequestException(FineMessageSet messageSet) {
    super(BAD_REQUEST, messageSet);
  }

  /*default*/ ApiBadRequestException(FineMessageSet messageSet, Throwable ex) {
    super(BAD_REQUEST, messageSet, ex);
  }
}
