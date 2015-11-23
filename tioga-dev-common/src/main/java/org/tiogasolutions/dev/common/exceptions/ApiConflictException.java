package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.CONFLICT;

public class ApiConflictException extends Api4xxException {

  private static final long serialVersionUID = 1L;

  /*default*/ ApiConflictException() {
    super(CONFLICT);
  }

  /*default*/ ApiConflictException(String message, String... traits) {
    super(CONFLICT, message, traits);
  }

  /*default*/ ApiConflictException(Throwable ex, String... traits) {
    super(CONFLICT, ex, traits);
  }

  /*default*/ ApiConflictException(String message, Throwable ex, String... traits) {
    super(CONFLICT, message, ex, traits);
  }

  /*default*/ ApiConflictException(FineMessage richMessage) {
    super(CONFLICT, richMessage);
  }

  /*default*/ ApiConflictException(FineMessage richMessage, Throwable ex) {
    super(CONFLICT, richMessage, ex);
  }

  /*default*/ ApiConflictException(FineMessageSet messageSet) {
    super(CONFLICT, messageSet);
  }

  /*default*/ ApiConflictException(FineMessageSet messageSet, Throwable ex) {
    super(CONFLICT, messageSet, ex);
  }
}
