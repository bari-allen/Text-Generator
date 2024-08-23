//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Markov Text Generator - Tester Methods
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
 * Tests the implementation of the MyStack and MyQueue classes
 */

public class MarkovTester {

    /**
     * Tests the functionality of the push() method in the MyStack class
     *
     * @return true if all the tests pass or false if any of the tests fail
     */
    public static boolean testStackAdd() {
        MyStack<String> testStack = new MyStack<>();
        String test = "test string";
        String helloWorld = "hello world";
        String endString = "this is the end";

        testStack.push(endString);
        testStack.push(test);
        testStack.push(helloWorld);
        ArrayList<String> testList = testStack.getList();

        if (testList.size() != 3) return false;
        if (!testList.get(0).equals(helloWorld)) return false;
        if (!testList.get(1).equals(test)) return false;
        if (!testList.get(2).equals(endString)) return false;

        return true;
    }

    /**
     * Tests the functionality of the pop() method in the MyStack class
     *
     * @return true if all the tests pass or false if any of the tests fail
     */
    public static boolean testStackRemove() {
        MyStack<String> testStack = new MyStack<>();
        String test = "test string";
        String helloWorld = "hello world";
        String endString = "this is the end";

        testStack.push(endString);
        testStack.push(test);
        testStack.push(helloWorld);

        if (!testStack.pop().equals(helloWorld)) return false;
        if (!testStack.pop().equals(test)) return false;
        if (!testStack.pop().equals(endString)) return false;

        return true;
    }

    /**
     * Tests the functionality of the shuffle() method iun the MyStack class
     *
     * @return true if all the tests pass or false if any of the tests fail
     */
    public static boolean testStackShuffle() {
        MyStack<String> testStack = new MyStack<>();
        ArrayList<String> originalList;
        ArrayList<String> shuffledList;
        String test = "test string";
        String helloWorld = "hello world";
        String endString = "this is the end";
        boolean matchFound = false;

        testStack.push(endString);
        testStack.push(test);
        testStack.push(helloWorld);
        originalList = testStack.getList();
        testStack.shuffle();
        shuffledList = testStack.getList();

        if (shuffledList.size() != originalList.size()) return false;

        for (String originalString : originalList) {
            if (!shuffledList.contains(originalString)) return false;
        }

        if (shuffledList.get(0).equals(originalList.get(0))
                && shuffledList.get(1).equals(originalList.get(1))
                && shuffledList.get(2).equals(originalList.get(2))) return false;

        return true;
    }

    /**
     * Tests the functionality of the enqueue() method in the MyQueue class
     *
     * @return true if all the tests pass or false if any of the tests fail
     */
    public static boolean testQueueAdd() {
        MyQueue<String> testQueue = new MyQueue<>();
        String compSci = "Comp Sci 300";
        String testString = "This is a test string";
        String randomString = "hfjkkfds";

        testQueue.enqueue(compSci);
        testQueue.enqueue(testString);
        testQueue.enqueue(randomString);
        ArrayList<String> queueList = testQueue.getList();

        if (testQueue.isEmpty()) return false;
        if (testQueue.size() != 3) return false;
        if (!queueList.get(0).equals(compSci)) return false;
        if (!queueList.get(1).equals(testString)) return false;
        if (!queueList.get(2).equals(randomString)) return false;

        return true;
    }

    /**
     * Tests the functionality of the dequeue() method in the MyQueue class
     *
     * @return true if all the tests pass or false if any of the tests fail
     */
    public static boolean testQueueRemove() {
        MyQueue<String> testQueue = new MyQueue<>();
        String compSci = "Comp Sci 300";
        String testString = "This is a test string";
        String randomString = "hfjkkfds";

        testQueue.enqueue(randomString);
        testQueue.enqueue(compSci);
        testQueue.enqueue(testString);

        testQueue.dequeue();
        testQueue.dequeue();
        ArrayList<String> queueItems = testQueue.getList();

        if (testQueue.size() != 1) return false;
        if (queueItems.size() != testQueue.size()) return false;
        if (!queueItems.get(0).equals(testString)) return false;

        return true;
    }

    /**
     * Tests the functionality of the peek() methods in the MyStack and MyQueue classes
     *
     * @return true if all the tests pass or false if any of the tests fail
     */
    public static boolean testPeek() {
        {
            MyStack<String> testStack = new MyStack<>();
            String testOne = "test";
            String testTwo = "TEST";
            testStack.push(testOne);
            testStack.push(testTwo);
            ArrayList<String> originalStack = testStack.getList();

            String peekedItem = testStack.peek();
            ArrayList<String> peekedStack = testStack.getList();

            if (!peekedItem.equals(testTwo)) return false;
            if (!originalStack.equals(peekedStack)) return false;
        }

        {
            MyQueue<String> testQueue = new MyQueue<>();
            String testOne = "test";
            String testTwo = "TEST";
            testQueue.enqueue(testOne);
            testQueue.enqueue(testTwo);
            ArrayList<String> originalQueue = testQueue.getList();

            String peekedItem = testQueue.peek();
            ArrayList<String> peekedQueue = testQueue.getList();

            if (!peekedItem.equals(testOne)) return false;
            if (!originalQueue.equals(peekedQueue)) return false;
        }

        return true;
    }

    /**
     * Runs all the tests and prints out the results
     *
     * @param args (unused)
     */

    public static void main(String[] args) {
        System.out.println("My Tests:");
        System.out.println("testStackAdd(): " + testStackAdd());
        System.out.println("testStackRemove(): " + testStackRemove());
        System.out.println("testStackShuffle(): " + testStackShuffle());
        System.out.println("testQueueAdd(): " + testQueueAdd());
        System.out.println("testQueueRemove(): " + testQueueRemove());
        System.out.println("testPeek(): " + testPeek());
    }
}
