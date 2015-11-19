package org.tiogasolutions.dev.common;

import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LogbackUtilsTest {

  private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogbackUtilsTest.class);

  @Test
  public void testInitialization() throws Exception {
    LogbackUtils.initLogback(Level.ALL);

    log.debug("Testing debug 123.");
    log.error("Testing error 123.");
    log.info("Testing info 123.");
    log.trace("Testing trace 123.");
    log.warn("Testing warn 123.");
  }
}