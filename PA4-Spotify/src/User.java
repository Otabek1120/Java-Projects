/* 
 * File:     User.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class is responsible for
 *           dealing with user data, like logging in,
 *           logging out, password, username and so on
 * Course #: CS 210, Spring 2022
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class User {

    private String userName;
    private String userPassword;
    private Map<String, Playlist> namePlaylistMap;

    public User(String name, String password) {
        this.userName = name;
        this.userPassword = password;
        this.namePlaylistMap = new TreeMap<String, Playlist>();
    }

    public String getName() {
        return userName;
    }

    public boolean attemptLogin(String password) {
        if (userPassword.equals(password)) {
            return true;
        }
        return false;
    }

    public void addPlaylist(Playlist newPlaylist) {
        String name = newPlaylist.getName();
        namePlaylistMap.put(name, newPlaylist);
    }

    public List<Playlist> getPlaylists() {
        List<Playlist> playlistsList = new LinkedList<Playlist>();
        for (String name: namePlaylistMap.keySet()) {
            playlistsList.add(namePlaylistMap.get(name));
        }
        return playlistsList;
    }

    public void selectPlaylist(String name) {
        if (namePlaylistMap.containsKey(name)) {
            namePlaylistMap.get(name).play();
        }
    }

    public String toString() {
        String result = "";
        result = userName + ", " + 
                 String.valueOf(namePlaylistMap.size()) + 
                 " playlists";
        return result;
    }


}

    