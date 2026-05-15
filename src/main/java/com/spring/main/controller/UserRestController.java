package com.spring.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.main.entity.User;

import com.spring.main.services.userServices.UserServices;

@RestController
public class UserRestController {
	
	@Autowired
	private UserServices userServices;
	
	
	@PostMapping("/user")
	public  boolean  addUser(@RequestBody User user) {
		System.out.println("Method for Add Data ... Add User....");
		return userServices.addUser(user);
		
	}

	@GetMapping("/user")
     public List<User> getAllUser() {
		System.out.println("Method for fetch All user....Get AllUsers...");
		return userServices.getAllUser();
		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> modifyUser(@PathVariable int  id,@RequestBody User user) {
		System.out.println("Method for Modify user Data..");
		User newuser=userServices.getUser(id).orElse(null);          //ResponseEntity is used to represent entire HTTP response
		if(newuser!=null) {
			return ResponseEntity.ok().body(newuser);
			
		}else {
			return ResponseEntity.notFound().build();
		}
		
		
		
	}
	@DeleteMapping("/user{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		System.out.println("Method for delete Data...or user...");
		boolean status = userServices.deleteUser(id);

	    if (status) {
	        return ResponseEntity.ok("User deleted successfully");
	    } else {
	        return ResponseEntity.status(404).body("User not found, deletion failed");
	    }
		
	}
	
	
	
	
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int  id) 
	{                           
		System.out.println("Get user by Id");                                                            //@PathVariable is used to take data from URL ..
	User user=userServices.getUser(id).orElse(null);          //ResponseEntity is used to represent entire HTTP response
	if(user!=null) {
		return ResponseEntity.ok().body(user);
		
	}else {
		return ResponseEntity.notFound().build();
	}
	
		
	}
	
	

}
