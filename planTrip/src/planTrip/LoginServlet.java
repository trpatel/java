package planTrip;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("txtUsername");
		String password = request.getParameter("txtPassword");
		
		PreparedStatement preparedstatement = null;
		final String USER = "root";
		final String PASS = "password";
		String url = "jdbc:mysql://localhost:3306/";
		String db = "trips";
		try{
			//register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			//Connect to database
			Connection myConn = DriverManager.getConnection(url+db, USER, PASS);
			String sql = "SELECT * FROM user WHERE user= ? AND pass = ?";
			preparedstatement = myConn.prepareStatement(sql);
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);
			preparedstatement.executeQuery();
			if(preparedstatement.getResultSet().next()){
				HttpSession session = request.getSession();
				User visitor = new User();
				visitor.username = username;
				visitor.password = password;
				session.setAttribute("user", visitor);
				RequestDispatcher t = request.getRequestDispatcher("/successful.jsp");
				t.forward(request, response);
			}else{
				RequestDispatcher t = request.getRequestDispatcher("/error.jsp");
				t.forward(request, response);
			}
		}catch(Exception e){
			RequestDispatcher t = request.getRequestDispatcher("/error.jsp");
			t.forward(request, response);
		}
	}
}
