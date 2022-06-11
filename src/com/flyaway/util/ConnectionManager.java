package com.flyaway.util;

import java.sql.Connection;
import java.sql.SQLException;


public interface ConnectionManager {
	  public Connection getConnection() throws SQLException;
}
