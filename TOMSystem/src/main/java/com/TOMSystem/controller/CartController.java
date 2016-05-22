package com.TOMSystem.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.model.InvoiceDetails;
import com.TOMSystem.model.Item;
import com.TOMSystem.service.InvoiceDetailsService;
import com.TOMSystem.service.ItemService;

@Controller
public class CartController {

	@Autowired
	private	ItemService itemService;
	
	@Autowired
	private InvoiceDetailsService invoiceDetailsService;

	//Creating an list of all items in the cart
	public HashMap<Item,Integer> cart = new HashMap<Item,Integer>();
	
	//public ArrayList<InvoiceDetails> cart = new ArrayList<InvoiceDetails>();
	/*
	 * To add an element in the cart 
	 * First check whether the element exists. If YES?
	 * increment count, else add 
	 */
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addItemToCart(@RequestParam("item_id") int item_id,@RequestParam("item_quantity") int item_quantity, Model model, HttpServletRequest request){


		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null)
		{
			System.out.println("Item Id is....."+Integer.valueOf(item_id));
			System.out.println("Item quantity is...."+Integer.valueOf(item_quantity));
			
			//int item_id1=Integer.valueOf(item_id);
			//int item_quantity1= Integer.valueOf(item_quantity);
			//InvoiceDetails tempInvoiceItem= new InvoiceDetails(); 
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));
			cart.put(tempItem, item_quantity);
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
			cart.remove(tempItem);
				/*if(cart.get(i).getName().equals(tempItem.getName())){
					cart.remove(i);
					break;
				}*/
			
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
