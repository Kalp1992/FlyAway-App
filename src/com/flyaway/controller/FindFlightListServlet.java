package com.flyaway.controller;

import java.awt.List;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.dao.FlightDao;
import com.flyaway.dao.FlightDaoImpl;
import com.flyaway.dto.Flight;


public class FindFlightListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Source = request.getParameter("source");
		String Destination = request.getParameter("destination");
		
        String startdate22=request.getParameter("dte");
        Flight flight=new Flight(Source, Destination, startdate22);
		FlightDao dao;
		try {
			dao = new FlightDaoImpl();
			Set<Flight> flights=  dao.findFlight(Source, Destination, startdate22);
			
			if(flights!= null) {
				
				request.setAttribute("flightlist", flights);
				RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayFlight.jsp");
				dispatcher.forward(request, response);
				//Set<Flight> searchedflight = new HashSet<>();
				//List searchedflight = new List();
				//HttpSession session = request.getSession();
				//session.setAttribute("flightlist",searchedflight );;
				//response.sendRedirect("DisplayFlight.jsp");
			}else {
				response.sendRedirect("InvalidSearch.html");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();}
		} 
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
