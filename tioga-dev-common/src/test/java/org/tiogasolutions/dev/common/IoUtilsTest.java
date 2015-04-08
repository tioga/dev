package org.tiogasolutions.dev.common;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class IoUtilsTest {

  private File rootFile;
  private Path rootPath;

  @BeforeClass
  public void beforeClass() throws Exception {
    URL url = getClass().getResource("/io-utils-tests/test-1.txt");
    assertNotNull(url);

    File file = new File(url.toURI());
    rootFile = file.getParentFile();
    rootPath = rootFile.toPath();
  }

  public void testListFiles() throws Exception {
    List<String> expected = Arrays.asList(
        "dir-a", "dir-b", "dir-c",
        "test-1.html", "test-1.txt",
        "test-2.html", "test-2.txt",
        "test-3.html", "test-3.txt");

    assertContainsAllFiles(expected, IoUtils.list(rootFile));
  }

  public void testListFilteredFiles() throws Exception {
    List<String> expected = Arrays.asList("dir-a", "dir-b", "dir-c");
    assertContainsAllFiles(expected, IoUtils.list(rootFile, dirFilter));

    expected = Arrays.asList("test-1.txt", "test-2.txt", "test-3.txt");
    assertContainsAllFiles(expected, IoUtils.list(rootFile, txtFilter));
  }

  public void testListPaths() throws Exception {
    List<String> expected = Arrays.asList(
        "dir-a", "dir-b", "dir-c",
        "test-1.html", "test-1.txt",
        "test-2.html", "test-2.txt",
        "test-3.html", "test-3.txt");

    assertContainsAllPaths(expected, IoUtils.list(rootPath));
  }

  public void testListFilteredPaths() throws Exception {
    List<String> expected = Arrays.asList("dir-a", "dir-b", "dir-c");
    assertContainsAllPaths(expected, IoUtils.list(rootPath, dirFilter));

    expected = Arrays.asList("test-1.txt", "test-2.txt", "test-3.txt");
    assertContainsAllPaths(expected, IoUtils.list(rootPath, txtFilter));
  }

  public static DirectoryStream.Filter<Path> dirFilter = new DirectoryStream.Filter<Path>(){
    @Override public boolean accept(Path entry) throws IOException {
      return entry.toFile().isDirectory();
    }
  };

  public static DirectoryStream.Filter<Path> txtFilter = new DirectoryStream.Filter<Path>(){
    @Override public boolean accept(Path entry) throws IOException {
      return entry.toFile().getName().endsWith(".txt");
    }
  };

  private void assertContainsAllFiles(List<String> expected, List<File> files) {
    expected = new ArrayList<>(expected);
    assertEquals(files.size(), expected.size());

    for (File file : files) {
      String name = file.getName();
      assertTrue(expected.remove(name), name+" not found: " + files);
    }
  }

  private void assertContainsAllPaths(List<String> expected, List<Path> paths) {
    expected = new ArrayList<>(expected);
    assertEquals(paths.size(), expected.size());

    for (Path path : paths) {
      String name = path.toFile().getName();
      assertTrue(expected.remove(name), name+" not found: " + paths);
    }
  }
}