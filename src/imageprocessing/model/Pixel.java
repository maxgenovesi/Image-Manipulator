package imageprocessing.model;


import java.util.Map;

/**
 * Interface to represent a Pixel, and methods that return
 * states as well as manipulate them.
 */
public interface Pixel {
  /**
   * Gets a pixels red green and blue values.
   *
   * @return a hashmap containing colorKeys, and corresponding colorValues.
   */
  Map<String, Integer> getRGB();

  /**
   * Gets a pixels red green and blue values in TYPE_INT_RGB format.
   *
   * @return an int containing rgb values in the channels specified in
   *     TYPE_INT_RGB.
   */
  int getRGBByte();

  /**
   * Greyscale a pixel based on the specified color value.
   *
   * @param color the color with which to base the greyscale operation on.
   * @throws IllegalArgumentException if the color provided is not "red", "green" or "blue"
   *                                  (not case-sensitive).
   */
  void greyscaleColor(String color) throws IllegalArgumentException;

  /**
   * Greyscale a pixel based on the value of the pixel.
   */
  void greyscaleValue();

  /**
   * Greyscale a pixel based on the intensity of the pixel.
   */
  void greyscaleIntensity();

  /**
   * Greyscale a pixel based on the luma of the pixel.
   */
  void greyscaleLuma();

  /**
   * Brighten or darken a pixel.
   *
   * @param constant the amount by which to brighten/darken the pixel.
   */
  void adjustBrightness(int constant);

  /**
   * Transforms a pixel to grey scale using a color transformation.
   */
  void greyScale();

  /**
   * Transforms a pixel to sepia using a color transformation.
   */
  void toSepia();

  /**
   * Blurs a pixel using a kernel and applying a gaussian blur filter.
   */
  void gaussianBlur(Pixel[][] kernel);

  /**
   * Sharpens a pixel using a kernel and applying a sharpening filter.
   */
  void sharpen(Pixel[][] kernel);

  /**
   * Gets the red RGB value of a pixel so that the filter methods can mutate a pixel based on its
   * pixel color value.
   *
   * @return this pixel's red RGB value
   */
  int getRed();

  /**
   * Gets the green RGB value of a pixel so that the filter methods can mutate a pixel based on its
   * pixel color value.
   *
   * @return this pixel's green RGB value
   */
  int getGreen();

  /**
   * Gets the blue RGB value of a pixel so that the filter methods can mutate a pixel based on its
   * pixel color value.
   *
   * @return this pixel's blue RGB value
   */
  int getBlue();
}
