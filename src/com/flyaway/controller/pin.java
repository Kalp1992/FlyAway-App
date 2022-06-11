package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.util.ConnectionManagerImpl;
import com.mysql.cj.xdevapi.Statement;

//@WebServlet("/pin")
public class pin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public pin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//doGet(request, response);
				PrintWriter pw = response.getWriter();
				String meth = request.getParameter("paymet");
				System.out.println(meth);
				String totalprice = request.getParameter("totalprice");

				String Noofperson = request.getParameter("Noofperson");
				System.out.println("pin.java"+Noofperson);
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
				 
					
				String card_no= request.getParameter("card_no");
				System.out.println("pincard:"+card_no);
				String pin = request.getParameter("pinvalue");
				System.out.println("pininpinjava:"+pin);
				try {
					Connection connection;
				     connection = new ConnectionManagerImpl().getConnection();
				PreparedStatement st = null;
				ResultSet rs = null;
				st = connection.prepareStatement("select pin from cardtype where card_no = ?;");
				st.setBigDecimal(1, new BigDecimal(card_no));
				rs = st.executeQuery();
				//connection.close();
				while(rs.next()){
				int p = rs.getInt(1);
				System.out.println(p);
				
				if(p==Integer.parseInt(pin)){
					Connection connection1;
				     connection1 = new ConnectionManagerImpl().getConnection();
					if(meth.equals("debitmode")){
					st = connection1.prepareStatement("select balance from customer where card_no= ?;");
					st.setBigDecimal(1, new BigDecimal(card_no));
					rs = st.executeQuery();
					while(rs.next()){
					int b = rs.getInt(1);
					
					b = b - Integer.parseInt(totalprice);
					
					st = connection1.prepareStatement("update customer set balance = "+b+" where card_no= ?;");
					st.setBigDecimal(1, new BigDecimal(card_no));
					
					
					int rsupdate = st.executeUpdate();
					//connection1.close();
					if(rsupdate == 1){
					 pw.write("payment received");
					 pw.println(" ****Confirmation Page****");			
					 Connection connection2;
				     connection2 = new ConnectionManagerImpl().getConnection();
			    		 
			    		PreparedStatement stt = null;
			    		ResultSet rss = null;
			    		stt = connection2.prepareStatement("insert into transactions(uid,Noofperson,amount,Noofticket,address,payment_mode,payment_status,booking_status) values(?,?,?,?,?,?,?,?)");
			    		stt.setInt(1, 155044);
			    		stt.setString(2, Noofperson);
			    		stt.setInt(3, Integer.parseInt(totalprice));
			    		stt.setInt(4, 1);
			    		stt.setString(5, "Jalgaon");
			    		stt.setString(6, meth);
			    		stt.setString(7, "received");
			    		stt.setString(8, "Booked");
			    			
			    		int rsupdate1 = stt.executeUpdate();
			    		//connection2.close();
			    		System.out.println("rsupdated:"+rsupdate1);
						if(rsupdate1 == 1){
						 pw.write(". Flight booked");
						 Connection connection3;
				         connection3 = new ConnectionManagerImpl().getConnection();
				        
				    	 String query= "select * from flights where FLIGHT_NO = 2";
				    	 java.sql.Statement stmt=connection3.createStatement();
				    	 ResultSet rss2= stmt.executeQuery(query);
				    	 while(rss2.next()){
				    	  pw.println("Flight Detail..... "); 
				    	  pw.println("*Flight No: " +rss2.getInt("FLIGHT_NO"));
				    	  pw.println("*Source: " +rss2.getString("SOURCE"));
				    	  pw.println("*Destination: " +rss2.getString("DESTINATION"));
				    	  pw.println("*Start Date: "+rss2.getString("START_DATE"));
				    	  pw.println ("*End Date: " +rss2.getString("END_DATE"));
				    	  pw.println ("*Departure Time: " +rss2.getString("DEPARTURE_TIME"));
				    	  pw.println ("*Arrival Time: " +rss2.getString("ARRIVAL_TIME"));
				    	  pw.println("*Total Amount paid:" +rss2.getInt("TICKET_PRICE"));
				    	     	 	 
				    	 }
				    	 String query2= "select * from seats where FLIGHT_NO =2";
				    	 java.sql.Statement stmt2=connection3.createStatement();
				    	 ResultSet rss4= stmt2.executeQuery(query2);
				    	 while(rss4.next()){
				    		 pw.println("Seating Arrangmemnt....");
				    		 pw.println("*Seat No: " +rss4.getInt("SEAT_NO"));
				    	  
						}
				    	 
				    	 String query1= "select * from passengers where PHONE ='9665420981'";
				    	 java.sql.Statement stmt1=connection3.createStatement();
				    	 ResultSet rss3= stmt1.executeQuery(query1);
				    	 while(rss3.next()){
				    		 pw.println("Passenger Detail....");
				    		 pw.println("*Passenger Name: " +rss3.getString("FULLNAME"));
				    		 pw.println("*Date of Birth: " +rss3.getString("DATE_OF_BIRTH"));
				    		 pw.println("*Email Id: " +rss3.getString("EMAIL"));
				    		 pw.println("*Contact No: "+rss3.getString("PHONE"));
				    		 pw.println ("*Address: " +rss3.getString("ADDRESS"));
				    	
						}
						}
			
					}
					System.out.println(rsupdate);
					}
					}
					if(meth.equals("creditmode")){
						st = connection1.prepareStatement("select amt from credit where card_no= ?;");
					 st.setBigDecimal(1, new BigDecimal(card_no));
					 rs = st.executeQuery();
					 while(rs.next()){
					 int b = rs.getInt(1);
					 
					 b = b + Integer.parseInt(totalprice);
					 
					 st = connection1.prepareStatement("update credit set amt = "+b+" where card_no= ?;");
					 st.setBigDecimal(1, new BigDecimal(card_no));
					 int rsupdate = st.executeUpdate();
					 if(rsupdate == 1){
						 pw.write("payment received");
						 pw.println(" ****Confirmation Page****");	

						 Connection connection2;
					     connection2 = new ConnectionManagerImpl().getConnection();
				    		 
				    		PreparedStatement stt = null;
				    		ResultSet rss = null;
				    		stt = connection2.prepareStatement("insert into transactions(uid,Noofperson,amount,Noofticket,address,payment_mode,payment_status,booking_status) values(?,?,?,?,?,?,?,?)");
				    		stt.setInt(1, 155044);
				    		stt.setString(2, Noofperson);
				    		
				    		stt.setInt(3, Integer.parseInt(totalprice));
				    		
				    		stt.setInt(4, 1);
				    		stt.setString(5, "Jalgaon");
				    		stt.setString(6, meth);
				    		stt.setString(7, "received");
				    		stt.setString(8, "Booked");
				    			
				    		int rsupdate1 = stt.executeUpdate();
				    		//connection2.close();
				    		System.out.println("rsupdate:"+rsupdate1);
							if(rsupdate1 == 1){
							 pw.write("Flight booked");
							 Connection connection3;
					         connection3 = new ConnectionManagerImpl().getConnection();
					        
					    	 String query= "select * from flights where FLIGHT_NO = 2";
					    	 java.sql.Statement stmt=connection3.createStatement();
					    	 ResultSet rss2= stmt.executeQuery(query);
					    	 while(rss2.next()){
					    	  pw.println("Flight Detail..... "); 
					    	  pw.println("*Flight No: " +rss2.getInt("FLIGHT_NO"));
					    	  pw.println("*Source: " +rss2.getString("SOURCE"));
					    	  pw.println("*Destination: " +rss2.getString("DESTINATION"));
					    	  pw.println("*Start Date: "+rss2.getString("START_DATE"));
					    	  pw.println ("*End Date: " +rss2.getString("END_DATE"));
					    	  pw.println ("*Departure Time: " +rss2.getString("DEPARTURE_TIME"));
					    	  pw.println ("*Arrival Time: " +rss2.getString("ARRIVAL_TIME"));
					    	  pw.println("*Total Amount paid:" +rss2.getInt("TICKET_PRICE"));
					    	     	 	 
					    	 }
					    	 String query2= "select * from seats where FLIGHT_NO =2";
					    	 java.sql.Statement stmt2=connection3.createStatement();
					    	 ResultSet rss4= stmt2.executeQuery(query2);
					    	 while(rss4.next()){
					    		 pw.println("Seating Arrangmemnt....");
					    		 pw.println("*Seat No: " +rss4.getInt("SEAT_NO"));
					    	  
							}
					    	 
					    	 String query1= "select * from passengers where PHONE ='9665420981'";
					    	 java.sql.Statement stmt1=connection3.createStatement();
					    	 ResultSet rss3= stmt1.executeQuery(query1);
					    	 while(rss3.next()){
					    		 pw.println("Passenger Detail....");
					    		 pw.println("*Passenger Name: " +rss3.getString("FULLNAME"));
					    		 pw.println("*Date of Birth: " +rss3.getString("DATE_OF_BIRTH"));
					    		 pw.println("*Email Id: " +rss3.getString("EMAIL"));
					    		 pw.println("*Contact No: "+rss3.getString("PHONE"));
					    		 pw.println ("*Address: " +rss3.getString("ADDRESS"));
					    	
							}
							}
				    	
					 }
					 System.out.println(rsupdate);
					}
				}
				}
				
				else{
					RequestDispatcher rd = request.getRequestDispatcher("failure.jsp");
					rd.forward(request, response);
				}
				}
				}
				catch(Exception e ){
					e.printStackTrace();
				}
							
				
				
			}
	}


