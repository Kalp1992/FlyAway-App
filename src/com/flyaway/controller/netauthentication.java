package com.flyaway.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyaway.util.ConnectionManagerImpl;

//@WebServlet("/netauthentication")
public class netauthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public netauthentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bank = request.getParameter("bank");
		String totalprice = request.getParameter("totalprice");
		String uname = request.getParameter("uname");
		String pswd = request.getParameter("pswd");

		String Noofperson = request.getParameter("Noofperson");
		String meth = request.getParameter("paymet");
		
		try{
		    Connection connection;
			connection = new ConnectionManagerImpl().getConnection();
			
			Statement st = null;
			ResultSet rs = null;
			st = connection.createStatement();
			String sql = ("select pswd from "+bank+" where uname = '"+uname+"';");
			System.out.println(sql);
			rs = st.executeQuery(sql);
			//connection.close();
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
			if(rs != null){
				while(rs.next()){
					String pwd = rs.getString(1);
						if(pwd.equals(pswd)){
							pw.write("Pay a sum of Rs."+totalprice+". Click 'Proceed' to confirm");
							pw.write("<form method='post' action = 'netfinal.jsp'>");
							pw.write("<input type='hidden' name='bank' value='"+bank+"'>");
							pw.write("<input type='hidden' name='totalprice' value='"+totalprice+"'>");
							pw.write("<input type='hidden' name='paymet' value='"+meth+"'>");
							pw.write("<input type='hidden' name='Noofperson' value='"+Noofperson+"'>");
							pw.write("<input type='hidden' name='uname' value='"+uname+"'>");
							pw.write("<input type = 'submit' value = 'Proceed'");
							pw.write("</form>");
						}
					else{
						pw.write("Wrong password");
					}
					
				}
			}
			else{
				pw.write("Please check your username");
			}
			pw.write("</body></html>");
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
