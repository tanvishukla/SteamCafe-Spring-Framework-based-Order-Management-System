package com.TOMSystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


import com.TOMSystem.User.User;
import com.TOMSystem.service.UserService;
import com.TOMSystem.User.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	//The function
	@RequestMapping("/")
<<<<<<< HEAD
	public String setUpProfile(Map<String,Object> map)
=======
	public String HomePage(Map<String,Object> map)
>>>>>>> 799d81ffefb0bdf9d1cec927e69fc982962b510a
	{
		
		return "login";
	}
	
<<<<<<< HEAD
	@RequestMapping(value="/authUser" , method = RequestMethod.POST)
	//public @ResponseBody 
	String authUser(@ModelAttribute User user, BindingResult result,@RequestParam String action,Map<String, Object> map,Model model){
		
		
		System.out.println("In authUser");
		System.out.println("User email is :"+user.getEmail());
		System.out.println("User password is :"+user.getPassword());
		User searchedUser = userService.getUser(user.getEmail());
		//System.out.println("User password is :"+searchedUser.getName());
		if(searchedUser==null)
		{
			model.addAttribute("email","Invalid Login");
			
			return "login";
			
			
		}
		else{
		model.addAttribute("email",user.getEmail());
		return "menu";
		}
		
	
=======
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
		
		   
>>>>>>> 799d81ffefb0bdf9d1cec927e69fc982962b510a
	}
	
}