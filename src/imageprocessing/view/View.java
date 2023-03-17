package imageprocessing.view;

import imageprocessing.controller.Features;
import imageprocessing.model.Image;

/**
 * Interface to represent the View of an image.
 */
public interface View {
  /**
   * renders an image to the view.
   *
   * @param i the image to render onto the view.
   */
  void renderImage(Image i);

  /**
   * renders a histogram to the view.
   *
   * @param i the image to render onto the view.
   */
  void renderHistogram(Image i);

  /**
   * gets the filepath provided by a user and returns it as a string.
   *
   * @return the filepath a user would like to load from.
   */
  String getLoadFilePath();

  /**
   * gets the filepath provided by a user and returns it as a string.
   *
   * @return the filepath a user would like to save from.
   */
  String getSaveFilePath();

  /**
   * gets the brighten value provided by a user and returns it as an int.
   *
   * @return the value by which a user wants to brighten or darken the image.
   */
  int getBrightenValue();

  /**
   * gets the greyscale provided by a user and returns it as an string.
   *
   * @return the type of greyscale operation a user wants for the image.
   */
  String getGreyScaleType();

  /**
   * adds all the features as defined by the controller to the view.
   */
  void addFeatures(Features features);

}
