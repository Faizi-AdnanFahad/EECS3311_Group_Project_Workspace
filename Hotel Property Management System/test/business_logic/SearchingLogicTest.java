package business_logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain_objects_Rooms.Reservation;

public class SearchingLogicTest {
	
	Reservation mockRes = new Reservation("", "", "", "", "");
	
	@Before
	public void setUp() throws Exception {
		ReservationLogic.addReservation(mockRes);
	}

	@Test
	public void testSearchByResNum() {
		assertEquals(mockRes, SearchingLogic.searchByResNum(mockRes.getResNumber()));
	}
	
	@Test
	public void testSearchByResNumFalse() {
		assertFalse("Error: Should not be equal", mockRes == SearchingLogic.searchByResNum(mockRes.getResNumber() + 1));
	}

}
