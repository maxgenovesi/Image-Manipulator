package imageprocessing.controller;

import java.util.Map;

import imageprocessing.model.Image;

/**
 * Interface to represent the CommandController for a set of images.
 */
public interface ImagesCommand {
  /**
   * Runs the controller and allows a user to pass in commands. Creates a copy of the image to be
   * worked on, storing the original in imageStorage.
   *
   * @param imageStorage The storage containing all images created and manipulated (as a hashmap).
   */
  void run(Map<String, Image> imageStorage);
}
