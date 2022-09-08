/* 
 * File:     MaxPQ.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class creates priority queue of Tupels 
 *           of ladder and priority using a binary max-heap. 
 *              
 * Course #: CS 210, Spring 2022
 */

import java.util.List;

public class MaxPQ {
    private Tuple[] priorityQueue;
    private final static int INITIAL_SIZE = 10;
    private int size;
    
    /* 
     * These private functions return the 
     * index of left child, right child, and 
     * parent respectively.
     */
    private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex; }
    private int getRightChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
    private int getParentIndex(int childIndex) { return childIndex / 2; }

    /* 
     * These private functions return  
     * true if left child exits, right child exits, and 
     * parent exists respectively.
     */
    private boolean hasLeftChild(int parentIndex) { 
                    return getLeftChildIndex(parentIndex) <= size; }
    private boolean hasRightChild(int parentIndex) { 
                    return getRightChildIndex(parentIndex) <= size; }
    private boolean hasParent(int childIndex) {
                    return getParentIndex(childIndex) >= 1; }
    
    /* 
     * These private functions return  
     * left child, right child, and 
     * parent respectively if they exist. 
     * Return null if not.
     */
    private Tuple getLeftChild(int parentIndex) { 
        if (hasLeftChild(parentIndex)) {
            return priorityQueue[getLeftChildIndex(parentIndex)];
        }
        return null;
    }
    private Tuple getRightChild(int parentIndex) { 
        if (hasRightChild(parentIndex)) {
            return priorityQueue[getRightChildIndex(parentIndex)];
        }
        return null;
    }
    private Tuple getParent(int childIndex) { 
        if (hasParent(childIndex)) {
            return priorityQueue[getParentIndex(childIndex)];
        }
        return null;
    }

    /* Constructor */
    public MaxPQ() {
        this.priorityQueue = new Tuple[INITIAL_SIZE];
        this.size = 0;
    }
    
    /* 
     * Creates a Tuple object of ladder and 
     * priority and adds it to the queue
     * by the priority.
     * 
     * @param ladder: a list of valid wiki links 
     *                that take from one page to another
     * @param priority: the given priority
     */
    public void enqueue(List<String> ladder, int priority) {
        ensureCapacity();
        priorityQueue[size + 1] = new Tuple(ladder, priority);
        size ++;
        heapUp(size);
    }
    

    /*
     * Remove the first patient in the queue by replacing the 
     * first Tuple with the last one in the queue and 
     * return its ladder. Throws an
     * error if the queue is empty
     */
    public List<String> dequeue() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException(" The queue is empty");
        List<String> retVal = priorityQueue[1].getLadder();
        priorityQueue[1] = priorityQueue[size];
        size --;
        heapDown(1);
        return retVal;
    }
    
     /*
     * Makes sure that the binary heap has
     * the enough size. If not, grows the heap.
     */
    private void ensureCapacity() {
        if (size >= priorityQueue.length - 1) {
            growHeap();
        } 
    }

    /* 
     * Grows the size of a binary heap 
     */
    private void growHeap() {
        Tuple[] newArray = new Tuple[2 * priorityQueue.length];
        for (int i = 0; i < priorityQueue.length; i ++) {
            newArray[i] = priorityQueue[i];
        }
        priorityQueue = newArray;
    }

    /*
     * Restores the order of the max-heap by 
     * bubbling up the newly-added item 
     * by its priority if it is out of order.
     * 
     * @param startIndex: the index of the newly-added item           
     */
    private void heapUp(int index) {
        int childNewIndex = index;
        while (hasParent(childNewIndex)) {
            if (getParent(childNewIndex).priority < priorityQueue[childNewIndex].priority) {
                swap(childNewIndex, getParentIndex(childNewIndex));   
            } else if (getParent(childNewIndex).priority == priorityQueue[childNewIndex].priority) {

                if (priorityQueue[childNewIndex].getLastPage().compareTo(getParent(childNewIndex).getLastPage()) == -1) {
                    swap(childNewIndex, getParentIndex(childNewIndex));
                }
            }
            childNewIndex = getParentIndex(childNewIndex);
        }   
    }

    /* 
     * Restores the order of the heap after 
     * a dequeue by bubbling down if the 
     * Tuple at index 1 is out of order.
     */
    private void heapDown(int startIndex) {
        int index = startIndex;
        while (hasLeftChild(index)) {
            int biggerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index)) {
                if (getRightChild(index).priority > getLeftChild(index).priority) {
                    biggerChildIndex = getRightChildIndex(index);
                } 
            }             
            if (priorityQueue[index].priority < priorityQueue[biggerChildIndex].priority) {
                swap(biggerChildIndex, index);
                index = biggerChildIndex; 
            } else if (priorityQueue[index].priority == priorityQueue[biggerChildIndex].priority) {
                if (priorityQueue[biggerChildIndex].getLastPage().compareTo(priorityQueue[index].getLastPage()) < 0) {
                    swap(biggerChildIndex, index);
                    index = biggerChildIndex;  
                } else {
                    break;
                }
            } else {
                break;
            } 
        }
    }

    /*
     * Swaps two Tuples in the queue given by their
     * indexes. 
     */
    private void swap(int childIndex, int parentIndex) {
        Tuple tmp = priorityQueue[parentIndex];
        priorityQueue[parentIndex] = priorityQueue[childIndex];
        priorityQueue[childIndex] = tmp;
    }

    
    /*
     * Returns the ladder of the first Tuple in 
     * the queue without removing or altering it.
     * Throws an exception if the queue is empty.
     */
    public List<String> peek() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException(" The queue is empty!");
        return priorityQueue[1].getLadder();
    }

    /*
     * Returns the priority of the first Tuple in 
     * the queue without removing or altering it.
     * Throws an exception if the queue is empty.
     */
    public int peekPriority() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException(" The queue is empty!");
        return priorityQueue[1].priority;
    }
 
    /*
     * Returns true if the queue is empty,
     * false as otherwise.
     */
    public boolean isEmpty() { 
        return size == 0;
    }

    /*
     * Returns the size of the priority queue.
     */
    public int size() {
        return size;
    }

    /*
     * Removes all the items from the queue.
     */
    public void clear() {
        size = 0;
        Tuple[] tmp = new Tuple[INITIAL_SIZE];
        priorityQueue = tmp;
    }

    /*
     * Returns the string representation of the 
     * priority queue.
     */
    public String toString() {
        String result = "{";
        for (int i = 1; i < size + 1; i ++) {
            result += priorityQueue[i].toString();
            if (i != size) {
                result += ", ";
            }
        }
        result += "}";

        return result;
    }


    /* This private class creates a Tuple 
     * of ladder and its priority  
     */
    private class Tuple {
        private List<String> ladder;
        private int priority;

        /* Constructor */
        public Tuple(List<String> ladder2, int priority) {
            this.ladder = ladder2;
            this.priority = priority;
        }

        /*
         * Returns the ladder 
         */
        public List<String> getLadder() {
            return ladder;
        }

        public String getLastPage() {
            return ladder.get(ladder.size() - 1);
        }

         /*
         * Returns the string representation
         * of the Tuple object.
         */
        public String toString() {
            return "(" + ladder + "," + priority + ")";
        }

    }

}


