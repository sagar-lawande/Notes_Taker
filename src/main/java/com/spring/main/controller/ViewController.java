package com.spring.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.main.entity.User;
import com.spring.main.services.userServices.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class ViewController {
	
	@Autowired
	private UserServices userServices;
	
	
	@GetMapping("/openRegPage")
	public String openRegPage(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitRegUser(@ModelAttribute("user")User user,Model model) {
		boolean status=userServices.addUser(user);
		if(status) {
			model.addAttribute("sucessMsg","User Registered Sucessfully..");
			System.out.println("Registation Done  Sucessfully");
			
		}else {
			model.addAttribute("errMsg","User Registration failed..");
			System.out.println("Registation Failed");
		}
		model.addAttribute("user", new User()); // reset form
	    return "login";
		
	}
	
	@GetMapping("/openLogPage")
	public String openLoginPage(Model model) {
		model.addAttribute("user",new User());
		return "login";
	}
	
	
	
	
	
	@PostMapping("/logForm")
	public String submitLoginForm(@ModelAttribute("user") User user,
	                              Model model,
	                              HttpSession session) {

	    User validUser = userServices.getUser(user.getEmail(), user.getPassword());

	    if (validUser != null) {

	        // ✅ save user in session
	        session.setAttribute("loggedInUser", validUser);

	        System.out.println("User Login Successfully...");
	        return "redirect:/profile";

	    } else {
	        model.addAttribute("errMsg", "Invalid Email or Password");
	        return "login";
	    }
	}
}
