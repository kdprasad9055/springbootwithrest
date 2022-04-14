package com.lazy.dao;

import java.util.List;

import com.lazy.dto.User;

public interface UserDAO {
	
	List<User> findAll();
public User findOne(int id);
	
	User createUser(User user);
	
	public User deleteById(int id );

}
