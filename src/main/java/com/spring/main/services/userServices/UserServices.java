package com.spring.main.services.userServices;

import java.util.List;
import java.util.Optional;

import com.spring.main.entity.User;

public interface UserServices {
	
	public boolean addUser(User user);
	
	public List<User> getAllUser();
	
	public Optional<User> getUser(int id);
	
	public User getUser(String email,String password);
	
	public User modifyUser(int id,User newUser);
	
	public boolean deleteUser(int id);
	

}
