package imageprocessing.controller.operations;

import java.util.Map;

import imageprocessing.controller.ImagesCommand;
import imageprocessing.controller.strategies.GeneralLoad;
import imageprocessing.controller.strategies.LoadBehavior;
import imageprocessing.controller.strategies.PPMLoad;
import imageprocessing.model.Image;

/**
 * Function object that allows the controller to delegate to the model to load an image into the
 * system.
 */
public class Load implements ImagesCommand {
  private final String filePath;
  private final String programName;
  private final LoadBehavior loadBehavior;

  /**
   * Constructs the function object and gives the model the image's file path.
   *
   * @param path the path wherein the image's file is located on the user's computer
   * @param name the image's name upon which it will be referenced
   */
  public Load(String path, String name) {
    this.filePath = path;
    this.programName = name;
    if (filePath.substring(filePath.lastIndexOf(".") + 1).equals("ppm")) {
      loadBehavior = new PPMLoad();
    } else {
      loadBehavior = new GeneralLoad();
    }
  }

  @Override
  public void run(Map<String, Image> imageStorage) {
    Image imageToLoad = loadBehavior.load(filePath);
    imageStorage.put(programName, imageToLoad);
  }

}

