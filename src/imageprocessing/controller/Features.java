package imageprocessing.controller;

/**
 * Interface to represent all the features offered by our GUI.
 */
public interface Features {

  /**
   * loads an image into the GUI from the users computer.
   */
  void load(String filePath);

  /**
   * saves an image from the GUI to a location on the users computer.
   */
  void save(String filePath);

  /**
   * greyScales the image in the GUI.
   */
  void greyScale(String type);

  /**
   * flips the image in the GUI horizontally.
   */
  void horizontalFlip();

  /**
   * flips the image in the GUI vertically.
   */
  void verticalFlip();

  /**
   * brightens/ darkens an image in the gui.
   */
  void brighten(int adjustVal);

  /**
   * blurs an image in the GUI.
   */
  void blur();

  /**
   * sharpens an image in the GUI.
   */
  void sharpen();

  /**
   * Sepia an image in the GUI.
   */
  void sepia();
}
