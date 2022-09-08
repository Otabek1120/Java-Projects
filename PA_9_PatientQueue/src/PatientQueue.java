/* 
 * File:     PatientQueue.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class creates priority queue of patients
 *           using a mini-heap. 
 *              
 * Course #: CS 210, Spring 2022
 */

public class PatientQueue {
    private Patient[] priorityQueue;
    private final static int INITIALSIZE = 10;
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
    private Patient getLeftChild(int parentIndex) { 
        if (hasLeftChild(parentIndex)) {
            return priorityQueue[getLeftChildIndex(parentIndex)];
        }
        return null;
    }
    private Patient getRightChild(int parentIndex) { 
        if (hasRightChild(parentIndex)) {
            return priorityQueue[getRightChildIndex(parentIndex)];
        }
        return null;
    }
    private Patient getParent(int childIndex) { 
        if (hasParent(childIndex)) {
            return priorityQueue[getParentIndex(childIndex)];
        }
        return null;
    }


    /* Constructor */
    public PatientQueue() {
        this.priorityQueue = new Patient[10];
        this.size = 0;
    }

 
    /* 
     * Adds the given person into the priority
     * queue with the given priority.
     * 
     * @param name: the name of the patient
     * @param priority: the given priority
     */
    public void enqueue(String name, int priority) {
        ensureCapacity();
        priorityQueue[size + 1] = new Patient(name, priority);
        size ++;
        heapUp(size);
    }
    
    /* 
     * Adds the patient Object to the priority
     * queue.
     * 
     * @param patient: a patient object of type Patient
     */
    public void enqueue(Patient patient) {
        ensureCapacity();
        priorityQueue[size + 1] = patient;
        size ++;
        heapUp(size);
    }

    /*
     * Remove the first patient in the queue by replacing the 
     * first patient with the last one in the queue and 
     * return their name as a string. Throws an
     * error if the queue is empty
     */
    public String dequeue() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException(" The queue is empty");
        String retVal = priorityQueue[1].name;
        priorityQueue[1] = priorityQueue[size];
        size --;
        heapDown(1);
        return retVal;
    }
    
     /*
     * Makes sure that the binary head has
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
    public void growHeap() {
        Patient[] newArray = new Patient[2 * priorityQueue.length];
        for (int i = 0; i < priorityQueue.length; i ++) {
            newArray[i] = priorityQueue[i];
        }
        priorityQueue = newArray;
    }

    /*
     * Restores the order of the heap by 
     * bubbling up the newly-added item given
     * by its index if it is out of order.
     * 
     * @param startIndex: the index of the newly-added item
     *              
     */
    private void heapUp(int index) {
        int childNewIndex = index;
        while (hasParent(childNewIndex)) {
            if (getParent(childNewIndex).priority > priorityQueue[childNewIndex].priority) {
                swap(childNewIndex, getParentIndex(childNewIndex));   
            } else if (getParent(childNewIndex).priority == priorityQueue[childNewIndex].priority) {
                if (priorityQueue[childNewIndex].name.compareTo(getParent(childNewIndex).name) == -1) {
                    swap(childNewIndex, getParentIndex(childNewIndex));
                }
            }
            childNewIndex = getParentIndex(childNewIndex);
        }  
    }

    /* 
     * Restores the order of the heap after 
     * a dequeue by bubbling down if the 
     * item at index 1 is out of order.
     */
    private void heapDown(int startIndex) {
        int index = startIndex;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index)) {
                if (getRightChild(index).priority < getLeftChild(index).priority) {
                    smallerChildIndex = getRightChildIndex(index);
                } else if (getRightChild(index).priority == getLeftChild(index).priority) {
                    if (getRightChild(index).name.compareTo(getLeftChild(index).name) < 0) {
                        smallerChildIndex = getRightChildIndex(index);
                    }
                }
            }             
            if (priorityQueue[index].priority > priorityQueue[smallerChildIndex].priority) {
                swap(smallerChildIndex, index);
                index = smallerChildIndex; 
            } else if (priorityQueue[index].priority == priorityQueue[smallerChildIndex].priority) {
                if (priorityQueue[smallerChildIndex].name.compareTo(priorityQueue[index].name) < 0) {
                    swap(smallerChildIndex, index);
                    index = smallerChildIndex;  
                } else {
                    break;
                }
            } else {
                break;
            }
            
        }
    }

    /*
     * Swaps two patients in the queue given by their
     * indexes in the heap. 
     */
    public void swap(int childIndex, int parentIndex) {
        Patient tmp = priorityQueue[parentIndex];
        priorityQueue[parentIndex] = priorityQueue[childIndex];
        priorityQueue[childIndex] = tmp;
    }


    /*
     * Returns the name of the first patient in 
     * the queue without removing or altering it.
     * Throws an exception if the queue is empty.
     */
    public String peek() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException(" The queue is empty!");
        return priorityQueue[1].name;
    }

    /*
     * Returns the priority of the first patient in 
     * the queue without removing or altering it.
     * Throws an exception if the queue is empty.
     */
    public int peekPriority() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException(" The queue is empty!");
        return priorityQueue[1].priority;
    }

    /*
     * Changes the priority of the patient given by the 
     * name in the first occurence. Nothing happens if  
     * the same patient is given the same priority again. 
     * If the name not found, nothing happens.
     * 
     * @param name: the name of the patient
     * @param newPriority: the new priority to be assigned
     */
    public void changePriority(String name, int newPriority) {
        for (int i = 1; i <= size; i ++) {
            if (priorityQueue[i].name.equals(name)) {
                priorityQueue[i].priority = newPriority;
                heapUp(i);
                heapDown(i);
                break;
            }
        }
    }
   
    /*
     * Returns true if the patient queue is empty,
     * false as otherwise.
     */
    public boolean isEmpty() { 
        return size == 0;
    }

    /*
     * Returns the number of patients in the queue.
     */
    public int size() {
        return size;
    }

    /*
     * Removes all the patients from the queue.
     */
    public void clear() {
        size = 0;
        Patient[] tmp = new Patient[10];
        priorityQueue = tmp;
    }

    /*
     * Returns the string representation of the 
     * priority queue of patients
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
}
