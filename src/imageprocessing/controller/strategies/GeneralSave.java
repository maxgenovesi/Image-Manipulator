package imageprocessing.controller.strategies;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import imageprocessing.model.Image;
import imageprocessing.model.Pixel;

/**
 * strategy object for the way that most images are saved.
 */
public class GeneralSave implements SaveBehavior {

  @Override
  public void save(String fileDestPath, String fileType, Image imageToSave)
          throws IllegalStateException {
    BufferedImage saveableImage =
            new BufferedImage(imageToSave.returnWidth(), imageToSave.returnHeight(),
                    BufferedImage.TYPE_INT_RGB);
    Pixel[][] imagePixels = imageToSave.returnPixels();
    for (int row = 0; row < imageToSave.returnHeight(); row++) {
      for (int col = 0; col < imageToSave.returnWidth(); col++) {
        int pixelColorBytes = imagePixels[row][col].getRGBByte();
        saveableImage.setRGB(col, row, pixelColorBytes);
      }
    }
    try {
      ImageIO.write(saveableImage, fileType, new File(fileDestPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
