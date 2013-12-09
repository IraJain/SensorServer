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


// TODO: Auto-generated Javadoc
/**
 * The Class BidLogic.
 */
public class BidLogic {
	private static CreditController cred_control;
	
	public BidLogic(){
		cred_control = new CreditController();
	}
	
	public void setCreditController(CreditController creditController){
		cred_control = creditController;
	}

/**
 * Calculate winner.
 *
 * @param start_time the start_time
 * @return the array list
 */
public static ArrayList<NestData> CalculateWinner(Long start_time){ 


	String query; 
	String query_user; 
	Integer remaining_credit;
	
	Integer new_bid;
	Integer bid_amount;
	Integer bid_amount_user;	
    String user_id;
    Float temp;
    String room;
    Long start;
    Long tstmp_user;
   
    ArrayList<NestData> nestArr = new ArrayList<NestData>();
    
	try {
		
	 query = "SELECT s.user_id, s.room_no, s.temperature,s.bid_amount,s.timestamp FROM sensor s, "+			
				"(SELECT max(bid_amount) as maxbid, room_no FROM sensor "+
				"where start_time = " + start_time+ " GROUP BY room_no) maxresults " + 			
				"WHERE s.room_no = maxresults.room_no AND s.bid_amount= maxresults.maxbid AND s.start_time =" + start_time;
		
	 ArrayList <SensorData> sdArr = DbAccess.getSensorData(query);
	 for (SensorData sd :sdArr){

	 //For each room 
	
	    room = sd.getRoom();
		temp = sd.getTemperature();
	    start = start_time;
	    user_id = sd.getUser_id();
	    bid_amount = sd.getBid_amount();
		  
	
		 // create NEST array with winning temperature
		   NestData nest = new NestData();
		   nest.setRoom(room);
		   nest.setStart(start);
		   nest.setTemp(temp);
		   
		 //append nest data to array
		   nestArr.add(nest);
		
		    // Remove credit from winning user and adjust all future bids for the user
		   
	        remaining_credit  = cred_control.remainingCredit(user_id,"SenseServer",bid_amount); 
		
		
				   
		   // get all records of user for all room  where meeting start time >current time + 30 min 
		  query_user = "Select * from sensor where user_id = '" + user_id + "' and start_time > "+ start_time; 		   
		  
		  ArrayList <SensorData> sdArr_user = DbAccess.getSensorData(query_user);
	    for (SensorData sd_user :sdArr_user){
	    	 bid_amount_user = sd_user.getBid_amount();
	    	 tstmp_user = sd_user.getTimestamp();
	    	 
	    	

			   if (bid_amount_user> remaining_credit){
			   new_bid = remaining_credit;
			   System.out.println("credit - "+ user_id);
				
			   DbAccess.updateSensor(user_id,new_bid,tstmp_user);
			  
			   }
		  
			   else{

		   //No row update required in sensor table
			   }					   		  
			 }
		    
	}

}	 

	catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
	
	return nestArr;

}

}
