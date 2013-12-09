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
 * The Class CreditServlet.
 */
@WebServlet(
        name = "CreditServlet", 
        urlPatterns = {"/credit/*"}
    )

public class CreditServlet  extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void service (HttpServletRequest req, HttpServletResponse resp) {
		
		try{
		doPost(req,resp);
		}  catch (Exception e) {
	  
	 		  e.printStackTrace();
	 	  }	
		}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		// update user credit in credit table
		 if (uri.equalsIgnoreCase("/credit/updateUserCredit")) 
     	{ 
 	   try{
 		   
 		  String user =  req.getParameter("user");
 		  int credit = Integer.parseInt(req.getParameter("credit"));
 		   String incr_by="Staff" ;
 				 
 		  DbAccess.updateUserCredit(user,incr_by,credit); 
 		   
 		   
 	   }
 		   
 
 	   
 	  catch (Exception e) {
   		  e.printStackTrace();
   	  }
		
     	}
		 
		 // get user credit from credit table
		 else if (uri.equalsIgnoreCase("//credit/getUserCredit")) {
			 // Parse request to get the user name  
			 String user = ParseJson(req);
			 // Get left credits from user table
			 int creditLeft = DbAccess.getUserCredit(user); 
			 
			 // Form a json object to send back as response to application
			 resp.setContentType("application/json");
	 	     PrintWriter out = resp.getWriter();	 	     
	 //	     Form a json object from json string	 
	 	     String jsonCreditString = "{'UserCredit':{'credits' :"+ creditLeft + "}}";	 	
	 	     JSONObject jsonTempObject = JSONObject.fromObject(jsonCreditString);
	 	     out.print(jsonTempObject);
	 	     out.flush();
			 
			 
		 } 
		 
		 
	}
	
	 /**
     * Parses the json.
     *
     * @param req the req
     * @return the user
     */
    protected String ParseJson(HttpServletRequest req)
    {
    	StringBuffer jb = new StringBuffer(); 
    	String line = null;
    	String user=null;
  	  try {
  
  	    BufferedReader reader = req.getReader();
  	    while ((line = reader.readLine()) != null)
  	      jb.append(line);
  	    
    		
  	    JSONObject jsonObject = JSONObject.fromObject(jb.toString());
  	    System.out.println(jsonObject);
  		JSONObject info = jsonObject.getJSONObject("UserCredit");
  		
  		Map<String,String> out = new HashMap<String, String>();

  	    parse(info,out);
  	    

  	     user = out.get("user_id");
	   
  	   
  	  }
  	  catch (Exception e) {
      	  
     	   // throw new IOException(jb.toString());
     		  e.printStackTrace();
     	  }
  	 return user;
    	
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
	


