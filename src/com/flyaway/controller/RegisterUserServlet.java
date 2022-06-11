package com.flyaway.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.dao.UserDao;
import com.flyaway.dao.UserDaoImpl;
import com.flyaway.dto.User;


public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String userName = request.getParameter("uname");
		String password = request.getParameter("pword");
		User user = new User(firstName, lastName, userName, password);
		try {
			UserDao dao= new UserDaoImpl();
			boolean isUserRegistered=dao.RegisterUser(user);
			if(isUserRegistered) {
				response.sendRedirect("RegistrationSuccess.html");
			}else {
				response.sendRedirect("RegistrationFailure.html");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
