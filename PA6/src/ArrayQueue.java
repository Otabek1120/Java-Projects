/* 
 * File:     ArrayStack.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class implements interface class
 *            QueueInterface.java. It implements 
 *            the java queue using arrays.  
 *              
 * Course #: CS 210, Spring 2022
 */

public class ArrayQueue implements QueueInterface {

    private static final int DEFAULT_SIZE = 10;
    private int[] arrayQueue;
    private int size; 

    /* Constructor */
    public ArrayQueue() {
        size = 0;
        arrayQueue = new int[DEFAULT_SIZE];
    }

     /* Copy Constructor */
     public ArrayQueue(ArrayQueue copy) {
        // Leave as an exercise
        this.size = copy.size;
        this.arrayQueue = copy.arrayQueue;
    }

    /*
     * This method grows the array used to 
     * build the queue
     */
    private void growArrayStack() {
        int[] newArrayQueue = new int[2 * size];
        for (int i = 0; i < size; i ++) {
            newArrayQueue[i] = arrayQueue[i];
        }
        arrayQueue = newArrayQueue;
    }

    /*
     * Adds an element to the back of the queue.
     * @param value: an int to be added
     */
    @Override
    public void enqueue(int value) {
        if (size >= arrayQueue.length) {
            growArrayStack();
        }
        arrayQueue[size] = value;
        size ++; 
    }

    /*
     * Removes and returns the front element 
     * in the queue unless the queue is empty.
     * If not, it returns -1.
     */
    @Override
    public int dequeue() {
        if (size == 0) {
            return -1;
        }
        int firstElem = arrayQueue[0];
        for (int i = 0; i < size; i++) {
            arrayQueue[i] = arrayQueue[i + 1];
        }
        size --;
        return firstElem;
    }

    /*
     * Returns the top element of the queue
     * without removing it.
     * If the user attempts to peek on an empty queue,
     * returns -1.
     */
    @Override
    public int peek() {
        if (size == 0) {
            return -1;
        }
        return arrayQueue[0];
    }

    /*
     * Returns true if the queue has no elements
     * and false as otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

     /*
     * Returns the number of elements in the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Removes all elements from the queue.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i ++) {
            arrayQueue[i] = arrayQueue[arrayQueue.length - 1];
        }
        size = 0;
    }

    /*
     * Compares two instances of the class 
     * and returns true if they both have the 
     * same size and the elements. If not, retruns false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayQueue) {
            ArrayQueue other = (ArrayQueue) obj;
            if (size != other.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (arrayQueue[i] != other.arrayQueue[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /*
     * Returns the string reresentation
     * of the object.
     */
    @Override
    public String toString() {
        String result = "{";
        for (int i = 0; i < size; i ++) {
            result += arrayQueue[i];
            if (i != size - 1)
                result += ",";
        }
        result += "}";
        return result;
    }
}
