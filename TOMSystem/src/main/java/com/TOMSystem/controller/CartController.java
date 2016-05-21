package com.TOMSystem.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.TOMSystem.model.Item;
import com.TOMSystem.service.ItemService;

@Controller
public class CartController {

	@Autowired
	private	ItemService itemService;
	
	//Creating an list of all items in the cart
	public ArrayList<Item> cart = new ArrayList<Item>();
	
	/*
	 * To add an element in the cart 
	 * First check whether the element exists. If YES?
	 * increment count, else add 
	 */
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addItemToCart(@RequestBody String item_id, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null)
		{
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));
			cart.add(tempItem);
			session.setAttribute("cart", cart);

			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			model.addAttribute("cart", session.getAttribute("cart"));
			//System.out.println("Cart:"+ cart.size());
			return "items";
		}
		else
		{
			return "login";
		}
	}
	
	/*
	 * To remove an element from the cart 
	 */
	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public String removeItemFromCart(@RequestBody String item_id, Model model,HttpServletRequest request){

		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null)
		{
			//Fetch the item from table
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));
			
			//Remove the selected item
			for(int i=0; i<cart.size(); i++){						
				if(cart.get(i).getName().equals(tempItem.getName())){
					cart.remove(i);
					break;
				}
			}
			System.out.println(cart);

			session.setAttribute("cart", cart);

			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			model.addAttribute("cart", session.getAttribute("cart"));
			System.out.println("Cart:"+ cart.size());
			return "items";
		}
		else
		{
			return "login";
		}
	}
	
	/*
	 * 
	 * Add items to invoice with dates
	 * 
	 */
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String proceedToCheckout(@RequestBody String item_id, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null)
		{
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));
			cart.add(tempItem);
			session.setAttribute("cart", cart);

			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			model.addAttribute("cart", session.getAttribute("cart"));
			//System.out.println("Cart:"+ cart.size());
			return "items";
		}
		else
		{
			return "login";
		}
	}
	
	
}
