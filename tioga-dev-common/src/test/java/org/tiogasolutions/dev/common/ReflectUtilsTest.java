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

package org.tiogasolutions.dev.common;

import java.lang.reflect.*;
import java.util.*;
import org.testng.*;
import org.testng.annotations.*;

@Test
public class ReflectUtilsTest {
  
  public void testGetClassName() {
    Assert.assertEquals(ReflectUtils.getClassName(0), ReflectUtilsTest.class.getName());
  }
  
  public void testGetQualifiedMethodName() {
    Assert.assertEquals(ReflectUtils.getQualifiedMethodName(0), ReflectUtilsTest.class.getName()+".testGetQualifiedMethodName");
  }
  
  public void testGetCurrentMethodName() throws Exception {
    Assert.assertEquals(ReflectUtils.getMethodName(0), "testGetCurrentMethodName");
    testLevelOne();
  }

  private void testLevelOne() {
    Assert.assertEquals(ReflectUtils.getMethodName(0), "testLevelOne");
    Assert.assertEquals(ReflectUtils.getMethodName(1), "testGetCurrentMethodName");
    testLevelTwo();
  }
  
  private void testLevelTwo() {
    Assert.assertEquals(ReflectUtils.getMethodName(0), "testLevelTwo");
    Assert.assertEquals(ReflectUtils.getMethodName(1), "testLevelOne");
    Assert.assertEquals(ReflectUtils.getMethodName(2), "testGetCurrentMethodName");
  }

  public void testGetField() {
    String[] names = {"name", "active", "canceled", "stuff"};
    for (String name : names) {
      Field field = ReflectUtils.getField(TestBean.class, name);
      // make sure we got a field
      Assert.assertNotNull(field, "Testing "+name);
      // and that we got the correct method
      Assert.assertEquals(field.getName(), name);
    }
  }

  public void testGetFieldValue() {
    TestBean bean = new TestBean();

    Map<String,Object> map = new HashMap<String,Object>();
    map.put("name",     bean.name);
    map.put("id",       bean.id);
    map.put("active",   bean.active);
    map.put("canceled", bean.canceled);
    map.put("stuff",    bean.stuff);

    for (Map.Entry<String,Object> entry : map.entrySet()) {
      Field field = ReflectUtils.getField(TestBean.class, entry.getKey());
      // make sure we got the right value
      Object value = ReflectUtils.getFieldValue(bean, field);
      Assert.assertEquals(value, entry.getValue(), "Asserting value for "+entry.getKey());
    }
  }

  public void testGetReadMethod() {
    String[] names = {"name", "active", "canceled", "stuffInIt"};
    for (String name : names) {
      Method method = ReflectUtils.getReadMethod(TestBean.class, name);
      // make sure we got a method
      Assert.assertNotNull(method);

      // and that we got the correct method - compare the ends of the methods case insensitvely. 
      String end = method.getName().substring(method.getName().length() - name.length());
      Assert.assertEquals(end.toLowerCase(), name.toLowerCase());
    }
  }
}
