package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

public class Api4xxException extends ApiException {

  private static final long serialVersionUID = 1L;

  public Api4xxException(HttpStatusCode httpStatusCode) {
    super(assert400(httpStatusCode));
  }

  public Api4xxException(HttpStatusCode httpStatusCode, String message, String... traits) {
    super(assert400(httpStatusCode), message, traits);
  }

  public Api4xxException(HttpStatusCode httpStatusCode, Throwable ex, String... traits) {
    super(assert400(httpStatusCode), ex, traits);
  }

  public Api4xxException(HttpStatusCode httpStatusCode, String message, Throwable ex, String... traits) {
    super(assert400(httpStatusCode), message, ex, traits);
  }

  public Api4xxException(HttpStatusCode httpStatusCode, FineMessage richMessage) {
    super(assert400(httpStatusCode), richMessage);
  }

  public Api4xxException(HttpStatusCode httpStatusCode, FineMessage richMessage, Throwable ex) {
    super(assert400(httpStatusCode), richMessage, ex);
  }

  public Api4xxException(HttpStatusCode httpStatusCode, FineMessageSet messageSet) {
    super(assert400(httpStatusCode), messageSet);
  }

  public Api4xxException(HttpStatusCode httpStatusCode, FineMessageSet messageSet, Throwable ex) {
    super(assert400(httpStatusCode), messageSet, ex);
  }

  private static HttpStatusCode assert400(HttpStatusCode httpStatusCode) {
    int code = httpStatusCode.getCode();
    if (code < 400 || code > 499) {
      String msg = String.format("The HTTP status code (%s) must between 400 & 499 inclusive.", code);
      throw new IllegalArgumentException(msg);
    }
    return httpStatusCode;
  }
}
