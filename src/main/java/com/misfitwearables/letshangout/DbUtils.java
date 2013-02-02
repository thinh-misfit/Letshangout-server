package com.misfitwearables.letshangout;

import java.net.UnknownHostException;
import java.util.Vector;

import org.codehaus.jackson.map.ObjectMapper;

import com.misfitwearables.letshangout.checkin.CheckInEntry;
import com.misfitwearables.letshangout.checkin.CheckIns;
import com.misfitwearables.letshangout.checkin.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.util.JSON;

public class DbUtils {

    private static final String host = "localhost";
    private static int port = 27017;
    private static final String DB_NAME = "letshangout";
    public static final String USERS = "users";
    public static final String CHECKINS = "checkins";

    private Mongo m;
    private DB db;
    private DBCollection coll;

    public DbUtils(String collection) {
        initiate(collection);
    }
    
    private void initiate(String collection) {
        try {
            m = new Mongo(host, port);
            db = m.getDB(DB_NAME);
            if (coll == null && collection != null) {
                System.out.println("LOG [DbUtils.DbUtils]: 11"); 
                
                coll = db.getCollection(collection);
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public DbUtils() {
        initiate(null);
    }

    public void close() {
        if (m != null) {
            m.close();
        }
    }

    public Mongo getMongo() {
        return m;
    }

    public DB getDb() {
        return db;
    }

    public DBCollection getColl(String collection) {
        System.out.println("LOG [DbUtils.getColl]: db= "+ db);
        coll = db.getCollection(collection);
        return coll;
    }

    public DBCollection getColl() {
        return coll;
    }

    
    public boolean addUser(User user) {
        coll = getColl(USERS);
        DBObject result = coll.findOne( jsonToDb("{'fbuid': '" + user.getFbuid() +"'}"), null);
        if (result == null) {
            System.out.println("LOG [DbUtils.addUser]: no user");
            coll.save(objectToDb(user));
            return true;
        } else {
            System.out.println("LOG [DbUtils.addUser]: use is there");
            return false;
        }
    }
  
    public boolean addCheckIn(String fbuid, CheckInEntry checkInEntry) {
        coll = getColl(CHECKINS);
        DBObject old = coll.findOne( jsonToDb("{'fbuid': '" + fbuid +"'}"), null);
        if (old == null) {
            System.out.println("LOG [DbUtils.addCheckIn]: no user");
            Vector<CheckInEntry> entries = new Vector<CheckInEntry>();
            entries.add(checkInEntry);
            CheckIns checkIns = new CheckIns(fbuid, entries);
            coll.save(objectToDb(checkIns));
            return true;
        } else {
            System.out.println("LOG [DbUtils.addCheckIn]: update the entries");
            CheckIns checkIns = (CheckIns) dbToObject(old, CheckIns.class);
            checkIns.getCheckins().add(checkInEntry);
            coll.update(new BasicDBObject().append("fbuid",  fbuid), objectToDb(checkIns));
            return true;
        }
    }
    
    public static Object dbToObject(DBObject dbObject, Class classType) {       
        String json = dbToJson(dbObject);
        return jsonToObject(json, classType);
    }
    
    public static DBObject objectToDb(Object object) {
       String json = objectToJson(object);
       return jsonToDb(json);
    }
    
    public static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            System.out.println("LOG [DbUtils.objectToJson]: fail to convert: " + e);
            return null;
        }
    }
    
    public static Object jsonToObject(String json, Class classType) {
        Object result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(json, classType);
        } catch (Exception e) {
            System.out.println("LOG [DbUtils.createObject]: failed to deserialize: "+ e);
        }
        
        return result;
    }
    
    public static DBObject jsonToDb(String json) {
        return (DBObject) JSON.parse(json);
    }
    
   public static String dbToJson(DBObject dbObject) {
        return JSON.serialize(dbObject);
    }
    
}
