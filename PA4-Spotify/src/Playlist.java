/* 
 * File:     Playlist.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class will keep track of a playlist of 
 *           songs created by a user
 * Course #: CS 210, Spring 2022
 */

import java.util.LinkedList;
import java.util.List;

public class Playlist {
    private String playlistName;
    private List<Song> songsList;


    public Playlist(String name) {
        this.playlistName = name;
        this.songsList = new LinkedList<Song>();
    }

    public Playlist(String name, List<Song> contents) {
        this.playlistName = name;
        this.songsList.addAll(contents);
    }

    public String getName() {
        return playlistName;
    }

    public void addSong(Song song) {
        songsList.add(song);
    }

    public void play() {
        for (Song song: songsList) {
            song.play();
        }
    }

    public void removeSong(Song song) {
        songsList.remove(song);
    }
}