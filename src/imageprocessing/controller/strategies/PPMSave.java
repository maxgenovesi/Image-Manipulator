package imageprocessing.controller.strategies;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

import imageprocessing.model.GeneralImage;
import imageprocessing.model.Image;
import imageprocessing.model.Pixel;
import imageprocessing.utils.FileUtils;

/**
 * strategy object for the way that ppm images are saved.
 */
public class PPMSave implements SaveBehavior {

  @Override
  public void save(String fileDestPath, String programName, Image imageToCopy)
          throws IllegalStateException {
    if (imageToCopy == null) {
      throw new IllegalArgumentException("program name provided does not exist");
    }

    Image imageToSave = new GeneralImage(imageToCopy);

    try (BufferedWriter fileToWrite = FileUtils.makeFile(fileDestPath)) {
      fileToWrite.append("P3\n");
      fileToWrite.append(imageToSave.returnWidth() + " ");
      fileToWrite.append(imageToSave.returnHeight() + "\n");
      fileToWrite.append(imageToSave.returnMaxValue() + "\n");
      Pixel[][] pixelSet = imageToSave.returnPixels();
      for (int row = 0; row < imageToSave.returnHeight(); row++) {
        for (int col = 0; col < imageToSave.returnWidth(); col++) {
          Map<String, Integer> pixelData = pixelSet[row][col].getRGB();
          String red = pixelData.get("Red").toString();
          String green = pixelData.get("Green").toString();
          String blue = pixelData.get("Blue").toString();
          fileToWrite.append(String.format("%s\n%s\n%s\n", red, green, blue));
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Path Invalid");
    }
  }
}
