package imageprocessing.controller.operations;

import java.util.Map;
import java.util.Objects;

import imageprocessing.controller.ImagesCommand;
import imageprocessing.controller.strategies.GeneralSave;
import imageprocessing.controller.strategies.PPMSave;
import imageprocessing.controller.strategies.SaveBehavior;
import imageprocessing.model.Image;

/**
 * Function object that allows the controller to delegate to the model to save an image into the
 * system.
 */
public class Save implements ImagesCommand {
  private final String filePath;
  private final String programName;
  private final SaveBehavior saveBehavior;

  /**
   * Constructs the function object and gives the model the image's file path.
   *
   * @param path the path wherein the image's file is located on the user's computer
   * @param name the image's name upon which it will be referenced
   */
  public Save(String path, String name) throws NullPointerException {
    this.filePath = Objects.requireNonNull(path);
    this.programName = Objects.requireNonNull(name);
    if (filePath.substring(filePath.lastIndexOf(".") + 1)
            .equalsIgnoreCase("ppm")) {
      this.saveBehavior = new PPMSave();
    } else {
      this.saveBehavior = new GeneralSave();
    }
  }

  @Override
  public void run(Map<String, Image> imageStorage) {
    String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
    saveBehavior.save(filePath, fileExtension, imageStorage.get(programName));
  }

}
