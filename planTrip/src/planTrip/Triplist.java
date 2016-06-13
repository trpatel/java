package planTrip;

import java.util.*;


public class Triplist {
	//store each trip in an arraylist
	private List destinations = new ArrayList();
	/**
	 * @return the destinations
	 */
	public List getDestinations() {
		return destinations;
	}
	//add the trip to the arraylist
	public void addDestination(Trip location){
		destinations.add(location);
	}
	//delete the trip from the arraylist
	public void delDestination(Trip location){
		destinations.remove(location);
	}
	//method to get the trip's location
	public String toString(Trip location){
		return location.getLocation();
	}
}
