package com.revature.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	/*
	 * This method will be responsible for determining what resouce the client is requesting
	 * It will take in the URL and capture only the end part after "/FrontController/".
	 * 
	 * Once it captures the distication it will call another class called RequestHelper
	 * which will supply the right functionality 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) Save the URI and rewrite it
		final String URI = request.getRequestURI().replace("/FrontController/", ""); // This leaves nothing at the end part like "/login" or "/employees"
		
		// 2) Set up a Switch case statement in which we call the appropriate functionality based on the URI returned
		switch(URI) {
		case "login":
			// call some processLogin() functionality and pass the request and response objects
			RequestHelper.processLogin(request, response);
			break;
		case "employee":
			RequestHelper.processEmployees(request, response); // returns a JSON string of all employees in the DB
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
