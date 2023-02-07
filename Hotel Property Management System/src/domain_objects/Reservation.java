package domain_objects;

import java.util.ArrayList;

public class Reservation {
	
	public String arrival_date = "";
	public String departure_date = "";
	public Profile profile = new Customer(){
	};
	
	private Room room;
	
	public Reservation(String last_name, String first_name, String address, String phone_num, String credit_card) {
		profile.setLast_name(last_name);
		profile.setFirst_name(first_name);
		profile.setAddress(address);
		profile.setPhone_num(phone_num);
		profile.setCredit_card(credit_card);
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
}