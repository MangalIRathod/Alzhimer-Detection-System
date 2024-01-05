/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

import AlzhimerDetection.JavaBeans;
import ConvNuralNetwork.Convolution.POJO.JAVABeans;
import ConvNuralNetwork.Convolution.convolution;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author virendra
 */
public class AlzhimerDetectionSystem {

    /**35465
     */
    
//    public static void main(String[] args) throws IOException {
//        main("D:\\AlzheimerDataset\\test\\VeryMildDemented\\26 (56).jpg");
//    }
  static  BufferedWriter bw;
    
    public static void main(String selectimagepath,String mode) throws IOException {
        System.out.println("Enter into main method");
        String path = selectimagepath;
        ArrayList<String> SIFTFeatures = SIFTDetector.FindFeatures(path);
        int[] LBPFeatures = LBPAlzheimerDetection.FindFeatures(path);
        Vector<Integer> CNNFeatures = convolution.GenerateConvMatrix(path);
        System.out.println("SIFT:  "+ SIFTFeatures);
        System.out.println("LBP:  "+LBPFeatures.length);
        System.out.println("CNNFeatures: "+CNNFeatures.size());
        JAVABeans.setSIFTFeatures(SIFTFeatures);
        JAVABeans.setLBPFeatures(LBPFeatures);
        JAVABeans.setCNNFeatures(CNNFeatures);
       
        if(mode.equals("testing"))
        {
       bw =  new BufferedWriter(new FileWriter(new File("E:\\test.txt")));
        }
        else
      bw =  new BufferedWriter(new FileWriter(new File("E:\\train.txt")));
      
      
        for (int i = 0; i < SIFTFeatures.size(); i++) 
        {
           String get = SIFTFeatures.get(i);
            bw.write(get+",");
            
        }
        
        for (int i = 0; i < LBPFeatures.length; i++) {
            int LBPFeature = LBPFeatures[i];
            bw.write(""+LBPFeature+",");
        }
        
        for (int i = 0; i < CNNFeatures.size(); i++) {
            Integer elementAt = CNNFeatures.elementAt(i);
            if(i == CNNFeatures.size()-1)
            {
                bw.write(""+elementAt);
                bw.newLine();
            }
                else
            bw.write(""+elementAt+",");
        }
        
        bw.close();
        
         if(mode.equals("testing"))
        {
       PSO.main("testing");
        }
        
//       if(mode.equals("testing"))
//        {
//     ABC.main("testing");
//        }
        

        
        System.out.println("END");
       
    }
}
