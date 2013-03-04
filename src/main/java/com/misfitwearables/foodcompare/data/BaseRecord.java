package com.misfitwearables.foodcompare.data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BaseRecord {

    private String _id;
//    private String hash;
    
    public BaseRecord(String hash) {
        if (hash == null || hash.equalsIgnoreCase("")) {
            this._id = calculateHash();
        } else {
            this._id = hash;
        }
    }
    
    protected String calculateHash() {
        String pass = String.valueOf(System.currentTimeMillis()) + String.valueOf(Math.random() * 1000);
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            byte[] data = pass.getBytes();
            m.update(data, 0, data.length);
            BigInteger i = new BigInteger(1, m.digest());
            return String.format("%1$032X", i);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
    
    
    
}
