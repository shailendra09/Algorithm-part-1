import edu.princeton.cs.algs4.StdOut;

/**
 * Created by shailendra on 8/5/17.
 */
public class Test {
    public static void main(String[] args) {
        Point p1 = new Point(456, 13);
        Point p2 = new Point(42, 13);
        StdOut.print(p1.slopeTo(p2));
        //StdOut.print(1.0*(362-400)/(368-368));
        /*StdOut.println(p1.slopeTo(p1));
        StdOut.println(1.0*(300-300)/(300-300));
        p1 = new Point(2,2);
        p2 = new Point(4,2);
        StdOut.println(p1.slopeTo(p2));*/
    }
}
