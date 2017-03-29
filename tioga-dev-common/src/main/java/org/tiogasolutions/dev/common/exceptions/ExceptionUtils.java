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

import org.tiogasolutions.dev.common.ReflectUtils;
import org.tiogasolutions.dev.common.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

public abstract class ExceptionUtils {


    public static Path assertIsFile(Path path) throws IOException {
        return assertIsFile(path, IOException.class);
    }
    public static <X extends Exception> Path assertIsFile(Path path, Class<X> type) throws X {
        assertIsFile(path.toFile(), type);
        return path;
    }



    public static File assertIsFile(File file) throws IOException {
        return assertIsFile(file, IOException.class);
    }
    public static <X extends Exception> File assertIsFile(File file, Class<X> type) throws X {
        assertExists(file, type);
        if (file.isFile() == false) {
            String msg = String.format("The path \"%s\" is not a file.", file.getAbsolutePath());
            throwException(type, msg);
        }
        return file;
    }



    public static Path assertIsDirectory(Path path) throws IOException {
        return assertIsDirectory(path, IOException.class);
    }
    public static <X extends Exception> Path assertIsDirectory(Path path, Class<X> type) throws X {
        assertIsDirectory(path.toFile(), type);
        return path;
    }



    public static File assertIsDirectory(File file) throws IOException {
        return assertIsDirectory(file, IOException.class);
    }
    public static <X extends Exception> File assertIsDirectory(File file, Class<X> type) throws X {
        assertExists(file, type);
        if (file.isDirectory() == false) {
            String msg = String.format("The path \"%s\" is not a directory.", file.getAbsolutePath());
            throwException(type, msg);
        }
        return file;
    }



    public static Path assertExists(Path path) throws FileNotFoundException {
        return assertExists(path, FileNotFoundException.class);
    }
    public static <X extends Exception> Path assertExists(Path path, Class<X> type) throws X {
        assertExists(path.toFile(), type);
        return path;
    }



    public static File assertExists(File file) throws FileNotFoundException {
        return assertExists(file, FileNotFoundException.class);
    }
    public static <X extends Exception> File assertExists(File file, Class<X> type) throws X {
        if (file.exists() == false) {
            String msg = format("The path \"%s\" does not exist.", file.getAbsolutePath());
            throwException(type, msg);
        }
        return file;
    }



    public static String assertNotZeroLength(String value, String name) throws NullPointerException {
        return assertNotZeroLength(value, name, NullPointerException.class, IllegalArgumentException.class);
    }
    public static <X1 extends Exception, X2 extends Exception> String assertNotZeroLength(String value, String name, Class<X1> nullType, Class<X2> type) throws X1, X2 {
        assertNotNull(value, name, nullType);

        if (value.isEmpty()) {
            String msg = String.format("The value \"%s\" is an empty string.", name);
            throwException(type, msg);
        }

        return value;
    }



    public static <T> T assertNotNull(T value, String name) {
        return assertNotNull(value, name, NullPointerException.class);
    }
    public static <T, X extends Exception> T assertNotNull(T value, String name, Class<X> type) throws X {
        if (value == null) {
            String msg = String.format("The value \"%s\" cannot be null.", name);
            X ex = createException(type, msg);
            throw trimStackTrace(ex);
        }
        return value;
    }



    public static <T> T[] assertNotEmpty(T[] value, String name) {
        return assertNotEmpty(value, name, NullPointerException.class, IllegalArgumentException.class);
    }
    public static <T, X1 extends Exception, X2 extends Exception> T[] assertNotEmpty(T[] value, String name, Class<X1> nullType, Class<X2> type) throws X1, X2 {
        assertNotNull(value, name, nullType);

        if (value.length == 0) {
            String msg = String.format("The value \"%s\" cannot be empty.", name);
            X2 ex = createException(type, msg);
            throw trimStackTrace(ex);
        }

        return value;
    }



