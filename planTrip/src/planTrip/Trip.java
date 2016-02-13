package planTrip;

import java.util.Calendar;

public class Trip {
	//destination name
	private String location;
	//total cost of trip to destination
	private double tripCost;
	//amount that will be contributed weekly
	private double fund;
	//amount that will be taken out weekly
	private double withdrawal;
	//how many weeks it will take to accumulate for the trip
	private String expect;
	public Trip(String location, double tripCost, double fund, double withdrawal, String expect){
		//entirety of destination input will be upper-case
		this.location = location.toUpperCase();
		this.tripCost = tripCost;
		this.fund = fund;
		this.withdrawal = withdrawal;
		
		//get current date and time
		Calendar now = Calendar.getInstance();
		//total amount contributed each week
		double totalCon = fund - withdrawal;
		//act as accumulator
		double total = 0;
		int i;
		//indicates amount contributed weekly is negative
		if(totalCon < 0){
			expect += "Your withdrawal is higher than the deposit";
		//indicates amount contributed weekly will not grow
		}else if(totalCon == 0){
			expect += "Your weekly contribution will not accumulate";
		//calculates amount of weeks it will take until user can afford the trip
		}else{
			//calculate how many weeks
			for(i = 0; total < tripCost; i++){
				total += totalCon;
			}
			//adds the number of weeks calculated to the current date
			now.add(Calendar.WEEK_OF_YEAR, i);
			//store the anticipated trip date
			expect += i + " week(s) or\n" + now.getTime().toString();
		}
		this.expect=expect;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	public String getExpect(){
		return expect;
	}
	/**
	 * @return the tripCost
	 */
	public double getTripCost() {
		return tripCost;
	}
	/**
	 * @return the fund
	 */
	public double getFund() {
		return fund;
	}
	/**
	 * @return the withdrawal
	 */
	public double getWithdrawal() {
		return withdrawal;
	}
}
