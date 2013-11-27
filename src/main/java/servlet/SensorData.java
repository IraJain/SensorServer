package servlet;

public class SensorData {
	String user_id;
	String room;
	float temperature;
	int bid_amount;
	Long start;
	Long end;
	Long timestamp;
	
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public int getBid_amount() {
		return bid_amount;
	}
	public void setBid_amount(int bid_amount) {
		this.bid_amount = bid_amount;
	}
	
	

}
