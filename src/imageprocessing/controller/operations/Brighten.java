package imageprocessing.controller.operations;

import java.util.Map;
import java.util.Objects;

import imageprocessing.controller.ImagesCommand;
import imageprocessing.model.GeneralImage;
import imageprocessing.model.Image;
import imageprocessing.model.ImageInputMock;

/**
 * Function object that allows the controller to delegate to the model to adjust an image's
 * brightness. If the number is positive the image will brighten. If it is negative it will darken.
 */
public class Brighten implements ImagesCommand {
  private final Integer adjustor;
  private final String origName;
  private final String copyName;

  /**
   * Constructs the function object and gives the model the brightness constant, original name, and
   * copy name.
   *
   * @param adj  the number to adjust the brightness by
   * @param orig the original image's name
   * @param copy the copied image's name
   * @throws NullPointerException if orig or copy are null
   */
  public Brighten(Integer adj, String orig, String copy) throws NullPointerException {
    this.adjustor = Objects.requireNonNull(adj);
    this.origName = Objects.requireNonNull(orig);
    this.copyName = Objects.requireNonNull(copy);
  }

  @Override
  public void run(Map<String, Image> imageStorage) {
    if (imageStorage.get(this.origName).getClass() == ImageInputMock.class) {
      // required for mock testing.
      imageStorage.put(this.copyName,
              new ImageInputMock((ImageInputMock) imageStorage.get(this.origName)));
      Image imageToOperate = imageStorage.get(this.copyName);
      imageToOperate.adjustBrightnessImage(this.adjustor);
    } else {
      imageStorage.put(this.copyName, new GeneralImage(imageStorage.get(this.origName)));
      Image imageToOperate = imageStorage.get(this.copyName);
      imageToOperate.adjustBrightnessImage(this.adjustor);
    }
  }
}
