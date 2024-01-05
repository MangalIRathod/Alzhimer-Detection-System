package alzhimerdetectionsystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LBPAlzheimerDetection {

    // Define the LBP operator
    public static int[] LBP(int[] pixels) {
        int[] result = new int[8];
        int center = pixels[4];
        result[0] = (pixels[0] >= center) ? 1 : 0;
        result[1] = (pixels[1] >= center) ? 1 : 0;
        result[2] = (pixels[2] >= center) ? 1 : 0;
        result[3] = (pixels[3] >= center) ? 1 : 0;
        result[4] = (pixels[5] >= center) ? 1 : 0;
        result[5] = (pixels[6] >= center) ? 1 : 0;
        result[6] = (pixels[7] >= center) ? 1 : 0;
        result[7] = (pixels[8] >= center) ? 1 : 0;
        return result;
    }

    // Define the LBP histogram feature vector
    public static int[] LBPHistogram(int[][] pixels) {
        int[] histogram = new int[256];
        for (int i = 1; i < pixels.length - 1; i++) {
            for (int j = 1; j < pixels[0].length - 1; j++) {
                int[] lbp = LBP(new int[]{pixels[i - 1][j - 1], pixels[i - 1][j], pixels[i - 1][j + 1],
                    pixels[i][j - 1], pixels[i][j], pixels[i][j + 1], pixels[i + 1][j - 1], pixels[i + 1][j],
                    pixels[i + 1][j + 1]});
                int value = 0;
                for (int k = 0; k < 8; k++) {
                    value += lbp[k] * Math.pow(2, k);
                }
                histogram[value]++;
            }
        }
        return histogram;
    }

    // Define the main function
    public static int[] FindFeatures(String path) throws IOException {
        // Load the image
        BufferedImage image = ImageIO.read(new File(path));

        // Convert the image to grayscale
        BufferedImage grayImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = grayImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        // Convert the grayscale image to a 2D array of pixel values
        int[][] pixels = new int[grayImage.getHeight()][grayImage.getWidth()];
        for (int i = 0; i < grayImage.getHeight(); i++) {
            for (int j = 0; j < grayImage.getWidth(); j++) {
                pixels[i][j] = grayImage.getRaster().getPixel(j, i, new int[1])[0];
            }
        }

        // Compute the LBP histogram feature vector
        int[] histogram = LBPHistogram(pixels);
        for (int i = 0; i < histogram.length; i++) {
            int j = histogram[i];
            System.out.println(j);
        }
        return histogram;
    }
}
// Write the feature vector to a file
    //    BufferedWriter writer = new BufferedWriter(new FileWriter("path/to/featureVector"));
