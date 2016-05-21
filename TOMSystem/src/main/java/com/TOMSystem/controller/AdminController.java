package com.TOMSystem.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
	public String uploadFileHandler(@RequestParam("item_name") String item_name, @RequestParam("image") MultipartFile file, @RequestParam("category") String category, @RequestParam("item_price") double item_price, @RequestParam("item_calories") double item_calories, @RequestParam("item_prep_time") int item_prep_time, Model model,HttpServletRequest request) 
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			Item tempItem = new Item();
			if (!file.isEmpty()) 
			{
				try 
				{
					tempItem.setName(item_name);
					tempItem.setCalories(item_calories);
					tempItem.setCategory(category);
					tempItem.setPicture(item_name);
					tempItem.setPrep_time(item_prep_time);
					tempItem.setUnit_price(item_price);
					tempItem.setavailability(true);
					
					//add item to table
					itemService.addItem(tempItem);

					//uploading image
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					//String rootPath = System.getProperty("catalina.home");
					String rootPath = "F:/MSSE/Spring2016/275Zang/Project/CMPE-275-Project/TOMSystem/src/main/webapp/WEB-INF";

					File dir = new File(rootPath + File.separator + "images");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + item_name +".png");
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					logger.info("Server File Location=" + serverFile.getAbsolutePath());
					System.out.println("Server File Location=" + serverFile.getAbsolutePath());

					model.addAttribute("message", "The item was added successfully...!");
				} 
				catch (Exception e)
				{
					e.printStackTrace();
					model.addAttribute("message", "Sorry! Something went wrong..!");
					return "addItem";
				}
			}
			else 
			{
				model.addAttribute("message", "Please upload image!");
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
}
