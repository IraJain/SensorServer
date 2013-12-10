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

import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// TODO: Auto-generated Javadoc
/**
 * The Class NestTemp.
 */
public class NestTemp {
	
/**
 * Sets the nest temp.
 */
public static void SetNestTemp(){
	
	// code to execute repeated - execute every 60 seconds			
	ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
	exec.scheduleAtFixedRate(new Runnable() {
	public void run() {
		
	//UTC time	
	Long current_time = System.currentTimeMillis(); 
	
	//Start time = current time + 30 min
	//Long start_time = current_time + 1800000;
	Long start_time = current_time + 7200;
	 
	ArrayList<NestData> nestArr = new ArrayList<NestData>();
	nestArr =  BidLogic.CalculateWinner(start_time);
	   
	 // link to API NEST FACADE to connect to Nest for each room in array list
	

	//clear nest array for next iteration 
	nestArr.clear();
			          
	} }, 0, 60, TimeUnit.SECONDS);	
}

}
