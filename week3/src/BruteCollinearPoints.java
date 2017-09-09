import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by shailendra on 7/5/17.
 */
public class BruteCollinearPoints {
    private final Point[] copyPoints;
    private final ArrayList<LineSegment> lineSegments;
    private final LineSegment[] finalSegment;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            // StdOut.print("Point array is null");
            throw new java.lang.NullPointerException();
        }
        checkDuplicate(points);
        this.copyPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyPoints);
        /*for (Point p : points) {
            StdOut.println("Points : " + p.toString());
        }*/
        // finds all line segments containing 4 copyPoints

        lineSegments = new ArrayList<>();
        getLine();
        finalSegment = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return finalSegment;
    }

    private void getLine() {
        for (int i = 0; i < copyPoints.length; ++i) {
            Comparator<Point> comparators = copyPoints[i].slopeOrder();
            for (int j = i+1; j < copyPoints.length; ++j) {
                // StdOut.println("--------------------------");
                for(int k = j+1; k < copyPoints.length; ++k) {
                    for (int l = k+1; l < copyPoints.length; ++l) {
                        // StdOut.println(i+ " : " + j + " : " + k + " : " + l);
                        if (comparators.compare(copyPoints[j], copyPoints[k]) == 0 && comparators.compare(copyPoints[k], copyPoints[l]) == 0) {
                            //Point first = getFirstEnd(copyPoints[i], copyPoints[j], copyPoints[k], copyPoints[l]);
                            //Point second = getSecondEnd(copyPoints[i], copyPoints[j], copyPoints[k], copyPoints[l]);
                            lineSegments.add(new LineSegment(copyPoints[i], copyPoints[l]));
                            // StdOut.println(copyPoints[i].toString());
                            // StdOut.println(copyPoints[j].toString());
                            // StdOut.println(copyPoints[k].toString());
                            // StdOut.println(copyPoints[l].toString());
                        }
                    }
                }
            }
        }
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
}
