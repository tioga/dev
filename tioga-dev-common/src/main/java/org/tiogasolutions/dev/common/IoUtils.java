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

import java.util.zip.*;
import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;
import org.tiogasolutions.dev.common.io.FileNameFilter;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class IoUtils {

  public static String toString(URL url) throws IOException {
    try {
      return toString(url.toURI());
    } catch (URISyntaxException e) {
      throw new IOException(e);
    }
  }

  public static String toString(URI uri) throws IOException {
    File file = new File(uri);
    return toString(file);
  }

  public static String toString(Exception e) {
    StringWriter writer = new StringWriter();
    e.printStackTrace(new PrintWriter(writer));
    return writer.toString();
  }

  public static String toString(File file) throws IOException {
    return toString(new FileReader(file));
  }

  public static String toString(InputStream is) throws IOException {
    return toString(new BufferedReader(new InputStreamReader(is)));
  }

  public static String toString(Reader reader) throws IOException {
    try {
      String line = "";
      StringBuilder builder = new StringBuilder();
      BufferedReader bufferedReader = (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);

      while ( (line=bufferedReader.readLine()) != null) {
        builder.append(line);
        builder.append("\n");
      }

      return builder.toString();

    } finally {
      reader.close();
    }
  }

  public static void toFile(InputStream inputStream, File file) throws IOException {
    deleteFile(file);
    FileOutputStream out = new FileOutputStream(file);
    toStream(inputStream,  out);
  }

  public static byte[] toBytes(ZipInputStream zis) throws IOException {
    return toBytes(zis, 1024);
  }

  public static byte[] toBytes(ZipInputStream zis, int initCapacity) throws IOException {
    // advance to the next entry
    zis.getNextEntry();

    int count;
    byte data[] = new byte[initCapacity];

    ByteArrayOutputStream output = new ByteArrayOutputStream();

    try (BufferedOutputStream buffer = new BufferedOutputStream(output, initCapacity)) {
      while ((count = zis.read(data, 0, initCapacity))  != -1) {
        buffer.write(data, 0, count);
      }
    }

    return output.toByteArray();
  }

  public static byte[] toBytes(InputStream in) throws IOException {
    return toBytes(in, 1024);
  }

  public static byte[] toBytes(InputStream in, int initCapacity) throws IOException {
    ByteArrayOutputStream out = new ByteArrayOutputStream(initCapacity);
    toStream(in, out);
    return out.toByteArray();
  }

  public static void toStream(InputStream in, OutputStream out) throws IOException {
    try {

      int count;
      byte[] buf = new byte[1024];

      while( (count = in.read(buf)) != -1) {
         if( Thread.interrupted() ) {
             throw new IOException("Thread was interrupted.");
         }
         out.write(buf, 0, count);
      }

    } finally {
      out.close();
      in.close();
    }
  }

  public static void write(File file, String result) throws IOException {
    try (FileWriter writer = new FileWriter(file)) {
      writer.write(result);
    }
  }

  public static void write(File file, byte[] bytes) throws IOException {
    try (FileOutputStream os = new FileOutputStream(file)) {
      os.write(bytes);
    }
  }

  public static List<String> toList(File file) throws IOException {
    return toList(new FileReader(file));
  }

  public static List<String> toList(Reader reader) throws IOException {
    try {

      String line = "";
      List<String> list = new ArrayList<String>();
      BufferedReader bufferedReader = (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);

      while ( (line=bufferedReader.readLine()) != null) {
        list.add(line);
      }

      return list;

    } finally {
      reader.close();
    }
  }

  public static Path createPath(Path path) throws IOException {
    createPath(path.toFile());
    return path;
  }

  public static File createPath(File file) throws IOException {
    if (file.exists() == false && file.mkdirs() == false) {
      throw new IOException("Unable to create directory: " + file.getAbsolutePath());
    }
    return file;
  }

  public static void deleteFile(Path path) throws IOException {
    deleteFile(path.toFile());
  }
  public static void deleteFile(File file) throws IOException {
    if (file.exists() == false) {
      return; // it doesn't exist.
    }

    if (file.delete() == false) {
      String msg = String.format("Unable to delete the file %s", file.getAbsolutePath());
      throw new IOException(msg);
    }
  }

  public static void deletePath(File file) throws IOException {
    deletePath(file.toPath());
  }

  public static void deletePath(Path path) throws IOException {

    if (path.toFile().exists() == false) {
      return; // it doesn't exist.
    }

    Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        Files.delete(dir);
        return FileVisitResult.CONTINUE;
      }
    });
  }

  public static File currentDir() {
    return new File(System.getProperty("user.dir"));
  }

  public static File findDirNearCurrent(String dirName) {
    return findDirNear(currentDir(), dirName);
  }

  public static File findDirNear(File startDir, String dirName) {

    if (dirName.equalsIgnoreCase(startDir.getName())) {
      return startDir;
    }

    // Is it a sub directory
    List<File> dirs = listDirs(startDir, new FileNameFilter(dirName));
    if (!dirs.isEmpty()) {
      return dirs.get(0);
    }

    // Is it a subdirectory of parent
    dirs = listDirs(startDir.getParentFile(), new FileNameFilter(dirName));
    if (!dirs.isEmpty()) {
      return dirs.get(0);
    }

    return null;
  }


  public static List<Path> toPaths(Collection<File> files) {
    List<Path> paths = new ArrayList<>();

    for (File file : files) {
      paths.add(file.toPath());
    }

    return paths;
  }

  public static List<File> toFiles(Collection<Path> paths) {
    List<File> files = new ArrayList<>();

    for (Path path : paths) {
      files.add(path.toFile());
    }

    return files;
  }

  public static List<File> list(File directory) throws IOException {
    return list(directory, null);
  }
  public static List<File> list(File directory, DirectoryStream.Filter<? super Path> filter) throws IOException {
    // Use the existing method to list all the files.
    List<Path> paths = list(directory.toPath(), filter);
    return toFiles(paths);
  }

  public static List<Path> list(Path directory) throws IOException {
    return list(directory, null);
  }
  public static List<Path> list(Path directory, DirectoryStream.Filter<? super Path> filter) throws IOException {
    ExceptionUtils.assertNotNull(directory, "directory");
    ExceptionUtils.assertIsDirectory(directory);

    if (filter == null) {
      filter = new DirectoryStream.Filter<Path>() {
        @Override public boolean accept(Path entry) { return true; }
      };
    }

    List<Path> paths = new ArrayList<>();

    try (DirectoryStream<Path> ds = Files.newDirectoryStream(directory, filter)) {
      for (Path path : ds) {
        paths.add(path);
      }
    }

    return paths;
  }

  public static List<Path> listDirs(Path directory, FileFilter...fileFilters) throws IOException {
    // Use the existing method to list all the files.
    List<File> files = listDirs(directory.toFile(), fileFilters);
    return toPaths(files);
  }

  public static List<File> listDirs(File parentDir, FileFilter...fileFilters) {
    List<File> dirList = new ArrayList<File>();

    File[] listFiles = null;
    if (parentDir != null && parentDir.exists()) {
      listFiles = parentDir.listFiles();
    }
    if (listFiles != null) {
      for (File file : listFiles) {
        if (file.isDirectory()) {
          boolean accepted = true;
          for (FileFilter filter : fileFilters) {
            if (!filter.accept(file)) {
              // Rejected by filter so no need to look at others.
              accepted = false;
              break;
            }
          }
          if (accepted) {
            dirList.add(file);
          }
        }
      }
    }
    return dirList;
  }

  public static void copy(Path inputPath, Path outputPath) throws IOException {
    ExceptionUtils.assertNotNull(inputPath, "inputPath");
    ExceptionUtils.assertNotNull(outputPath, "outputPath");
    copy(inputPath.toFile(), outputPath.toFile());
  }

  public static void copy(File inputFile, File outputFile) throws IOException {
    ExceptionUtils.assertNotNull(inputFile, "inputFile");
    ExceptionUtils.assertNotNull(outputFile, "outputFile");

    ExceptionUtils.assertExists(inputFile);
    createPath(outputFile.getParentFile());

    FileInputStream inputStream = null;
    FileOutputStream outputStream = null;

    try {

      inputStream = new FileInputStream(inputFile);
      outputStream = new FileOutputStream(outputFile);

      byte[] buffer = new byte[1024];
      int length;
      while ((length = inputStream.read(buffer)) >= 0) {
        outputStream.write(buffer, 0, length);
      }

    } finally {
      if (inputStream != null) {
        inputStream.close();
      }

      if (outputStream != null) {
        outputStream.close();
      }
    }
  }
}
