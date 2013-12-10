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

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class Core.
 */
@WebServlet(
        name = "Core", 
        urlPatterns = {"/sensor/*"}
    )

public class Core  extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String uri = req.getRequestURI();
		ServletContext context = getServletContext();  
		
		 
		 
		String web_pattern = uri.replace("/sensor", "");
		System.out.println(web_pattern);

	if (web_pattern.matches("/smartSense/.*"))			 
      	{
		System.out.println("smartsense core");
			 
			  RequestDispatcher dispatcher = context.getRequestDispatcher(web_pattern);  
			  try {  
				  dispatcher.forward(req,resp);  
				  return;  
			       } catch (ServletException e) {}    
      	}
		  
		  
		  
		  
		  else if (web_pattern.matches("/credit/.*"))	  {
			  RequestDispatcher dispatcher = context.getRequestDispatcher(web_pattern);  
			  try {  
				  dispatcher.forward(req,resp);  
				  return;  
			       } catch (ServletException e) {}    
		  
      	
			  
		  }
		  
		  
	}

}
