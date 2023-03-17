package imageprocessing.model;

/**
 * Interface to represent an image, and operations to change or get data.
 */
public interface Image {

  /**
   * Flips an image horizontally.
   */
  void horizontalFlip();

  /**
   * Flips an image vertically.
   */
  void verticalFlip();

  /**
   * Displays the contained pixels in an image.
   *
   * @return the contents of an image's pixels as a list of list
   */
  Pixel[][] returnPixels();

  /**
   * Allows the image's width to be used. Specifically used when reconstructing an image in load
   * save.
   *
   * @return the image's width
   */
  int returnWidth();

  /**
   * Allows the image's height to be used. Specifically used when reconstructing an image in load
   * save.
   *
   * @return the image's height
   */
  int returnHeight();

  /**
   * Allows the image's maxValue to be used. Specifically used when reconstructing an image in save.
   *
   * @return the image's max value
   */
  int returnMaxValue();

  /**
   * Greyscale each pixel in the image based on the supplied color.
   *
   * @param color the color with which to base the greyscale operation on.
   */
  void greyScaleColorImage(String color);

  /**
   * Greyscale each pixel in the image based on the intensity of the pixel.
   */
  void greyScaleIntensityImage();

  /**
   * Greyscale each pixel in the image based on the luma of the pixel.
   */
  void greyScaleLumaImage();

  /**
   * Greyscale each pixel in the image based on the value of the pixel.
   */
  void greyScaleValueImage();

  /**
   * Brighten or darken each pixel in the image.
   *
   * @param constant the amount by which to brighten/darken the pixel.
   */
  void adjustBrightnessImage(int constant);

  /**
   * Transforms an image to grey scale using a color transformation.
   */
  void greyScaleImage();

  /**
   * Transforms an image to sepia using a color transformation.
   */
  void toSepiaImage();

  /**
   * Blurs an image using a gaussian blur filter.
   */
  void gaussianBlurImage();

  /**
   * Sharpens an image using a sharpening filter.
   */
  void sharpenImage();

  /**
   * makes an Image from our proprietary format to Java's.
   * Mainly for the purposes of rendering to the GUI.
   *
   * @return an Image in the awt.Image format.
   */
  java.awt.Image toJavaImage();
}
