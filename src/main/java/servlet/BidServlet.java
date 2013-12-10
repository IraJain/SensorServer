/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
All rights reserved. 

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

Please contact http://www.cmu.edu/silicon-valley/ if you have any 
questions.
 */
package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;



// TODO: Auto-generated Javadoc
/**
 * The Class BidServlet.
 *
 * @author irajain
 */
@WebServlet(
        name = "BidServlet", 
        urlPatterns = {"/smartSense/*"}
       
    )
public class BidServlet extends HttpServlet {
	
	/** 
	 * Service method calls do post with request and response
	 * 
	 */
	protected void service (HttpServletRequest req, HttpServletResponse resp) {		
	try{
		System.out.println("bid service");	
	doPost(req,resp);
	}  catch (Exception e) {
  
 		  e.printStackTrace();
 	  }	
	}

    /** 
     * doPost with request and response objects from client application
     * @param req, resp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    
    	float winTemp = 0;
    	/**
    	 * 	Sensor data 
    	 */
    	SensorData sd = new SensorData();
    	SensorData sd_winner = new SensorData();   	
    	
    	String uri = req.getRequestURI();
    	System.out.println("bid servlet");
    	System.out.println(uri);
    	
    	// bids and saves value in db
       if (uri.equalsIgnoreCase("//smartSense/bid")) 
        	{ 
    	   try{
    	    	
    //Parsing logic for JSON   
    		sd = ParseJson(req);   
    	    sd_winner = DbAccess.save(sd);
    	    CurrentWinner cw = new CurrentWinner();

    	     winTemp = cw.getWinner(sd_winner.getRoom(),sd_winner.getStart());

     // form a response object
    	     resp.setContentType("application/json");
    	     PrintWriter out = resp.getWriter();
    	     
    // Form a json object from json string
    	     String jsonTempString = "{'winTemperatue':{'temperature' :"+ winTemp + "}}";
    	     JSONObject jsonTempObject = JSONObject.fromObject(jsonTempString);
    	     out.print(jsonTempObject);
    	     out.flush();
    	     
    // Push data to Hana every time someone bids
    	     //Pending on Bo's side to introduce new activity, registration tables in HANA
    	     
    	  }

    	   catch (Exception e) {
    	  
    	   // throw new IOException(jb.toString());
    		  e.printStackTrace();
    	  }
    	}
    	 
       // calls bidlogic in loop and controls NEST
    	else if (uri.equalsIgnoreCase("//smartSense/nest")) {
 	     // call bid logic - to remove should be in cron job
    		
 	    NestTemp nt = new NestTemp();
 	    nt.SetNestTemp();
 	     
    	}
    	
       // gets current winner for the room. 
    	else if (uri.equalsIgnoreCase("//smartSense/currentWin")) {
    		System.out.println("inside current");
 		   // call current winner and return the response to main servelet
    	 sd = ParseJson(req);
    		
    	 CurrentWinner cw = new CurrentWinner();
	      winTemp = cw.getWinner(sd.getRoom(),sd.getStart());
	      CreditController credit_control = new CreditController();
	      int creditLeft = credit_control.userCredit(sd.getUser_id()); 
 	     
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
    	

    
    /**
     * Parses the json.
     *
     * @param req the req
     * @return the sensor data
     */
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
    
    /**
     * Parses the.
     *
     * @param json the json
     * @param out the out
     * @return the map
     * @throws JSONException the jSON exception
     */
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
    
    

