package com.misfitwearables.foodcompare;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import net.sourceforge.htmlunit.corejs.javascript.ObjToIntMap;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.misfitwearables.foodcompare.data.Categories;
import com.misfitwearables.foodcompare.data.Category;
import com.misfitwearables.foodcompare.data.Entry;
import com.misfitwearables.foodcompare.data.User;
import com.misfitwearables.foodcompare.data.VoteEntry;

@Path("/")
public class FoodCompare {

    Logger logger = Logger.getLogger(FoodCompare.class);

    @Context
    private UriInfo context;

    private int results = 1;

    public FoodCompare() {
    }

    // To test if the service is up and running
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHello() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateNow = dateFormat.format(date);

        System.out.println("LOG [FoodCompareServer.sayHello]: " + results++);

        logger.info("------------------------- Up and running  --------------------");
        return "<html> " + "<title>" + "Hello from Letshangout" + "</title>" + "<body><h1>"
                + "Hello from FoodCompare <br/>Time = " + dateNow + "</body></h1>" + "</html> ";
    }

    // @GET
    // @Path("/getUsers")
    // @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
    // MediaType.TEXT_HTML })
    // public String getUsers(@Context UriInfo ui) {
    // logger.info("Get all users");
    //
    // return "{'username': 'password'}";
    // }

    @GET
    @Path("/categories")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML })
    public String getCategories(@Context UriInfo ui) {
        logger.info("Get all categories");
        DbUtils db = new DbUtils(DbUtils.CATEGORIES);

        String result = db.getCategories();
        return result;
    }

    @GET
    @Path("/getimages")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML })
    public String getImages(@Context UriInfo ui) {
        logger.info("Get  images");
        String category = "";
        try {
            MultivaluedMap<String, String> map = ui.getQueryParameters();
            category = map.get("category").get(0);
        } catch (Exception e) {
        }
        
        if (category == null || category.isEmpty()) {
            return "{'error': 'No category chosen'}";
        }

//        DbUtils db = new DbUtils(DbUtils.ENTRIES);
//
//        Entry entry = db.getOneEntries();
//        Vector<Entry> entries = new Vector<Entry>();
//            
//        entries.add(entry);
//        entries.add(entry);
//        VoteEntry vote = new VoteEntry(entries, null);
//        
//        
//        String result = DbUtils.objectToJson(vote);
//        return result;
        return testGetImages();
    }
    
    private String testGetImages2() {
        DbUtils db = new DbUtils(DbUtils.ENTRIES);

        Entry entry = db.getOneEntries();
        Vector<Entry> entries = new Vector<Entry>();

        entries.add(entry);
        entries.add(entry);
        VoteEntry vote = new VoteEntry(entries, null);

        String result = DbUtils.objectToJson(vote);
        return result;
    }
    
    private String testGetImages() {
        Vector<Entry> entries = new Vector<Entry>();
        
        for (int i = 1; i < 3; i++) {
            Vector<String> tags = new Vector<String>();
            tags.add("tag1");
            tags.add("tag2");
            tags.add("tag3");
            Entry entry = new Entry("Entry " + i, "URL " + i, "Description " + i, i, i, i * 100, null, tags);
            entries.add(entry);
        }
        
        VoteEntry vote = new VoteEntry(entries, null);
        String result = DbUtils.objectToJson(vote);
        System.out.println("LOG [FoodCompare.testGetImages]: " + result);
        return result;

//        DbUtils db = new DbUtils(DbUtils.ENTRIES);
//
//        Entry entry = db.getOneEntries();
//        Vector<Entry> entries = new Vector<Entry>();
//            
//        entries.add(entry);
//        entries.add(entry);
//        VoteEntry vote = new VoteEntry(entries, null);
//        
//        
//        String result = DbUtils.objectToJson(vote);
//        return result;
    }

    /**
     * Add a user to server Parameter: user : json string represents a user with
     * his friends, for instance {"fbuid" : "id12", "name" : "name", "friends" :
     * [ "friend1", "friend2" ] }
     * 
     * @param ui
     * @return
     * @throws UnknownHostException
     */
    // @POST
    // @Path("/adduser")
    // @Produces({ MediaType.APPLICATION_JSON })
    // public String register(@Context UriInfo ui) throws UnknownHostException {
    // logger.info("add user");
    // MultivaluedMap<String, String> map = ui.getQueryParameters();
    // String userJson = map.get("user").get(0);
    // User user = (User) DbUtils.jsonToObject(userJson, User.class);
    // DbUtils util = new DbUtils();
    // boolean ok = util.addUser(user);
    // if (!ok) {
    // return "{'error_code': 'Failed to add user'}";
    // }
    // return "{'error_code': 'OK'}";
    // }
    //
    // // /**
    // * User checks in at a place Parameter: checkin: {"fbuid" : "id2",
    // * "checkins" : [ { "id" : "4chuj9", "place" : null, "created_time" : null
    // }
    // * ] }
    // */
    // @POST
    // @Path("/checkin")
    // @Produces({ MediaType.APPLICATION_JSON })
    // public String checkin(@Context UriInfo ui) throws UnknownHostException {
    // logger.info("checkin");
    // MultivaluedMap<String, String> map = ui.getQueryParameters();
    // String checkInJson = map.get("checkin").get(0);
    // CheckIns checkIns = (CheckIns) DbUtils.jsonToObject(checkInJson,
    // CheckIns.class);
    // DbUtils util = new DbUtils();
    // boolean ok = util.addCheckIn(checkIns.getFbuid(),
    // checkIns.getCheckins().get(0));
    // return "{'error_code': 'OK'}";
    // }
    //

    // ------------ internal ---------------------

    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        System.out.println("LOG [FoodCompareServer.main]: testing");
        FoodCompare server = new FoodCompare();

        //

        // logger.info("Get all categories");
        // DbUtils db = new DbUtils(DbUtils.CATEGORIES);
        //
        // String result = db.getCategories();
        // System.out.println("LOG [FoodCompare.main]: result: " + result);
        //
        // server.addCategories();
