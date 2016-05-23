package com.TOMSystem.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.TOMSystem.model.Invoice;
import com.TOMSystem.model.Item;
import com.TOMSystem.service.InvoiceDetailsService;
import com.TOMSystem.service.InvoiceService;
import com.TOMSystem.service.ItemService;
import com.TOMSystem.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private UserService userService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private InvoiceDetailsService invoiceDetailsService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	/*
	 * 
	 *	To clear all order status
	 * 
	 */	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String resetOrderSystem(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			//clear all invoices
			invoiceService.deleteAll();
			//clear all invoice details
			invoiceDetailsService.deleteAll();
			return "addItem";
		}
		else
		{
			return "login";
		}
	}
	
	
	/*
	 * 
	 *	To display addItem page 
	 * 
	 */	
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public String setupForm(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			Item item= new Item();
			model.addAttribute("item", item);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("message", null);
			return "addItem";
		}
		else
		{
			return "login";
		}
	}

	
	/*
	 * 
	 *	Add an item to the table
	 * 
	 */
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("item_name") String item_name, @RequestParam("image") String image, @RequestParam("category") String category, @RequestParam("item_price") double item_price, @RequestParam("item_calories") double item_calories, @RequestParam("item_prep_time") int item_prep_time, Model model,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			Item tempItem = new Item();
			
				try 
				{
					tempItem.setName(item_name);
					tempItem.setCalories(item_calories);
					tempItem.setCategory(category);
					tempItem.setPicture(image);
					tempItem.setPrep_time(item_prep_time);
					tempItem.setUnit_price(item_price);
					tempItem.setavailability(true);
					
					//add item to table
					itemService.addItem(tempItem);

					model.addAttribute("message", "The item was added successfully...!");
				} 
				catch (Exception e)
				{
					e.printStackTrace();
					model.addAttribute("message", "Sorry! Something went wrong..!");
					return "addItem";
				}
			
			
			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			return "addItem";
		}
		else
		{
			return "login";
		}
	}
	
	
	/*
	 * 
	 * To display the remove item page 
	 * (Fetch all existing items from table into list and 
	 * bind that list to model)
	 */
	@RequestMapping(value = "/removeItem", method = RequestMethod.GET)
	public String setupRemoveForm(HttpServletRequest request, Model model){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			Item item= new Item();
			model.addAttribute("item", item);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			return "removeItem";
		}
		else
		{
			return "login";
		}
	}

	
	/*
	 * 
	 * To remove an item
	 * (set Availability = false and push back to table)
	 * 
	 */
	@RequestMapping(value = "/removeItem", method = RequestMethod.POST)
	public String removeSelectedItem(@RequestBody String item_id, Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));
			tempItem.setavailability(false);
			itemService.editItem(tempItem);

			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());

			return "removeItem";
		}
		else
		{
			return "login";
		}
	}
	
	
	/*
	 * To make unavailable item Available again
	 * (Get that particular item by id
	 * Set it's availability to true
	 * Push it back to the table )
	 */
	@RequestMapping(value = "/addThisItem", method = RequestMethod.POST)
	public String addSelectedItem(@RequestBody String item_id, Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));
			tempItem.setavailability(true);
			itemService.editItem(tempItem);

			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());

			return "removeItem";
		}
		else
		{
			return "login";
		}
	}
	
	////////////////****************Admin Report/////////////////
	/*
	 * To show the admin the reports according to the selected date range
	 * 
	 * 
	 */
	@RequestMapping(value ="/getReports", method = RequestMethod.GET)
	public String getReportsForAdmin(Model model,HttpServletRequest request){
		return "selectDateRange";
	}
	
	/*
	 * 
	 * Get the report details sorted by date selected by the admin
	 */
	@RequestMapping(value="/DateSortedReports",method = RequestMethod.POST)
	public String DateSortedReports(@RequestParam("from") String from,@RequestParam("to") String to, Map<String, Object> map,Model model, HttpServletRequest 
request) throws ParseException{
						HttpSession session = request.getSession();
						
						if(session.getAttribute("userId")!=null)
						{
											
							String[] strArr = from.split("/");			
							int year = Integer.parseInt(strArr[2]);
							int month = Integer.parseInt(strArr[0])-1;
							int day = Integer.parseInt(strArr[1]);
							
						    Calendar cal = Calendar.getInstance();
						    cal.set(year, month, day);
							Date from_date= cal.getTime();
							
							String[] strArr1 = to.split("/");			
							int year1 = Integer.parseInt(strArr1[2]);
							int month1 = Integer.parseInt(strArr1[0])-1;
							int day1 = Integer.parseInt(strArr1[1]);
							
						    Calendar cal1 = Calendar.getInstance();
						    cal1.set(year1, month1, day1);
							Date to_date= cal1.getTime();
																										
											
							System.out.println("After----------------->"+to_date);
							System.out.println("After----------------->"+from_date);
							
							List<Invoice> list1 = (List) invoiceService.getAllInvoice();
							
							List<Invoice> showInvoices = new ArrayList();
							
							Calendar cal2= Calendar.getInstance();
							for (int i = 0; i < list1.size(); i++) {
								
								System.out.println("cal"+cal.getTime()+"cal1"+cal1.getTime()+"cal2"+cal2.getTime());
								//System.out.println(list1.get(i).getInvoice_id());
								cal2.setTime(list1.get(i).getOrderDate());
								
								if(cal2.get(Calendar.YEAR)== cal.get(Calendar.YEAR) && cal2.get(Calendar.MONTH)== cal.get(Calendar.MONTH) && cal2.get(Calendar.DAY_OF_MONTH)== cal.get(Calendar.DAY_OF_MONTH))
								{
									showInvoices.add(list1.get(i));
								}
								
								if(cal2.after(cal) && cal2.before(cal1)){
									
									showInvoices.add(list1.get(i));
								}
								
								model.addAttribute("InvoicesList",showInvoices);
							} 
							
							
							
						}
						
						return "selectDateRange";
	}
}
