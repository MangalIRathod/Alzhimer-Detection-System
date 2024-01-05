/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

/**
 *
 * @author Codeft
 */
public class check {
    public static void main(String [] args)
    {
     int truePositives = 8;
     int falsePositives = 27;
     int falseNegatives = 3;
      int trueNegatives = 9;
     
      double accuracy = (double) (truePositives + trueNegatives) / (truePositives + falsePositives + trueNegatives + falseNegatives);
     
      double precisionValue = (double) truePositives / (truePositives + falsePositives);

       
     
      double recallValue = (double) truePositives / (truePositives + falseNegatives);
      
      double f1Score = 2 * (precisionValue * recallValue) / (precisionValue + recallValue);
      System.out.println(accuracy);
    }
}
