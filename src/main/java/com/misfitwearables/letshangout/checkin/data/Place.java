package com.misfitwearables.letshangout.checkin.data;

public class Place extends BaseEntry {

    private String loc;
    private Location location;
    
    public Place(String name, String id, String loc, Location location) {
        super(name, id);
        this.loc = loc;
        this.location = location;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    
}

class Location {
    private String latitude;
    private String longitude;
    
    public Location(String latitude, String longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
