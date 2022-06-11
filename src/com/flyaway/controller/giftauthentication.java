package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.util.ConnectionManagerImpl;



public class giftauthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public giftauthentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				try{
		 
		    			String totalprice = request.getParameter("totalprice");
		    			System.out.println("costingiftau:"+totalprice);
		    			String card = request.getParameter("card");
		    			System.out.println("cardingiftau:"+card);
		    			String Noofperson = request.getParameter("Noofperson");
		    			String meth = request.getParameter("paymet");
		    			System.out.println("giftauthentication.java :"+Noofperson);
		    			
		    			Statement st = null;
		    			ResultSet rs = null;
		    		    Connection connection;
		    			connection = new ConnectionManagerImpl().getConnection();
		    			
		    			st = connection.createStatement();
		    			String sql = ("select status,amt from gift_card where card = '"+card+"';");
		    			System.out.println(sql);
		    			System.out.println("executed");
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
		    			
		    			rs = st.executeQuery(sql);
		    			//connection.close();
		    			while(rs.next()){
		    				System.out.println("inside next()");
		    				String status = rs.getString(1);
		    				System.out.println(status);
		        			int p = rs.getInt(2);
		        			System.out.println(p);
		        			
		        			if(status.equals("unused")){
		        				if(p >= Integer.parseInt(totalprice)){
		        					sql = ("update gift_card set status = 'used' where card = '"+card+"';");
		        	    			System.out.println(sql);
		        	    			int rsupdate = st.executeUpdate(sql);
		        	    			System.out.println("executed");
		        	    			if(rsupdate == 1){
		        	    				pw.write("payment received");
		        	    				//Enter code here

		        	    				connection = new ConnectionManagerImpl().getConnection();
		        			    		PreparedStatement stt = null;
		        			    		ResultSet rss = null;
		        			    		stt = connection.prepareStatement("insert into transactions(uid,items,amount,quantity,address,payment_mode,payment_status,order_status) values(?,?,?,?,?,?,?,?)");
		        			    		stt.setInt(1, 155044);
		        			    		stt.setString(2, Noofperson);
		        			    		stt.setInt(3, Integer.parseInt(totalprice));
		        			    		stt.setInt(4, 2);
		        			    		stt.setString(5, "Jalgaon");
		        			    		stt.setString(6, meth);
		        			    		stt.setString(7, "received");
		        			    		stt.setString(8, "Not packed");
		        			    			
		        			    		int rsupdate1 = stt.executeUpdate();
		        			    		//connection.close();
		        						if(rsupdate1 == 1){
		        						 pw.write("Flight booked");
		        						}
		        			
		        	    			}
		        	    			
		        				}
		        				else{
		        					pw.write("The maximum amount of the gift card is lesser than what you are trying to buy");
		        				}
		        			}
		        			else{
		        				pw.write("This code has been used. Please enter a different code.");
		        			}
		    			}
		    						    					
		    			
		    			
		    	}
		    	catch(Exception e){
		    		e.printStackTrace();
		    	}
			
	}

}
