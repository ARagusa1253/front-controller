package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestHelper {
	
	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// This method will be called by the FrontCotnroller when a user requests the login resources
		// We will extract the parameters from the forwarded request
		
		// 1) Extract parameters from the request (username & password)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 2) Call the confirmLogin method in the EmployeeService and see what that returns
		Employee e = eserv.confirmLogin(username, password);
		
		// 3) If the above method returns a valid user, let's print their info to the screen using PrintObject
		if (e != null) {
			
			// grab the session, add the user to the session
			HttpSession session = request.getSession();
			session.setAttribute("the-user", e);
			
			// print out the user's data with the PrintWriter
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h1>Welcome " + e.getFirstName() + "!</h1>");
			out.println("<h3>You have successfully logged in </h3>");
			out.println(om.writeValueAsString(e)); // This will print out a JSON string of the Employee object
			
		} else {
			// if the object returned is null
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("No user found, sorry");
			response.setStatus(204); // 200 level codes mean connection success, but there is no content to be returned
		}
		
	}
	
	public static void processEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		// 1) Set the content type of the response to be text/html
		response.setContentType("text/html");
		
		// 2) Call the findAll() method on the eserv which calls the dao to return a list of all employees in the DB
		List<Employee> allEmployees = eserv.findAll();
		
		// 3) Transform the list of java objects returned into a JSON string (using Jackson Object Mapper)
		String jsonString = om.writeValueAsString(allEmployees);
		
		// 4) Print that string to the screen using the PrintWriter
		PrintWriter out = response.getWriter();
		out.println(jsonString); // Prints out the list like this: "{  {firstName: "Bob", lastName: "Smith", ....}, {next object}  }"
	}
	
	
}
