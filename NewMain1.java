/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Codeft
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic 
        
        try  {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("E:\\pso.txt"));
            int x = br.readLine().length();
            System.out.println(""+x);
            int count = 0; 
            
            
            while ((line = br.readLine()) != null) {
                count++;
                String[] values = line.split(",");
                double[] featureVector = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                featureVector[i] = Double.parseDouble(values[i]);
                 
                }
                
            }
              System.out.println("" +count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
