/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlzhimerDetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.photo.Photo;



/**
 *
 * @author Codeft
 */
public class L0_Smoothing {
    public static void main(String[] args) {
        
    
    
      //  String Mat = jTextField1.getText();
       
         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
         System.out.println("liabrary Added Succesufully.");
       
        Mat input = Highgui.imread("D:\\AlzheimerDataset\\dataset\\MildDemented\\Dem0.jpg" , Highgui.IMREAD_GRAYSCALE);
       // Mat image = Highgui.imread("f:/sm
        // Apply L0 smoothing
        Mat output = new Mat();
        Photo.fastNlMeansDenoising(input , output , 0.05f , 1 , 11);
       
        // Save output image
        Highgui.imwrite("D:\\AlzheimerDataset\\dataset\\MildDemented\\Dem0.jpg" , output);
       // saveImage(dst, dstImage);
         // ImageIcon ii = new ImageIcon();
       //  Image imFit=ii.getImage();
           // Image imgFit=imFit.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
           
         //   jLabel5.setIcon(new  ImageIcon(imgFit));
       
       
     //   jLabel5.setText(Mat);
    
    }
}