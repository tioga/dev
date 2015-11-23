package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.PRECONDITION_FAILED;

public class ApiPreconditionFailedException extends Api4xxException {

  private static final long serialVersionUID = 1L;

  /*default*/ ApiPreconditionFailedException() {
    super(PRECONDITION_FAILED);
  }

  /*default*/ ApiPreconditionFailedException(String message, String... traits) {
    super(PRECONDITION_FAILED, message, traits);
  }

  /*default*/ ApiPreconditionFailedException(Throwable ex, String... traits) {
    super(PRECONDITION_FAILED, ex, traits);
  }

  /*default*/ ApiPreconditionFailedException(String message, Throwable ex, String... traits) {
    super(PRECONDITION_FAILED, message, ex, traits);
  }

  /*default*/ ApiPreconditionFailedException(FineMessage richMessage) {
    super(PRECONDITION_FAILED, richMessage);
  }

  /*default*/ ApiPreconditionFailedException(FineMessage richMessage, Throwable ex) {
    super(PRECONDITION_FAILED, richMessage, ex);
  }

  /*default*/ ApiPreconditionFailedException(FineMessageSet messageSet) {
    super(PRECONDITION_FAILED, messageSet);
  }

  /*default*/ ApiPreconditionFailedException(FineMessageSet messageSet, Throwable ex) {
    super(PRECONDITION_FAILED, messageSet, ex);
  }
}
