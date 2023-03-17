package imageprocessing.model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent a Pixel in an image.
 */
public class GeneralPixel implements Pixel {
  private final int maxVal;
  private int r;
  private int g;
  private int b;

  /**
   * Constructor for a Pixel with the maxValue set automatically at 255 for testing purposes.
   *
   * @param red   red color value
   * @param green green color value
   * @param blue  blue color value
   * @throws IllegalArgumentException if any RGB value is negative or greater than 255
   */
  public GeneralPixel(int red, int green, int blue) throws IllegalArgumentException {
    this.maxVal = 255;
    if (red < 0 || green < 0 || blue < 0
        || red > maxVal || green > maxVal || blue > maxVal) {
      throw new IllegalArgumentException("Test pixel RGB values must be between 0 and 255");
    }
    this.r = red;
    this.g = green;
    this.b = blue;
  }

  /**
   * Constructor for a pixel of an image.
   *
   * @param red      red color value
   * @param green    green color value
   * @param blue     blue color value
   * @param maxValue the highest an RGB value can be
   * @throws IllegalArgumentException if the maxValue is less than zero
   */
  public GeneralPixel(int red, int green, int blue, int maxValue) throws IllegalArgumentException {
    if (maxValue < 0) {
      throw new IllegalArgumentException("Invalid max value.");
    }
    this.maxVal = maxValue;
    this.r = checkRange(red);
    this.g = checkRange(green);
    this.b = checkRange(blue);
  }

  /**
   * CopyConstructor for a pixel of an image.
   *
   * @param p the pixel passed in.
   */
  public GeneralPixel(Pixel p) throws IllegalArgumentException {
    this.maxVal = 255;
    this.r = p.getRed();
    this.g = p.getGreen();
    this.b = p.getBlue();
  }

  /**
   * Overrides ToString to return pertinent info for a pixel.
   *
   * @return a string that describes the red, green, and blue color values.
   */
  @Override
  public String toString() {
    return String.format("Red:%d, Green:%d, Blue:%d", r, g, b);
  }

  @Override
  public Map<String, Integer> getRGB() {
    Map<String, Integer> rgbMap = new HashMap<>();
    rgbMap.put("Red", this.r);
    rgbMap.put("Green", this.g);
    rgbMap.put("Blue", this.b);
    return rgbMap;
  }

  @Override
  public int getRGBByte() {
    Color pixelColor = new Color(this.r, this.g, this.b);
    return pixelColor.getRGB();
  }

  @Override
  public void greyscaleColor(String color) throws IllegalArgumentException {
    String c = color.toLowerCase();
    if (!(c.equalsIgnoreCase("red") || c.equalsIgnoreCase("green")
          || c.equalsIgnoreCase("blue"))) {
      throw new IllegalArgumentException("Color specified must be red, green or blue.");
    }
    switch (c) {
      case "red":
        int red = checkRange(this.r);
        this.g = red;
        this.b = red;
        break;
      case "green":
        int green = checkRange(this.g);
        this.r = green;
        this.b = green;
        break;
      case "blue":
        int blue = checkRange(this.b);
        this.r = blue;
        this.g = blue;
        break;
      default: // c can only be red, green, or blue, an exception is thrown otherwise
    }
  }

  @Override
  public void greyscaleValue() {
    int max = checkRange(this.returnVal());
    this.r = max;
    this.g = max;
    this.b = max;
  }

  @Override
  public void greyscaleIntensity() {
    int intensity = checkRange((this.r + this.g + this.b) / 3);
    this.r = intensity;
    this.g = intensity;
    this.b = intensity;
  }

  @Override
  public void greyscaleLuma() {
    int luma = checkRange((int) ((this.r * 0.2126) + (this.g * 0.7152) + (this.b * 0.0722)));
    this.r = luma;
    this.g = luma;
    this.b = luma;
  }

  @Override
  public void adjustBrightness(int constant) {
    this.r = checkRange(this.r + constant);
    this.g = checkRange(this.g + constant);
    this.b = checkRange(this.b + constant);
  }

  @Override
  public void greyScale() {
    int greyScale = checkRange((int) ((this.r * 0.2126) + (this.g * 0.7152) + (this.b * 0.0722)));

    this.r = greyScale;
    this.g = greyScale;
    this.b = greyScale;
  }

  @Override
  public void toSepia() {
    int redValue = checkRange((int) ((this.r * 0.393) + (this.g * 0.769) + (this.b * 0.189)));
    int greenValue = checkRange((int) ((this.r * 0.349) + (this.g * 0.686) + (this.b * 0.168)));
    int blueValue = checkRange((int) ((this.r * 0.272) + (this.g * 0.534) + (this.b * 0.131)));

    this.r = redValue;
    this.g = greenValue;
    this.b = blueValue;
  }

  @Override
  public void gaussianBlur(Pixel[][] kernel) {
    double[] blur = {0.0625, 0.125, 0.0625, 0.125, 0.25, 0.125, 0.0625, 0.125, 0.0625};
    applyFilter(kernel, blur);
  }

  @Override
  public void sharpen(Pixel[][] kernel) {
    double[] sharpen = {-0.125, -0.125, -0.125, -0.125, -0.125, -0.125, 0.25, 0.25, 0.25, -0.125,
      -0.125, 0.25, 1, 0.25, -0.125, -0.125, -0.125, 0.25, 0.25, 0.25, -0.125, -0.125, -0.125,
      -0.125, -0.125};
    applyFilter(kernel, sharpen);
  }

  @Override
  public int getRed() {
    return this.r;
  }

  @Override
  public int getGreen() {
    return this.g;
  }

  @Override
  public int getBlue() {
    return this.b;
  }

  /**
   * Gives the value of a pixel (the maximum value of the three components for each pixel).
   *
   * @return an int matching the highest value of r,g, or b.
   */
  private int returnVal() {
    int max = 0;
    String[] colorList = {"Red", "Green", "Blue"};
    Map<String, Integer> valMap = this.getRGB();
    for (String s : colorList) {
      //iterates through all colors and saves the largest to a local var (max).
      max = Math.max(valMap.get(s), max);
    }
    return max;
  }

  /**
   * Makes sure that the RGB value is not greater than the maxVal or less than zero. If the value is
   * greater than the maxValue, it is set to the maxValue. If the value is less than zero, it is set
   * to zero.
   *
   * @param value the int being checked
   * @return the appropriate int
   */
  private int checkRange(int value) {
    if (value > this.maxVal) {
      return this.maxVal;
    }
    return Math.max(value, 0);
  }

  /**
   * Applies the supplied filter to a pixel.
   *
   * @param kernel the kernel pertaining to the pixel
   * @param filter the desired filter to be applied
   */
  private void applyFilter(Pixel[][] kernel, double[] filter) {
    int redChannel = 0;
    int blueChannel = 0;
    int greenChannel = 0;
    int count = 0;
    for (int kernRow = 0; kernRow < Math.sqrt(filter.length); kernRow++) {
      for (int kernCol = 0; kernCol < Math.sqrt(filter.length); kernCol++) {
        int currentRed = kernel[kernRow][kernCol].getRed();
        int currentGreen = kernel[kernRow][kernCol].getGreen();
        int currentBlue = kernel[kernRow][kernCol].getBlue();
        redChannel += currentRed * filter[count];
        greenChannel += currentGreen * filter[count];
        blueChannel += currentBlue * filter[count];
        count++;
      }
    }
    this.r = checkRange(redChannel);
    this.g = checkRange(greenChannel);
    this.b = checkRange(blueChannel);
  }
}
