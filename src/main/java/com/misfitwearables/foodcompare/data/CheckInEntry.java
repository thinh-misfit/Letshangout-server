package com.misfitwearables.foodcompare.data;

import com.misfitwearables.letshangout.data.entries.Place;

public class CheckInEntry {
    
    private String id;
    private Place place;
    private String created_time;
    
    public CheckInEntry() {
        
    }
    public CheckInEntry(String id, Place place, String created_time) {
        super();
        this.id = id;
        this.place = place;
        this.created_time = created_time;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }
    public String getCreated_time() {
        return created_time;
    }
    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}


