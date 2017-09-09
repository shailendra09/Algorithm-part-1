
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by shailendra on 7/5/17.
 */


public class FastCollinearPoints {
    private final Point[] copyPoint;
    private final ArrayList<LineSegment> lineSegments;
    private final double[] slope;
    private final int[] pos;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) {
            // StdOut.print("Point array is null");
            throw new java.lang.NullPointerException();
        }
        lineSegments = new ArrayList<>();
        checkDuplicate(points);
        this.copyPoint = Arrays.copyOf(points, points.length);
        Arrays.sort(copyPoint);
        slope = new double[points.length];
        pos = new int[points.length];
        for(int i = 0; i < copyPoint.length; ++i) {
            // StdOut.println("ZZZZZZZ " + i + "   " + copyPoint.length);
            solve(i);
            // StdOut.println("XXXXXXXXX " + i + "   " + copyPoint.length);
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        // the line segments
        return lineSegments.toArray(new LineSegment[numberOfSegments()]);
    }

    private void checkDuplicate(Point[] points) {
        for (int i = 0; i < points.length; ++i) {
            for(int j = i+1; j < points.length; ++j){
                if (points[i].compareTo(points[j]) == 0) {
                    // StdOut.println(points[i].toString());
                    // StdOut.println(points[j].toString());
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private void solve(int position) {
        // StdOut.println("***************Solving for position " + position + "**********");
        for (int i = 0; i < copyPoint.length; ++i) {
            pos[i] = i;
            slope[i] = 0;
        }
        for (int i = position+1; i < copyPoint.length; ++i) {
            slope[i] = copyPoint[position].slopeTo(copyPoint[i]);
        }
        /* StdOut.println("Before Sorting");
        Arrays.stream(slope).forEach(StdOut::println); */
        sort();
        /* StdOut.println("After Sorting");
        Arrays.stream(slope).forEach(StdOut::println); */
        for(int i = 1; i < copyPoint.length; ++i) {
            // StdOut.println("Inside SOLVE forloop");
            if (slope[i] != 0.0) {
                int count = 1;
                while (i < copyPoint.length && slope[i] == slope[i-1]) {
                    // StdOut.print("Inside while loop");
                    ++i;
                    count++;
                }
                //--i;
                if (count >= 3) {
                    // StdOut.print("LINE :: " + position + " second point :" + pos[i-1]);
                    // StdOut.println(copyPoint[position].toString() + "     " + copyPoint[pos[i-1]].toString());
                    if (copyPoint[position] == null) {
                        StdOut.println("Position is null");
                    }
                    if (copyPoint[pos[i-1]] == null) {
                        StdOut.println("NULL is : " + pos[i-1]);
                    }
                    lineSegments.add(new LineSegment(copyPoint[position], copyPoint[pos[i-1]]));
                }
            }
        }
    }

    private void sort() {
        int n = copyPoint.length;
        for (int size = 1; size < n; size = size+size) {
            for(int lo = 0; lo < (n-size); lo += size + size) {
                merge(lo, lo+size-1, Math.min(lo+size+size-1, n-1));
            }
        }
    }

    private void merge(int low, int mid, int high) {
        // StdOut.println(low + " : " + mid + " : " + high);
        double[] auxSlope = new double[copyPoint.length];
        int[] auxPos = new int[copyPoint.length];
        for (int k = low; k <= high; ++k) {
            auxSlope[k] = slope[k];
            auxPos[k] = pos[k];
        }

        int i = low, j = mid+1;
        for (int k = low; k <= high; ++k) {
            if (i > mid) {
                swap(k, auxSlope[j], auxPos[j]);
                j++;
            }else if (j > high) {
                swap(k, auxSlope[i], auxPos[i]);
                ++i;
            }else if (auxSlope[j] < auxSlope[i]) {
                // StdOut.println("SWAP  j = " + j + " : " + i);
                swap(k, auxSlope[j], auxPos[j]);
                j++;
            }else {
                swap(k, auxSlope[i], auxPos[i]);
                ++i;
            }
        }
    }

    private void swap(int k,double slope1,int pos1){
        slope[k] = slope1;
        pos[k] = pos1;
    }

}
