package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.FORBIDDEN;

public class ApiForbiddenException extends Api4xxException {

  /*default*/ ApiForbiddenException() {
    super(FORBIDDEN);
  }

  /*default*/ ApiForbiddenException(String message, String... traits) {
    super(FORBIDDEN, message, traits);
  }

  /*default*/ ApiForbiddenException(Throwable ex, String... traits) {
    super(FORBIDDEN, ex, traits);
  }

  /*default*/ ApiForbiddenException(String message, Throwable ex, String... traits) {
    super(FORBIDDEN, message, ex, traits);
  }

  /*default*/ ApiForbiddenException(FineMessage richMessage) {
    super(FORBIDDEN, richMessage);
  }

  /*default*/ ApiForbiddenException(FineMessage richMessage, Throwable ex) {
    super(FORBIDDEN, richMessage, ex);
  }

  /*default*/ ApiForbiddenException(FineMessageSet messageSet) {
    super(FORBIDDEN, messageSet);
  }

  /*default*/ ApiForbiddenException(FineMessageSet messageSet, Throwable ex) {
    super(FORBIDDEN, messageSet, ex);
  }
}
