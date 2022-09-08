
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/* 
 * File:     WordSearch.java
 * Author:   Otabek Abduraimov
 * Purpose:  This Java class reads in a grid file of characters
                by row x column and a dictionary file of valid words.
                It searches through the grid of letters to find 
                valid words in the grid, and then prints out all 
                the valid words in a specific order.
                The valid words are:
                                    1. length of 3 at least
                                    2. it can be formed from the letters in the grid 
                                       that are adjacent along a row (left-to-right or
                                       right-to-left) or along a column 
                                       (top-to-bottom or bottom-to-top)
                                    3. exists in the dictionary file.
 * Course #: CS 210, Spring 2022
 */

public class WordSearch {
    public static void main(String[] args) throws FileNotFoundException {

        Set<String> wordsSetInDictionary = fileRead(args[0]);
        String[][] wordsOfGrid = wordsOfGridRead(args[1]);
        List<String> createdWords = createPossibleWords(wordsOfGrid);
        List<String> possibleWordsList = possibleWords(createdWords);
        List<String> validWords = validWords(possibleWordsList, wordsSetInDictionary);
        printValidWords(validWords);
  
    }

    public static Set<String> fileRead(String wordsDictionary) throws FileNotFoundException {
        /*
         * :param wordsDictionary:       the name of the file
         * :return dictionaryWords: a TreeSet of all the words in the file
         * This function reads in a dictionary file and stores
         * the words into a TreeSet and returns it.
        */
        Scanner scanner = new Scanner(new File(wordsDictionary));
        Set<String> dictionaryWords = new TreeSet<String>();

        while (scanner.hasNext()) {
            dictionaryWords.add(scanner.nextLine().toLowerCase());
        }
        scanner.close();
   ;
        return dictionaryWords;
    }

    public static String[][] wordsOfGridRead(String fileName) throws FileNotFoundException {
        /*
         * :param fileName:     the of grid file of characters
         * :return charsOfGrid: 2D array of characters in the file
         * This one reads in the grid file into a 2D array.
         * and returns it to the user
        */
        Scanner scanner = new Scanner(new File(fileName));
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        String[][] charsOfGrid = new String[rows][columns];

        while (scanner.hasNext()) {
            for (int i = 0; i < rows; i ++) {
                for (int j = 0; j < columns; j ++) {
                    charsOfGrid[i][j] = scanner.next().toLowerCase();
                }
            }
        }
        scanner.close();

        return charsOfGrid;
    }

    public static List<String> createPossibleWords(String[][] gridChars) {
        /*
         * :param gridChars:      2D array of characters in the grid file 
         * :return possibleChars: a list of strings created with letters
         *                        in each row and column. 
         * This one takes a 2D list of letters in the grid file
         * and makes strings of each line and column. It stores them
         * into list and returns it.
        */
        List<String> possileChars = new LinkedList<String>();

        for (String[] aLine : gridChars) {
            String line = joinChars(aLine);
            possileChars.add(line);
        }
        for (String[] aLine : gridChars) {
            String lineReverse = reverseString(aLine);
            possileChars.add(lineReverse);
        }
        for (int j = 0, m = gridChars[0].length; j < m; j ++) {
            String[] columnChars = new String[gridChars.length];
            int k = 0;
            for (int i = 0, n = gridChars.length; i < n; i ++) {
                columnChars[k] = gridChars[i][j];
                k ++;
            }
            String columnCharsConect = joinChars(columnChars);
            possileChars.add(columnCharsConect);
        }
        for (int j = 0, m = gridChars[0].length; j < m; j ++) {
            String[] columnChars = new String[gridChars.length];
            int k = 0;
            for (int i = 0, n = gridChars.length; i < n; i ++) {
                columnChars[k] = gridChars[i][j];
                k ++;
            }
            String columnCharsReverse = reverseString(columnChars);
            possileChars.add(columnCharsReverse);
        }
        
        return possileChars;
    }

    public static String reverseString(String[] seqOfChars) {
        /*
         * :param seqOfCharts: an array of letters       
         * :return String text: a reversed string
         * This fucntion takes an array of letters 
         * and connects them into a string in the reverse
         * order and returns the string.
        */
        String text = "";
        for (int i = seqOfChars.length; i > 0; i --) {
            text += seqOfChars[i-1];
        }

        return text;
    }

    public static String joinChars(String[] seqOfChars) {
         /*
         * :param seqOfCharts: an array of letters       
         * :return String text: a string
         * This one takes an array of letters 
         * and connects them into a string 
         * and returns the string.
        */
        String text = "";
        for (String aChar : seqOfChars) {
            text += aChar;
        }
        return text;
    }

    public static List<String> possibleWords(List<String> charsList) {
         /*
         * :param List charsList: a list of joined letters of the grid file    
         * :return List<String> possibleWordsList: a list of all the words 
         *                                          that can be made using the grid
         * This function returns a list of all possible strings
         * that can be a valid word
        */
        List<String> possibleWordsList = new LinkedList<String>();

        for (String text : charsList) {
            for (int i = 0, n = text.length(); i < n; i ++) {
                for (int j = i + 3, m = text.length(); j < m + 1; j ++) {
                    String subString = text.substring(i, j);
                    possibleWordsList.add(subString);
                }
            }
        }
    
        return possibleWordsList;
    }

    public static List<String> validWords(List<String> possibleWords, Set<String> dictionary) {
        /*
         * :param possibleWords: a list of all strings in the grid
         * :param dictionary: a set of all the words in the dictionary file
         * :return validWordsList: a list of all the valid words
         * This function checks whether the string in the possibleWords
         * list is a valid and if so, stores it into a list. 
        */
        List<String> validWordsList = new LinkedList<String>();
        for (String word : possibleWords) {
            // System.out.println(word);
            if (dictionary.contains(word)) {
                validWordsList.add(word);
            }
        }
        return validWordsList;
    }

    public static void printValidWords(List<String> allValidWordsList) {
        /*
         * :param allValidWordsList: a list of all the valid words in the grid
         * return: Null
         * It prints out all the valid words in the grid
         * one per line.
        */
        for (String validWord : allValidWordsList) {
            System.out.println(validWord);
        }
    }
}
