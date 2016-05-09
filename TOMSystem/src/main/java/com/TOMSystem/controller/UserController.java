package com.TOMSystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.TOMSystem.service.UserService;
import com.TOMSystem.User.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	//The function
	@RequestMapping("/")
	public String setUpProfile(Map<String,Object> map)
	{
		
		return "login";
	}
	
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
		
	
	}
	
}