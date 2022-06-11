<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.Connection"%>
    <%@ page import="com.flyaway.util.ConnectionManagerImpl"%>
     <%@ page import="java.sql.PreparedStatement"%>
     <%@ page import="java.sql.ResultSet"%>
     <%@ page import="java.sql.Statement"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="jquery/jquery-latest.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	 $('body').bind('cut copy paste', function (e) {
	        e.preventDefault();
	    });
	   
	    //Disable mouse right click
	    $("body").on("contextmenu",function(e){
	        return false;
	    });
	    window.history.pushState(null, "", window.location.href);        
        window.onpopstate = function() {
            window.history.pushState(null, "", window.location.href);
        };
});
</script>


</head>
<body>
<% try{
Connection connection;
connection = new ConnectionManagerImpl().getConnection();
String bank = request.getParameter("bank");
String meth = request.getParameter("paymet");
String Noofperson = request.getParameter("Noofperson");
int totalprice = Integer.parseInt(request.getParameter("totalprice"));
String uname = request.getParameter("uname");
System.out.println("netfinal.jsp :"+Noofperson);
Statement st = null;
ResultSet rs = null;
st = connection.createStatement();
String sql = ("select balance from "+bank+" where uname = '"+uname+"';");
System.out.println(sql);
rs = st.executeQuery(sql);
while(rs.next()){
	float bal = rs.getFloat(1);
	System.out.print("bal: "+bal);
	if(bal >= totalprice){
		bal = bal - totalprice;
		Statement pst = connection.createStatement();
		String usql = ("update "+bank+" set balance = "+bal+" where uname = '"+uname+"';");
		int rsupdate = pst.executeUpdate(usql);
		if(rsupdate == 1){
			out.println(" ****Confirmation Page****");
			out.println("                                                                                                                ");
			out.print("  Payment received");
			//Enter code here
			Connection connection1;
            connection1 = new ConnectionManagerImpl().getConnection();
    		 
    		PreparedStatement stt = null;
    		ResultSet rss = null;
    		stt = connection1.prepareStatement("insert into transactions(uid,Noofperson,amount,Noofticket,address,payment_mode,payment_status,booking_status) values(?,?,?,?,?,?,?,?)");
    		stt.setInt(1, 155044);
    		stt.setString(2, Noofperson);
    		stt.setInt(3, totalprice);
    		stt.setInt(4, 1);
    		stt.setString(5, "Jalgaon");
    		stt.setString(6, meth);
    		stt.setString(7, "received");
    		stt.setString(8, "Booked");
    			
    		int rsupdate1 = stt.executeUpdate();
			if(rsupdate1 == 1){
			 out.write(". Flight booked");
			 Connection connection3;
	         connection3 = new ConnectionManagerImpl().getConnection();
	        
	    	 String query= "select * from flights where FLIGHT_NO = 2";
	    	 Statement stmt=connection3.createStatement();
	    	 ResultSet rss2= stmt.executeQuery(query);
	    	 while(rss2.next()){
	    	  out.println("Flight Detail..... "); 
	    	  out.println("*Flight No: " +rss2.getInt("FLIGHT_NO"));
	    	  out.println("*Source: " +rss2.getString("SOURCE"));
	    	  out.println("*Destination: " +rss2.getString("DESTINATION"));
	    	  out.println("*Start Date: "+rss2.getString("START_DATE"));
	    	  out.println ("*End Date: " +rss2.getString("END_DATE"));
	    	  out.println ("*Departure Time: " +rss2.getString("DEPARTURE_TIME"));
	    	  out.println ("*Arrival Time: " +rss2.getString("ARRIVAL_TIME"));
	    	  out.println("*Total Amount paid:" +rss2.getInt("TICKET_PRICE"));
	    	     	 	 
	    	 }
	    	 String query2= "select * from seats where FLIGHT_NO =2";
	    	 Statement stmt2=connection3.createStatement();
	    	 ResultSet rss4= stmt2.executeQuery(query2);
	    	 while(rss4.next()){
	    	  out.println("Seating Arrangmemnt....");
	    	  out.println("*Seat No: " +rss4.getInt("SEAT_NO"));
	    	  
			}
	    	 
	    	 String query1= "select * from passengers where PHONE ='9665420981'";
	    	 Statement stmt1=connection3.createStatement();
	    	 ResultSet rss3= stmt1.executeQuery(query1);
	    	 while(rss3.next()){
	    	  out.println("Passenger Detail....");
	    	  out.println("*Passenger Name: " +rss3.getString("FULLNAME"));
	    	  out.println("*Date of Birth: " +rss3.getString("DATE_OF_BIRTH"));
	    	  out.println("*Email Id: " +rss3.getString("EMAIL"));
	    	  out.println("*Contact No: "+rss3.getString("PHONE"));
	    	  out.println ("*Address: " +rss3.getString("ADDRESS"));
	    	
			}
	    	 
		}
		else{
			out.print("Insufficient balance");
		}
		
	}
	else{
		out.print("Not enough balance. Transaction failed!");
	}
	}}}catch(NumberFormatException nfe){
		 nfe.printStackTrace();
	}
%>
</body>
</html>