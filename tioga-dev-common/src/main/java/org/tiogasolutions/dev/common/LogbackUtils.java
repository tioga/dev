package org.tiogasolutions.dev.common;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.LogbackException;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.nio.file.Files;
import java.nio.file.Path;

public class LogbackUtils {

  private static final org.slf4j.Logger log = LoggerFactory.getLogger(LogbackUtils.class);

  public static void initLogback(Level level) {

    // Reroute java.util.Logger to SLF4J
    SLF4JBridgeHandler.removeHandlersForRootLogger();
    SLF4JBridgeHandler.install();

    ILoggerFactory factory = LoggerFactory.getILoggerFactory();
    if (factory instanceof LoggerContext == false) {
      throw new LogbackException("Expected LOGBACK binding with SLF4J, but another log system has taken the place: " + factory.getClass().getSimpleName());
    }

    LoggerContext context = (LoggerContext)factory;

    PatternLayoutEncoder ple = new PatternLayoutEncoder();
    String pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";
    ple.setPattern(pattern);
    ple.setContext(context);
    ple.start();

    ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
    appender.setName("STDOUT");
    appender.setContext(context);
    appender.setEncoder(ple);

    Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    root.setLevel(level);
    root.addAppender(appender);

    log.info("Using default logback config: {} ({})", level, pattern);
  }

  public static Path initLogback(Path configDir, String propertyName, String fileName) {

    // Configure everything to initially log to the console.
    initLogback(Level.WARN);

    if (configDir == null) {
      log.info("Config directory not specified, using default logback config.");
      return null;
    } else if (Files.exists(configDir) == false) {
      log.info("Config directory ({}) does not exist, using default logback config.", configDir);
      return null;
    }

    String logConfigArg = EnvUtils.findProperty(propertyName, fileName);
    Path logConfigFile = configDir.resolve(logConfigArg);

    if (Files.notExists(logConfigFile)) {
      log.info("Missing {}, using default logback config.", logConfigFile.toString());
      return null;
    }

    log.info("Configured logging from  {}", logConfigFile.toString());
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    try {
      JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(context);

      // Call context.reset() to clear any previous configuration, e.g. default
      // configuration. For multi-step configuration, omit calling context.reset().
      context.reset();
      configurator.doConfigure(logConfigFile.toString());

    } catch (JoranException ignored) {/* ignored */}

    StatusPrinter.printInCaseOfErrorsOrWarnings(context);

    return logConfigFile;
  }
}
