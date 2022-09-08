/* 
 * File:     Library.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class keeps track of the library 
 *           of songs in in the Spotify program.
 *           It reads a song file, which is passed as a
 *           command line argument, and stores them in this 
 *           class. 
 * Course #: CS 210, Spring 2022
 */

import java.util.Map;
import java.util.TreeMap;

public class Library {
    private Map<String, Song> titleSongMap;

    /**
     * The Library constructor creates
     * an empty map to store song title and
     * Song object pairs
     */
    public Library() {
        this.titleSongMap = new TreeMap<String, Song>();
    }   
    
    /**
     * returns the Song associated with the String title passed
     * in if it exists in the library, or 
     * null if the song does not exist in the library.
     * @param title: the title of a Song object
     */
    public Song getSong(String title) {
        if (titleSongMap.containsKey(title)) {
            return titleSongMap.get(title);
        } else {
            return null;
        }
    }

    /**
     * This method adds a new title-Song pair
     * to the map
     * @param song: a Song object
     */
    public void addSong(Song song) {
        String title = song.getTitle();
        titleSongMap.put(title, song);
    }

    /**
     * This one removes a Song objects off
     * the title-Song map
     * @param song
     */
    public void removeSong(Song song) {
        String title = song.getTitle();
        if (titleSongMap.containsKey(title)) {
            titleSongMap.remove(title);
        }
    }

    /**
     * This one prints out the string representation
     * of the Library class
     */
    public String toString() {
        String libraryString = "";
        for (String songTitle: titleSongMap.keySet()) {
            String info = titleSongMap.get(songTitle).toString() + "\n";
            libraryString += info;
        }
        return libraryString;
    }
}