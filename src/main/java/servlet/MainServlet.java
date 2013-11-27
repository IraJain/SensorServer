package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



@WebServlet(
        name = "MainServlet", 
        urlPatterns = {"/sensor/*"}
    )
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	float winTemp = 0;
    	String uri = req.getRequestURI();
    	SensorData sd = new SensorData();
    	SensorData sd_winner = new SensorData();
    	
    	//Parsing logic for JSON
    	sd = ParseJson(req);
    	
       if (uri.equalsIgnoreCase("/sensor/bid")) 
        	{ 
    	   try{ 	
    	    sd_winner = DbAccess.save(sd);
    	    CurrentWinner cw = new CurrentWinner();

    	     winTemp = cw.getWinner(sd_winner.getRoom(),sd_winner.getStart());
    	     
   // 	     System.out.println(winTemp);
    	    //form a response object
    	     resp.setContentType("application/json");
    	     PrintWriter out = resp.getWriter();
    	     
    //	     Form a json object from json string
    	     String jsonTempString = "{'winTemperatue':{'temperature' :"+ winTemp + "}}";
    	     JSONObject jsonTempObject = JSONObject.fromObject(jsonTempString);
    	     out.print(jsonTempObject);
    	     out.flush();
    	  }

    	   catch (Exception e) {
    	  
    	   // throw new IOException(jb.toString());
    		  e.printStackTrace();
    	  }
    	}
    	  
    	else if (uri.equalsIgnoreCase("/sensor/nest")) {
 	     // call bid logic - to remove should be in cron job
 	    NestTemp nt = new NestTemp();
 	    nt.SetNestTemp();
 	     
    	}
    	
    	else if (uri.equalsIgnoreCase("/sensor/currentWin")) {
 		   // call current winner and return the response to main servlet
	     
    		
    	 CurrentWinner cw = new CurrentWinner();
	      winTemp = cw.getWinner(sd.getRoom(),sd.getStart());
 	     
 	     System.out.println(winTemp);
 	    //form a response object
 	     resp.setContentType("application/json");
 	     PrintWriter out = resp.getWriter();
 	     
 //	     Form a json object from json string
 
 	     String jsonTempString = "{'winTemperatue':{'temperature' :"+ winTemp + "}}";
 	     JSONObject jsonTempObject = JSONObject.fromObject(jsonTempString);
 	     out.print(jsonTempObject);
 	     out.flush();
    	}
    	
    }
    	

    
    
    
    protected SensorData ParseJson(HttpServletRequest req)
    {
    	StringBuffer jb = new StringBuffer();
    	SensorData sd_parse = new SensorData();   
    	String line = null;
  	 
  	  try {
  
  	    BufferedReader reader = req.getReader();
  	    while ((line = reader.readLine()) != null)
  	      jb.append(line);
  	    
    		
  	    JSONObject jsonObject = JSONObject.fromObject(jb.toString());
  	    System.out.println(jsonObject);
  		JSONObject info = jsonObject.getJSONObject("bidTemperature");
  		
  		Map<String,String> out = new HashMap<String, String>();

  	    parse(info,out);
  	    

  	    sd_parse.setUser_id(out.get("user_id"));
	    sd_parse.setRoom (out.get("room_no"));
	    sd_parse.setStart(Long.parseLong(out.get("start_time")));
	    sd_parse.setEnd (Long.parseLong(out.get("end_time")));
	    sd_parse.setTemperature (Float.parseFloat(out.get("temperature_f")));
	    sd_parse.setBid_amount(Integer.parseInt(out.get("bid_amount")));
	    sd_parse.setTimestamp (Long.parseLong(out.get("timestamp")));
	    
	   
  	   
  	  }
  	  catch (Exception e) {
      	  
     	   // throw new IOException(jb.toString());
     		  e.printStackTrace();
     	  }
  	 return sd_parse;
    	
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
   
    }
    
    