//        server.testAddEntry();
        server.testAddEntry();
        server.testGetImages2();
    }

    private void addCategories() {
        Vector<Category> cats = new Vector<Category>();
        cats.add(new Category("Asian", "https://dl.dropbox.com/u/64316378/IMG_3249.jpg"));
        cats.add(new Category("Europe", "https://dl.dropbox.com/u/64316378/IMG_3253.jpg"));
        cats.add(new Category("American", "https://dl.dropbox.com/u/64316378/IMG_3251.jpg"));
        cats.add(new Category("Latino", "https://dl.dropbox.com/u/64316378/IMG_3256.jpg"));

        DbUtils db = new DbUtils(DbUtils.CATEGORIES);
        Categories setCats = new Categories(cats);
        db.addCategories(setCats);
        db.close();
    }

    //
    // public void testUsers() {
    // Vector<String> friends = new Vector<String>();
    // friends.add("friend1");
    // friends.add("friend2");
    //
    // // User user = new User("id", TextTool.getRandomString(3, 5), friends);
    // User user = new User("id12", "name", friends);
    // DbUtils util = new DbUtils();
    //
    // util.addUser(user);
    // }
    //
    // public boolean addUser(User user) {
    // DbUtils util = new DbUtils();
    // boolean result = util.addUser(user);
    // util.close();
    // return result;
    // }
    //
    private void testAddEntry() {
        DbUtils db = new DbUtils(DbUtils.ENTRIES);
        for (int i = 1; i < 10; i++) {
            Vector<String> tags = new Vector<String>();
            tags.add("tag1");
            tags.add("tag2");
            tags.add("tag3");
            Entry entry = new Entry("Entry " + i, "URL " + i, "Description " + i, i, i, i * 100, null, tags);
            db.addEntry(entry);
        }

    }

    // public boolean checkIn(String fbuid, CheckInEntry checkInEntry) {
    // DbUtils util = new DbUtils();
    // System.out.println("LOG [FoodCompareServer.checkIn]: " + checkInEntry);
    // boolean result = util.addCheckIn(fbuid, checkInEntry);
    // util.close();
    // return result;
    // }

    // public void testCheckIn() {
    // String fbuid = "id2";
    // String entryId = TextTool.getRandomString(3, 4);
    // System.out.println("LOG [FoodCompareServer.testCheckIn]: entryId= " +
    // entryId);
    // CheckInEntry entry = new CheckInEntry(entryId, null, null);
    //
    // checkIn(fbuid, entry);
    // }

}
