package com.flyaway.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {
	 public Connection getConnection() throws SQLException{
		 Connection connection=null;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/flyaway", "Kalp1992", "Kalp#1992");
		    System.out.println("Connected");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		 
		 return connection;
	}
}
