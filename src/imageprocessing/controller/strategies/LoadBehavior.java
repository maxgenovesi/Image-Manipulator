package imageprocessing.controller.strategies;

import imageprocessing.model.Image;

/**
 * Interface to represent a load function object,
 * load strategies extend this interface.
 */
public interface LoadBehavior {
  /**
   * the general load method that all load strategies must implement.
   *
   * @param filePath the path wherein the image is coming from on the users computer.
   * @throws IllegalStateException if any of the parsing of a file goes wrong
   */
  Image load(String filePath) throws IllegalStateException;
}
