package imageprocessing.controller.operations;

import java.util.Map;
import java.util.Objects;

import imageprocessing.controller.ImagesCommand;
import imageprocessing.model.GeneralImage;
import imageprocessing.model.Image;
import imageprocessing.model.ImageInputMock;

/**
 * Function object that allows the controller to delegate to the model to greyScaleColor blue.
 */
public class BlueGreyscale implements ImagesCommand {
  private final String origName;
  private final String copyName;

  /**
   * Constructs the function object to greyScale by the color blue, the original name and copy's
   * name are specified.
   *
   * @param orig the original image's name
   * @param copy the copied image's name
   * @throws NullPointerException if orig or copy are null
   */
  public BlueGreyscale(String orig, String copy) throws NullPointerException {
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
      imageToOperate.greyScaleColorImage("blue");
    } else {
      imageStorage.put(this.copyName, new GeneralImage(imageStorage.get(this.origName)));
      Image imageToOperate = imageStorage.get(this.copyName);
      imageToOperate.greyScaleColorImage("blue");
    }
  }
}
