
import java.awt.Color;

/**
 * A library of image processing functions.
 */
public class Runigram {

    public static void main(String[] args) {

        //// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
        // print(tinypic);

        // Creates an image which will be the result of various 
        // image processing operations:
        Color[][] image;

        // Tests the horizontal flipping of an image:
        image = flippedHorizontally(tinypic);
        System.out.println("flipped hotizontally:");
        print(image);

        // System.out.println("tinypic again:");
        // print(tinypic);

        // System.out.println("flipped vertically:");
        // image = flippedVertically(tinypic);
        // System.out.println();
        // print(image);

        // System.out.println("tinypic again:");
        // print(tinypic);

        // System.out.println("gray tinypic:");
        // image = grayScaled(tinypic);
        // System.out.println();
        // print(image);

        Color[][] pic1 = {
            {new Color(0, 0, 1), new Color(0, 1, 2), new Color(0, 2, 3)},
            {new Color(1, 0, 4), new Color(1, 1, 5), new Color(1, 2, 6)},
            {new Color(2, 0, 7), new Color(2, 1, 8), new Color(2, 2, 9)},
            {new Color(3, 0, 10), new Color(3, 1, 11), new Color(3, 2, 12)}
        };

        Color[][] pic2 = {
            {new Color(0, 0, 0), new Color(1, 1, 1), new Color(2, 2, 2)},
            {new Color(3, 3, 3), new Color(4, 4, 4), new Color(5, 5, 5)},
            {new Color(6, 6, 6), new Color(7, 7, 7), new Color(8, 8, 8)},
            {new Color(9, 9, 9), new Color(10, 10, 10), new Color(11, 11, 11)}
        };

        Color[][] pic3 = {
            {new Color(10, 10, 10), new Color(11, 11, 11), new Color(12, 12, 12)},
            {new Color(13, 13, 0), new Color(14, 14, 0), new Color(15, 15, 0)},
            {new Color(16, 16, 0), new Color(17, 17, 0), new Color(18, 18, 0)},
            {new Color(19, 19, 0), new Color(20, 20, 0), new Color(21, 21, 0)}
        };

        // image = scaled(tinypic, 3, 5);
        // System.out.println();
        // print(image);

        // System.out.println();
        // System.out.println("pic2:");
        // print(pic2);

        // System.out.println("pic3:");
        // print(pic3);
        // System.out.println();

        // System.out.println("blend pic2, pic3, alpha=0.5: ");
        // image = blend(pic2, pic3, 0.5);
        // System.out.println();
        // print(image);

    }

