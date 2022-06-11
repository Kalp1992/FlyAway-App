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


public class ValidateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("uname");
		String Password=request.getParameter("pword");
		User user =new User(userName, Password);
		try {
			UserDao dao=new UserDaoImpl();
			boolean isUserValid=dao.ValidateUser(user);
			if(isUserValid) {
				response.sendRedirect("FlightSearch.html");
			}else{
				response.sendRedirect("InvalidUser.html");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
