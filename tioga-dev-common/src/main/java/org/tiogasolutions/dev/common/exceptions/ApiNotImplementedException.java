package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.NOT_IMPLEMENTED;

public class ApiNotImplementedException extends Api5xxException {

  /*default*/ ApiNotImplementedException() {
    super(NOT_IMPLEMENTED);
  }

  /*default*/ ApiNotImplementedException(String message, String... traits) {
    super(NOT_IMPLEMENTED, message, traits);
  }

  /*default*/ ApiNotImplementedException(Throwable ex, String... traits) {
    super(NOT_IMPLEMENTED, ex, traits);
  }

  /*default*/ ApiNotImplementedException(String message, Throwable ex, String... traits) {
    super(NOT_IMPLEMENTED, message, ex, traits);
  }

  /*default*/ ApiNotImplementedException(FineMessage richMessage) {
    super(NOT_IMPLEMENTED, richMessage);
  }

  /*default*/ ApiNotImplementedException(FineMessage richMessage, Throwable ex) {
    super(NOT_IMPLEMENTED, richMessage, ex);
  }

  /*default*/ ApiNotImplementedException(FineMessageSet messageSet) {
    super(NOT_IMPLEMENTED, messageSet);
  }

  /*default*/ ApiNotImplementedException(FineMessageSet messageSet, Throwable ex) {
    super(NOT_IMPLEMENTED, messageSet, ex);
  }
}
