# Use Me
### This file documents all usages of our ImageProcessor program.

#### *To preface; Before any image operation can be used an image must be first loaded in with the load command.*
#### *If you want to see your image saved to your computer, you must use the save command.*

#### Operations through the GUI are outlined below this section.

Here is the list of commands our programs support and how to use them:

##### Load - Type "load *filePathToLoadFrom* *nameForImageInProgram*" ex. load res/house.ppm house
##### Save - Type "save *filePathToSaveTo* *nameForImageFromProgram*" ex. save res/newhouse.ppm house
##### GreyScaleRed - Type "red-component *storedImageNameInProgram* *newNameForImageInProgram*" ex. red-component house redhouse
##### GreyScaleGreen - Type "green-component *storedImageNameInProgram* *newNameForImageInProgram*" ex. green-component house greenhouse
##### GreyScaleBlue - Type "blue-component *storedImageNameInProgram* *newNameForImageInProgram*" ex. blue-component house bluehouse
##### GreyScaleIntensity - Type "intensity-component *storedImageNameInProgram* *newNameForImageInProgram*" ex. intensity-component house intensityhouse
##### GreyScaleValue - Type "value-component *storedImageNameInProgram* *newNameForImageInProgram*" ex. value-component house valuehouse
##### GreyScaleLuma - Type "luma-component *storedImageNameInProgram* *newNameForImageInProgram*" ex. luma-component house lumahouse
##### GreyScale - Type "greyscale *storedImageNameInProgram* *newNameForImageInProgram*" ex. greyscale house greyscalehouse
##### Sepia - Type "sepia *storedImageNameInProgram* *newNameForImageInProgram*" ex. sepia house sepiahouse
##### Gaussian Blur - Type "gaussian-blur *storedImageNameInProgram* *newNameForImageInProgram*" ex. gaussian-blur house gaussianhouse
##### Sharpen - Type "sharpen *storedImageNameInProgram* *newNameForImageInProgram*" ex. sharpen house sharpenhouse
##### Adjust Brightness - Type "brighten *numberToAdjustWith* *storedImageNameInProgram* *newNameForImageInProgram*" ex. brighten 20 house brighterhouse
##### Flip Horizontally - Type "horizontal-flip *storedImageNameInProgram* *newNameForImageInProgram*" ex. horizontal-flip house horizontalhouse
##### Flip Vertically - Type "vertical-flip *storedImageNameInProgram* *newNameForImageInProgram*" ex. vertical-flip house horizontalhouse

### An example of running the program would look like:
1. launch with -text command line argument
2. load res/house.ppm house
3. brighten house brighterHouse
4. save res/customhouse.ppm brighterHouse
OR
1. create a script called in a txt file.
2. launch with -script [path to script.txt]
OR
* launch the GUI and manipulate an image after loading it.

## For GUI operations, the effects are all the same, while going about them is slightly altered.
### for starters, you must launch the program with no command line arguments.

* **Load/Open**: hit the open button, navigate to the image you would like in the
image editor then double click it. (NOTE: the image must be a supported filetype or else it will be greyed out.)
* **Save**: hit the save button, navigate to the folder you would like to save to, then name your file
  (NOTE: please add an extension that is supported to the file name (PPM, JPEG,PNG,BMP)).
* **GreyScale**: for any of the greyscale operations, click the greyscale button, then select 
the desired type in the popup window.
* **Sepia**: Simply click the Sepia button.
* **Gaussian Blur**: Simply click the blur button.
* **Sharpen**: Simply click the sharpen button.
* **Adjust Brightness**: Fill the textbox with some positive (brighten) or negative (darken) number
then click the Brighten button.
* **Flip Horizontally**: click the horizontal flip button.
* **Flip Vertically**: click the vertical flip button.





