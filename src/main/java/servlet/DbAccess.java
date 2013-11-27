package servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



public class DbAccess {

	public static Connection c = null ;
	
	private static Connection connect() {
		//Connection c = null;
		
		
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      c.setAutoCommit(false);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return c;
	  }
	
	
	
	// create public save in db method 
	public static SensorData save(SensorData sd){
		
		float curWinTemp= 0;
		String room= "";
		Long start_time= (long) 0;
		
		if (c == null){
			connect();
		}
		// Save json object to database
		Statement stmt = null;
	
	    try {
	//    c.setAutoCommit(false);
	    stmt = c.createStatement();
	    
	    String user_id = sd.getUser_id();
	    room = sd.getRoom();
		float temperature = sd.getTemperature();
	    start_time = sd.getStart();
	    Long end_time = sd.getEnd();
	    
	    int bid_amount = sd.getBid_amount();
	    Long timestamp = sd.getTimestamp();
	    
	       
	    String sql = "INSERT INTO  sensor VALUES ('" + user_id +"','" + room  + "'," + start_time +  "," + end_time  +  "," + temperature  +  "," + bid_amount  +  "," + timestamp +  ");";
	    
	    stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();	      	   	    
	    }
	    catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	      }
	    SensorData sd_winner = new SensorData();
	      sd_winner.setRoom(room);
	      sd_winner.setStart(start_time);
	      return sd;

	
		
	}
	


	
	public static ArrayList<SensorData> getSensorData(String query){ 
		if (c == null) { connect(); }
		ArrayList<SensorData> sdArr = new ArrayList<SensorData>();

	try {
		
		 Statement stmt = c.createStatement();
   
	
	ResultSet rs = stmt.executeQuery(query);
    while ( rs.next() ) {
    	 SensorData sd = new SensorData();
	    sd.setRoom (rs.getString("room_no"));
	    sd.setTemperature ( rs.getFloat("temperature"));
	    sd.setUser_id(rs.getString("user_id"));
	    sd.setBid_amount(rs.getInt("bid_amount"));
	    sd.setTimestamp(rs.getLong("timestamp"));
	   
	    sdArr.add(sd);
    }  
    rs.close();
      stmt.close();
     } catch ( Exception e ) {
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    System.exit(0);
  }
	return sdArr;
	}
	
	
	
	
	
	public static float getCurrentWin(String room, Long start_time){ 
		if (c == null) { connect(); }
	float temperature = 0;
	    
	try {		
		 Statement stmt = c.createStatement();
    //select temperature of max bid_amount from sensor table for a particular room
		
		 String query = 	"SELECT s.temperature,s.bid_amount FROM sensor s, " +
		"(SELECT max(bid_amount) as maxbid,room_no,start_time FROM sensor where start_time = " +start_time+ " and  room_no = '" +room + "') maxresults " +
		 "WHERE s.room_no = maxresults.room_no " +
		"AND s.bid_amount= maxresults.maxbid AND s.start_time = maxresults.start_time;";

		 ResultSet rs = stmt.executeQuery(query);   
		 temperature = rs.getFloat("temperature");

      rs.close();
		 stmt.close();
     } catch ( Exception e ) {
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    System.exit(0);
  }
	return temperature;
	}
	
	
	
	
	
	
	public static int updateCredit(String user,String incr_by, Integer bid){ 
		if (c == null) { connect(); }
    int amt =0;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		stmt = c.createStatement();

	String sql_query = "Select * from credit where user_id = '" +user+ "' ;";
	rs = stmt.executeQuery(sql_query);
	
	amt = rs.getInt("amount") - bid;
	if (amt < 0) {amt =0 ;}
	sql_query = "Update credit set amount = "+amt+ ", inc_by_amount = -"+ bid + ",last_inc = '" + incr_by + "' where user_id = '" +user+ "' ;";
	
	stmt.executeUpdate(sql_query);
    
    stmt.close();
    c.commit();	
     } catch ( Exception e ) {
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    System.exit(0);
  }
	return amt;
	}
	
	
	
	
	
	
	
	
	
	public static void updateSensor(String user, Integer bid, Long tstmp){ 
		if (c == null) { connect(); }

	try {
	Statement stmt = null;
		
	stmt = c.createStatement();
	String sql_query = "Update sensor set bid_amount = " + bid +  " where user_id = '" + user + "' and timestamp = "+ tstmp  ;
	
	stmt.executeUpdate(sql_query);
	
    stmt.close();
    c.commit();	
     } catch ( Exception e ) {
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    System.exit(0);
  }

	}
	
	
	

	
	

}
