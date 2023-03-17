# Image Manipulator
### a simple Image manipulator for PPM files.

Hi and welcome to our image manipulator, this readme will provide the necessary outline
needed to understand the Interfaces,classes, and methods contained in this project.
This manipulator has full support for PPMs, JPEGs, BMPs, and PNGs.
A user can flip,greyscale, sepia, brighten, gaussian blur, or sharpen
any of these image types. Finally, a User can do all this through 
* providing a script as a startup argument.
* providing text inputs through the console or terminal.
* using our GUI.
  * (details on how are in the USEME.md)

Additionally, the end of this readme contains an example script for the commandline, a changelog, and citations
for our image and symbols used for the GUI buttons.

This manipulator uses the command design pattern to allow a user
the ability to manipulate images via the command line, these parts
are contained within the controller package, which holds a package of
operations offered by the model, the controller scans the command line
for inputs and uses a hashmap to recognize what command is being asked
and a function-object to execute that command in the model. All commands
have a common run() method stemming from the Imagescommand Interface
that allows this.

The GUIImageController works very similarly, however it utilizes a features
interface and callbacks to get input from the GUI. This way
our controller and view are quite decoupled and it makes for easier
addition to the project

The Controller additionally contains a Hashmap:
- this hashmap stores all images loaded or created in a session.
  - it is of the form K: String V: Image
- it uses the arguments from the command line to find images to operate
- additionally uses args to know what name to save an image as
or where to save an image
- IMPORTANT: The controller makes a copy of the image within the program under 
the new given name by the user whenever an operation is called.

NOTE: as the GUI version of our image manipulator does not
support multiple images at a time,therefore it does not contain a hashmap,
just a single Image field.

The model contains 2 interfaces:
- The Image interface
  - this is the second interface used when manipulating images
    and thus has more specific methods
  - it takes in images and manipulates them by greyScaling,flipping,
  brightening, sepia, gaussian blur, and sharpening.
- The Pixel interface
  - This is our lowest level interface.
  - Its methods operate solely on a singular pixel at a time.

These two interfaces are implemented by 2 classes:
- PPMImage implements the Image interface
  - it holds its width, height, and maxvalue in int fields.
  - it holds all its pixels within a list of list of pixels.
- PPMPixel implements the Pixel interface.
  - It holds its rgb values in three seperate int fields 
  called r, g, and b.

Finally, The View renders a histogram:
* the histogram is created in the model
  * with 4 arraylists for red,green,blue, and intensity.
* In the view, red is represented by the color red, this follows for the other two,
while intensity is represented with yellow.
## To close, we provide an example script that a user may input at the command line
1. Run the program
2. Type This Script:
load res/house.ppm house
   (This loads our house file into the program with the name house)
3. Hit enter
4. Type This Script:
vertical-flip house verticalhouse
   (This makes a new image called verticalhouse flips it vertically)
5. Hit enter
6. Type this Script:
save res/houseflippedvertically.ppm verticalhouse
   (This saves our vertically flipped house to a new image called
houseflippedvertically.ppm)
7. Terminate the program to actually see the file in the path.

You may also paste all scripts and then hit enter.

# Changes
#### Assignment 5
* Refactored PPMImage and PPMPixel to be
  GeneralImage, and GeneralPixel.
  * This was because our PPMImage and PPMPixel methods will work
  with any image type after we have parsed it and stored it into our program,
  including but not limited to, PPMs.
  * everything works the same, just a name that matches the idea
  that all images are stored in the program effectively the same,
  so all image methods work on all filetypes.
  * We added our new filters to the GeneralImage and GeneralPixel classes.
* Removed ImageStorage and moved its operations to the controller.
  * load and save now work with ppms,jpgs,pngs,and bmps.
  * the hashmap is stored in the controller and modified in the operations
  function objects.
* made it so the controller's operations and command pattern
are mostly handled in a class called GeneralImageController rather than main.
  * This made testing far easier.
* fully tested our controller-model interactions using a mock
  * this required a bit of refactoring in each operation function object.
  * We had to make an if else to check if the object we are working with is a mock or not.
* abstracted all for each loops into a general forEachPixel method.
#### Assignment 6
* Pulled our load and save methods into a strategies class that
allows both the GeneralImageController and GUIImageController
to load and save images.
  * this also involved abstracting the parameters a bit, to not require a hashmap.
  which in retrospect should have been what it was originally.
* Added a method in Image that converts it to a bufferedImage for GUI rendering
* Added a histogram class and interface to represent histograms,
how they are stored, and how they are rendered.
  * tested this class' storage of RGB values and their quantities.
* added support for three seperate ways of launching our imageProcessor
  * with a script
  * with user commands provided in the console/terminal
  * with users able to launch our GUI.
* Last but by no means least, added a GUI using Javas swing library
and added support for all previously supported image operations and images.


# Citations
Our house image is from:
http://www.cs.cornell.edu/courses/cs664/2003fa/images/
At the first link to download called Test image pairs for part 1.
it was originally called house_1.ppm.