    public static <T> Collection<T> assertNotEmpty(Collection<T> value, String name) {
        return assertNotEmpty(value, name, NullPointerException.class, IllegalArgumentException.class);
    }
    public static <T, X1 extends Exception, X2 extends Exception> Collection<T> assertNotEmpty(Collection<T> value, String name, Class<X1> nullType, Class<X2> type) throws X1, X2 {
        assertNotNull(value, name, nullType);

        if (value.size() == 0) {
            String msg = String.format("The value \"%s\" cannot be empty.", name);
            X2 ex = createException(type, msg);
            throw trimStackTrace(ex);
        }

        return value;
    }



    public static long assertPositive(long value, String name) {
        return assertPositive(value, name, IllegalArgumentException.class);
    }
    public static <X extends Exception> long assertPositive(long value, String name, Class<X> type) throws X {
        if (value <= 0) {
            String msg = String.format("The value \"%s\" cannot be less than zero.", name);
            X ex = createException(type, msg);
            throw trimStackTrace(ex);
        }
        return value;
    }



    public static long assertNegative(long value, String name) {
        return assertNegative(value, name, IllegalArgumentException.class);
    }
    public static <X extends Exception> long assertNegative(long value, String name, Class<X> type) throws X {
        if (value >= 0) {
            String msg = String.format("The value \"%s\" cannot be greater than zero.", name);
            throw trimStackTrace(new IllegalArgumentException(msg));
        }
        return value;
    }



    private static <T extends Exception> T trimStackTrace(T ex) {
        List<StackTraceElement> elements = new ArrayList<>();
        Collections.addAll(elements, ex.getStackTrace());
        elements.remove(0);
        ex.setStackTrace(ReflectUtils.toArray(StackTraceElement.class, elements));
        return ex;
    }



    public static String toString(Throwable e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }



    public static Throwable getRootCause(Throwable e) {
        Throwable cause = e.getCause();
        while (cause != null && cause != e) {
            e = cause;
            cause = e.getCause();
        }
        return e;
    }


    /**
     * Gets the message for an exception taking into consideration that
     * not all exceptions (ie NPE) will have a message in which case the
     * class's name will be used in lue of no message.
     *
     * @param ex any throwable
     * @return a non-null message
     */
    public static String getMessage(Throwable ex) {
        String msg = ex.getMessage();
        return StringUtils.isBlank(msg) ? ex.getClass().getName() : msg;
    }

    /**
     * Gets the message for an exception's root exception taking into
     * consideration that not all exceptions (ie NPE) will have a message
     * in which case the class's name will be used in lue of no message.
     *
     * @param ex any throwable
     * @return a non-null message for the root throwable
     */
    public static String getRootMessage(Throwable ex) {
        ex = getRootCause(ex);
        String msg = ex.getMessage();
        return StringUtils.isBlank(msg) ? ex.getClass().getName() : msg;
    }

    public static String getStackTrace(Throwable e) {
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        return writer.toString();
    }

    public static List<? extends Throwable> getRootCauses(Throwable e) {

        Throwable last = e.getCause();

        List<Throwable> list = new ArrayList<>();
        list.add(e);

        while (last != e && last != null) {
            list.add(last);
            e = last;
            last = e.getCause();
        }

        return list;
    }

    public static <X extends Exception> X createException(Class<X> type, String msg) {
        try {
            return type.getConstructor(String.class).newInstance(msg);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) {
            String errMsg = String.format("Could not create constructor for %s with a single String parameter.", type.getName());
            throw new RuntimeException(errMsg, e);
        }
    }

    public static <X extends Exception> void throwException(Class<X> type, String msg) throws X {
        try {
            throw type.getConstructor(String.class).newInstance(msg);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException  e) {
            String errMsg = String.format("Could not create constructor for %s with a single String parameter.", type.getName());
            throw new RuntimeException(errMsg, e);
        }
    }
}
