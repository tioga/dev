/*
 * Copyright 2014 Harlan Noonkester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tiogasolutions.dev.common.exceptions;

import org.tiogasolutions.dev.common.fine.FineMessage;
import org.tiogasolutions.dev.common.fine.FineMessageSet;
import org.tiogasolutions.dev.common.net.HttpStatusCode;

import static org.tiogasolutions.dev.common.net.HttpStatusCode.*;

public class ApiException extends FineRuntimeException {

  private final HttpStatusCode httpStatusCode;

  public ApiException(HttpStatusCode httpStatusCode) {
    super();
    this.httpStatusCode = httpStatusCode;
  }
  public ApiException(HttpStatusCode httpStatusCode,  String message, String...traits) {
    super(message, traits);
    this.httpStatusCode = httpStatusCode;
  }
  public ApiException(HttpStatusCode httpStatusCode,  Throwable ex, String...traits) {
    super(ex, traits);
    this.httpStatusCode = httpStatusCode;
  }
  public ApiException(HttpStatusCode httpStatusCode,  String message, Throwable ex, String...traits) {
    super(message, ex, traits);
    this.httpStatusCode = httpStatusCode;
  }



  public ApiException(HttpStatusCode httpStatusCode,  FineMessage richMessage) {
    super(richMessage);
    this.httpStatusCode = httpStatusCode;
  }
  public ApiException(HttpStatusCode httpStatusCode,  FineMessage richMessage, Throwable ex) {
    super(richMessage, ex);
    this.httpStatusCode = httpStatusCode;
  }



  public ApiException(HttpStatusCode httpStatusCode,  FineMessageSet messageSet) {
    super(messageSet);
    this.httpStatusCode = httpStatusCode;
  }
  public ApiException(HttpStatusCode httpStatusCode,  FineMessageSet messageSet, Throwable ex) {
    super(messageSet, ex);
    this.httpStatusCode = httpStatusCode;
  }



  public int getStatusCode() {
    return (httpStatusCode == null) ? -1 : httpStatusCode.getCode();
  }

  public HttpStatusCode getHttpStatusCode() {
    return httpStatusCode;
  }



  public static ApiException forbidden() {                                                         return new ApiException(FORBIDDEN); }
  public static ApiException forbidden(String message, String...traits) {                          return new ApiException(FORBIDDEN, message, traits); }
  public static ApiException forbidden(Throwable ex, String...traits) {                            return new ApiException(FORBIDDEN, ex, traits); }
  public static ApiException forbidden(String message, Throwable ex, String...traits) {            return new ApiException(FORBIDDEN, message, ex, traits); }
  public static ApiException forbidden(FineMessage richMessage) {                                  return new ApiException(FORBIDDEN, richMessage); }
  public static ApiException forbidden(FineMessage richMessage, Throwable ex) {                    return new ApiException(FORBIDDEN, richMessage, ex); }
  public static ApiException forbidden(FineMessageSet messageSet) {                                return new ApiException(FORBIDDEN, messageSet); }
  public static ApiException forbidden(FineMessageSet messageSet, Throwable ex) {                  return new ApiException(FORBIDDEN, messageSet, ex); }



  public static ApiException unauthorized() {                                                         return new ApiException(UNAUTHORIZED); }
  public static ApiException unauthorized(String message, String...traits) {                          return new ApiException(UNAUTHORIZED, message, traits); }
  public static ApiException unauthorized(Throwable ex, String...traits) {                            return new ApiException(UNAUTHORIZED, ex, traits); }
  public static ApiException unauthorized(String message, Throwable ex, String...traits) {            return new ApiException(UNAUTHORIZED, message, ex, traits); }
  public static ApiException unauthorized(FineMessage richMessage) {                                  return new ApiException(UNAUTHORIZED, richMessage); }
  public static ApiException unauthorized(FineMessage richMessage, Throwable ex) {                    return new ApiException(UNAUTHORIZED, richMessage, ex); }
  public static ApiException unauthorized(FineMessageSet messageSet) {                                return new ApiException(UNAUTHORIZED, messageSet); }
  public static ApiException unauthorized(FineMessageSet messageSet, Throwable ex) {                  return new ApiException(UNAUTHORIZED, messageSet, ex); }



  public static ApiException conflict() {                                                         return new ApiException(CONFLICT); }
  public static ApiException conflict(String message, String...traits) {                          return new ApiException(CONFLICT, message, traits); }
  public static ApiException conflict(Throwable ex, String...traits) {                            return new ApiException(CONFLICT, ex, traits); }
  public static ApiException conflict(String message, Throwable ex, String...traits) {            return new ApiException(CONFLICT, message, ex, traits); }
  public static ApiException conflict(FineMessage richMessage) {                                  return new ApiException(CONFLICT, richMessage); }
  public static ApiException conflict(FineMessage richMessage, Throwable ex) {                    return new ApiException(CONFLICT, richMessage, ex); }
  public static ApiException conflict(FineMessageSet messageSet) {                                return new ApiException(CONFLICT, messageSet); }
  public static ApiException conflict(FineMessageSet messageSet, Throwable ex) {                  return new ApiException(CONFLICT, messageSet, ex); }



  public static ApiException notFound() {                                                         return new ApiException(NOT_FOUND); }
  public static ApiException notFound(String message, String...traits) {                          return new ApiException(NOT_FOUND, message, traits); }
  public static ApiException notFound(Throwable ex, String...traits) {                            return new ApiException(NOT_FOUND, ex, traits); }
  public static ApiException notFound(String message, Throwable ex, String...traits) {            return new ApiException(NOT_FOUND, message, ex, traits); }
  public static ApiException notFound(FineMessage richMessage) {                                  return new ApiException(NOT_FOUND, richMessage); }
  public static ApiException notFound(FineMessage richMessage, Throwable ex) {                    return new ApiException(NOT_FOUND, richMessage, ex); }
  public static ApiException notFound(FineMessageSet messageSet) {                                return new ApiException(NOT_FOUND, messageSet); }
  public static ApiException notFound(FineMessageSet messageSet, Throwable ex) {                  return new ApiException(NOT_FOUND, messageSet, ex); }



  public static ApiException badRequest() {                                                         return new ApiException(BAD_REQUEST); }
  public static ApiException badRequest(String message, String...traits) {                          return new ApiException(BAD_REQUEST, message, traits); }
  public static ApiException badRequest(Throwable ex, String...traits) {                            return new ApiException(BAD_REQUEST, ex, traits); }
  public static ApiException badRequest(String message, Throwable ex, String...traits) {            return new ApiException(BAD_REQUEST, message, ex, traits); }
  public static ApiException badRequest(FineMessage richMessage) {                                  return new ApiException(BAD_REQUEST, richMessage); }
  public static ApiException badRequest(FineMessage richMessage, Throwable ex) {                    return new ApiException(BAD_REQUEST, richMessage, ex); }
  public static ApiException badRequest(FineMessageSet messageSet) {                                return new ApiException(BAD_REQUEST, messageSet); }
  public static ApiException badRequest(FineMessageSet messageSet, Throwable ex) {                  return new ApiException(BAD_REQUEST, messageSet, ex); }



  public static ApiException internalServerError() {                                                        return new ApiException(INTERNAL_SERVER_ERROR); }
  public static ApiException internalServerError(String message, String...traits) {                         return new ApiException(INTERNAL_SERVER_ERROR, message, traits); }
  public static ApiException internalServerError(Throwable ex, String...traits) {                           return new ApiException(INTERNAL_SERVER_ERROR, ex, traits); }
  public static ApiException internalServerError(String message, Throwable ex, String...traits) {           return new ApiException(INTERNAL_SERVER_ERROR, message, ex, traits); }
  public static ApiException internalServerError(FineMessage richMessage) {                                 return new ApiException(INTERNAL_SERVER_ERROR, richMessage); }
  public static ApiException internalServerError(FineMessage richMessage, Throwable ex) {                   return new ApiException(INTERNAL_SERVER_ERROR, richMessage, ex); }
  public static ApiException internalServerError(FineMessageSet messageSet) {                               return new ApiException(INTERNAL_SERVER_ERROR, messageSet); }
  public static ApiException internalServerError(FineMessageSet messageSet, Throwable ex) {                 return new ApiException(INTERNAL_SERVER_ERROR, messageSet, ex); }



  public static ApiException serviceUnavailable() {                                                         return new ApiException(SERVICE_UNAVAILABLE); }
  public static ApiException serviceUnavailable(String message, String...traits) {                          return new ApiException(SERVICE_UNAVAILABLE, message, traits); }
  public static ApiException serviceUnavailable(Throwable ex, String...traits) {                            return new ApiException(SERVICE_UNAVAILABLE, ex, traits); }
  public static ApiException serviceUnavailable(String message, Throwable ex, String...traits) {            return new ApiException(SERVICE_UNAVAILABLE, message, ex, traits); }
  public static ApiException serviceUnavailable(FineMessage richMessage) {                                  return new ApiException(SERVICE_UNAVAILABLE, richMessage); }
  public static ApiException serviceUnavailable(FineMessage richMessage, Throwable ex) {                    return new ApiException(SERVICE_UNAVAILABLE, richMessage, ex); }
  public static ApiException serviceUnavailable(FineMessageSet messageSet) {                                return new ApiException(SERVICE_UNAVAILABLE, messageSet); }
  public static ApiException serviceUnavailable(FineMessageSet messageSet, Throwable ex) {                  return new ApiException(SERVICE_UNAVAILABLE, messageSet, ex); }



  public static ApiException preconditionFailed() {                                                         return new ApiException(PRECONDITION_FAILED); }
  public static ApiException preconditionFailed(String message, String...traits) {                          return new ApiException(PRECONDITION_FAILED, message, traits); }
  public static ApiException preconditionFailed(Throwable ex, String...traits) {                            return new ApiException(PRECONDITION_FAILED, ex, traits); }
  public static ApiException preconditionFailed(String message, Throwable ex, String...traits) {            return new ApiException(PRECONDITION_FAILED, message, ex, traits); }
  public static ApiException preconditionFailed(FineMessage richMessage) {                                  return new ApiException(PRECONDITION_FAILED, richMessage); }
  public static ApiException preconditionFailed(FineMessage richMessage, Throwable ex) {                    return new ApiException(PRECONDITION_FAILED, richMessage, ex); }
  public static ApiException preconditionFailed(FineMessageSet messageSet) {                                return new ApiException(PRECONDITION_FAILED, messageSet); }
  public static ApiException preconditionFailed(FineMessageSet messageSet, Throwable ex) {                  return new ApiException(PRECONDITION_FAILED, messageSet, ex); }
}
