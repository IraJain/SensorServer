package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;



@WebServlet(
        name = "MainServlet", 
        urlPatterns = {"/sensor"}
    )
public class MainServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	StringBuffer jb = new StringBuffer();
    	  String line = null;
    	  try {
    	    BufferedReader reader = req.getReader();
    	    while ((line = reader.readLine()) != null)
    	      jb.append(line);
    	  } catch (Exception e) { /*report an error*/ }

    	  try {
    	    JSONObject jsonObject = JSONObject.fromObject(jb.toString());
    	    System.out.println(jsonObject);
    	   // DbAccess.save(jsonObject);
    	  } catch (Exception e) {
    	    // crash and burn
    	    throw new IOException("Error parsing JSON request string");
    	  }
    	  
    	
    	
    //	JSONObject jsonObj = requestParamsToJSON(req);
    	

    	
    	 
    	
//    
//     // save in db and Connect to db (singleton ?) may be inside dbaccess
    	//DbAccess.save(jsonObj);
//    /////////////////////////////////////////	
//    	// Set Nest temp
//    	//NestTemp.SetNestTemp();
    	
    	
    	

    }
    public JSONObject requestParamsToJSON(ServletRequest req) {
    	  JSONObject jsonObj = new JSONObject();
    	  Map<String,String[]> params = req.getParameterMap();
    	  for (Map.Entry<String,String[]> entry : params.entrySet()) {
    	    String v[] = entry.getValue();
    	    Object o = (v.length == 1) ? v[0] : v;
    	    jsonObj.put(entry.getKey(), o);
    	  }
    	  
    	  return jsonObj;
    	}
    
}

