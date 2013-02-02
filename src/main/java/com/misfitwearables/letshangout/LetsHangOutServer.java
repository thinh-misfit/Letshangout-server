package com.misfitwearables.letshangout;

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

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.misfit.ta.utils.TextTool;
import com.misfitwearables.letshangout.checkin.CheckInEntry;
import com.misfitwearables.letshangout.checkin.CheckIns;
import com.misfitwearables.letshangout.checkin.User;

@Path("/")
public class LetsHangOutServer {

    Logger logger = Logger.getLogger(LetsHangOutServer.class);

    /* Time before the locked data expired, 4 minutes in seconds. */
    private static long LOCKED_DATA_EXPIRING_TIME = 240000;

    @Context
    private UriInfo context;

    private int results = 1;

    public LetsHangOutServer() {
    }

    // To test if the service is up and running
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHello() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateNow = dateFormat.format(date);

        System.out.println("LOG [LetsHangOutServer.sayHello]: " + results++);

        logger.info("------------------------- Up and running  --------------------");
        return "<html> " + "<title>" + "Hello from Letshangout" + "</title>" + "<body><h1>"
                + "Hello from Letshangout, Puh4 no more hibernate <br>Time = " + dateNow + "</body></h1>" + "</html> ";
    }

    @GET
    @Path("/getUsers")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML })
    public String getUsers(@Context UriInfo ui) {
        logger.info("Get all users");

        return "{'username': 'password'}";
    }

    @GET
    @Path("/test")
    @Produces({ MediaType.TEXT_HTML })
    public String test(@Context UriInfo ui) throws UnknownHostException {
        logger.info("Get all users");

        return "{'username': 'password'}";
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
    @POST
    @Path("/adduser")
    @Produces({ MediaType.APPLICATION_JSON })
    public String register(@Context UriInfo ui) throws UnknownHostException {
        logger.info("add user");
        MultivaluedMap<String, String> map = ui.getQueryParameters();
        String userJson = map.get("user").get(0);
        User user = (User) DbUtils.jsonToObject(userJson, User.class);
        DbUtils util = new DbUtils();
        boolean ok = util.addUser(user);
        if (!ok) {
            return "{'error_code': 'Failed to add user'}";
        }
        return "{'error_code': 'OK'}";
    }

    /**
     * User checks in at a place Parameter: checkin: {"fbuid" : "id2",
     * "checkins" : [ { "id" : "4chuj9", "place" : null, "created_time" : null }
     * ] }
     */
    @POST
    @Path("/checkin")
    @Produces({ MediaType.APPLICATION_JSON })
    public String checkin(@Context UriInfo ui) throws UnknownHostException {
        logger.info("checkin");
        MultivaluedMap<String, String> map = ui.getQueryParameters();
        String checkInJson = map.get("checkin").get(0);
        CheckIns checkIns = (CheckIns) DbUtils.jsonToObject(checkInJson, CheckIns.class);
        DbUtils util = new DbUtils();
        boolean ok = util.addCheckIn(checkIns.getFbuid(), checkIns.getCheckins().get(0));
        return "{'error_code': 'OK'}";
    }
    
    
    // ------------ internal ---------------------

    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        System.out.println("LOG [LetsHangOutServer.main]: testing");
        LetsHangOutServer server = new LetsHangOutServer();
        server.testCheckIn();
        // server.testUsers();
    }

    public void testUsers() {
        Vector<String> friends = new Vector<String>();
        friends.add("friend1");
        friends.add("friend2");

        // User user = new User("id", TextTool.getRandomString(3, 5), friends);
        User user = new User("id12", "name", friends);
        DbUtils util = new DbUtils();

        util.addUser(user);
    }

    public boolean addUser(User user) {
        DbUtils util = new DbUtils();
        boolean result = util.addUser(user);
        util.close();
        return result;

    }

    public boolean checkIn(String fbuid, CheckInEntry checkInEntry) {
        DbUtils util = new DbUtils();
        System.out.println("LOG [LetsHangOutServer.checkIn]: " + checkInEntry);
        boolean result = util.addCheckIn(fbuid, checkInEntry);
        util.close();
        return result;
    }

    public void testCheckIn() {
        String fbuid = "id2";
        String entryId = TextTool.getRandomString(3, 4);
        System.out.println("LOG [LetsHangOutServer.testCheckIn]: entryId= " + entryId);
        CheckInEntry entry = new CheckInEntry(entryId, null, null);

        checkIn(fbuid, entry);
    }

}
