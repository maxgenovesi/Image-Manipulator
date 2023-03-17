package imageprocessing.controller;

import imageprocessing.controller.strategies.GeneralLoad;
import imageprocessing.controller.strategies.GeneralSave;
import imageprocessing.controller.strategies.LoadBehavior;
import imageprocessing.controller.strategies.PPMLoad;
import imageprocessing.controller.strategies.PPMSave;
import imageprocessing.controller.strategies.SaveBehavior;
import imageprocessing.model.Image;
import imageprocessing.view.View;

/**
 * This class implements Features to make an asynchronous controller for a GUI view.
 */
public class GUIImageController implements Features {

  private Image image;
  private View view;
  private SaveBehavior saveBehavior;
  private LoadBehavior loadBehavior;

  /**
   * sets the view.
   * @param v the current view the controller has been provided.
   */
  public void setView(View v) {
    this.view = v;
    //provide view with all the callbacks.
    view.addFeatures(this);
  }

  /**
   * loads the provided image at the filepath to the GUI.
   * @param filePath the path to the image as selected in the JFileChooser.
   */
  @Override
  public void load(String filePath) {
    if (filePath.substring(filePath.lastIndexOf(".") + 1).equals("ppm")) {
      loadBehavior = new PPMLoad();
    } else {
      loadBehavior = new GeneralLoad();
    }
    this.image = loadBehavior.load(filePath);
    this.view.renderImage(this.image);
    this.view.renderHistogram(this.image);
  }


  /**
   * saves the current Image in the GUI to the provided filepath.
   * @param filePath the directory to save in with the filename as provided through JFileChooser.
   */
  @Override
  public void save(String filePath) {
    String extension = filePath.substring(filePath.lastIndexOf(".") + 1)
            .toLowerCase();
    if (extension.equalsIgnoreCase("ppm")) {
      this.saveBehavior = new PPMSave();
    } else {
      this.saveBehavior = new GeneralSave();
    }
    saveBehavior.save(filePath, extension, this.image);
  }

  /**
   * greyScales the image given.
   * @param type the type of greyscale to apply to the iamge, as received by the user through a
   *             button press.
   */
  @Override
  public void greyScale(String type) {
    switch (type.toLowerCase()) {
      case "red":
        this.image.greyScaleColorImage("red");
        this.view.renderImage(this.image);
        this.view.renderHistogram(this.image);
        break;
      case "green":
        this.image.greyScaleColorImage("green");
        this.view.renderImage(this.image);
        this.view.renderHistogram(this.image);
        break;
      case "blue":
        this.image.greyScaleColorImage("blue");
        this.view.renderImage(this.image);
        this.view.renderHistogram(this.image);
        break;
      case "value":
        this.image.greyScaleValueImage();
        this.view.renderImage(this.image);
        this.view.renderHistogram(this.image);
        break;
      case "intensity":
        this.image.greyScaleIntensityImage();
        this.view.renderImage(this.image);
        this.view.renderHistogram(this.image);
        break;
      case "luma":
        this.image.greyScaleLumaImage();
        this.view.renderImage(this.image);
        this.view.renderHistogram(this.image);
        break;
      case "default":
        this.image.greyScaleImage();
        this.view.renderImage(this.image);
        this.view.renderHistogram(this.image);
        break;
      default:
        throw new IllegalArgumentException("No greyscale technique of this specification found.");
    }
  }

  @Override
  public void horizontalFlip() {
    this.image.horizontalFlip();
    this.view.renderImage(this.image);
    this.view.renderHistogram(this.image);
  }

  @Override
  public void verticalFlip() {
    this.image.verticalFlip();
    this.view.renderImage(this.image);
    this.view.renderHistogram(this.image);
  }

  @Override
  public void brighten(int adjustVal) {
    this.image.adjustBrightnessImage(adjustVal);
    this.view.renderImage(this.image);
    this.view.renderHistogram(this.image);
  }

  @Override
  public void blur() {
    this.image.gaussianBlurImage();
    this.view.renderImage(this.image);
    this.view.renderHistogram(this.image);
  }

  @Override
  public void sharpen() {
    this.image.sharpenImage();
    this.view.renderImage(this.image);
    this.view.renderHistogram(this.image);
  }

  @Override
  public void sepia() {
    this.image.toSepiaImage();
    this.view.renderImage(this.image);
    this.view.renderHistogram(this.image);
  }
}


