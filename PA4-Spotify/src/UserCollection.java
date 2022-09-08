/* 
 * File:     User.java
 * Author:   Otabek Abduraimov
 * Purpose:  This class tracks all of the users in Spotify
 *           their login credentials, and adding users.
 * Course #: CS 210, Spring 2022
 */


import java.util.Map;
import java.util.TreeMap;

public class UserCollection {
    private Map<String, User> usernameUserMap;

    public UserCollection() {
        this.usernameUserMap = new TreeMap<String, User>();
    }

    public boolean userExists(String username) {
        if (usernameUserMap.containsKey(username)) {
            return true;
        } 
        return false;
    }

    public User login(String username, String password) {
        if (usernameUserMap.containsKey(username)) {
            if (usernameUserMap.get(username).attemptLogin(password)) {
                return usernameUserMap.get(username);
            } 
        }
        return null;
    }

    public void addUser(User add) {
        String name = add.getName();
        usernameUserMap.put(name, add);
    }

    public String toString() {
        String result = "{ ";
        for (String userName : usernameUserMap.keySet()) {
            String name = userName;
            int playlistNum = usernameUserMap.get(userName).getPlaylists().size();
            String userInfo = name + ": " + 
                                String.valueOf(playlistNum) + 
                                " playlist(s), ";
            result += userInfo;
        }
        result += "}";
        return result;
    }

}