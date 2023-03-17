package imageprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import imageprocessing.controller.GUIImageController;
import imageprocessing.controller.GeneralImageController;
import imageprocessing.controller.ImageController;
import imageprocessing.view.JFrameView;
import imageprocessing.view.View;

/**
 * Creates the objects and allows the user to perform operations on an image.
 */
public class ImageProcessor {
  /**
   * Main method that reads user inputs to determine what action to perform.
   *
   * @param args represents the arguments passed in by the user
   */
  public static void main(String[] args) {
    ImageController controller;
    GUIImageController guiController;
    if (args.length == 0) {
      guiController = new GUIImageController();
      View view = new JFrameView("Image Processor");
      guiController.setView(view);
    }
    if (args.length > 0 && args[0].equalsIgnoreCase("-file")) {
      File script = new File(args[1]);
      try {
        controller = new GeneralImageController(new FileReader(script));
        controller.runGame();
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    if (args.length > 0 && args[0].equalsIgnoreCase("-text")) {
      controller = new GeneralImageController(new InputStreamReader(System.in));
      controller.runGame();
    }
  }
}