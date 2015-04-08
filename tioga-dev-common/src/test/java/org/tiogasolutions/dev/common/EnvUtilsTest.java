package org.tiogasolutions.dev.common;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

@Test
public class EnvUtilsTest {

  public void testFindProperty() {

    String value;
    Map<String,String> envMap;



    // Natural search
    envMap = createEnvMap("Stc.Res.Home", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "Stc.Res.Home", "Not Found");
    assertEquals(value, "Testing 123");

    // Forced lower
    envMap = createEnvMap("stc.res.home", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "STC.RES.HOME", "Not Found");
    assertEquals(value, "Testing 123");

    // Forced upper
    envMap = createEnvMap("STC.RES.HOME", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "stc.res.home", "Not Found");
    assertEquals(value, "Testing 123");


    // Natural search, with underscore
    envMap = createEnvMap("Stc_Res_Home", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "Stc.Res.Home", "Not Found");
    assertEquals(value, "Testing 123");

    // Forced lower, with underscore
    envMap = createEnvMap("stc_res_home", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "STC.RES.HOME", "Not Found");
    assertEquals(value, "Testing 123");

    // Forced upper, with underscore
    envMap = createEnvMap("STC_RES_HOME", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "stc.res.home", "Not Found");
    assertEquals(value, "Testing 123");



    // Natural search, with period
    envMap = createEnvMap("Stc.Res.Home", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "Stc_Res_Home", "Not Found");
    assertEquals(value, "Testing 123");

    // Forced lower, with period
    envMap = createEnvMap("stc.res.home", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "STC_RES_HOME", "Not Found");
    assertEquals(value, "Testing 123");

    // Forced upper, with period
    envMap = createEnvMap("STC.RES.HOME", "Testing 123");
    value = EnvUtils.findEnvironmentProperty(envMap, "stc_res_home", "Not Found");
    assertEquals(value, "Testing 123");
  }

  private Map<String,String> createEnvMap(String key, String value) {
    Map<String,String> envMap =  new HashMap<>();
    envMap.put(key,value);
    return envMap;
  }

  public void testGetWindowsProperties() {
    if (OsUtils.isWindows() == false) return;

    String value = EnvUtils.findProperty("PROCESSOR_LEVEL");
    assertNotNull(value, value);

    value = EnvUtils.findProperty("processor_level");
    assertNotNull(value, value);

    value = EnvUtils.findProperty("PROCESSOR.LEVEL");
    assertNotNull(value, value);

    value = EnvUtils.findProperty("processor.level");
    assertNotNull(value, value);
  }
}