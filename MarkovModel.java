//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Markov Text Generator - Markov Model
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

import java.util.HashMap;

/**
 * Generates random text based on a sample input text
 */
public class MarkovModel {
    /**
     * A map of substrings with length windowWidth to stacks containing characters
     * that follow the substring
     */
    private HashMap<String, MyStack<Character>> model;

    /**
     * The current substring with windowWidth number of characters used to predict
     * the next character
     */
    private MyQueue<Character> currentQueue;

    /**
     * The length of each substring used to predict the next character
     */
    private int windowWidth;

    /**
     * Whether the generateText() method will shuffle the stack after accessing it
     */
    private boolean shuffleStacks;

    /**
     * Initializes windowWidth to k and sets shuffleStack based on shuffle
     *
     * @param k the windowWidth
     * @param shuffle whether the generateText() method will shuffle the stack
     */
    public MarkovModel(int k, boolean shuffle) {
        windowWidth = k;
        shuffleStacks = shuffle;
        model = new HashMap<>();
        currentQueue = new MyQueue<>();
    }

    /**
     * Separates the input text into substrings with length of windowWidth and adds all the
     * character following it to its respective stack
     *
     * @param text the input text the method will learn
     */
    public void processText(String text) {
        int startingNum = 0;
        int endingNum = windowWidth - 1;
        char[] characters = text.toCharArray();
        char nextChar;
        String substring;

        while (endingNum + 1 < text.length()) {
            substring = text.substring(startingNum, endingNum + 1);

            for (int i = endingNum + 1; i < text.length(); ++i) {
                nextChar = characters[i];
                model.computeIfAbsent(substring, k -> new MyStack<>()).push(nextChar);
            }
            startingNum = startingNum + windowWidth - 1;
            endingNum = endingNum + windowWidth - 1;
        }
    }

    /**
     * Initializes the queue with the first substring with length windowWidth from the
     * input text
     *
     * @param text the text to initialize the queue
     */
    public void initializeQueue(String text) {
        String firstSubstring = text.substring(0, windowWidth);
        char[] firstSubstringArray = firstSubstring.toCharArray();

        for (char c : firstSubstringArray) {
            currentQueue.enqueue(c);
        }
        currentQueue.maintainSize(windowWidth);
    }

    /**
     * Generates new text based on the learned values from the processText() method.
     * Adds the substring from the currentQueue to the generated string and generates new text
     * based on the new substring in the currentQueue
     *
     * @param length the length of the generated string
     * @param text the text used to re-seed the currentQueue if it is empty or the model doesn't
     *             have a stack for the given substring
     * @return the generated text
     */
    public String generateText(int length, String text) {
        String genString = "";
        genString = genString.concat(currentQueue.toString());

        while (genString.length() <= length) {
            if (model.containsKey(currentQueue.toString())) {
                Character poppedChar = model.get(currentQueue.toString()).pop();
                genString = genString.concat(poppedChar.toString());
                if (shuffleStacks) model.get(currentQueue.toString()).shuffle();
                currentQueue.enqueue(poppedChar);
                currentQueue.maintainSize(windowWidth);
                continue;
            }
            if (!model.containsKey(currentQueue.toString())
                    || model.get(currentQueue.toString()).isEmpty()) {
                initializeQueue(text);
                genString = genString.concat("\n");
            }
        }

        return genString;
    }
}
