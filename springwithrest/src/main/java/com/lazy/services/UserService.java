package com.lazy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazy.dao.UserDAO;
import com.lazy.dto.User;
import com.lazy.exception.UserNotFound;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO ;
	
	public List<User> findall(){
		return userDAO.findAll();
	}
	
	public User findOne(int id) {
		User user = userDAO.findOne(id);
		if(user == null) {
			 throw new UserNotFound(id + "User id Not Found");
		}
		return user ;
	}
	
	public User addUser(User user) {
		return userDAO.createUser(user);
	}
	
	public User deleteId(int id) {
		return userDAO.deleteById(id);
	}

}
