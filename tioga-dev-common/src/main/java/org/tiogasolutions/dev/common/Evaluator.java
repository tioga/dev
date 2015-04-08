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

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Jacob D. Parr
 */
public class Evaluator {

  private Evaluator() {
  }
  
  /** 
   * Evaluates two objects by first testing for null conditions and then calling a.equals(b).
   * In the case of classes derived from java.util.Date, namely java.sql.Date, java.sql.Time
   * and java.sql.Timestamp, the objects are compared according to their type. For example,
   * java.sql.Time objects are comapred ignoring the date component present in the  
   * parent class where java.sql.Date objects are compared ignoring the time component.
   * @param objectA the first value to evaluate
   * @param objectB the second value to evaluate
   * @return true if the two objects are considered to be equivalent
   */
  public static boolean evaluate(Object objectA, Object objectB) {
    if (objectA == objectB) {
      // same instance or both are null
      return true;
      
    } else if (objectA == null || objectB == null) {
      // one of them is null
      return false;
    }
    
    boolean retVal;
    if (objectA instanceof java.util.Date) {
      GregorianCalendar calA = new GregorianCalendar();
      calA.setTime((java.util.Date)objectA);
      GregorianCalendar calB = new GregorianCalendar();
      calA.setTime((java.util.Date)objectB);
      
      if (objectA instanceof java.sql.Timestamp) {
        retVal = evaluateDatePortion(calA, calB) && 
                 evaluateTimePortion(calA, calB);
      } else if (objectA instanceof java.sql.Time) {
        retVal = evaluateTimePortion(calA, calB);
      } else if (objectA instanceof java.sql.Date) {
        retVal = evaluateDatePortion(calA, calB);
      } else {
        retVal = objectA.equals(objectB);
      }
    } else {
      retVal = objectA.equals(objectB);
    }
    return retVal;
  }

  /** Helper method to compare only the date portion of a java.util.Date instance */
  private static boolean evaluateDatePortion(GregorianCalendar calA, GregorianCalendar calB) {
    return calA.get(Calendar.MONTH) == calB.get(Calendar.MONTH) &&
           calA.get(Calendar.DAY_OF_MONTH) == calB.get(Calendar.DAY_OF_MONTH) &&
           calA.get(Calendar.YEAR) == calB.get(Calendar.YEAR);
  }
  
  /** Helper method to compare only the time portion of a java.util.Date instance */
  private static boolean evaluateTimePortion(GregorianCalendar calA, GregorianCalendar calB) {
    return calA.get(Calendar.HOUR_OF_DAY) == calB.get(Calendar.HOUR_OF_DAY) &&
           calA.get(Calendar.MINUTE) == calB.get(Calendar.MINUTE) &&
           calA.get(Calendar.SECOND) == calB.get(Calendar.SECOND) &&
           calA.get(Calendar.MILLISECOND) == calB.get(Calendar.MILLISECOND);
  }
}
