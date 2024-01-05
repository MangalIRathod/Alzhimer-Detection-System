/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

/**
 *
 * @author CodeftTechnologies
 */

import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;

import java.io.IOException;
import java.util.ArrayList;


public class SIFTDetector {

    public static ArrayList<String> FindFeatures(String path) throws IOException {


        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\nloaded library done");

        String bookObject = path;
        
        System.out.println("Started....");
        System.out.println("Loading images...");
        Mat objectImage = Highgui.imread(bookObject, Highgui.CV_LOAD_IMAGE_COLOR);
      

        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
        FeatureDetector featureDetector = FeatureDetector.create(FeatureDetector.SURF);
        System.out.println("Detecting key points...");
        featureDetector.detect(objectImage, objectKeyPoints);
        KeyPoint[] keypoints = objectKeyPoints.toArray();
       // System.out.println(keypoints);
       
      
       System.out.println("length keypoint:  "+ keypoints.length); 
        ArrayList<String> kp = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            KeyPoint keyPoint = keypoints[i];
//            System.out.println(keyPoint.pt);
               
            kp.add(""+keyPoint.response);
          
         
        }
        
       // JavaBeans.setKpfeatures(kp);

        MatOfKeyPoint objectDescriptors = new MatOfKeyPoint();
        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.SURF);
        System.out.println("Computing descriptors...");
        descriptorExtractor.compute(objectImage, objectKeyPoints, objectDescriptors);

        // Create the matrix for output image.
        Mat outputImage = new Mat(objectImage.rows(), objectImage.cols(), Highgui.CV_LOAD_IMAGE_COLOR);
        Scalar newKeypointColor = new Scalar(255, 0, 0);

        System.out.println("Drawing key points on object image...");
        Features2d.drawKeypoints(objectImage, objectKeyPoints, outputImage, newKeypointColor, 0);

            Highgui.imwrite("E://outputImage.jpg", outputImage);
            
          //  JavaBeans.setOutputfilepath("E://outputImage.jpg");
          return kp;
        } 
      
}
