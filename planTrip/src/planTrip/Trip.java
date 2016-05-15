package planTrip;

import java.util.Calendar;

public class Trip {
	//destination name
	private String location;
	//total cost of the trip
	private double tripCost;
	//amount that will be contributed weekly
	private double fund;
	//amount that will be taken out weekly
	private double withdrawal;
	//expected destination date
	private String expect;
	
	public String getLocation(){
		return location;
	}
	public void setLocation(String location){
		this.location = location;
	}
	public double getTripCost(){
		return tripCost;
	}
	public void setTripCost(double tripCost){
		this.tripCost = tripCost;
	}
	public double getFund(){
		return fund;
	}
	public void setFund(double fund){
		this.fund = fund;
	}
	public double getWithdrawal(){
		return withdrawal;
	}
	public void setWithdrawal(double withdrawal){
		this.withdrawal = withdrawal;
	}
	public String getExpect(){
		return expect;
	}
	public void setExpect(String location, double tripCost, double fund, double withdrawal){
		//current time and date
		Calendar now = Calendar.getInstance();
		String temp;
		//total weekly savings
		double totalContribution = fund - withdrawal;
		double total = 0;
		int i;
		//weekly savings is negative
		if(totalContribution < 0){
			temp = "Your weekly withdrawal amount is higher than the deposit amount.";
		//weekly savings is zero
		}else if(totalContribution == 0){
			temp = "Your weekly savings will not accumulate.";
		//how many weeks it will take to accumulate the total amount
		}else{
			for(i=0; total < tripCost; i++){
				total += totalContribution;
			}
			now.add(Calendar.WEEK_OF_YEAR, i);
			temp = i + " week(s) or\n" + now.getTime().toString();
		}
		this.expect = temp;
	}
}