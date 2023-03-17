package imageprocessing.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * This class contains the data from an image in order to make a respective histogram of that
 * image's values. The graph is painted by extending JPanel and overriding the paintComponent
 *
 */
public class GeneralHistogram extends JPanel {
  private final ArrayList<Integer> redValues;
  private final ArrayList<Integer> greenValues;
  private final ArrayList<Integer> blueValues;
  private final ArrayList<Integer> intensityValues;

  /**
   * Constructs a GeneralHistogram passed in with the data from the supplied image.
   * @param image the image to have a histogram be made of
   */
  public GeneralHistogram(Image image) {
    this.redValues = new ArrayList<>();
    this.greenValues = new ArrayList<>();
    this.blueValues = new ArrayList<>();
    this.intensityValues = new ArrayList<>();
    this.setPreferredSize(new Dimension(255, image.returnHeight() * 3));
    this.setBackground(Color.lightGray);

    Pixel[][] pixels = image.returnPixels();
    for (Pixel[] row : pixels) {
      for (Pixel p : row) {
        int currentRed = p.getRed();
        int currentGreen = p.getGreen();
        int currentBlue = p.getBlue();
        float intensity = (float) ((currentRed + currentGreen + currentBlue) / 3);

        this.redValues.add(currentRed);
        this.greenValues.add(currentGreen);
        this.blueValues.add(currentBlue);
        this.intensityValues.add(Math.round(intensity));
      }
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();

    HashMap<String, ArrayList<Integer>> map = histogramData();
    ArrayList<Integer> red = map.get("Red");
    ArrayList<Integer> green = map.get("Green");
    ArrayList<Integer> blue = map.get("Blue");
    ArrayList<Integer> intensity = map.get("Intensity");

    g2d.rotate(Math.toRadians(180), 125, 125);
    this.repaint();

    g2d.setColor(Color.red);
    for (int r = 0; r < red.size(); r++) {
      g2d.fillRect(r - 7, -60, 1, red.get(r));
    }
    g2d.setColor(Color.green);
    for (int gr = 0; gr < green.size(); gr++) {
      g2d.fillRect(gr - 7, -60, 1, green.get(gr));
    }
    g2d.setColor(Color.blue);
    for (int b = 0; b < blue.size(); b++) {
      g2d.fillRect(b - 7, -60, 1, blue.get(b));
    }
    g2d.setColor(Color.yellow);
    for (int i = 0; i < intensity.size(); i++) {
      g2d.fillRect(i - 7, -60, 1, intensity.get(i));
    }
  }

  /**
   * Sorts the image's RGB values into the correct bins for the histogram.
   *
   * @return a map of the values
   */
  public HashMap<String, ArrayList<Integer>> histogramData() {
    ArrayList<Integer> redBin = new ArrayList<>();
    ArrayList<Integer> greenBin = new ArrayList<>();
    ArrayList<Integer> blueBin = new ArrayList<>();
    ArrayList<Integer> intensityBin = new ArrayList<>();
    HashMap<String, ArrayList<Integer>> data = new HashMap<>();

    for (int valueRange = 0; valueRange < 256; valueRange++) {
      redBin.add(binHelper(redValues, valueRange));
      greenBin.add(binHelper(greenValues, valueRange));
      blueBin.add(binHelper(blueValues, valueRange));
      intensityBin.add(binHelper(intensityValues, valueRange));
    }
    data.put("Red", redBin);
    data.put("Green", greenBin);
    data.put("Blue", blueBin);
    data.put("Intensity", intensityBin);
    return data;
  }

  /**
   * Helper method for histogramData(), checks how many occurrences of a number there are in an
   * array list.
   */
  private int binHelper(ArrayList<Integer> values, int value) {
    int count = 0;

    for (int num : values) {
      if (num == value) {
        count++;
      }
    }
    return count;
  }
}
