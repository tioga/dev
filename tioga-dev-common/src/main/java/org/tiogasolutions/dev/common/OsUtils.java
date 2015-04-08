package org.tiogasolutions.dev.common;

/**
 * Util class used to determine the current Operating System.
 */
public class OsUtils {

  private OsUtils() {
  }

  public static OperatingSystem getOperatingSystem() {
    // get the name of the operating system.
    String osName = System.getProperty("os.name").toLowerCase();

    // The most common convention is "win" and "mac" for the two operating systems.
    // Without providing support to the AccessFactory, we don't want to define more here.
    
    if (osName.contains("win")) {
      return OperatingSystem.Windows;
    } else if (osName.contains("mac")) {
      return OperatingSystem.Mac;
    } else {
      return OperatingSystem.Unknown;
    }
  }
  
  public static boolean isMac() {
    return getOperatingSystem().isMac();
  }
  
  public static boolean isWindows() {
    return getOperatingSystem().isWindows();
  }
  
  public static boolean isUnknown() {
    return getOperatingSystem().isUnknown();
  }
}
