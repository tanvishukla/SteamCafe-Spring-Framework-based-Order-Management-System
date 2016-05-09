package com.TOMSystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.TOMSystem.User.User;
import com.TOMSystem.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	//The function
	@RequestMapping("/")
	public String HomePage(Map<String,Object> map)
	{
		
		return "login";
	}
	
	@RequestMapping(value="/LoginPage", method=RequestMethod.POST)
	public String GetLoginPage(Map<String,Object> map)
	{
		
		return "UserHome";
	}
	
	@RequestMapping(value="/SignupPage", method=RequestMethod.POST)
	public String GetSignupPage(Map<String,Object> map)
	{
		
		return "SignUp";
	}
	
	@RequestMapping(value="/Signup", method=RequestMethod.POST)
	public String Signup(@ModelAttribute User user, BindingResult result,@RequestParam String action,Map<String, Object> map)
	{
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		User userResult=new User();
		if((userService.getUser(user.getEmail())!=null))
		{
			System.out.println("inside already exist"+user.getEmail());
			//userService.edit(user);
			///Added to map***************
			map.put("userAlreadyExistError","User already exist!!!Either login or signup with new emailId");
			return "SignUp";
		}
		else
		{
			userService.add(user);
			System.out.println("Added to database successfully");
			 userResult=userService.getUser(user.getEmail());
				map.put("user", userResult);
				//map.put("profileList", profileService.getAllProfiles());
			     return "login";
		}
		
		   
	}
	
}