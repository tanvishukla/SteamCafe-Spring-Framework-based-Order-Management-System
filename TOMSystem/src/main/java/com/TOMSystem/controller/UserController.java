package com.TOMSystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.service.UserService;


//The main controller
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	//The function
	@RequestMapping("/index")
	public String setUpProfile(Map<String,Object> map)
	{
		//Profile profile= new Profile();
		//map.put("profile", profile);
		//map.put("profileList", profileService.getAllProfiles());
		return "SignUp";
	}
	
//	//Get text profile
//		@RequestMapping(value = "/profile/{id}", params="brief", method=RequestMethod.GET)
//		public String getTextProfile(@PathVariable String id,@RequestParam("brief") String brief,Model model)
//		{
//			//model.addAttribute("str", "true");
//			System.out.println("In text");
//			//Profile profile= new Profile();
//			Profile searchedProfile = profileService.getProfile(id);
//			if(searchedProfile==null)
//			{
//				model.addAttribute("id",id);
//				return "ProfileNotFound";
//			}
//			else{
//			model.addAttribute("profile",searchedProfile);
//			//model.addAttribute("profileList",profileService.getAllProfiles());
//			if(brief.equals("true")){
//			return "getProfileText";
//			}
//			else
//			return "getProfile";
//			}
//		}
//	
//	//get profile by ID on HTML page 
//	@RequestMapping(value = "/profile/{id}", method=RequestMethod.GET)
//	public String getProfileHTML(@PathVariable String id,Model model)
//	{
//		//Profile profile= new Profile();
//		Profile searchedProfile = profileService.getProfile(id);
//		if(searchedProfile==null)
//		{
//			model.addAttribute("id",id);
//			return "ProfileNotFound";
//		}
//		else{
//		model.addAttribute("profile",searchedProfile);
//		model.addAttribute("profileList",profileService.getAllProfiles());
//		return "getProfile";
//		}
//	}
//	
//	//get HTML page for Create profile 
//	@RequestMapping(value = "/profile", method=RequestMethod.GET)
//	public String getCreateProfileHTML(Model model)
//	{
//		Profile searchedProfile=new Profile();		
//		model.addAttribute("profile",searchedProfile);
//		return "createProfile";
//		
//	}
//	
//	//create profile POST method
//	@RequestMapping(value = "/profile", method = {RequestMethod.POST})
//	public String create(@ModelAttribute Profile profile, BindingResult result,@RequestParam String action,Map<String, Object> map)
//	{
//		Profile profileResult=new Profile();
//		if((profileService.getProfile(profile.getId())!=null))
//		{
//			System.out.println("inside already exist"+profile.getId());
//			profileService.update(profile);
//		}
//		else
//		{
//			if(profile.getId()!=null)
//			{
//				profileService.create(profile);
//			}
//		}
//		profileResult=profileService.getProfile(profile.getId());
//			map.put("profile", profileResult);
//			map.put("profileList", profileService.getAllProfiles());
//		     return "getProfile";
//	}
//	
//	
//	//Update or delete after getting profile by ID
//	@RequestMapping(value = {"/profile/{id}","/update"}, method = {RequestMethod.POST})
//	public String updateOrDelete(@ModelAttribute Profile profile, BindingResult result,@RequestParam String action,Map<String, Object> map)
//	{
//		Profile profileResult=new Profile();
//		switch(action.toLowerCase()){
//		case "update":System.out.println("hellooooo");
//			        	profileService.update(profile);
//			        	profileResult= profileService.getProfile(profile.getId());
//			        	map.put("profile", profileResult);
//						map.put("profileList", profileService.getAllProfiles());
//					    // return "getProfile";
//					     break;
//		case "delete":System.out.println("hellooooo delete");
//			profileService.delete(profile.getId());
//			profileResult=new Profile();
//			map.put("profile", profileResult);
//			//map.put("profileList", profileService.getAllProfiles());
//			 //return "createProfile";
//			break;
//		}
//		
//		if(action.equalsIgnoreCase("update"))
//		{
//			return "getProfile";
//		}
//		else
//		{
//			return "createProfile"; 
//		}
//	}
//	
//	//Create or Update profile from URL
//	@RequestMapping(value = "/profile/{id}", params={"firstname","lastname","email","address","organization","aboutMyself"}, method=RequestMethod.GET)
//	public String updateOrCreateFromURL(@PathVariable String id,@RequestParam("firstname") String firstname,
//			@RequestParam("lastname") String lastname,
//			@RequestParam("email") String email,
//			@RequestParam("address") String address,
//			@RequestParam("organization") String organization,
//			@RequestParam("aboutMyself") String aboutMyself,
//			Map<String, Object> map)
//	{
//		
//		Profile profileResult=new Profile(id,firstname,lastname,email,address,organization,aboutMyself);
//		if((profileService.getProfile(id)!=null))
//		{
//			System.out.println("inside already exist"+id);
//			profileService.update(profileResult);
//		}
//		else
//		{
//		profileService.create(profileResult);
//		}
//		//profileResult=profileService.getProfile(profile.getId());
//			map.put("profile", profileResult);
//			map.put("profileList", profileService.getAllProfiles());
//		    return "getProfile";
//	}
//	
//	
//	//Method to delete from URL
//	@RequestMapping(value = "/profile/{id}", method=RequestMethod.DELETE)
//	public String deleteFromURL(@PathVariable String id,Model model,Map<String, Object> map)
//	{
//		Profile profileResult = new Profile();
//		profileResult = profileService.getProfile(id);
//		System.out.println("inside delete");
//		if(profileResult!=null)
//		{
//			profileService.delete(id);
//			return "createProfile";  
//		}
//		else{
//		
//		map.put("id", profileResult);
//		return "ProfileNotFound";
//		}
//	}
//	
//	
	
}
