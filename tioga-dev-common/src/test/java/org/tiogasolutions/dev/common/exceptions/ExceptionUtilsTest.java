/*
 * Copyright 2012 Jacob D Parr
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

import java.io.IOException;
import java.util.List;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test
public class ExceptionUtilsTest {

  @Test(enabled = false)
  public void testGetStackTrace() throws Exception {
    Throwable e = getTestableException();
    String stackTrace = ExceptionUtils.getStackTrace(e);

    stackTrace = stackTrace.replace("\r", "");
    stackTrace = stackTrace.replace("\n", "");
    stackTrace = stackTrace.replace("\t", "");

    String expected = "java.io.IOException: He's having issues" +
        "at ExceptionUtilsTest.wrappedException(ExceptionUtilsTest.java:69)" +
        "at ExceptionUtilsTest.getTestableException(ExceptionUtilsTest.java:53)" +
        "at ExceptionUtilsTest.testGetStackTrace(ExceptionUtilsTest.java:13)" +
        "at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)" +
        "at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)" +
        "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)" +
        "at java.lang.reflect.Method.invoke(Method.java:606)" +
        "at org.testng.internal.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:80)" +
        "at org.testng.internal.Invoker.invokeMethod(Invoker.java:691)" +
        "at org.testng.internal.Invoker.invokeTestMethod(Invoker.java:883)" +
        "at org.testng.internal.Invoker.invokeTestMethods(Invoker.java:1208)" +
        "at org.testng.internal.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:127)" +
        "at org.testng.internal.TestMethodWorker.run(TestMethodWorker.java:111)" +
        "at org.testng.TestRunner.privateRun(TestRunner.java:758)" +
        "at org.testng.TestRunner.run(TestRunner.java:613)" +
        "at org.testng.SuiteRunner.runTest(SuiteRunner.java:334)" +
        "at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:329)" +
        "at org.testng.SuiteRunner.privateRun(SuiteRunner.java:291)" +
        "at org.testng.SuiteRunner.run(SuiteRunner.java:240)" +
        "at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:53)" +
        "at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:87)" +
        "at org.testng.TestNG.runSuitesSequentially(TestNG.java:1137)" +
        "at org.testng.TestNG.runSuitesLocally(TestNG.java:1062)" +
        "at org.testng.TestNG.run(TestNG.java:974)" +
        "at org.testng.remote.RemoteTestNG.run(RemoteTestNG.java:109)" +
        "at org.testng.remote.RemoteTestNG.initAndRun(RemoteTestNG.java:202)" +
        "at org.testng.remote.RemoteTestNG.main(RemoteTestNG.java:173)" +
        "at org.testng.RemoteTestNGStarter.main(RemoteTestNGStarter.java:125)" +
        "Caused by: java.lang.IllegalArgumentException: I just can't handle it!" +
        "at ExceptionUtilsTest.originalFailure(ExceptionUtilsTest.java:62)" +
        "at ExceptionUtilsTest.wrappedException(ExceptionUtilsTest.java:73)" +
        "... 27 more";

    assertEquals(stackTrace, expected);
  }

  private Throwable getTestableException() {
    try {
      wrappedException();
      return null;

    } catch (Throwable e) {
      return e;
    }
  }

  private void originalFailure() throws IOException {
    throw new IllegalArgumentException("I just can't handle it!");
  }

  private void wrappedException() throws Exception {
    try {
      originalFailure();
    } catch (Throwable e) {
      throw new IOException("He's having issues", e);
    }
  }

  public void testGetRootCauses() throws Exception {
    IOException exceptionA = new IOException("This is the IOException.");
    IllegalArgumentException exceptionB = new IllegalArgumentException("This is the IllegalArgumentException.", exceptionA);
    UnsupportedOperationException exceptionC = new UnsupportedOperationException("This is the UnsupportedOperationException.", exceptionB);

    List<? extends Throwable> list = ExceptionUtils.getRootCauses(exceptionC);
    assertEquals(list.size(), 3);

    assertEquals(list.get(0).getClass(), UnsupportedOperationException.class);
    assertEquals(list.get(0).getMessage(), "This is the UnsupportedOperationException.");

    assertEquals(list.get(1).getClass(), IllegalArgumentException.class);
    assertEquals(list.get(1).getMessage(), "This is the IllegalArgumentException.");

    assertEquals(list.get(2).getClass(), IOException.class);
    assertEquals(list.get(2).getMessage(), "This is the IOException.");
  }
}
