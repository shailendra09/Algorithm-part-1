/******************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque
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

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;
    
    public Deque() {
        // construct an empty deque
        size = 0;
        first = null;
        last = first;
    }
    
    public boolean isEmpty() {
        // is the deque empty?
        if (size == 0) {
            return true;
        }
        return false;
    }
    
    public int size() {
        // return the number of items on the deque
        return size;
    }
    
    public void addFirst(Item value) {
        // add the item to the front
        if (value == null) {
            throw new java.lang.NullPointerException();
        }
        size++;
        Node<Item> newFirst = new Node<>();
        newFirst.item = value;
        if (first == null) {
            first = newFirst;
            last = newFirst;
        }
        else {
            newFirst.next = first;
            first.previous = newFirst;
            first = newFirst;
        }
    }
    
    public void addLast(Item value) {
        // add the item to the end
        if (value == null) {
            throw new java.lang.NullPointerException();
        }
        size++;
        Node<Item> newLast = new Node<>();
        newLast.item = value;
        if (last == null) {
            last = newLast;
            first = newLast;
        }
        else {
            last.next = newLast;
            newLast.previous = last;
            last = newLast;
        }     
    }
    
    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        size--;
        Node<Item> oldFirst = first;
        if (size == 0) {
            last = null;
            Item temp = first.item;
            first = null;
            return temp;
        }
        else {
            first = first.next;
            first.previous = null;
            return oldFirst.item;
        }        
    }
    
    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        size--;
        Node<Item> oldLast = last;
        if (size == 0) {
            first = null;
            Item temp = last.item;
            last = null;
            return temp;
        }
        else {
            last = last.previous;
            last.next = null;
            return oldLast.item;
        }
    }
    
    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }
    
    public static void main(String[] args) {
        // unit testing (optional)
        
    }
    
    private class Node<Item> {
        private Item item = null;
        private Node<Item> previous = null;
        private Node<Item> next = null;
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
            // Not supported
            throw new java.lang.UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}