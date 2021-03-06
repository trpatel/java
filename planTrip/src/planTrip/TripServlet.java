package planTrip;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class TripServlet
 */
@WebServlet("/TripServlet")
public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TripServlet() {
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location;
		double tripCost;
		double fund;
		double withdrawal;
		//tests to ensure valid inputs
		if(request.getParameter("location") == ""){
			location = "UNKNOWN";
		}else{
			location = request.getParameter("location").toUpperCase();
		}
		try{
			tripCost = Double.parseDouble(request.getParameter("tripCost"));
		}catch(Exception e){
			tripCost = 0.0;
		}
		try{
			fund = Double.parseDouble(request.getParameter("fund"));
		}catch(Exception e){
			fund = 0.0;
		}
		try{
			withdrawal = Double.parseDouble(request.getParameter("withdrawal"));
		}catch(Exception e){
			withdrawal = 0.0;
		}
		Triplist vacation = (Triplist)request.getSession(true).getAttribute("triplist");
		//check to see if the arraylist of trips has already been created
		if(vacation == null){
			vacation = new Triplist();
		}
		//create the trip object
		Trip loc = new Trip();
		loc.setLocation(location);
		loc.setTripCost(tripCost);
		loc.setFund(fund);
		loc.setWithdrawal(withdrawal);
		loc.setExpect(location, tripCost, fund, withdrawal);
		PreparedStatement preparedstatement = null;
		final String USER = "root";
		final String PASS = "password";
		String url = "jdbc:mysql://localhost:3306/";
		String db = "trips";
		try{
			int acount=0;
			Trip delete = null;
			//register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//Connect to database
			Connection myConn = DriverManager.getConnection(url+db, USER, PASS);
			//iterate through each trip object in the arraylist
			for(Object name: vacation.getDestinations()){
				//create a duplicate of the trip object to test
				Trip test = (Trip) name;
				//check to see if user has already listed the trip location
				if(test.getLocation().equals(location)){
					//indicator that trip location is already listed
					acount++;
					//will store trip object for deletion
					delete = test;
				}
			}
			//see if user wants to add the trip
			if((request.getParameter("bttn")).equals("Add")){
				//trip location already listed
				if(acount > 0){
					System.out.println("Trip already set");
					//add the trip to the arraylist
				}else{
					vacation.addDestination(loc);
					//insert new query
					String sql = "INSERT INTO destinations (location, time, cost, saving, withdrawal) VALUES " + "(?, ?, ?, ?, ?)";
					preparedstatement = myConn.prepareStatement(sql);
					preparedstatement.setString(1, loc.getLocation());
					preparedstatement.setString(2, loc.getExpect());
					preparedstatement.setDouble(3, loc.getTripCost());
					preparedstatement.setDouble(4, loc.getFund());
					preparedstatement.setDouble(5, loc.getWithdrawal());
					//execute sql query
					preparedstatement.executeUpdate();
					preparedstatement.close();
					myConn.close();
				}	
			}
			//see if user wants to delete the trip
			if((request.getParameter("bttn")).equals("Delete")){
				//delete the trip from the arraylist
				vacation.delDestination(delete);
				//delete query
				String sql = "DELETE FROM destinations WHERE location=" + "(?)";
				preparedstatement = myConn.prepareStatement(sql);
				preparedstatement.setString(1, loc.getLocation());
				//execute sql query
				preparedstatement.executeUpdate();
				preparedstatement.close();
				myConn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//allow for Trip.jsp to have access to the session that has the arraylist
		request.getSession().setAttribute("triplist", vacation);
		getServletContext().getRequestDispatcher("/Trip.jsp").forward(request, response);
	}
}
