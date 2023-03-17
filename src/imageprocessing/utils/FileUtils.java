package imageprocessing.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Utils class for file operations.
 */
public class FileUtils {

  /**
   * Scans a file.
   *
   * @param sc The scanner that reads the file.
   * @return a StringBuilder that holds all text in a file.
   */
  public static StringBuilder scanFile(Scanner sc) {
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    return builder;
  }

  /**
   * Creates a file in the system with the supplied path name.
   *
   * @param path the path wherein the image's file is located on the user's computer
   * @return a buffered writer
   */
  public static BufferedWriter makeFile(String path) {
    File dir = new File(path);

    try {
      BufferedWriter output = new BufferedWriter(new FileWriter(path));
      return output;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
