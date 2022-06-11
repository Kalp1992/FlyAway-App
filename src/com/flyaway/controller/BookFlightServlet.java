package com.flyaway.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.dao.FlightDao;
import com.flyaway.dao.FlightDaoImpl;
import com.flyaway.dao.PassengerDao;
import com.flyaway.dao.PassengerDaoImpl;
import com.flyaway.dto.Flight;
import com.flyaway.dto.Passenger;



public class BookFlightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter("fname");
		String dob = request.getParameter("DOB");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");  
		Passenger passenger = new Passenger(fullName, dob, email, phone, address);
		PassengerDao dao;
			try {
				dao = new PassengerDaoImpl();
				boolean isPassengerCreated= dao.createPassenger(passenger);
				if(isPassengerCreated) {
				
						response.sendRedirect("PayLink.jsp");
							
						}
							else {
							response.sendRedirect("ReservationFull.html");
						}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
				} 
			
					
			
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
