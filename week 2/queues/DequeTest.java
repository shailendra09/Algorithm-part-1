import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class DequeTest {
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        
        doIteration(deque);
        deque.addLast(1);
        StdOut.println("Added 1");
        doIteration(deque);
        StdOut.println("Removed : " + deque.removeLast());
        doIteration(deque);
        deque.addLast(3);
        doIteration(deque);
        StdOut.println("Removed : " + deque.removeLast());
        doIteration(deque);
        deque.addFirst(5);
        doIteration(deque);
        deque.addFirst(6);
        doIteration(deque);
        deque.addFirst(7);
        doIteration(deque);
        StdOut.println("Removed : " + deque.removeLast());
        doIteration(deque);
        StdOut.println("Size :" + deque.size());
        doIteration(deque);        
    }
    private static void doIteration(Deque deque){
        StdOut.println("********************");
        Iterator<Integer> it = deque.iterator();
        while (it.hasNext()) {
            StdOut.println("Iterate : " + it.next());
        }
        StdOut.println("--------------------");
    }
}