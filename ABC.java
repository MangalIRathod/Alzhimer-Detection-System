/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

import AlzhimerDetection.JavaBeans;
import static alzhimerdetectionsystem.PSO.featureMatrix;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Codeft
 */
public class ABC {
// ABC Parameters
    private static final int NUM_EMPLOYED_BEES = 20;
    private static final int NUM_ONLOOKER_BEES = 20;
    private static final int MAX_ITERATIONS = 100;
    private static final double MIN_BOUND = 0.0;
    private static final double MAX_BOUND = 1.0;

    // Problem-specific parameters
    private static  int NUM_FEATURES ;
    private static  int MAX_FEATURES_TO_SELECT;
     static BufferedWriter bwriter;
     static int[][] featureMatrix;
    public static void main(String args) throws IOException {
        
        AlzhimerDetection.JavaBeans beans = new JavaBeans();
         System.out.println("3:  "+beans.getMode());
        
    if(args.equals("testing"))
        {
       featureMatrix = loadFeatureMatrix("E:\\test.txt");
        }
   else
       featureMatrix   = loadFeatureMatrix("E:\\train.txt");
          
         NUM_FEATURES = featureMatrix.length * featureMatrix[0].length;
         MAX_FEATURES_TO_SELECT = featureMatrix.length * featureMatrix[0].length;


    if(args.equals("testing"))
        {
    bwriter  = new BufferedWriter(new FileWriter(new File("E:\\abctest.txt"),true));
        }
    else 
       bwriter  = new BufferedWriter(new FileWriter(new File("E:\\abc.txt"),true));
    
        // Initialize employed bees with random solutions
        Bee[] employedBees = new Bee[NUM_EMPLOYED_BEES];
        for (int i = 0; i < NUM_EMPLOYED_BEES; i++) {
            employedBees[i] = new Bee(NUM_FEATURES, MAX_FEATURES_TO_SELECT);
        }

        // ABC optimization
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            // Employed bees phase
            for (Bee bee : employedBees) {
                Bee newBee = bee.explore();
                double fitness = evaluateFitness(newBee.getSelectedFeatures());
                if (fitness > bee.getFitness()) {
                    bee.update(newBee.getSelectedFeatures(), fitness);
                }
            }

            // Calculate probabilities for onlooker bees
            double totalFitness = getTotalFitness(employedBees);
            for (Bee bee : employedBees) {
                bee.setProbability(bee.getFitness() / totalFitness);
            }

            // Onlooker bees phase
            for (int i = 0; i < NUM_ONLOOKER_BEES; i++) {
                Bee selectedBee = selectBeeWithProbability(employedBees);
                Bee newBee = selectedBee.explore();
                double fitness = evaluateFitness(newBee.getSelectedFeatures());
                if (fitness > selectedBee.getFitness()) {
                    selectedBee.update(newBee.getSelectedFeatures(), fitness);
                }
            }

            // Update best solution
            Bee bestBee = getBestBee(employedBees);

            // Scout bees phase (abandon solutions)
            for (Bee bee : employedBees) {
                if (bee.getTrials() > MAX_ITERATIONS / 2) {
                    bee.randomizeSolution();
                }
            }
        }

        // Get the best bee and its selected features
        Bee bestBee = getBestBee(employedBees);
        int[] selectedFeatures = bestBee.getSelectedFeatures();

        // Print selected features
       System.out.println("Selected Features: ");
        for (int featureIndex : selectedFeatures) {
            System.out.print(featureIndex + " ");
        }
        
