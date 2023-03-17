package imageprocessing.controller.strategies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import imageprocessing.model.GeneralImage;
import imageprocessing.model.GeneralPixel;
import imageprocessing.model.Image;
import imageprocessing.model.Pixel;
import imageprocessing.utils.FileUtils;

/**
 * strategy object for the way that ppm images are loaded.
 */
public class PPMLoad implements LoadBehavior {
  @Override
  public Image load(String filePath) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filePath + " not found!");
    }

    //now set up the scanner to read from the string we just built in our helper.
    sc = new Scanner(FileUtils.scanFile(sc).toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] pixels = new Pixel[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel thisPix = new GeneralPixel(r, g, b, maxValue);
        pixels[row][col] = thisPix;
      }
    }
    return new GeneralImage(width, height, maxValue, pixels);
  }
}
