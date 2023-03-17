package imageprocessing.model;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


/**
 * The class that handles all images after they are stored in the program,
 * Implements image interface fully, with methods operating much like described in
 * the JavaDocs.
 */
public class GeneralImage implements Image {
  // int to represent the width of an Image.
  private final int width;
  // int to represent the height of an Image.
  private final int height;
  // int to represent the max value a pixel may have in an Image.
  private final int max;
  // A nested array of pixels in an Image.
  private final Pixel[][] pixels;

  /**
   * Standard constructor for an Image.
   *
   * @param width  the width of the image.
   * @param height the height of the image.
   * @param max    the maxVal a pixel may have in the image.
   * @throws IllegalArgumentException if width, height, or maxValue are less than zero or pixels is
   *                                  null.
   */
  public GeneralImage(int width, int height, int max, Pixel[][] pixels)
          throws IllegalArgumentException {
    if (width < 0 || height < 0 || max < 0 || pixels == null) {
      throw new IllegalArgumentException("Invalid width, height, maxValue, or pixels.");
    }
    this.width = width;
    this.height = height;
    this.max = max;
    this.pixels = pixels;
  }

  /**
   * Shallow copy constructor.
   *
   * @param image the image to be copied.
   */
  public GeneralImage(Image image) {
    width = image.returnWidth();
    height = image.returnHeight();
    max = image.returnMaxValue();
    pixels = image.returnPixels();
    new GeneralImage(width, height, max, pixels);
  }

  /**
   * Overriding toString for image testing purposes. Red: Green: BLue: prefacing all values.
   *
   * @return a string with all image data and rgb values exactly like a PPM is written, but with
   *     Red: [redVal] Green: [greenVal] Blue: [blueVal].
   */
  @Override
  public String toString() {
    StringBuilder imageData = new StringBuilder();
    imageData.append(this.width + " ");
    imageData.append(this.height + "\n");
    imageData.append(this.max + "\n");
    for (Pixel[] pixelRow : pixels) {
      for (Pixel p : pixelRow) {
        imageData.append(p.toString() + "\n");
      }
    }
    return imageData.toString();
  }

  @Override
  public Pixel[][] returnPixels() {
    Pixel[][] pixelsCopy = new Pixel[this.height][this.width];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        pixelsCopy[row][col] = new GeneralPixel(this.pixels[row][col]);
      }
    }
    return pixelsCopy;
  }

  @Override
  public int returnWidth() {
    return this.width;
  }

  @Override
  public int returnHeight() {
    return this.height;
  }

  @Override
  public int returnMaxValue() {
    return this.max;
  }

  @Override
  public void horizontalFlip() {
    Collections.reverse(Arrays.asList(pixels));
  }

  @Override
  public void verticalFlip() {
    for (Pixel[] row : pixels) {
      Collections.reverse(Arrays.asList(row));
    }
  }

  @Override
  public void greyScaleColorImage(String color) {
    if (Objects.equals(color, "red")) {
      forEachPixel("red", null);
    }
    if (Objects.equals(color, "green")) {
      forEachPixel("green", null);
    }
    if (Objects.equals(color, "blue")) {
      forEachPixel("blue", null);
    }
  }

  @Override
  public void greyScaleIntensityImage() {
    forEachPixel("intensity", null);
  }

  @Override
  public void greyScaleLumaImage() {
    forEachPixel("luma", null);
  }

  @Override
  public void greyScaleValueImage() {
    forEachPixel("value", null);
  }

  @Override
  public void adjustBrightnessImage(int constant) {
    String[] args = new String[1];
    args[0] = String.valueOf(constant);
    forEachPixel("brightness", args);
  }

  @Override
  public void greyScaleImage() {
    forEachPixel("greyscale", null);
  }

  @Override
  public void toSepiaImage() {
    forEachPixel("sepia", null);
  }

  @Override
  public void gaussianBlurImage() {
    int pixRow = 0;
    int pixCol = 0;
    for (Pixel[] row : pixels) {
      for (Pixel p : row) {
        p.gaussianBlur(this.generateKernel(3, pixRow, pixCol));
        pixCol++;
      }
      pixRow++;
      pixCol = 0;
    }
  }

  @Override
  public void sharpenImage() {
    int pixRow = 0;
    int pixCol = 0;
    for (Pixel[] row : pixels) {
      for (Pixel p : row) {
        p.sharpen(this.generateKernel(5, pixRow, pixCol));
        pixCol++;
      }
      pixRow++;
      pixCol = 0;
    }
  }

  @Override
  public java.awt.Image toJavaImage() {
    BufferedImage image =
            new BufferedImage(this.returnWidth(), this.returnHeight(),
                    BufferedImage.TYPE_INT_RGB);
    Pixel[][] pixelList = this.returnPixels();
    for (int row = 0; row < this.returnHeight(); row++) {
      for (int col = 0; col < this.returnWidth(); col++) {
        int pixelColorBytes = pixelList[row][col].getRGBByte();
        image.setRGB(col, row, pixelColorBytes);
      }
    }
    return image;
  }

  /**
   * Creates a Kernel for every pixel in an image, and puts them in an ArrayList.
   *
   * @param kernelWidth the desired width of the kernel.
   * @return an ArrayList with kernels for every Pixel.
   */
  private Pixel[][] generateKernel(int kernelWidth, int pixRow, int pixCol) {
    Pixel[][] pixels = this.pixels;

    Pixel[][] pixelKern = new Pixel[kernelWidth][kernelWidth];

    for (int kernRow = 0; kernRow < kernelWidth; kernRow++) {
      for (int kernCol = 0; kernCol < kernelWidth; kernCol++) {
        try {
          pixelKern[kernRow][kernCol] = pixels[pixRow - ((kernelWidth / 2) - kernRow)]
                  [pixCol - ((kernelWidth / 2) - kernCol)];
        } catch (IndexOutOfBoundsException e) {
          pixelKern[kernRow][kernCol] = new GeneralPixel(0, 0, 0);
        }
      }
    }
    return pixelKern;
  }

  /**
   * Helper method that reduces the code duplication of for each loops in several image methods.
   *
   * @param operation the desired method so the helper can recognize which method in pixel to call
   * @param args      parameters from methods that are needed (null if not needed)
   * @throws IllegalArgumentException if an unrecognized operation is passed through
   */
  private void forEachPixel(String operation, String[] args) throws IllegalArgumentException {
    for (Pixel[] row : pixels) {
      for (Pixel p : row) {
        switch (operation) {
          case "red":
            p.greyscaleColor("red");
            break;
          case "green":
            p.greyscaleColor("green");
            break;
          case "blue":
            p.greyscaleColor("blue");
            break;
          case "intensity":
            p.greyscaleIntensity();
            break;
          case "luma":
            p.greyscaleLuma();
            break;
          case "value":
            p.greyscaleValue();
            break;
          case "greyscale":
            p.greyScale();
            break;
          case "sepia":
            p.toSepia();
            break;
          case "brightness":
            p.adjustBrightness(Integer.parseInt(args[0]));
            break;
          default:
            throw new IllegalArgumentException("Must pass a valid operation.");
        }
      }
    }
  }
}