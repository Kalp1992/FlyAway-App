package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.util.ConnectionManagerImpl;

//@WebServlet("/transaction")
public class transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		String meth = (String)hs.getAttribute("paymet");
		String Noofperson = (String)hs.getAttribute("Noofperson");
		String totalprice = (String)hs.getAttribute("totalprice");
		PrintWriter pw = response.getWriter();
		pw.write("<html><head>");
		pw.write("<script src='jquery/jquery-latest.js'></script>");
		pw.write("<script type='text/javascript'>");
		pw.write("$(document).ready(function () {");
		pw.write("	 $('body').bind('cut copy paste', function (e) {");
		pw.write("e.preventDefault(); });");
		pw.write("$('body').on('contextmenu',function(e){	        return false;	    });");
		pw.write("	    window.history.pushState(null, '', window.location.href);        ");
		pw.write(" window.onpopstate = function() { window.history.pushState(null, '', window.location.href);     }; }); </script></head>");
		
		pw.write("<body onkeydown='return (event.keyCode != 116)'>");
		
		
		try{

			 Connection connection;
				connection = new ConnectionManagerImpl().getConnection();
    		PreparedStatement st = null;
    		ResultSet rs = null;
    		st = connection.prepareStatement("insert into transactions(uid,Noofperson,amount,Noofticket,address,payment_mode,payment_status,booking_status) values(?,?,?,?,?,?,?,?)");
    		st.setInt(1, 155044);
    		st.setString(2, Noofperson);
    		st.setInt(3, Integer.parseInt(totalprice));
    		st.setInt(4, 2);
    		st.setString(5, "Jalgaon");
    		st.setString(6, meth);
    		st.setString(7, "Not received");
    		st.setString(8, "Not packed");
    			
    		int rsupdate = st.executeUpdate();
    		connection.close();
			if(rsupdate == 1){
			 pw.write("Flight booked");
			}
    		 
    		 
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	}


}
