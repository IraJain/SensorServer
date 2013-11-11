package servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
	    System.out.println("Opened database successfully");
	    return c;
	  }
	
	
	
	// create public save in db method 
	public static void save(JSONObject jsonObject){
		if (c == null){
			connect();
		}
		// Save json object to database
		Statement stmt = null;
		//JSONObject info = jsonObject.getJSONObject("bidTemperature");
		
		Map<String,String> out = new HashMap<String, String>();

	    parse(jsonObject,out);
	    try {
	//    c.setAutoCommit(false);
	    stmt = c.createStatement();
	    
	    //room":"2","day":"3","month":"4","hours":"5","minutes":"6","temp":"6","coins":"6"
	    //String user_id = out.get("user_id");
	    String room = out.get("room");
	    String day = out.get("day");
	    String month = out.get("month");
	    String hours = out.get("hours");
	    String minutes = out.get("minutes");
	    String temp = out.get("temp");
	    String coins = out.get("coins");
	    		
	    
	    String sql = "INSERT INTO  bid VALUES ('" + room +"','" + day  +  "','" + month  +  "','" + hours  +  "', ,'" + minutes  +  "','" + temp  +  "','" + coins  +  "');"; 
	    stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	    }
	    catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	      }
	    

	    System.out.println(out.get("user_id"));
	    
		
	}
	
	private static Map<String,String> parse(JSONObject json , Map<String,String> out) throws JSONException{
	    Iterator<String> keys = json.keys();
	    while(keys.hasNext()){
	        String key = keys.next();
	        String val = null;
	        try{
	             JSONObject value = json.getJSONObject(key);
	             parse(value,out);
	        }catch(Exception e){
	            val = json.getString(key);
	        }

	        if(val != null){
	            out.put(key,val);
	        }
	    }
	    return out;
	}
	//
	
	public static ResultSet getData(Long current_time,Long duration){ 
		if (c == null) { connect(); }

	Statement stmt = null;
	ResultSet rs = null;
	try {
	stmt = c.createStatement();
	String sql_query = "SELECT * FROM TRIAL where ";
    rs = stmt.executeQuery(sql_query);
   
    stmt.close();
     } catch ( Exception e ) {
    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    System.exit(0);
  }
	return rs;
	}

 
}
