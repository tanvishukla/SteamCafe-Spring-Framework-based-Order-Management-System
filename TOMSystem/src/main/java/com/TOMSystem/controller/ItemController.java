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

import com.TOMSystem.*;
import com.TOMSystem.User.User;
import com.TOMSystem.service.ItemService;
import com.TOMSystem.Item.*;
@Controller
public class ItemController {

	//private com.TOMSystem.service.ItemService itemservice;
	@Autowired
	private ItemService itemService;
    
	@RequestMapping(value="/displayItems" , method = RequestMethod.GET)
	public String displayItems(Map<String,Object> map, Model model)

	{
		
		
		if(itemService.getDrinks().size()!=0)
		{
		model.addAttribute("drinkList", itemService.getDrinks());
		System.out.println("hiiiiiiiii***********8");
		}
		
		
		
		return "UserHome";
	}

	
	
	
}
