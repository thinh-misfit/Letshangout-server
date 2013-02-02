package com.misfitwearables.letshangout.checkin;

import java.util.Vector;


public class User {
    String fbuid = "a234";
    String name = "Some name";
    
    Vector<String> friends = new Vector<String>();
        
    public User(String fbuid, String name, Vector<String> friends) {
        this.fbuid = fbuid;
        this.name = name;
        this.friends.addAll(friends);
    }
    
    
    public String getFbuid() {
        return fbuid;
    }
    public void setFbuid(String fbuid) {
        this.fbuid = fbuid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public Vector<String> getFriends() {
        return friends;
    }


    public void setFriends(Vector<String> friends) {
        this.friends = friends;
    }
    
    
}
