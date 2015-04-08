package org.tiogasolutions.dev.common;

/**
 * Simple enumeration of operating systems.
 */
public enum OperatingSystem {
  
  Windows, 
  Mac,
  Unknown;
  
  public boolean isWindows() {
    return OperatingSystem.Windows.equals(this);
  }
  
  public boolean isMac() {
    return OperatingSystem.Mac.equals(this);
  }
  
  public boolean isUnknown() {
    return OperatingSystem.Unknown.equals(this);
  }
}
