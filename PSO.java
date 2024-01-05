/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alzhimerdetectionsystem;

/**
 *
 * @author virendra
 */
import AlzhimerDetection.JavaBeans;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PSO {
    private static final int NUM_PARTICLES = 30;
    private static final int MAX_ITERATIONS = 100;
    private static final double INITIAL_INERTIA_WEIGHT = 0.9; // Initial inertia weight
    private static final double FINAL_INERTIA_WEIGHT = 0.4;   // Final inertia weight
    private static final double ACCELERATION_COEFFICIENT1 = 2.0;
    private static final double ACCELERATION_COEFFICIENT2 = 2.0;

    private static  int NUM_FEATURES ;
    private static  int MAX_FEATURES_TO_SELECT ;
//7391 23917 28915 32934 28304 26981 12235 18814 9161 33265 3831
     static BufferedWriter bwriter;
    static int[][] featureMatrix;
    public static void main(String args) throws IOException {
        
         AlzhimerDetection.JavaBeans beans = new JavaBeans();
         System.out.println("3:  "+beans.getMode());
        // Load your feature matrix from a text file here
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
    bwriter  = new BufferedWriter(new FileWriter(new File("E:\\psotest.txt"),true));
        }
 else 
       bwriter  = new BufferedWriter(new FileWriter(new File("E:\\pso.txt"),true));
        // Initialize particles
        Particle[] particles = new Particle[NUM_PARTICLES];
        for (int i = 0; i < NUM_PARTICLES; i++) {
            particles[i] = new Particle(NUM_FEATURES, MAX_FEATURES_TO_SELECT);
        }

        // PSO optimization
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            double inertiaWeight = calculateInertiaWeight(iteration);

            for (Particle particle : particles) {
                // Evaluate fitness
                double fitness = evaluateFitness(particle.getSelectedFeatures());//here is problem getSelectedFeatures() not found 

                // Update personal best
                if (fitness > particle.getBestFitness()) {
                    particle.setBestFitness(fitness);
                    particle.setBestPosition(particle.getPosition().clone());
                }
            }

            // Update global best
            Particle globalBestParticle = getGlobalBestParticle(particles);

            // Update particle velocities and positions
            for (Particle particle : particles) {
                particle.updateVelocity(globalBestParticle, inertiaWeight);
                particle.updatePosition();
            }
        }

        // Get the best particle and its selected features
        Particle bestParticle = getGlobalBestParticle(particles);
        int[] selectedFeatures = bestParticle.getBestPosition();

        // Print selected features
        System.out.println("Selected Features: ");
        for (int featureIndex : selectedFeatures) {
            System.out.print(featureIndex + " ");
        }
        
        WriteFeatureVector();
        
    }

  private static double evaluateFitness(boolean[] selectedFeatures) {
    // Convert boolean array to int array
    int[] intSelectedFeatures = new int[selectedFeatures.length];
    for (int i = 0; i < selectedFeatures.length; i++) {
        intSelectedFeatures[i] = selectedFeatures[i] ? 1 : 0;
    }
    
    // Calculate fitness based on the selected features from the featureMatrix
    double fitness = 0.0;
    for (int i = 0; i < featureMatrix.length; i++) {
        for (int j = 0; j < featureMatrix[i].length; j++) {
            if (selectedFeatures[j] == true) {
                // Add the value from the featureMatrix for the selected feature
                fitness += featureMatrix[i][j];
            }
        }
    }
    
    return fitness;
}

    private static Particle getGlobalBestParticle(Particle[] particles) {
        Particle globalBest = particles[0];
        for (Particle particle : particles) {
            if (particle.getBestFitness() > globalBest.getBestFitness()) {
                globalBest = particle;
            }
        }
        return globalBest;
    }

    private static double calculateInertiaWeight(int iteration) {
        // Linearly decrease the inertia weight from initial to final over iterations
        double fraction = (double) iteration / MAX_ITERATIONS;
        return INITIAL_INERTIA_WEIGHT - fraction * (INITIAL_INERTIA_WEIGHT - FINAL_INERTIA_WEIGHT);
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
  class Particle {
    private int[] position;
    private int[] bestPosition;
    private double bestFitness;
    private double[] velocity;
  private boolean[] selectedFeatures;
     private static final double ACCELERATION_COEFFICIENT1 = 2.0;
    private static final double ACCELERATION_COEFFICIENT2 = 2.0;
    
    
    public Particle(int numFeatures, int maxFeaturesToSelect) {
        // Initialize particle position randomly
        position = new int[numFeatures];
        for (int i = 0; i < numFeatures; i++) {
            position[i] = i;
        }
        shuffleArray(position);

        bestPosition = position.clone();
        bestFitness = 0.0;

        // Initialize velocity randomly
        velocity = new double[numFeatures];
        for (int i = 0; i < numFeatures; i++) {
            velocity[i] = 0.0;
        }
        
        selectedFeatures = new boolean[numFeatures];
        for (int i = 0; i < maxFeaturesToSelect; i++) {
            selectedFeatures[position[i]] = true;
        }
    }

    public boolean[] getSelectedFeatures() {
        return selectedFeatures;
    }

    
    
    public int[] getPosition() {
        return position;
    }

    public int[] getBestPosition() {
        return bestPosition;
    }

    public double getBestFitness() {
        return bestFitness;
    }

    public void setBestFitness(double bestFitness) {
        this.bestFitness = bestFitness;
    }

    public void setBestPosition(int[] bestPosition) {
        this.bestPosition = bestPosition.clone();
    }

   public void updateVelocity(Particle globalBest, double inertiaWeight) {
    int[] personalBestPosition = getBestPosition();
    int[] currentPosition = getPosition();

    for (int i = 0; i < velocity.length; i++) {
        double r1 = 0.8; // Random number between 0 and 1
        double r2 = 0.6; // Random number between 0 and 1

        // PSO update equation for velocity
        velocity[i] = inertiaWeight * velocity[i] +
                      ACCELERATION_COEFFICIENT1 * r1 * (personalBestPosition[i] - currentPosition[i]) +
                      ACCELERATION_COEFFICIENT2 * r2 * (globalBest.getPosition()[i] - currentPosition[i]);
    }
}

public void updatePosition() {
    for (int i = 0; i < position.length; i++) {
        // Update particle position based on velocity
        position[i] += velocity[i];

        // Ensure position remains within bounds
        // You can add logic here to handle bounds, e.g., if position[i] goes out of bounds
        // you can reset it to the nearest bound.
    }
}
    // Helper function to shuffle an array
    private static void shuffleArray(int[] array) {
     
         Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    // Helper function to clone an array
    private static int[] cloneArray(int[] array) {
        int[] clone = new int[array.length];
        System.arraycopy(array, 0, clone, 0, array.length);
        return clone;
    }
}