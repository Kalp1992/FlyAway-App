package com.flyaway.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/ey")
public class ey extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ey() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String meth = request.getParameter("paymet");
		String Noofperson = request.getParameter("Noofperson");
		String totalprice = request.getParameter("totalprice");
		System.out.println(totalprice);
		System.out.println(meth);
		//response.getWriter().write("True");//data returning to jquery
		RequestDispatcher rd = null;
			if(meth.equals("creditmode")){
				rd = request.getRequestDispatcher("credit");
				System.out.println("inside hs");
				HttpSession hs = request.getSession();
				hs.setAttribute("totalprice", totalprice);
				hs.setAttribute("paymet", meth);
				hs.setAttribute("Noofperson", Noofperson);
				rd.forward(request, response);
			}
			if(meth.equals("debitmode")){
				rd = request.getRequestDispatcher("debit");
				HttpSession hs = request.getSession();
				hs.setAttribute("totalprice", totalprice);
				hs.setAttribute("paymet", meth);
				hs.setAttribute("Noofperson", Noofperson);
				rd.forward(request, response);
			}
			if(meth.equals("giftcard")){
				rd = request.getRequestDispatcher("gift");
				HttpSession hs = request.getSession();
				hs.setAttribute("totalprice", totalprice);
				hs.setAttribute("paymet", meth);
				hs.setAttribute("Noofperson", Noofperson);
				rd.forward(request, response);
			}
			if(meth.equals("netbank")){
				rd = request.getRequestDispatcher("netbk");
				HttpSession hs = request.getSession();
				hs.setAttribute("paymet", meth);
				hs.setAttribute("Noofperson", Noofperson);
				hs.setAttribute("totalprice", totalprice);
				rd.forward(request, response);
			}
//			if(meth.equals("cod")){
//				rd = request.getRequestDispatcher("trans");
//				HttpSession hs = request.getSession();
//				hs.setAttribute("paymet", meth);
//				hs.setAttribute("Noofperson", Noofperson);
//				hs.setAttribute("totalprice", totalprice);
//				rd.forward(request, response);
//			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
