package com.misfitwearables.foodcompare.data;

import java.util.Vector;

public class VoteEntry extends BaseRecord {
    
    private Vector<Entry> entries;
    
    public VoteEntry() {
        super(null);
    }

    public VoteEntry(Vector<Entry> entries, String hash) {
        super(hash);
        this.entries = entries;
    }

    public Vector<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Vector<Entry> entries) {
        this.entries = entries;
    }

}
