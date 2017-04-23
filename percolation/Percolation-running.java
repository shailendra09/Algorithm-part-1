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
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private int[] pool;
    private int[] size;
    private boolean[] status; 
    private int total_sites;
    private int open_sites;
    private int top,bottom;
    private int n;
    private boolean percolate_status;
    
    public Percolation(int n) {
        //StdOut.println(" Constrcutor :: " + n);
        this.n = n;
        this.percolate_status = false;
        pool = new int[n*n+5];
        status = new boolean[n*n+5];
        size = new int[n*n+5];
        top = 0;
        bottom = n*n+1;
        pool[top] = top;            // top
        pool[bottom] = bottom;      //bottom;
        size[top] = 1;
        size[bottom] = 1;
        for(int i=1; i<=n*n; ++i) {
            pool[i] = i;
            size[i] = 1;
            status[i] = false;
        }
        for(int i=top+1, j=bottom-1; i<=n; ++i, --j){
            union(top, i);     //make top as root
            //union(bottom, j);     //make bottom as root
        }        
        total_sites = n*n;
        open_sites = 0;
    }
    
    public void open(int row, int col) {
        boolean bottom_site = false;
        if( isOpen(row, col) ) {
            //StdOut.println("Already Open :  row :"+ row +" col:" + col );
            return;
        }
        
        int position = (row-1)*n + col;
        if(position > n*(n-1)){
            union(bottom, position);
            //bottom_site = true;
        }
        status[position] = true;
        /*for(int i=0; i<=n*n + 1; ++i){
            StdOut.println(i + "==>" +status[i] +"==>"+ pool[i] );
        }
        */
        //StdOut.println("xxxxxxxxxxxxx Open :: row : " + row + ", col : " + col + "  position : " + position);
        
        open_sites++;
        //check top
        int new_site = position - n;
        if( valid_site( new_site) && status[new_site] ) {
            //StdOut.println(" TOP " + ((row-1)*n + col) +"    "+ (((row-1)*n + col)-n));
            union( position, new_site );
        }
        //check bottom
        new_site = position + n;
        if( valid_site( new_site ) && status[new_site] ) {
            //StdOut.println(" BOTTOM " + ((row-1)*n + col) +"     "+ (((row-1)*n + col)+n));
            union( position, new_site);
        }
        //check left
        new_site = position - 1;
        if( valid_site( new_site ) && status[new_site] &&  position % n != 1) {
            //StdOut.println(" LEFT " + ((row-1)*n + col)+ "     " + (((row-1)*n + col)-1) );
            union( position, new_site);
        }
        //check right
        new_site = position + 1;
        if( valid_site( new_site ) && status[new_site] && position % n != 0) {
            //StdOut.println(" RIGHT " + ((row-1)*n + col) +"      "+ (((row-1)*n + col)+1));
            union( position, new_site);
        } 
        /*for(int i=0; i<n; ++i){
            for(int j=1; j<=n; ++j){
                StdOut.print(pool[i*n + j] + "  ");
            }
            StdOut.println();
        }*/

        //StdOut.println("Checking Percolation : " + percolates());  
        if(bottom_site == true){
            if(root(position) == root(top)){
                percolate_status = true;
            }
        }
    }
    
    public boolean isOpen(int row, int col) {
        int position = (row-1) * n + col;
        //StdOut.println(" ISOpen :: row : " + row + ", col : " + col);
        if( status[position] == true ) {
            return true;
        }
        return false;
    }
    
    public boolean isFull(int row, int col) {
        //StdOut.println(" IsFull :: row : " + row + ", col : " + col);
        if( find( (row-1)*n + col) && status[(row-1)*n + col]) {
            return true;
        }
        return false;
    }
    
    public int numberOfOpenSites() {
        //StdOut.println(" OpenSites " + open_sites);
        return open_sites;
    }
    
    public boolean percolates() {
        //StdOut.println(" percolates ::");
        if( root(bottom) == root(top) ) {
            return true;
        }
        /*if( percolate_status) return true;*/
        return false;
    }
    
    private boolean find(int position) {
        //check with top position that is pool[0][0];
        if(root(position) == root(top) ) {
            return true;
        }
        return false;  
    }
    
    private void union(int position1, int position2) {
        int root1 = root( pool[position1] );
        int root2 = root( pool[position2] );
        if(root1 == root2) return;
        if( size[root1] < size[root2]) {
            pool[root1] = root2;
            size[root2] += size[root1];
        }else{
            pool[root2] = root1;
            size[root1] += size[root2];
        }
        //pool[root2] = root1 ;
    }
    
    private boolean valid_site(int position){
        if(position >= 1 && position <= n*n) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args){
        
    }
    
    private int root(int position) {
        int temp = position;
        //StdOut.println("Checking root of position : " + position);
        while( pool[ position ] != position ){
            position = pool[ position ];
        }
        pool[temp] = position;
        //StdOut.print("Root is : " + position);
        return position; 
    }
    
}
    