/******************************************************************************
 *  Compilation:  javac Permutation.java
 *  Execution:    java Permutation
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
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
public class Permutation {
    
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        
        /* String item;
        while (!StdIn.isEmpty()) {
            item =  StdIn.readString();
            if (StdRandom.uniform(2)%2 == 0) {
                if(queue.size() >= k && k != 0){
                    queue.dequeue();
                }
                queue.enqueue(item);
            }
        } 
        for (int i = 0; queue.size() != 0 && i < k; ++i) {
            StdOut.println(queue.dequeue());
        }
        // StdOut.println(queue.size());
        */
        String[] item = StdIn.readAllStrings();
        for (int i = 0; i < k && i < item.length; ) {
            int position = StdRandom.uniform(item.length);
            if (item[position] != null) {
                queue.enqueue(item[position]);
                item[position] = null;
                ++i;
            }
        }
        Iterator<String> it = queue.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
        
    }
}