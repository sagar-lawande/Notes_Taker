package com.spring.main.services.userServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.main.entity.User;
import com.spring.main.repository.UserRepository;

@Service
public  class UserServicesImpl implements UserServices{

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public boolean addUser(User user) {
		boolean status=false;
		try {
			userRepository.save(user);
			status=true;
		}catch(Exception e) {
			e.printStackTrace();
			status=false;
		}
		
		return status;
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public User modifyUser(int id,User newUser) {
		
		User userData=userRepository.findById((long) id).orElse(null);
		if (userData != null) {
	        userData.setName(newUser.getName());
	        userData.setEmail(newUser.getEmail());
	        userData.setPassword(newUser.getPassword());

	        return userRepository.save(userData);   // save existing object
	    } else {
	        throw new RuntimeException("User not found with id " + id);
	    }
	}

	@Override
	public boolean deleteUser(int id) {
		User user = userRepository.findById((long) id).orElse(null);

	    if (user != null) {
	        userRepository.deleteById((long) id);
	        return true;
	    }
	    return false;
		
	}

	@Override
	public Optional<User> getUser(int id){
		
		return userRepository.findById((long) id);
	}

	@Override
	public User getUser(String email, String password) {
	    try {
	        User validUser = userRepository.findByEmail(email);

	        if (validUser != null && password.equals(validUser.getPassword())) {
	            System.out.println("Login Successfully..");
	            return validUser;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Login Fails");
	    }

	    return null;
	}
}
		

	
	
	


