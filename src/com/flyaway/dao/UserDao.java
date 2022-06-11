package com.flyaway.dao;

import com.flyaway.dto.User;

public interface UserDao {
	 public boolean RegisterUser(User user);
     public boolean ValidateUser(User user);
}
