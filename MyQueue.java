//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Markov Text Generator - My Queue
// Course:   CS 300 Spring 2024
//
// Author:   Karl Haidinyak
// Email:    haidinyak@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * A generic, singly-linked queue which contains extra methods to assist with the
 * Markov model text generation
 *
 * @param <T> a generic input
 * @author karl haidinyak
 */
public class MyQueue<T> implements QueueADT<T> {

    /**
     * The reference that points to the node in the back of the queue (added most recently)
     */
    private LinkedNode<T> back;

    /**
     * The reference that points to the node in the front of the queue (added least recently)
     */
    private LinkedNode<T> front;

    /**
     * The amount of elements inside the queue
     */
    private int size;

    /**
     * Sets the maximum number of elements that can be inside the queue. If the size of the queue
     * is less than the input size, then this method does nothing. If the size of the queue is
     * larger than the input size, then this method dequeues the queue until the size of the
     * queue equals the input size.
     *
     * @param size the maximum number of elements in the queue
     */
    public void maintainSize(int size) {
        if (this.size < size) return;
        while (this.size > size) {
            dequeue();
        }
    }

    /**
     * Returns the elements in the queue in an ArrayList
     *
     * @return the elements in the queue in an ArrayList
     */

    public ArrayList<T> getList() {
        LinkedNode<T> iteratedNode = front;
        ArrayList<T> nodeList = new ArrayList<>();

        for (int i = 0; i < size; ++i) {
            nodeList.add(iteratedNode.getData());
            iteratedNode = iteratedNode.getNext();
        }

        return nodeList;
    }

    /**
     * Returns the String representation of the queue
     *
     * @return the String representation of the queue
     */

    @Override
    public String toString() {
        String returnedString = "";
        LinkedNode<T> iteratedNode = front;

        for (int i = 0; i < size; ++i) {
            returnedString = returnedString.concat(iteratedNode.getData().toString());
            iteratedNode = iteratedNode.getNext();
        }

        return returnedString;
    }

    /**
     * Adds the inputted element to the queue and increases the size of the queue by 1.
     *
     * @param value the value to add to the back of the queue
     */
    public void enqueue(T value) {
        LinkedNode<T> nextNode = new LinkedNode<>(value);

        //if the queue is empty, then the front and back references are
        //set to the inputted value
        if (isEmpty()) {
            front = nextNode;
            back = nextNode;
            front.setNext(back);
            ++size;
            return;
        }

        back.setNext(nextNode);
        back = nextNode;
        ++size;

    }

    /**
     * Removes and returns the front element of the queue or returns null if the queue is empty
     *
     * @return the front element of the queue or null if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) return null;

        T leastRecentValue = front.getData();
        front  = front.getNext();
        --size;

        return leastRecentValue;
    }

    /**
     * Returns the front element of the queue or null if the queue is empty
     *
     * @return the front element of the queue or null if the queue is empty
     */
    public T peek() {
        if (isEmpty()) return null;

        return front.getData();
    }

    /**
     * Returns whether the queue is empty by checking that the size is 0 and the front and back
     * elements are set to null.
     *
     * @return whether the queue is empty
     */

    public boolean isEmpty() {
        return size == 0 && front == null && back == null;
    }

    /**
     * Returns the number of elements in the queue
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return this.size;
    }
}
