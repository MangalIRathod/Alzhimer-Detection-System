/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author virendra
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("E:\\train.txt")) );
    String line;
        while ((line = br.readLine())!=null) {
           String string[] = line.split(",");
              System.out.println("total no:  "+string.length); 
        }
      
    
    }
    
}
