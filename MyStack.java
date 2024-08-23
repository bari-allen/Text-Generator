//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Markov Text Generator - My Stack
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
import java.util.Collections;

/**
 * A generic, singly-linked Stack which contains extra methods to assist with the
 * Markov model text generation
 *
 * @param <T> a generic input
 * @author karl haidinyak
 */
public class MyStack<T> implements StackADT<T> {

    /**
     * A reference to the node at the top of the stack (added most recently)
     */
    private LinkedNode<T> top;

    /**
     * Returns the elements in the stack in an ArrayList
     *
     * @return the elements in the stack in an ArrayList
     */

    public ArrayList<T> getList() {
        LinkedNode<T> iteratedItem = top;
        ArrayList<T> itemList = new ArrayList<>();

        while (iteratedItem != null) {
            itemList.add(iteratedItem.getData());
            iteratedItem = iteratedItem.getNext();
        }

        return itemList;
    }

    /**
     * Shuffles all the elements in the stack into a new arrangement
     */
    public void shuffle() {
        ArrayList<T> originalList = getList();
        ArrayList<T> shuffledList = getList();
        LinkedNode<T> iteratedItem = top;

        Collections.shuffle(shuffledList);

        if (!shuffledList.isEmpty()) {
            top = new LinkedNode<>(shuffledList.get(shuffledList.size() - 1));
            iteratedItem = top;
            for (int i = shuffledList.size() - 2; i >= 0; --i) {
                top = new LinkedNode<>(shuffledList.get(i));
                top.setNext(iteratedItem);
                iteratedItem = top;

            }
        }
    }

    /**
     * Adds the new element to the top of the stack
     *
     * @param value the value to add to the top of the stack
     */
    public void push(T value) {
        LinkedNode<T> savedTop = top;
        top = new LinkedNode<>(value);
        top.setNext(savedTop);
    }

    /**
     * Removes the element at the top of the stack and returns it
     *
     * @return the element at the top of the stack or null if the stack is empty
     */
    public T pop() {
        if (isEmpty()) return null;

        T topValue = top.getData();
        top = top.getNext();
        return topValue;
    }

    /**
     * Returns the element at the top of the stack or null if the stack is empty
     *
     * @return the element at the top of the stack or null if the stack is empty
     */
    public T peek() {
        if (isEmpty()) return null;

        return top.getData();
    }

    /**
     * Returns whether the stack is empty by checking whether the top of the stack is set to null
     *
     * @return true if the stack is empty or false otherwise
     */

    public boolean isEmpty() {
        return top == null;
    }
}
