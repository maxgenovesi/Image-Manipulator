package imageprocessing.model;

import java.util.Objects;

/**
 * Represents mock image inputs to help with testing controller.
 */
public class ImageInputMock implements Image {

  private final StringBuilder log;

  /**
   * Constructs a ImageInputMock.
   *
   * @param s the stringBuilder to be used as the log of entries
   */
  public ImageInputMock(StringBuilder s) {
    this.log = s;
  }

  public ImageInputMock(ImageInputMock i) {
    this.log = Objects.requireNonNullElseGet(i.log, StringBuilder::new);
  }

  @Override
  public void horizontalFlip() {
    log.append("Horizontal flip applied.");
  }

  @Override
  public void verticalFlip() {
    log.append("Vertical flip applied.");
  }

  @Override
  public Pixel[][] returnPixels() {
    return new Pixel[0][];
  }

  @Override
  public int returnWidth() {
    return 1;
  }

  @Override
  public int returnHeight() {
    return 1;
  }

  @Override
  public int returnMaxValue() {
    return 1;
  }

  @Override
  public void greyScaleColorImage(String color) {
    log.append("Greyscale color applied with color ").append(color).append(".");
  }

  @Override
  public void greyScaleIntensityImage() {
    log.append("Greyscale intensity applied.");
  }

  @Override
  public void greyScaleLumaImage() {
    log.append("Greyscale luma applied.");
  }

  @Override
  public void greyScaleValueImage() {
    log.append("Greyscale value applied.");
  }

  @Override
  public void adjustBrightnessImage(int constant) {
    log.append("Brighten applied with constant ").append(constant).append(".");
  }

  @Override
  public void greyScaleImage() {
    log.append("Greyscale filter applied.");
  }

  @Override
  public void toSepiaImage() {
    log.append("Sepia filter applied.");
  }

  @Override
  public void gaussianBlurImage() {
    log.append("Gaussian blur transformation applied.");
  }

  @Override
  public void sharpenImage() {
    log.append("Sharpen transformation applied.");
  }

  @Override
  public java.awt.Image toJavaImage() {
    return null;
  }
}
