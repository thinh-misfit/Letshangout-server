package com.misfitwearables.foodcompare.data;

import java.util.Vector;

public class Entry extends BaseRecord {

    private String name;
    private String photo;
    private String description;
    
    private int likes;
    private int healthies;
    private long calories;
    private String hash;
    private Vector<String> tags;

    public Entry() {
        super(null);
    }

    public Entry(String name, String photo, String description, int likes, int healthies, long calories, String hash, Vector<String> tags) {
        super(hash);
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.likes = likes;
        this.healthies = healthies;
        this.calories = calories;
        
        tags = new Vector<String>(tags);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getHealthies() {
        return healthies;
    }

    public void setHealthies(int healthies) {
        this.healthies = healthies;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }
    

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Vector<String> getTags() {
        return tags;
    }

    public void setTags(Vector<String> tags) {
        this.tags = tags;
    }


}
