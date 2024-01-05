/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlzhimerDetection;
import java.util.ArrayList;
/**
 *
 * @author Codeft
 */
public class JavaBeans {
    
    
public static Cluster[] clusters;
public static ArrayList<double[]> FeatureVector = new ArrayList<double[]>();
public static ArrayList<int[]> Clusterpoint = new ArrayList<int[]>();
public static ArrayList<Integer> mylist = new ArrayList<Integer>();
public  String mode ="";

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    
   
    public static ArrayList<int[]> getClusterpoint() {
        return Clusterpoint;
    }

    public static ArrayList<Integer> getMylist() {
        return mylist;
    }

    public static void setMylist(ArrayList<Integer> mylist) {
        JavaBeans.mylist = mylist;
    }

    public static void setClusterpoint(ArrayList<int[]> Clusterpoint) {
        JavaBeans.Clusterpoint = Clusterpoint;
    }

    public static ArrayList<double[]> getFeatureVector() {
        return FeatureVector;
    }

    public static void setFeatureVector(ArrayList<double[]> FeatureVector) {
        JavaBeans.FeatureVector = FeatureVector;
    }
    public static Cluster[] getClusters() {
        return clusters;
    }

    public static void setClusters(Cluster[] clusters) {
        JavaBeans.clusters = clusters;
    }

   
    public static String getSegmentfilepath() {
        return segmentfilepath;
    }

    public static void setSegmentfilepath(String segmentfilepath) {
        JavaBeans.segmentfilepath = segmentfilepath;
    }

    public static ArrayList<String> getKpfeatures() {
        return kpfeatures;
    }

    public static void setKpfeatures(ArrayList<String> kpfeatures) {
        JavaBeans.kpfeatures = kpfeatures;
    }

    public static String getOutputfilepath() {
        return outputfilepath;
    }

    public static void setOutputfilepath(String outputfilepath) {
        JavaBeans.outputfilepath = outputfilepath;
    }
   
    public static String filepath = "";
   
    public static String segmentfilepath = "";
   
    public static String outputfilepath = "";
   
    public static ArrayList<String> kpfeatures = new ArrayList<String>();

    public static String getFilepath() {
        return filepath;
    }

    public static void setFilepath(String filepath) {
        JavaBeans.filepath = filepath;
    }
}
