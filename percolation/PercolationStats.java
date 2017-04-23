/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats
 *
 *  Prints "Hello, World". By tradition, this is everyone's first program.
 *
 *  % java HelloWorld
 *  Hello, World
 *
 *  These 17 lines of text are comments. They are not part of the program;
 *  they serve to remind us about its properties. The first two lines tell
 *  us what to type to compile and test the program. The next line describes
 *  the purpose of the program. The next few lines give a sample execution
 *  of the program and the resulting output. We will always include such 
 *  lines in our programs and encourage you to do the same.
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    // private int[] position;
    private double sampleMean; 
    private double confidenceLoValue;
    private double confidenceHiValue;
    
    private double standardDeviation;
    private double[] meanArray;
    
    public PercolationStats(int n, int trials) {
        // StdOut.println("Constrcutor called");
        meanArray = new double[trials];
        /* position = new int[n*n+5];
        for (int i = 0; i <= n*n+3; ++i) {
            position[i] = i;
        } */
        execute(n, trials);
    }
    
    public double mean() {
        return sampleMean;
    }
    
    public double stddev() {
        return standardDeviation;
    }
    
    public double confidenceLo() {
        return confidenceLoValue;
    }
    
    public double confidenceHi() {
        return confidenceHiValue;
    }
    
    public static void main(String[] args) {
        
        PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        StdOut.println(percStats.mean());
        StdOut.println(percStats.stddev());
        StdOut.println(percStats.confidenceLo() + "     " + percStats.confidenceHi());
    }
    
    private void execute(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Condition :: N > 0 and trails > 0");
        }
        double meanSum = 0.0;
        double deviationSquareSum = 0.0;
        
        for (int i = 0; i < trials; ++i) {
            // StdOut.println("Trails :: " + i);
            Percolation perc = new Percolation(n);
            /* StdRandom.shuffle(position, 1, n*n+1);
            for (int j = 1; j <= n*n; ++j) {
                int row = position[j]/n;
                if (position[j] % n != 0) {
                    row++;
                }
                int col = position[j] - (row - 1)*n;
                perc.open(row, col);
                if (perc.percolates()) {
                    meanArray[i] = 1.0 * perc.numberOfOpenSites()/ (n*n);
                    break;
                }
            } */
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                perc.open(row, col);
            }
            meanArray[i] = 1.0 * perc.numberOfOpenSites()/ (n*n);
            // meanSum += mean[i];
        }
        
        // sampleMean = meanSum * 1.0 / trails;
        sampleMean = StdStats.mean(meanArray);
        standardDeviation = StdStats.stddev(meanArray);
        // standardDeviation = StdStats.stddev(  )
        
        confidenceLoValue = sampleMean - (1.96 * standardDeviation / Math.sqrt(meanArray.length));
        confidenceHiValue = sampleMean + (1.96 * standardDeviation / Math.sqrt(meanArray.length));
        return;
    }
}