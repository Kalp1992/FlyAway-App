package com.flyaway.dao;

import java.sql.SQLException;
import java.util.Set;

import com.flyaway.dto.Flight;

public interface FlightDao {
	Set<Flight> findFlight(String source, String destination, String startdate ) throws SQLException ;
}
