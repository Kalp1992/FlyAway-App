package com.flyaway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.flyaway.dto.Flight;
import com.flyaway.dto.Passenger;
import com.flyaway.util.ConnectionManagerImpl;

public class PassengerDaoImpl implements PassengerDao {
	private Connection connection;
	public PassengerDaoImpl() throws SQLException {
		connection = new ConnectionManagerImpl().getConnection();
	}
	@Override
	public boolean createPassenger(Passenger passenger) {
		boolean isPassengerCreated=false;
		String query="insert into passengers values(?, ?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement= connection.prepareStatement(query);
			
			statement.setString(1, passenger.getPassangerName());
			statement.setString(2, passenger.getDoB());
			statement.setString(3, passenger.getEmail());
			statement.setString(4, passenger.getPhone());
			statement.setString(5, passenger.getAddress());
			int val= statement.executeUpdate();
			if(val>0) {
				isPassengerCreated=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isPassengerCreated;
	}
	
	}


