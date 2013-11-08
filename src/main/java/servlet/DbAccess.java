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
		JSONObject info = jsonObject.getJSONObject("bidTemperature");
		
		Map<String,String> out = new HashMap<String, String>();

	    parse(info,out);
	    try {
	//    c.setAutoCommit(false);
	    stmt = c.createStatement();
	    String user_id = out.get("user_id");
	    String room = out.get("room_no");
	    Long start_time = Long.parseLong(out.get("start_time"));
	    Long end_time = Long.parseLong(out.get("end_time"));
	    Double temperature = Double.parseDouble(out.get("temperature_f"));
	    Integer bid_amount = Integer.parseInt(out.get("bid_amount"));
	   // Long timestamp = out.get("timestamp");
	    
	    
	    
	    		
	    
	    String sql = "INSERT INTO  trial VALUES ('" + user_id +"','" + room  +  "');"; 
	  //  String sql = "insert into test " +
	 //                  "values ('" + user_id + "','" + room + "','" +  + "','" + 
	 //                   + "','" +  + "','"   + out.get("bid_amount") + "','"  + out.get("timestamp")+ "');"; 
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
