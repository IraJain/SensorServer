package servlet;

import java.util.ArrayList;

public class NestTemp {
	
public static void SetNestTemp(){
	
	 Long current_time ;
	 Long start_time;
	 int sleep_time = 3600;
	 ArrayList<NestData> nestArr = new ArrayList<NestData>();
		
	// UTC time in seconds
	 
		current_time = System.currentTimeMillis()/1000;  
			System.out.println(current_time);

		
	
	// looping for thread starts here and increment start time by incrementing it with sleep time every iteration		
		//Long start_time = current_time + 1800 ;
		start_time = (long) 300;
	
	nestArr =  BidLogic.CalculateWinner(start_time);
	   
	   // link to API to connect to Nest for each room in array list
	
	
	

	//clear nest arr for next iteration 
	nestArr.clear();
		
}

}
