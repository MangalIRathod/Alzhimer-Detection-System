/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

import com.sun.rowset.internal.Row;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Codeft
 */
public class Alzhimer {
      
 public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("E:\\train.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
             double count = 0;
            double totalcount=0;
            
            
            String line;
            
            while ((line = bufferedReader.readLine()) != null) {
              totalcount++;
                if(line.equals(""))
                {
                count++;
                }
                
            } 
           bufferedReader.close();
           double x = (count/totalcount) * 100;
            System.out.println("per :   "+ x);
            
             
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
     
   
    
}

