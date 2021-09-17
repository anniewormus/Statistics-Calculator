/*  Written by Annie Wormus
    9.16.2021
    A simple Java application that calculates all the formulas that I need for my statistics class
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class Calculator {

  public static void main(String[] args){

    try{
      // variable declarations
      int i = 0;

      File input = new File("input_data.txt");
      Scanner scan = new Scanner(input);

      while (scan.hasNextDouble()){
        scan.nextDouble();
        i++;
      }

      Scanner scan2 = new Scanner(input);
      double[] arr = new double[i];

      int e = 0;
      while (scan2.hasNextDouble()) {
        arr[e] = scan2.nextDouble();
        e++;
      }

      for(int x = 0; i < 17; i++){
        System.out.println(arr[x]);
      }

      // Math functions
      double mean = mean(arr);
      double median = median(arr);
      double mode = mode(arr);
      double range = range(arr);
      double standardDev = standardDev(arr);
      double variance = variance(arr);
      ZScore(arr);

      print(mean, median, mode, range, standardDev, variance);
    } catch(FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  // Returns the Mean or Average of the given data
  // Formula: (X1 + X2 + X3 + ... + Xn)/n
  public static double mean(double[] arr){
    // Variable declarations
    double sum = 0;
    // Gets the sum of all the data
    for(int i = 0; i < arr.length; i++){
      sum += arr[i];
    }
    // Divides by the total number of elements in the data set to find the mean
    double mean = (sum / arr.length);
    return mean;
  }

  // Returns the median of the given data
  // Formula:
  public static double median(double[] arr){
    // Variable declarations
    double med;
    int length = arr.length;
    Arrays.sort(arr);

    // Returns the middle data value if the total length of the data set is odd
    // Returns the average of the two mid values if the length of the data set is even
    if(length%2 == 0){
      double[] arr2 = {arr[length/2 - 1], arr[length/2]};
      med = mean(arr2);
    }else{
      med = arr[length/2];
    }
    return med;
  }

  // Returns the value of the number most apparent in the data set
  // ****NOTE**** THIS FUNCTION WORKS WITH DATA SET COMPOSED OF POSITIVE INTEGERS ONLY
  public static double mode(double[] arr){
    // Variable declarations
    Arrays.sort(arr);
    int high = (int)(arr[arr.length-1]);

    // Creates an array with a length the size of the largest number in the data set
    // and then keeps track of the index value with the most hits
    int[] counts = new int[high];

    for(int i = 0; i < arr.length-1; i++){
      int tmp = (int)arr[i];
      counts[tmp] += 1;
    }

    // Finds the index with the greatest amount of hits and returns the index value
    double mode = counts[0];
    for(int i = 0; i < counts.length; i++){
      if(counts[i] > mode)
        mode = i;
    }

    return mode;
  }

  // Returns the range of the given data
  // Formula: X0 - Xn
  public static double range(double[] arr){
    // Variable declarations
    Arrays.sort(arr);
    int size = arr.length;

    // Subtracts the lowest value in the data set from the highest value in the data set
    double range = arr[0] - arr[size-1];

    return Math.abs(range);
  }

  // Returns the Standard Deviation of the given data
  // Formula: sqrt of the variance
  public static double standardDev(double[] arr){

    double variance = variance(arr);
    double sd = Math.pow(variance, 0.5);

    return sd;
  }

  // Returns the Variance of the given data
  // Formula:
  public static double variance(double[] arr){
    // Variable declarations
    double sum = 0;
    double mean = mean(arr);

    // Gets the sum of each data value squared
    for(int i = 0; i < arr.length; i++){
      sum += Math.pow(arr[i] - mean, 2);
    }

    // Divides by length - 1
    double variance = sum / (arr.length-1);
    return variance;

  }

  // Returns the Z score of each data value
  // Formula: (x - mean)/stdv
  public static void ZScore(double[] arr){
    // Variable declarations
    double z;
    double mean = mean(arr);
    double stdv = standardDev(arr);

    for (int i = 0; i < arr.length; i++){
      z = arr[i] - mean;
      z = z/stdv;
      System.out.println("Z-score for " + arr[i] + " is " + z);
    }
  }

  public static void print(double mean, double median, double mode, double range,
                           double standardDev, double variance){
    System.out.println("Mean:\t\t\t" + mean +
                       "\nMedian:\t\t\t" + median +
                       "\nMode:\t\t\t" + mode +
                       "\nRange:\t\t\t" + range +
                       "\nStandard Deviation:\t" + standardDev +
                       "\nVariance:\t\t" + variance);
  }
}
