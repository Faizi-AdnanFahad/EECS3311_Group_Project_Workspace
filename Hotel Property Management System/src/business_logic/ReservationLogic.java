package business_logic;

import java.util.List;

import domain_objects.Reservation;
import domain_objects.Room;
import domain_objects.StandardRoom;
import domain_objects.DeluxeRoom;
import domain_objects.SuiteRoom;
import domain_objects.ExecutiveSuite;
import domain_objects.PresidentialSuite;
import persistence.DatabaseStubs;

public class ReservationLogic {

	private static List<Reservation> resList =DatabaseStubs.getReservations();
	
	public ReservationLogic() {
		resList = DatabaseStubs.getReservations();
	}
	
	public static void addReservation(Reservation reso) {
		resList.add(reso);
	}

	public void removeReservation(String resNum) {
		
	}
	
	public Reservation updatReservation(Reservation reservation){
		return reservation;
	}
	
	public List<Reservation> getAllReservations() {
		return resList;
	}
	
	public Reservation changeResDates(Reservation reservation, String newArrivalDate, String newDepartDate) {
		reservation.arrival_date = newArrivalDate;
		reservation.departure_date = newDepartDate;
		return reservation;
	}
	
	public static Room roomAvailable(String roomType) {
		Room room = null;
		switch(roomType) {
		case "Standard":
			StandardRoom standard = new StandardRoom();
			if (standard.getRoomsAvailable() != 0)
				room = standard;
			break;
		case "Deluxe":
			DeluxeRoom deluxe = new DeluxeRoom();
			if (deluxe.getRoomsAvailable() != 0) 
				room = deluxe;
			break;
		case "Suite":
			SuiteRoom suite = new SuiteRoom();
			if (suite.getRoomsAvailable() != 0) 
				room = suite;
			break;
		case "Executive":
			ExecutiveSuite executive = new ExecutiveSuite();
			if (executive.getRoomsAvailable() != 0) 
				room = executive;
			break;
		case "Presidential":
			PresidentialSuite presidential = new PresidentialSuite();
			if (presidential.getRoomsAvailable() != 0) 
				room = presidential;
			break;
		}
		return room;
	}

}
