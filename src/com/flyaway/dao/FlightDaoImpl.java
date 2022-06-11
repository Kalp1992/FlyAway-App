package com.flyaway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.flyaway.dto.Flight;
import com.flyaway.util.ConnectionManagerImpl;


public class FlightDaoImpl implements FlightDao{
private Connection connection;
	
	public FlightDaoImpl() throws SQLException {
		connection = new ConnectionManagerImpl().getConnection();
	}

	@Override
	public Set<Flight> findFlight(String source, String destination, String startdate) throws SQLException {
		// Read (Retrieve) Operation using PreparedStatement
		         Set<Flight> flights= new HashSet<Flight>();
				String getFlightFormat = "SELECT *FROM flights WHERE  SOURCE = ? and DESTINATION = ? and START_DATE=?  ";
				try (PreparedStatement preparedStatement = connection.prepareStatement(getFlightFormat);) {
					preparedStatement.setString(1, source);
					preparedStatement.setString(2, destination);
					preparedStatement.setString(3, startdate);
				    ResultSet rs = preparedStatement.executeQuery();

					if (!rs.isBeforeFirst()) {
						return null;
					}

					Flight flight = new Flight();
					while (rs.next()) {
						flight.setFlightNo(rs.getInt("FLIGHT_NO"));
						flight.setSource(rs.getString("SOURCE"));
						flight.setDestination(rs.getString("DESTINATION"));
						flight.setStartdate22(rs.getString("START_DATE"));
						flight.setEndDate(rs.getString("END_DATE"));
						flight.setDepartureTime(rs.getString("DEPARTURE_TIME"));
						flight.setArrivalTime(rs.getString("ARRIVAL_TIME"));
						flight.setNoPerson(rs.getInt("NO_OF_PERSON"));
						flight.setTicketPrice(rs.getDouble("TICKET_PRICE"));
						flights.add(flight);
						
					}
					
				}
				return flights;
	}
}
