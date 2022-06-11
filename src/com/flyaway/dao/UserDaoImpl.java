package com.flyaway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flyaway.dto.User;
import com.flyaway.util.ConnectionManagerImpl;

public class UserDaoImpl implements UserDao{
	private Connection connection;
	public UserDaoImpl() throws SQLException {
		connection = new ConnectionManagerImpl().getConnection();
	}
	@Override
	public boolean RegisterUser(User user) {
		boolean isUserRegistered=false;
		String query="insert into users values(?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement= connection.prepareStatement(query);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUserName());
			statement.setString(4, user.getPassword());
			int val= statement.executeUpdate();
			if(val>0) {
				isUserRegistered=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUserRegistered;
	}

	@Override
	public boolean ValidateUser(User user) {
		boolean isUserValid=false;
		String query="select * from users where USERNAME=? and PASSWORD =? ";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			ResultSet resultset=statement.executeQuery();
			if(resultset.next()) {
				isUserValid=true;
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return isUserValid;
	}

	}


