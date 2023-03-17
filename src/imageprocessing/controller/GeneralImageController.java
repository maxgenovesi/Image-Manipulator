package imageprocessing.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

import imageprocessing.controller.operations.BlueGreyscale;
import imageprocessing.controller.operations.Brighten;
import imageprocessing.controller.operations.GaussianBlur;
import imageprocessing.controller.operations.GreenGreyscale;
import imageprocessing.controller.operations.Greyscale;
import imageprocessing.controller.operations.HorizontalFlip;
import imageprocessing.controller.operations.IntensityGreyscale;
import imageprocessing.controller.operations.Load;
import imageprocessing.controller.operations.LumaGreyscale;
import imageprocessing.controller.operations.RedGreyscale;
import imageprocessing.controller.operations.Save;
import imageprocessing.controller.operations.Sepia;
import imageprocessing.controller.operations.Sharpen;
import imageprocessing.controller.operations.ValueGreyscale;
import imageprocessing.controller.operations.VerticalFlip;
import imageprocessing.model.Image;

/**
 * Represents a controller for an Image, this object reads the commands from the user and tells
 * the model what operations to perform.
 */
public class  GeneralImageController implements ImageController {
  private final Map<String, Image> imageStore;
  private final Readable input;

  /**
   * Constructs a controller for a PPMImage.
   *
   * @param input represents the inputs from the user
   * @throws IllegalArgumentException if the model or the input are null
   */
  public GeneralImageController(Readable input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("The input is null!");
    }
    this.input = input;
    this.imageStore = new HashMap<>();
  }

  /**
   * Constructs a controller for testing that takes in a predefined Image map.
   *
   * @param input represents the inputs from the user
   * @param map   is a predefined map to test.
   * @throws IllegalArgumentException if the map or the input are null
   */
  public GeneralImageController(Readable input, HashMap<String, Image> map)
          throws IllegalArgumentException {
    if (input == null || map == null) {
      throw new IllegalArgumentException("The input or map is null!");
    }
    this.input = input;
    this.imageStore = map;
  }

  @Override
  public void runGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.input);
    Stack<ImagesCommand> commands = new Stack<>();

    Map<String, Function<Scanner, ImagesCommand>> ppmCommands = new HashMap<>();
    ppmCommands.put("load", (Scanner s) -> new Load(s.next(), s.next()));
    ppmCommands.put("save", (Scanner s) -> new Save(s.next(), s.next()));
    ppmCommands.put("red-component", (Scanner s) -> new RedGreyscale(s.next(), s.next()));
    ppmCommands.put("blue-component", (Scanner s) -> new BlueGreyscale(s.next(), s.next()));
    ppmCommands.put("green-component", (Scanner s) -> new GreenGreyscale(s.next(), s.next()));
    ppmCommands.put("value-component", (Scanner s) -> new ValueGreyscale(s.next(), s.next()));
    ppmCommands.put("intensity-component", (Scanner s) ->
            new IntensityGreyscale(s.next(), s.next()));
    ppmCommands.put("luma-component", (Scanner s) -> new LumaGreyscale(s.next(), s.next()));
    ppmCommands.put("greyscale", (Scanner s) -> new Greyscale(s.next(), s.next()));
    ppmCommands.put("sepia", (Scanner s) -> new Sepia(s.next(), s.next()));
    ppmCommands.put("gaussian-blur", (Scanner s) -> new GaussianBlur(s.next(), s.next()));
    ppmCommands.put("sharpen", (Scanner s) -> new Sharpen(s.next(), s.next()));
    ppmCommands.put("brighten", (Scanner s) -> new Brighten(s.nextInt(), s.next(), s.next()));
    ppmCommands.put("horizontal-flip", (Scanner s) -> new HorizontalFlip(s.next(), s.next()));
    ppmCommands.put("vertical-flip", (Scanner s) -> new VerticalFlip(s.next(), s.next()));

    while (scan.hasNext()) {
      ImagesCommand command;
      String in = scan.next();
      Function<Scanner, ImagesCommand> cmd = ppmCommands.getOrDefault(in, null);
      if (cmd == null) {
        System.out.println("something went wrong while parsing your command, ensure " +
                           "you are typing the command correctly and providing the correct " +
                           "parameters.");
        // throw new IllegalArgumentException("Command is null.");
      } else {
        command = cmd.apply(scan);
        commands.add(command);
        command.run(this.imageStore);
      }
    }
  }

  @Override
  public HashMap<String, Image> returnStorage() {
    return new HashMap<>(imageStore);
  }
}