    /**
     * Returns a 2D array of Color values, representing the image data stored in
     * the given PPM file.
     */
    public static Color[][] read(String fileName) {
        In in = new In(fileName);
        // Reads the file header, ignoring the first and the third lines.
        in.readString();
        int numCols = in.readInt();
        int numRows = in.readInt();
        in.readInt();
        // Creates the image array
        Color[][] image = new Color[numRows][numCols];
        // Reads the RGB values from the file into the image array. 
        // For each pixel (i,j), reads 3 values from the file,
        // creates from the 3 colors a new Color object, and 
        // makes pixel (i,j) refer to that object.
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                int r = in.readInt();
                int g = in.readInt();
                int b = in.readInt();
                image[i][j] = new Color(r, g, b);
            }
        }
        return image;
    }

    // Prints the RGB values of a given color.
    private static void print(Color c) {
        System.out.print("(");
        System.out.printf("%3s,", c.getRed());   // Prints the red component
        System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s", c.getBlue());  // Prints the blue component
        System.out.print(")  ");
    }

    // Prints the pixels of the given image.
    // Each pixel is printed as a triplet of (r,g,b) values.
    // This function is used for debugging purposes.
    // For example, to check that some image processing function works correctly,
    // we can apply the function and then use this function to print the resulting image.
    private static void print(Color[][] image) {
        //// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                print(image[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Returns an image which is the horizontally flipped version of the given
     * image.
     */
    public static Color[][] flippedHorizontally(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        Color[][] flippedImage = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color pixel = image[i][j];
                flippedImage[i][j] = pixel;
            }
        }
        int middleRow = (int)((double)width/2.0);
        int counter=0;
        for (int i = 0; i < height; i++) {
            counter=i;
            for (int j = 0; j < middleRow; j++) {
                Color c = flippedImage[i][j];
                flippedImage[i][j] = flippedImage[i][width - 1 - j];
                flippedImage[i][width - 1 - j] = c;
            }
        }
        return flippedImage;
    }

    /**
     * Returns an image which is the vertically flipped version of the given
     * image.
     */
    public static Color[][] flippedVertically(Color[][] image) {
        Color[][] flippedImage = new Color[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                Color pixel = image[i][j];
                flippedImage[i][j] = pixel;
            }
        }
        for (int i = 0; i < flippedImage[0].length; i++) {
            for (int j = 0; j < flippedImage.length / 2; j++) {
                Color c = flippedImage[j][i];
                flippedImage[j][i] = flippedImage[flippedImage.length - j - 1][i];
                flippedImage[flippedImage.length - j - 1][i] = c;
            }
        }
        return flippedImage;
    }

    // Computes the luminance of the RGB values of the given pixel, using the formula 
    // lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
    // the three values r = lum, g = lum, b = lum.
    private static Color luminance(Color pixel) {
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int lum = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        // System.out.println("0.299 *" + r + " + 0.587 * " + g + " + 0.114 * " + b + ": " + lum);
        Color luminacePixel = new Color(lum, lum, lum);
        return luminacePixel;
    }

    /**
     * Returns an image which is the grayscaled version of the given image.
     */
    public static Color[][] grayScaled(Color[][] image) {
        Color[][] grayImage = new Color[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                Color luminacePixel = luminance(image[i][j]);
                grayImage[i][j] = luminacePixel;
            }
        }
        return grayImage;
    }

    /**
     * Returns an image which is the scaled version of the given image. The
     * image is scaled (resized) to have the given width and height.
     */
    public static Color[][] scaled(Color[][] image, int width, int height) {
        Color[][] scaledImage = new Color[height][width];
        double widthScale = (double) image[0].length / (double) width;
        double heightScale = (double) image.length / (double) height;

        for (int i = 0; i < height; i++) {
            int row = (int) (i * heightScale);
            for (int j = 0; j < width; j++) {
                int col = (int) (j * widthScale);
                // System.out.println("newImage[" + i + "][" + j + "] is now image[" + row + "][" + col + "]=");
                // print(image[row][col]);
                // System.out.println();
                scaledImage[i][j] = image[row][col];
            }
        }
        // print(scaledImage);
        return scaledImage;
    }

    /**
     * Computes and returns a blended color which is a linear combination of the
     * two given colors. Each r, g, b, value v in the returned color is
     * calculated using the formula v = alpha * v1 + (1 - alpha) * v2, where v1
     * and v2 are the corresponding r, g, b values in the two input color.
     */
    public static Color blend(Color c1, Color c2, double alpha) {
        int r1 = c1.getRed();
        int r2 = c2.getRed();
        int g1 = c1.getGreen();
        int g2 = c2.getGreen();
        int b1 = c1.getBlue();
        int b2 = c2.getBlue();

        int red = (int) ((alpha * r1) + ((1-alpha) * r2));
        int green = (int) ((alpha * g1) + ((1-alpha) * g2));
        int blue = (int) ((alpha * b1) + ((1-alpha) * b2));

        Color blendedColor = new Color(red, green, blue);
        return blendedColor;
    }

    /**
     * Cosntructs and returns an image which is the blending of the two given
     * images. The blended image is the linear combination of (alpha) part of
     * the first image and (1 - alpha) part the second image. The two images
     * must have the same dimensions.
     */
    public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
        int height = image1.length;
        int width = image1[0].length;
        Color[][] blendImage = new Color[height][width];
        if (!isMatchSize(image1, image2)) {
            return blendImage;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color image1pixel = image1[i][j];
                Color image2pixel = image2[i][j];
                Color blendedPixel = blend(image1pixel, image2pixel, alpha);
                blendImage[i][j] = blendedPixel;
            }
        }
        return blendImage;
    }

    public static boolean isMatchSize(Color[][] image1, Color[][] image2) {
        int height = image1.length;
        int width = image1[0].length;
        Color[][] blendImage = new Color[height][width];
        int image1Height = image1.length;
        int image1Width = image1[0].length;
        int image2Height = image2.length;
        int image2Width = image2[0].length;
        if ((image1Height != image2Height) || (image1Width != image2Width)) {
            return false;
        }
        return true;
    }

    /**
     * Morphs the source image into the target image, gradually, in n steps.
     * Animates the morphing process by displaying the morphed image in each
     * step. Before starting the process, scales the target image to the
     * dimensions of the source image.
     */
    public static void morph(Color[][] source, Color[][] target, int n) {
        int height = source.length;
        int width = source[0].length;
        Color[][] blendedImage = new Color[height][width];
        if (!isMatchSize(source, target)) {
            scaled(target, width, height);
        }
        for (int i = 0; i < n; i++) {
            double alpha = (n - i) / n;
            blendedImage = blend(source, target, alpha);
            display(blendedImage);
            StdDraw.pause(500);
        }
    }

    /**
     * Creates a canvas for the given image.
     */
    public static void setCanvas(Color[][] image) {
        StdDraw.setTitle("Runigram 2023");
        int height = image.length;
        int width = image[0].length;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
        // the StdDraw.show function is called.
        StdDraw.enableDoubleBuffering();
    }

    /**
     * Displays the given image on the current canvas.
     */
    public static void display(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Sets the pen color to the pixel color
                StdDraw.setPenColor(image[i][j].getRed(),
                        image[i][j].getGreen(),
                        image[i][j].getBlue());
                // Draws the pixel as a filled square of size 1
                StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
            }
        }
        StdDraw.show();
    }
}
