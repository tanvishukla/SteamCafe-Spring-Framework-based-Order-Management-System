package com.TOMSystem.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.TOMSystem.model.Item;
import com.TOMSystem.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;


	/*
	 * 
	 * To set up items page
	 * 
	 */
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String setupItemsForm(Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null && !session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			Item item= new Item();

			model.addAttribute("item", item);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("drinksList", itemService.getDrinks());
			model.addAttribute("appetizerList", itemService.getAppetizers());
			model.addAttribute("maincourseList", itemService.getMainCourse());
			model.addAttribute("dessertList", itemService.getDesserts());
			//ArrayList<CartItems> cart= new ArrayList<CartItems>();
			//cart=(ArrayList<CartItems>)session.getAttribute("cart");
			model.addAttribute("cart",CartController.cartItemsList );
			System.out.println("Session Cart is****** "+CartController.cartItemsList);
			
			return "items";
		}
		else
			return "login";
	}

}
