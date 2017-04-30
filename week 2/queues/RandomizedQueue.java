/******************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue
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
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
// import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private int size;
    private Item[] item;
    public RandomizedQueue() {
        n = 1;
        size = 0;
        item = (Item[]) new Object[1];
    }
    
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item newItem) {
        if (newItem == null) {
            throw new java.lang.NullPointerException();
        }
        if (n == size) {
            n = 2*n;
            Item[] newArray = (Item[]) new Object[n];
            for (int i = 0; i < size; ++i) {
                newArray[i] = this.item[i];
            }
            this.item = newArray;
        }
        this.item[size++] = newItem;
    }
    
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        if (size == n/4) {
            n = n/2;
            Item[] newArray = (Item[]) new Object[n];
            for (int i = 0; i < size; ++i) {
                newArray[i] = this.item[i];
            }
            this.item = newArray;
        }
        int randomPosition = StdRandom.uniform(size);
        Item removedItem = item[randomPosition];
        item[randomPosition] = item[--size];
        item[size] = null;
        return removedItem;
    }
    
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return item[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }
    
    public static void main(String[] args) {
        
    }
    
    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] iteratorItemArray;
        private int cursor;
        private int iteratorItemArraySize;
        RandomQueueIterator() {
            cursor = 0;
            iteratorItemArraySize = size;
            iteratorItemArray = item.clone();
            if (size != 0) {
                StdRandom.shuffle(iteratorItemArray, 0, size);
            }
            /* for( int i = 0; i < iteratorItemArray.length; ++i) {
                StdOut.println("Actual :: " + item[i] + "  " + iteratorItemArray[i]);
            } */
        }
        
        public boolean hasNext() {
            return cursor < iteratorItemArraySize;
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return iteratorItemArray[cursor++];
        }
        
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }        
    }    
}