/* 
 * File:     ArrayStack.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class implements interface class
 *            QueueInterface.java. It implements 
 *            the java queue using linked list.  
 *              
 * Course #: CS 210, Spring 2022
 */

public class ListQueue implements QueueInterface {

    private Node front;
    private Node end;
    private int size;

    /* Constructor */
    public ListQueue() {
        front = null;
        end = null;
        size = 0;
    }

    /* Copy Constructor */
    public ListQueue(ListQueue copy) {
        // Leave as an exercise
        this.front = copy.front;
        this.end = copy.end;
        this.size = copy.size;
    }

    /*
     * Adds an element to the back of the queue.
     * @param value: an int to be added.
     */
    @Override
    public void enqueue(int value) {
        if (front == null) {
            front = new Node(value, null);
            end = front;
        } else {
            end.next = new Node(value, null);
            end = end.next;
        }
        size += 1;     
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
        } else if (size == 1) {
            int result = front.data;
            front = null;
            end = null;
            size --;
            return result;
        } else {
            int result = front.data;
            Node currNode = front.next;
            front = currNode;
            size --;
            return result;
        }  
    }

     /*
     * Returns the top element of the queue
     * without removing it.
     * If the user attempts to peek on an empty queue,
     * returns -1.
     */
    @Override
    public int peek() {
        return front.data;
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
        front = null;
        end = null;
        size = 0;
    }

    /*
     * Compares two instances of the class 
     * and returns true if they both have the 
     * same size and the elements. If not, retruns false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListQueue) {
            ListQueue other = (ListQueue) obj;
            if (size != other.size) {
                return false;
            } 

            Node currNode = front;
            Node currNodeOther = other.front;
            while (currNode != null) {
                if (currNode.data != currNodeOther.data) {
                    return false;
                }
                currNode = currNode.next;
                currNodeOther = currNodeOther.next;
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
        Node currNode = front;
        while (currNode != null) {
            result += currNode.data;
            if (currNode.next != null) {
                result += ",";
            }
            currNode = currNode.next;
        }
        result += "}";
        return result;
    }

    /*
     * This nested class creates
     * linked list data structure
     * which has data and next attributes
     */
    private class Node {
        private int data;
        private Node next;

        public Node() {
            this(0, null);
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    
}
