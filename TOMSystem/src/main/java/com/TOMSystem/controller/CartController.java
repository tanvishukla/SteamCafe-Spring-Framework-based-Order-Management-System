package com.TOMSystem.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.model.Invoice;
import com.TOMSystem.model.InvoiceDetails;
import com.TOMSystem.model.Item;
import com.TOMSystem.service.InvoiceDetailsService;
import com.TOMSystem.service.InvoiceService;
import com.TOMSystem.service.ItemService;
import com.thoughtworks.xstream.persistence.StreamStrategy;



@Controller
public class CartController {

	@Autowired
	private	ItemService itemService;
	
	@Autowired
	private InvoiceDetailsService invoiceDetailsService;

	@Autowired
	private InvoiceService invoiceService;
	

	//Creating an list of all items in the cart
	//public HashMap<Item,Integer> cart = new HashMap<Item,Integer>();
	public ArrayList<Item> cart = new ArrayList<Item>();
	
	public ArrayList<CartItems> cartItemsList= new ArrayList<CartItems>();
	
	
	/*
	 * To add an element in the cart 
	 * First check whether the element exists. If YES?
	 * increment count, else add 
	 */
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addItemToCart(@RequestParam("item_id") int item_id,@RequestParam("item_quantity") int item_quantity, Map<String, Object> map,Model model, HttpServletRequest request) throws NoSuchFieldException, SecurityException{


		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null)
		{
			System.out.println("Item Id is....."+item_id);
			System.out.println("Item quantity is...."+item_quantity);
			
			
			
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));
			
			CartItems tempCartItem=new CartItems();
			tempCartItem.setItemId(tempItem.getId());
			tempCartItem.setItemName(tempItem.getName());
			tempCartItem.setPrice(tempItem.getUnit_price()*item_quantity);
			tempCartItem.setItem_PrepTime(tempItem.getPrep_time());
			tempCartItem.setQuantity(item_quantity);
			
			cartItemsList.add(tempCartItem);
			session.setAttribute("cart", cartItemsList);
		
					
			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			model.addAttribute("cart", session.getAttribute("cart"));
			//System.out.println("Cart:"+ cart.size());
			map.put("drinksList", itemService.getDrinks());
			map.put("appetizerList", itemService.getAppetizers());
			map.put("maincourseList", itemService.getMainCourse());
			map.put("dessertList", itemService.getDesserts());
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
	public String removeItemFromCart(@RequestBody String item_id, Map<String, Object> map,Model model,HttpServletRequest request){

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
			
			map.put("drinksList", itemService.getDrinks());
			map.put("appetizerList", itemService.getAppetizers());
			map.put("maincourseList", itemService.getMainCourse());
			map.put("dessertList", itemService.getDesserts());
			return "items";
		}
		else
		{
			return "login";
		}
	}

	

	
	//Takes time and date from customer and sets the invoice
		@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
		public String confirmOrder(@RequestParam("hours") int hours,@RequestParam("minutes") int minutes, @RequestParam("date") String date, Map<String, Object> map,HttpServletRequest request) throws ParseException{
			HttpSession session = request.getSession();
			if(session.getAttribute("userId")!=null)
			{
				
				System.out.println("Before----------------->"+date);
				String[] strArr = date.split("/");			
				int year = Integer.parseInt(strArr[2]);
				int month = Integer.parseInt(strArr[0])-1;
				int day = Integer.parseInt(strArr[1]);
				
			    Calendar cal = Calendar.getInstance();
			    cal.set(year, month, day, hours, minutes,0);
				Date d= cal.getTime();
				System.out.println("After----------------->"+d);
				
				Invoice tempInvoice = new Invoice();
				tempInvoice.setEmail(session.getAttribute("userId").toString());
				tempInvoice.setPickupTime(d);
				tempInvoice.setEndTime((Date)session.getAttribute("pickup"));
				tempInvoice.setStartTime((Date)session.getAttribute("start"));
				tempInvoice.setOrderDate((Date)session.getAttribute("start"));
				tempInvoice.setPrep_Time((Integer)session.getAttribute("totalPrepTime"));
				tempInvoice.setStatus("Queued");
				invoiceService.add(tempInvoice);
				
				
				
				//***************Adding to invoice Deatils table***********///
				
				
				System.out.println("Invoice id is----"+tempInvoice.getInvoice_id());
				
				InvoiceDetails invoiceDetails=new InvoiceDetails(); 
				invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());
				
				for(int i=0;i<cartItemsList.size();i++)
				{
					invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());
					invoiceDetails.setItem_id(cartItemsList.get(i).getItemId());
					invoiceDetails.setPrice(cartItemsList.get(i).getPrice());
					invoiceDetails.setQuantity((cartItemsList.get(i).getQuantity()));
					invoiceDetailsService.add(invoiceDetails);
				}
				 
				
				map.put("drinksList", itemService.getDrinks());
				map.put("appetizerList", itemService.getAppetizers());
				map.put("maincourseList", itemService.getMainCourse());
				map.put("dessertList", itemService.getDesserts());
				
				return "OrderConfirmationPage";
			}
			else
			{
				return "login";
			}
		}
		
	/*
	 * Add functionality HERE 
	 */
		//Provides earliest pickup time and sets the invoice
		@RequestMapping(value = "/earliestPickUp", method = RequestMethod.POST)
		public String earliestPickUp(@RequestBody String item_id, Model model,Map<String, Object> map, HttpServletRequest request){
			HttpSession session = request.getSession();
			
			System.out.println("in earliest pickup");
			
			
			if(session.getAttribute("userId")!=null)
			{
				System.out.println(session.getAttribute("pickup").toString());
				System.out.println(session.getAttribute("userId").toString());
				Invoice tempInvoice = new Invoice();
				tempInvoice.setEmail(session.getAttribute("userId").toString());
				tempInvoice.setPickupTime((Date)session.getAttribute("pickup"));
				tempInvoice.setEndTime((Date)session.getAttribute("pickup"));
				tempInvoice.setStartTime((Date)session.getAttribute("start"));
				tempInvoice.setOrderDate((Date)session.getAttribute("orderDate"));
				tempInvoice.setPrep_Time((Integer)session.getAttribute("totalPrepTime"));
				tempInvoice.setStatus("Queued");
				invoiceService.add(tempInvoice);	
				
				//***************Adding to invoice Deatils table***********///
				
				InvoiceDetails invoiceDetails=new InvoiceDetails(); 
				invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());
				
				for(int i=0;i<cartItemsList.size();i++)
				{
					invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());
					invoiceDetails.setItem_id(cartItemsList.get(i).getItemId());
					invoiceDetails.setPrice(cartItemsList.get(i).getPrice());
					invoiceDetails.setQuantity((cartItemsList.get(i).getQuantity()));
					invoiceDetailsService.add(invoiceDetails);
				}
				 
				
				return "OrderConfirmationPage";
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
				
				Calendar startTime = Calendar.getInstance();
		        System.out.println("Current Time: "+startTime.getTime());
				
				Calendar endTime = Calendar.getInstance();
				
				Calendar pickUpTime = Calendar.getInstance();
				
				int totalPrepTime = 0;
				for(int i=0; i<cart.size(); i++){
					totalPrepTime += cart.get(i).getPrep_time();
				}			
				
				endTime.add(Calendar.MINUTE, totalPrepTime);
				System.out.println("End Time: "+endTime.getTime());
				
				//Checking for timing constraints
				if((endTime.getTime().getHours() >= 21)){
					//Start the order from 5 AM
					startTime.add(Calendar.DAY_OF_MONTH, 1);
					startTime.set(Calendar.HOUR, 5);
				    startTime.set(Calendar.MINUTE, 00);
				    //Push the end time ahead
				    endTime.setTime(startTime.getTime());
					endTime.add(Calendar.MINUTE, totalPrepTime);
				}
				while(checkAvailability(startTime.getTime(), endTime.getTime()) == false)
				{
					endTime.add(Calendar.MINUTE, 1);
					startTime.add(Calendar.MINUTE, 1);
					if((endTime.getTime().getHours() == 21)){
						startTime.add(Calendar.MINUTE, 480 + totalPrepTime);
						endTime.add(Calendar.MINUTE, 480 + totalPrepTime);
					}
				}
				System.out.println("Start Time: "+startTime.getTime());
				System.out.println("End Time: "+endTime.getTime());
				
				if(endTime.get(Calendar.HOUR) < 6){
					pickUpTime.setTime(endTime.getTime());
					pickUpTime.set(Calendar.HOUR, 6);
					pickUpTime.set(Calendar.MINUTE, 0);
				}
				else
					pickUpTime.setTime(endTime.getTime());
				
				
				//System.out.println(formatDateToString(tempTime));
				model.addAttribute("pickup_time", pickUpTime.getTime());
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
	 * Check availability of chef (run this a maximun of 60 times)
	 * 
	 * boolean success = false;
	 * for(int i=0; i<60; i++){
	 * 		if(checkAvaialability(startTime, endTime) == true){
	 * 			insert into table
	 * 			success = true;
	 * 			break;
	 * 		}else if(startTime.before(new Calendar.getInstance())){
	 * 			break;
	 * 		}else{
	 * 			startTime.add(Calendar.MINUTE, -1);
	 * 			endTime.add(Calendar.MINUTE, -1);
	 * 		}	
	 * } 
	 * 
	 * if(success == true){
	 * 		model.addAttribute("message", "Your order has been placed!");
	 * }
	 * else
	 * 		model.addAttribute("message, "Sorry! All chefs are busy. Please change your order / pickup time");
	 * 
	 * return "checkout";
	 */

	public boolean checkAvailability(Date startTime, Date endTime) {
        int chefCount = 0;
        List<Invoice> tempInvoiceList = new ArrayList<Invoice>();
        tempInvoiceList.addAll(invoiceService.getAllInvoice());
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
