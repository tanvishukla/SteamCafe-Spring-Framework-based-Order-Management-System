package com.TOMSystem.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.model.InvoiceDetails;
import com.TOMSystem.model.Item;
import com.TOMSystem.service.InvoiceDetailsService;


import com.TOMSystem.model.Invoice;
import com.TOMSystem.model.Item;
import com.TOMSystem.service.InvoiceService;

import com.TOMSystem.service.ItemService;

@Controller
public class CartController {

	@Autowired
	private	ItemService itemService;
	
	@Autowired

	private InvoiceDetailsService invoiceDetailsService;


	private InvoiceService invoiceService;
	

	//Creating an list of all items in the cart
	//public HashMap<Item,Integer> cart = new HashMap<Item,Integer>();
	public ArrayList<Item> cart = new ArrayList<Item>();
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
			//cart.remove(tempItem);
			for(int i=0;i<cart.size();i++)
				if(cart.get(i).getName().equals(tempItem.getName())){
					cart.remove(i);
					break;
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

	
	/*
	 * Add functionality HERE 
	 */
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(@RequestBody String item_id, Model model, HttpServletRequest request){
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
	 * Add functionality HERE 
	 */
	@RequestMapping(value = "/earliestPickUp", method = RequestMethod.POST)
	public String earliestPickUp(@RequestBody String item_id, Model model, HttpServletRequest request){
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
	 * To display checkout page 
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String confirmOrder(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null)
		{
			session.setAttribute("cart", cart);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        Calendar startTime = Calendar.getInstance();
	        System.out.println("Current Time: "+dateFormat.format(startTime.getTime()));
			
			int totalPrepTime = 0;
			for(int i=0; i<cart.size(); i++){
				totalPrepTime += cart.get(i).getPrep_time();
			}
			
			Calendar endTime = Calendar.getInstance();
			endTime.add(Calendar.MINUTE, totalPrepTime);
			System.out.println("End Time: "+endTime.getTime());
			/*while(checkAvailability(startTime.getTime(), endTime.getTime()) == false)
			{
				endTime.add(Calendar.MINUTE, 1);
				startTime.add(Calendar.MINUTE, 1);
				if((endTime.getTime().getHours() == 21) && (endTime.getTime().getMinutes() == 1)){
					startTime.add(Calendar.MINUTE, 479 + + totalPrepTime);
					endTime.add(Calendar.MINUTE, 479 + totalPrepTime);
				}
			}
			*/System.out.println(startTime.getTime());
			
			//System.out.println(formatDateToString(tempTime));
			model.addAttribute("pickup_time", formatDateToString(endTime.getTime()));
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			model.addAttribute("cart", session.getAttribute("cart"));
			//System.out.println("Cart:"+ cart.size());
			return "checkout";
		}
		else
		{
			return "login";
		}
	}
	
	/*
	 * Convert date to PST format
	 * 
	 */
	public static String formatDateToString(Date date) {
        
		String timeZone="PST";
		// null check
        if (date == null) return null;
        // create SimpleDateFormat object with input format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // default system timezone if passed null or empty
        timeZone = Calendar.getInstance().getTimeZone().getID();
        // set timezone to SimpleDateFormat
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        // return Date in required format with timezone as String
        return sdf.format(date);
    }

	
	/*
	 * Check availability of chef
	 */
	
	public boolean checkAvailability(Date startTime, Date endTime) {
        int chefCount = 0;
        List<Invoice> tempInvoiceList = invoiceService.getAllInvoice();
		for(int i=0; i<tempInvoiceList.size(); i++){
			
			if( ( startTime.before(tempInvoiceList.get(i).getStartTime()) && endTime.after(tempInvoiceList.get(i).getStartTime()) )
					|| ( startTime.before(tempInvoiceList.get(i).getEndTime()) && endTime.after(tempInvoiceList.get(i).getStartTime()) )
					|| ( startTime.before(tempInvoiceList.get(i).getEndTime()) && endTime.after(tempInvoiceList.get(i).getEndTime()) ) ){
				chefCount++;
				if(chefCount == 3)
					return false;
			}
		}		
		return true;
    }
	
	

}
