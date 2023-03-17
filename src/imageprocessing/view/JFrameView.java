package imageprocessing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import imageprocessing.controller.Features;
import imageprocessing.model.GeneralHistogram;
import imageprocessing.model.Image;

/**
 * class that extends JFrame to create our GUI, and get inputs.
 */
public class JFrameView extends JFrame implements View {
  private final JPanel mainPanel;
  private final JPanel imagePanel;
  private final JPanel histPanel;
  private final JLabel display;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton greyscaleButton;
  private final JButton horizFlipButton;
  private final JButton vertFlipButton;
  private final JButton brightenButton;
  private final JTextField brightenNum;
  private final JButton sharpenButton;
  private final JButton blurButton;
  private final JButton sepiaButton;

  /**
   * Our constructor for the GUI.
   * @param caption the name of the window.
   */
  public JFrameView(String caption) {
    super(caption);

    setSize(new Dimension(400, 500));
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    JPanel brightenPanel = new JPanel();
    brightenPanel.setLayout(new FlowLayout());

    //loadButton
    loadButton = new JButton("Open");
    loadButton.setActionCommand("Open File");
    buttonPanel.add(loadButton);

    //saveButton
    saveButton = new JButton("Save");
    saveButton.setActionCommand("Save File");
    buttonPanel.add(saveButton);

    //greyscaleButton
    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("GreyScale Image");
    buttonPanel.add(greyscaleButton);


    //sepiaButton
    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia Image");
    buttonPanel.add(sepiaButton);

    //horizFlipButton
    horizFlipButton = new JButton("Horizontal Flip");
    horizFlipButton.setActionCommand("Horizontally flip image");
    buttonPanel.add(horizFlipButton);

    //vertFlipButton
    vertFlipButton = new JButton("Vertical Flip");
    vertFlipButton.setActionCommand("Vertically flip image");
    buttonPanel.add(vertFlipButton);

    //brightenButton
    brightenButton = new JButton("Brighten:");
    brightenButton.setActionCommand("Brighten Image");
    brightenPanel.add(brightenButton);

    //brightenButtonNum
    brightenNum = new JTextField();
    brightenNum.setBorder(BorderFactory.createEmptyBorder());
    brightenNum.setPreferredSize(new Dimension(30, 15));
    brightenPanel.add(brightenNum);
    buttonPanel.add(brightenPanel);


    //sharpenButton
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen Image");
    buttonPanel.add(sharpenButton);

    //blurButton
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur Image");
    buttonPanel.add(blurButton);

    this.mainPanel.add(buttonPanel);

    // image and histogram below the buttons.
    imagePanel = new JPanel();
    imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.X_AXIS));

    display = new JLabel();
    this.imagePanel.add(display);

    JScrollPane imageScrollPane = new JScrollPane(display);
    imageScrollPane.setPreferredSize(new Dimension(700, 600));
    imagePanel.add(imageScrollPane);

    histPanel = new JPanel();
    histPanel.setLayout(new FlowLayout());

    JLabel histogram = new JLabel();
    histogram.setBorder(BorderFactory.createBevelBorder(1));

    this.histPanel.add(histogram);
    this.histPanel.doLayout();


    this.mainPanel.add(imagePanel);
    this.mainPanel.add(histPanel);

    JLabel histogramKey = new JLabel();
    histogramKey.setText("Histogram Color Key: Red = Red RGB Volume, Green = Green RGB Volume," +
            " Blue = Blue RGB Volume, Yellow = RGB Intensity");

    this.mainPanel.add(histogramKey);
    this.add(mainPanel);

    pack();
    setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    loadButton.addActionListener(evt -> features.load(this.getLoadFilePath()));
    saveButton.addActionListener(evt -> features.save(this.getSaveFilePath()));
    greyscaleButton.addActionListener(evt -> features.greyScale(this.getGreyScaleType()));
    sepiaButton.addActionListener(evt -> features.sepia());
    horizFlipButton.addActionListener(evt -> features.horizontalFlip());
    vertFlipButton.addActionListener(evt -> features.verticalFlip());
    brightenButton.addActionListener(evt -> features.brighten(this.getBrightenValue()));
    sharpenButton.addActionListener(evt -> features.sharpen());
    blurButton.addActionListener(evt -> features.blur());
  }


  @Override
  public void renderImage(Image i) {
    java.awt.Image imageToRender = i.toJavaImage();
    display.setIcon(new ImageIcon(imageToRender));
    this.mainPanel.add(imagePanel);
  }

  @Override
  public void renderHistogram(Image i) {
    this.histPanel.removeAll();
    GeneralHistogram histogramToRender = new GeneralHistogram(i);
    this.histPanel.add(histogramToRender);
    this.mainPanel.add(histPanel);
    pack();
  }

  @Override
  public String getLoadFilePath() {
    String path = "";
    final JFileChooser fChooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG,PNG,BMP, and PPM Images", "jpg", "ppm", "bmp", "png");
    fChooser.setFileFilter(filter);
    int retValue = fChooser.showOpenDialog(this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      path = path + f.getAbsolutePath();
    }
    return path;
  }

  @Override
  public String getSaveFilePath() {
    String path = "";
    final JFileChooser fChooser = new JFileChooser(".");
    int retValue = fChooser.showSaveDialog(this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fChooser.getSelectedFile();
      path = path + f.getAbsolutePath();
    }
    return path;
  }

  @Override
  public int getBrightenValue() {
    if (!Objects.equals(this.brightenNum.getText(), "")) {
      return Integer.parseInt(this.brightenNum.getText());
    } else {
      JOptionPane.showMessageDialog(null, "You must fill the textbox" +
                                          " with some number to adjust the brightness by " +
                                          "(positive or negative).");
      throw new IllegalStateException("You must fill the textbox\" +\n" +
                                      "   with some number to adjust the brightness by \" +\n" +
                                      "              (positive or negative).");
    }
  }

  @Override
  public String getGreyScaleType() {
    String[] options = {"Blue", "Green", "Red", "Value", "Intensity", "Luma", "Default"};
    int retValue = JOptionPane.showOptionDialog(this,
            "Please choose a greyscale type", "Greyscale", JOptionPane.YES_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, options, options[6]);
    return options[retValue];
  }

}
