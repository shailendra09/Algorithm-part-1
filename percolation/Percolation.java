/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation
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
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] status; 
    private int openSites;
    private int top, bottom;
    private int n;
    private WeightedQuickUnionUF wquuf;
    private WeightedQuickUnionUF testWquuf;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N should be greater than zero");
        }
        this.n = n;
        status = new boolean[n*n+5];
        wquuf = new WeightedQuickUnionUF(n*n+5);
        testWquuf = new WeightedQuickUnionUF(n*n+5);
        top = 0;
        bottom = n*n + 1;
        openSites = 0;
        for (int i = 0; i < n*n+5; ++i) {
            status[i] = false;
        }
        for (int i = 1; i <= n; ++i) {
            wquuf.union(top, i);
            testWquuf.union(top, i);
        }
    }
    
    public void open(int row, int col) {
        validInput(row, col);
        if (isOpen(row, col)) {
            return;
        }
        
        int position = (row-1)*n + col;
        if (position > n*(n-1)) {
            wquuf.union(bottom, position);
        }
        status[position] = true;
        
        openSites++;
        // check top
        int newSite = position - n;
        if (validSite(newSite) && status[newSite]) {
            wquuf.union(position, newSite);
            testWquuf.union(position, newSite);
        }
        // check bottom
        newSite = position + n;
        if (validSite(newSite) && status[newSite]) {
            wquuf.union(position, newSite);
            testWquuf.union(position, newSite);
        }
        // check left
        newSite = position - 1;
        if (validSite(newSite) && status[newSite] &&  position % n != 1) {
            wquuf.union(position, newSite);
            testWquuf.union(position, newSite);
        }
        // check right
        newSite = position + 1;
        if (validSite(newSite) && status[newSite] && position % n != 0) {
            wquuf.union(position, newSite);
            testWquuf.union(position, newSite);
        } 
    }
    
    public boolean isOpen(int row, int col) {
        validInput(row, col);
        int position = (row-1) * n + col;
        if (status[position]) {
            return true;
        }
        return false;
    }
    
    public boolean isFull(int row, int col) {
        validInput(row, col);
        if (testWquuf.connected((row-1)*n + col, top) && status[(row-1)*n + col]) {
            return true;
        }
        return false;
    }
    
    public int numberOfOpenSites() {
        return openSites;
    }
    
    public boolean percolates() {
        if (wquuf.connected(top, bottom)) {
            return true;
        }
        return false;
    }
    
    private boolean validSite(int position) {
        if (position >= 1 && position <= n*n) {
            return true;
        }
        return false;
    }
    
    private void validInput(int row, int col) {
        if ((row >= 1 && row <= n) && (col >= 1 && col <= n)) {
            return;
        }
        throw new IndexOutOfBoundsException("Range should be 0 < Row,Col <= n ");
    }
    
    public static void main(String[] args) {
        
    }
    
}
    