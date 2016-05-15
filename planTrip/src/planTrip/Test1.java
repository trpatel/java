package planTrip;

import static org.junit.Assert.*;
import org.junit.Test;

public class Test1 {
	@Test
	public void testTrip() {
		//test data
		String loc1 = "Paris";
		String loc2 = "Alaska";
		double tc1 = 500.0;
		double tc2 = 0.0;
		double f1 = 100.0;
		double f2 = 0.0;
		double w1 = 0.0;
		double w2 = 0.0;
		double w3 = 20.0;
		Trip trip1 = new Trip();
		trip1.setLocation(loc1);
		trip1.setTripCost(tc1);
		trip1.setFund(f1);
		trip1.setWithdrawal(w1);
		trip1.setExpect(loc1, tc1, f1, w1);
		Trip trip2 = new Trip();
		trip2.setExpect(loc2, tc2, f2, w2);
		Trip trip3 = new Trip();
		trip3.setExpect(loc2, tc2, f2, w3);
		
		//Check that location for the trip has been set
		assertEquals(trip1.getLocation(), loc1);
		//Check that tripcost for the trip has been set
		assertEquals(Double.toString(trip1.getTripCost()), Double.toString(tc1));
		//Check that fund for the trip has been set
		assertEquals(Double.toString(trip1.getFund()), Double.toString(f1));
		//Check that withdrawal for the trip has been set
		assertEquals(Double.toString(trip1.getWithdrawal()), Double.toString(w1));
		//Check that expect was set
		assertNotNull(trip1.getExpect());
		//Check expect will be a message indicating savings will not grow
		assertTrue(trip2.getExpect().contains("will not accumulate"));
		//Check expect will be a message indicating savings will decrease
		assertTrue(trip3.getExpect().contains("withdrawal amount is higher"));
	}

}
