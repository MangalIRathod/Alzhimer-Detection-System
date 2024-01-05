/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlzhimerDetection;

import static AlzhimerDetection.Super_Pixel.loadImage;
import static AlzhimerDetection.Super_Pixel.saveImage;
import alzhimerdetectionsystem.KNN.FeatureVectorComparision;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.photo.Photo;

/**
 *
 * @author Codeft
 */
public class TempTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     AlzhimerDetection.JavaBeans beans = new JavaBeans();
       beans.setMode("testing");
        
      
     String filePath = "C:\\Users\\Codeft\\Desktop\\AlzheimerDataset\\test\\MildDemented\\26 (26).jpg";
          System.out.println(filePath);
                           try{
       // LoSmoothing(filePath);
       // SuperPixel(filePath);
        alzhimerdetectionsystem.AlzhimerDetectionSystem.main(filePath,"testing");
           System.out.println("END File..");
      }
      catch(Exception ex)
      {
          System.out.println("Erro:  "+ ex);
      }
                           
                        
        try
        {
         ArrayList<String> Result = FeatureVectorComparision.main(null);
            System.out.println(""+Result.size());
         
         for (int i = 0; i < Result.size(); i++) {               
            String get = Result.get(i);
             System.out.println("Result " + (i + 1) + ": " + get);
        }
        }
        catch(Exception ex)
        {
            System.out.println("Exception :  "+ex);
        }

    }
    
    
    
}
