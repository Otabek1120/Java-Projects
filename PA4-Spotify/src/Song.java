/* 
 * File:     Song.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class collects data/methods 
 *           for a song    
 * Course #: CS 210, Spring 2022
 */

public class Song {

    private String title;
    private String artist;
    private int playCount;

    /**
	 * The Song contsructor sets title and artist
     * to params title and artist respectively,
     * and playCount to 0.
	 **/
    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
        this.playCount = 0;
    }

    /**
     * This one returns the title of the song
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This one returns the author of the song
     * @return artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * This one returns the number of times
     * the current song has been played
     * @return playCount
     */
    public int getTimesPlayed() {
        return playCount;
    }

    /**
     * This one prints out the string version 
     * of the song once it is played and increments
     * the number of times it has been played by one after
     * that.
     */
    public void play() {
        System.out.println(toString());
        playCount ++;
    }

    /**
     * This one returns the string version
     * of the song tile followed by the artist
     * @return info 
     */
    public String toString() {
        String info = title + " by " + artist + ", " + 
                        String.valueOf(playCount) + " play(s)";
        return info;

    }
}