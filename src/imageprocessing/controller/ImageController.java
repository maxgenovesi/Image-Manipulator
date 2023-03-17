package imageprocessing.controller;

import java.util.HashMap;

import imageprocessing.model.Image;

/**
 * Represents a controller for image processing, its method allows the game to be run.
 */
public interface ImageController {
  /**
   * Allows the user to run the program.
   *
   * @throws IllegalStateException if the readable or appendable cannot be successfully transmitted
   */
  void runGame() throws IllegalStateException;

  /**
   * Return method for testing using the image storage and the images kept inside.
   *
   * @return a copy of its hashmap.
   */
  HashMap<String, Image> returnStorage();
}
