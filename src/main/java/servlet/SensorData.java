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

// TODO: Auto-generated Javadoc
/**
 * The Class SensorData.
 */
public class SensorData {
	
	/** The user_id. */
	String user_id;
	
	/** The room. */
	String room;
	
	/** The temperature. */
	float temperature;
	
	/** The bid_amount. */
	int bid_amount;
	
	/** The start. */
	Long start;
	
	/** The end. */
	Long end;
	
	/** The timestamp. */
	Long timestamp;
	
	/**
	 * Gets the end.
	 *
	 * @return the end
	 */
	public Long getEnd() {
		return end;
	}
	
	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	public void setEnd(Long end) {
		this.end = end;
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the room.
	 *
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}
	
	/**
	 * Sets the room.
	 *
	 * @param room the new room
	 */
	public void setRoom(String room) {
		this.room = room;
	}
	
	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Long getStart() {
		return start;
	}
	
	/**
	 * Sets the start.
	 *
	 * @param start the new start
	 */
	public void setStart(Long start) {
		this.start = start;
	}
	
	/**
	 * Gets the user_id.
	 *
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	/**
	 * Sets the user_id.
	 *
	 * @param user_id the new user_id
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	/**
	 * Gets the temperature.
	 *
	 * @return the temperature
	 */
	public float getTemperature() {
		return temperature;
	}
	
	/**
	 * Sets the temperature.
	 *
	 * @param temperature the new temperature
	 */
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * Gets the bid_amount.
	 *
	 * @return the bid_amount
	 */
	public int getBid_amount() {
		return bid_amount;
	}
	
	/**
	 * Sets the bid_amount.
	 *
	 * @param bid_amount the new bid_amount
	 */
	public void setBid_amount(int bid_amount) {
		this.bid_amount = bid_amount;
	}
	
	

}
