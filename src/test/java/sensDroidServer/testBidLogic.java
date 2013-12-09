package sensDroidServer;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest; 
import org.powermock.modules.junit4.PowerMockRunner; 

import servlet.BidLogic;
import servlet.CreditController;
import servlet.DbAccess;
import servlet.NestData;
import servlet.SensorData;
import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DbAccess.class})
public class testBidLogic{
	BidLogic bidlogic;
	String room = "Room";
	Long start = new Date().getTime();
	Float temperature = (float) 70;
	
	protected void setUp() throws Exception {
	}

	
	@Test
	public void testSample() throws Exception{		
		bidlogic = new BidLogic();
				
		ArrayList<SensorData> sensorData = new ArrayList<SensorData>();
		SensorData data = new SensorData();
		data.setRoom(room);
		data.setStart(start);
		data.setTemperature(temperature);
		sensorData.add(data);
		
		
		String query = "SELECT s.user_id, s.room_no, s.temperature,s.bid_amount,s.timestamp FROM sensor s, "+			
				"(SELECT max(bid_amount) as maxbid, room_no FROM sensor "+
				"where start_time = " + start + " GROUP BY room_no) maxresults " + 			
				"WHERE s.room_no = maxresults.room_no AND s.bid_amount= maxresults.maxbid AND s.start_time =" + start;
		
		PowerMockito.mockStatic(DbAccess.class);
	    PowerMockito.when(DbAccess.getSensorData(query)).thenReturn(sensorData);

		
		ArrayList<NestData> nestArr = BidLogic.CalculateWinner(start);
		assertEquals(1, nestArr.size());
	 }
	

}

