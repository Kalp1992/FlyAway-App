<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.Set"%>
     <%@page import="com.flyaway.dto.Flight"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
table{
border: 1px solid;
width:100%;
}
th,td{
border: 1px solid;
}
h1{
text-align:center;
}
</style>
<body>
<br>
<h1>Flight List</h1>
<table class="table table-striped" >
  <thead>
    <tr>
      <th scope="col">Flight No.</th>
      <th scope="col">Source</th>
      <th scope="col">Destination</th>
      <th scope="col">Start Date</th>
      <th scope="col">End Date</th>
      <th scope="col">Departure Time</th>
      <th scope="col">Arrival Time</th>
      <th scope="col">No. of person</th>
      <th scope="col">Ticket price</th>
      <th scope="col">Book Flight</th>
    </tr>
  </thead>
  <tbody>
<% 
     Set<Flight> flights= (Set<Flight>)request.getAttribute("flightlist");
       for(Flight flight : flights) { %>
       
    	
       <tr>
      <th scope="row"><%=flight.getFlightNo()%></th>
      <td><%=flight.getSource()%></td>
      <td><%=flight.getDestination()%></td>
      <td><%=flight.getStartdate22()%></td>
      <td><%=flight.getEndDate()%></td>
      <td><%=flight.getDepartureTime()%></td>
      <td><%=flight.getArrivalTime()%></td>
      <td><%=flight.getNoPerson()%></td>
      <td><%=flight.getTicketPrice()%></td>
       <td><a href="BookFlight.html">Book Now</a></td>
      
      
    </tr>
<%
       }
   %>
  </tbody>
</table>
  

</body>
</html>