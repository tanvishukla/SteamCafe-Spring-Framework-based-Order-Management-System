package com.TOMSystem.controller;

import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.model.Item;
import com.TOMSystem.model.User;
import com.TOMSystem.model.*;

public class OrderController {

	private com.TOMSystem.service.ItemService itemservice;
	
	@RequestMapping(value = "/addToOrder",  method=RequestMethod.POST)
	public String addItemToOrder(@ModelAttribute Item item, BindingResult result,@RequestParam String action,Map<String, Object> map,Model model){
		
		
		
		return null;
	}
	
}