        WriteFeatureVector();
        
    
    }

    // Example fitness function (replace with your own)
    private static double evaluateFitness(int[] selectedFeatures) {
        // Compute fitness using the selected features
        // Example: Train a model and return its accuracy or other performance metric
        return new Random().nextDouble(); // Placeholder
    }

    // Helper function to calculate total fitness
    private static double getTotalFitness(Bee[] bees) {
        double total = 0.0;
        for (Bee bee : bees) {
            total += bee.getFitness();
        }
        return total;
    }
    
   
   // Helper function to select a bee with probability

     private static Bee selectBeeWithProbability(Bee[] bees) {
        double randomValue = Math.random();
        double cumulativeProbability = 0.0;
        for (Bee bee : bees) {
        cumulativeProbability += bee.getProbability();
        if (randomValue <= cumulativeProbability) {
            return bee;
        }
     }
     return bees[bees.length - 1];
  }
    // Helper function to find the best bee
    private static Bee getBestBee(Bee[] bees){
        Bee bestBee = bees[0];
        for (Bee bee : bees) {
            if (bee.getFitness() > bestBee.getFitness()) {
                bestBee = bee;
            }
        }
        return bestBee;
    }
    
    private static int[][] loadFeatureMatrix(String filename) {
    int[][] matrix = null;
    try {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int numRows = 0;
        int numCols = 0;

        // Determine the number of rows and the maximum number of columns
        while ((line = br.readLine()) != null) {
            String[] values = line.split("[ ,]+");
            numRows++;
            if (values.length > numCols) {
                numCols = values.length;
            }
        }
        // Initialize the matrix with the determined dimensions
        matrix = new int[numRows][numCols];
        // Reset the buffered reader to read from the beginning of the file
        br.close();
        br = new BufferedReader(new FileReader(filename));

        // Populate the matrix with values
        numRows = 0;
        while ((line = br.readLine()) != null) {
            String[] values = line.split("[ ,]+");
            for (int i = 0; i < values.length; i++) {
                try {
                    double doubleValue = Double.parseDouble(values[i]);
                    matrix[numRows][i] = (int) doubleValue; 
                 //   matrix[numRows][i] = Integer.parseInt(values[i]);
                } catch (NumberFormatException ex) {
                    // Handle invalid number format
                    ex.printStackTrace();
                    // You might choose to set a default value or take other appropriate actions here
                }
            }
            numRows++;
        }

        br.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return matrix;
}
    private static void WriteFeatureVector() throws IOException {
        for (int i = 0; i < featureMatrix.length; i++) {
            int[] is = featureMatrix[i];
            for (int j = 0; j < 7000; j++) {
                int k = is[j];
                bwriter.write(""+k);
                
                if(j<6999)
                bwriter.write(",");
            }
            bwriter.newLine();
        }
        bwriter.close();
    }

     

}

class Bee {
    private int[] selectedFeatures;
    private double fitness;
    private double probability;
    private int trials;

    public Bee(int numFeatures, int maxFeaturesToSelect) {
        selectedFeatures = new int[numFeatures];
        for (int i = 0; i < numFeatures; i++) {
            selectedFeatures[i] = i;
        }
        shuffleArray(selectedFeatures, maxFeaturesToSelect);
        fitness = 0.0;
        probability = 0.0;
        trials = 0;
    }

    public int[] getSelectedFeatures() {
        return selectedFeatures;
    }

    public double getFitness() {
        return fitness;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public int getTrials() {
        return trials;
    }
    
    public double getProbability(){
        
        return probability;
    }
    
    
    public Bee explore() {
        Bee newBee = new Bee(selectedFeatures.length, selectedFeatures.length);
        int indexToChange = new Random().nextInt(selectedFeatures.length);
        newBee.getSelectedFeatures()[indexToChange] = selectedFeatures[indexToChange] == 0 ? 1 : 0;
        return newBee;
    }

    public void update(int[] newSelectedFeatures, double newFitness) {
        selectedFeatures = newSelectedFeatures.clone();
        fitness = newFitness;
        trials = 0;
    }

    public void randomizeSolution() {
        shuffleArray(selectedFeatures, selectedFeatures.length / 2);
        trials = 0;
    }

    // Helper function to shuffle an array
    private static void shuffleArray(int[] array, int numSwaps) {
        Random rnd = new Random();
        for (int i = 0; i < numSwaps; i++) {
            int index1 = rnd.nextInt(array.length);
            int index2 = rnd.nextInt(array.length);
            int temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
    }
}
