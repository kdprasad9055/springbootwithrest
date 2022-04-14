package com.lazy.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lazy.dto.User;
import com.lazy.exception.UserNotFound;
import com.lazy.services.UserService;

@RestController
public class PageController {

	@Autowired
	private UserService userService ;
	// Sample Rest Controller 
	
	@GetMapping( path="/hello")
	public String index() {
		
		return "Hello world" ;
	}
	
	// Simple Object Return Rest Controller 
	
	@GetMapping( path="/user")
	public User use() {
		User user = new User();
		user.setId(1);
		user.setName("Shiva Prasad");
		user.setDate(new Date());
		return user ;
	}
	
	// to Get All User  
	@GetMapping("/users")
	public List<User> allUsers(){
		
		return userService.findall();
	}
	
	// to Get Single User 
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Object> singleUser(@PathVariable int id) {
	User user =	userService.findOne(id);
	
	//HATEOS Concepect to add a other Links into in the Method 
	  EntityModel<User> resource = EntityModel.of(user);
	  resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allUsers())
			  .withRel("allusers"));
	 
		return new ResponseEntity<>(resource ,HttpStatus.OK) ;
	}
	
	

	// add a user 
	
	@PostMapping("/users")
	public ResponseEntity<Object> add(@Valid @RequestBody User user) {
		User saveUser =  userService.addUser(user);
		
		// this code for http response status code is return 
		
	URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("{/id}")
			.buildAndExpand(saveUser.getId()).toUri();
	
	return  ResponseEntity.created(location).build();
		   
	}
	
	@DeleteMapping("/user/{id}")
	public String DeleteById(@PathVariable int id) {
		
		User user = userService.deleteId(id);
		if(user == null ) {
			throw new UserNotFound(id +" This User Not Found ");
		}
		
		return "Deleted Sucessfully ";
	}
	
	
}
