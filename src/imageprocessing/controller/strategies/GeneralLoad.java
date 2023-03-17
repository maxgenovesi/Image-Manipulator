package imageprocessing.controller.strategies;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import imageprocessing.model.GeneralImage;
import imageprocessing.model.GeneralPixel;
import imageprocessing.model.Image;
import imageprocessing.model.Pixel;

/**
 * strategy object for the way that most images are loaded.
 */
public class GeneralLoad implements LoadBehavior {
  @Override
  public Image load(String filePath) throws IllegalStateException {
    Image imageToStore;
    try {
      BufferedImage imageToLoad = ImageIO.read(new File(filePath));
      int width = imageToLoad.getWidth();
      int height = imageToLoad.getHeight();
      Pixel[][] pixels = new Pixel[height][width];
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          int rgb = imageToLoad.getRGB(col, row);
          int r = rgb >> 16 & 0xFF;
          int g = rgb >> 8 & 0xFF;
          int b = rgb & 0xFF;
          int a = rgb >> 24 & 0xFF;
          Pixel thisPix = new GeneralPixel(r, g, b);
          pixels[row][col] = thisPix;
        }
      }
      imageToStore = new GeneralImage(width, height, 255, pixels);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return imageToStore;
  }
}

