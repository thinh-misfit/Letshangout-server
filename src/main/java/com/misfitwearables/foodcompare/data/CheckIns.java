package com.misfitwearables.foodcompare.data;

import java.util.Vector;

public class CheckIns {
    
    private Object _id;
    private String fbuid;
    private Vector<CheckInEntry> checkins = new Vector<CheckInEntry>();
    
    public CheckIns() {
    }
    
    public CheckIns(String fbuid, Vector<CheckInEntry> checkins) {
        super();
        this.fbuid = fbuid;
        this.checkins = checkins;
    }
    
    public String getFbuid() {
        return fbuid;
    }
    public void setFbuid(String fbuid) {
        this.fbuid = fbuid;
    }
    public Vector<CheckInEntry> getCheckins() {
        return checkins;
    }
    public void setCheckins(Vector<CheckInEntry> checkins) {
        this.checkins = checkins;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }
    
}
