package planTrip;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//store the input trip location upper-case
		String location = request.getParameter("location").toUpperCase();
		double tripCost = Double.parseDouble(request.getParameter("tripCost"));
		double fund = Double.parseDouble(request.getParameter("fund"));
		double withdrawal = Double.parseDouble(request.getParameter("withdrawal"));
		String expect = "";
		
		Triplist vacation = (Triplist)request.getSession(true).getAttribute("triplist");
		//check to see if the arraylist of trips has already been created
		if(vacation == null){
			vacation = new Triplist();
		}
		//create the trip object
		Trip loc = new Trip(location, tripCost, fund, withdrawal, expect);
		try{
			int acount=0;
			Trip delete = null;
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
				}	
			}
			//see if user wants to delete the trip
			if((request.getParameter("bttn")).equals("Delete")){
				//delete the trip from the arraylist
				vacation.delDestination(delete);
			}
		}catch(Exception e){
			System.out.println("error");
		}
		//allow for Trip.jsp to have access to the session that has the arraylist
		request.getSession().setAttribute("triplist", vacation);
		getServletContext().getRequestDispatcher("/Trip.jsp").forward(request, response);
	}

}
