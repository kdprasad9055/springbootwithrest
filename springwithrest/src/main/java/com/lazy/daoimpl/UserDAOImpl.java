package com.lazy.daoimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lazy.dao.UserDAO;
import com.lazy.dto.User;
import com.lazy.exception.UserNotFound;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	public static int userCount = 4;
	public static ArrayList<User> users = new ArrayList<User>(); 
	static {
		
		
		User user = new User();
		user.setId(1);
		user.setName("Shiva Prasad");
		user.setDate(new Date());
		users.add(user);
		
		user = new User();
		user.setId(2);
		user.setName("Durga");
		user.setDate(new Date());
		users.add(user);
		
		user = new User();
		user.setId(3);
		user.setName("jhon");
		user.setDate(new Date());
		users.add(user);
		
		user = new User();
		user.setId(4);
		user.setName("Ravi");
		user.setDate(new Date());
		users.add(user);
		
	}

	@Override
	public List<User> findAll() {
		
		return users;
	}

	@Override
	public User findOne(int id) {
		for(User user:users) {
			
			if(user.getId() == id){
				return user ;
			}
			
			
				
		}
		return null ;
		
	}

	@Override
	public User createUser(User user) {
  
		 if(user != null) {
			 user.setId(++userCount);
			 users.add(user);
			 
			 return user ;
		 }

		return null;
	}

	@Override
	public User deleteById(int id) {
		
	for(User user:users) {
			
			if(user.getId() == id){
				users.remove(user);
				return user ;
			}
			
			
				
		}
		return null ;
		
	}
	
	

}
