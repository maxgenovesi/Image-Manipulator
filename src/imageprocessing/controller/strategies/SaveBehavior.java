package imageprocessing.controller.strategies;

import imageprocessing.model.Image;

/**
 * Interface to represent a save function object,
 *  save strategies extend this interface.
 */
public interface SaveBehavior {
  /**
   * Save method to take an image and save it to the specified path on the User's computer.
   *
   * @param fileDestPath the path wherein the image will be saved on the user's computer.
   * @param fileType     the image's file type
   * @param imageToSave  the Image to save.
   * @throws IllegalArgumentException if the file does not exist in the program.
   */
  void save(String fileDestPath, String fileType, Image imageToSave)
          throws IllegalArgumentException;
}
