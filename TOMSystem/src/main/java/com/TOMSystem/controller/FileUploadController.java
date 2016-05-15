package com.TOMSystem.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.TOMSystem.Item.Item;
import com.TOMSystem.service.ItemService;
import com.TOMSystem.service.ItemServiceImpl;
import com.TOMSystem.Order.*;
import com.TOMSystem.service.OrderService;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	@Autowired
	private ItemService itemService;
	
	private OrderService orderService;
	
	public ArrayList<Item> cart = new ArrayList<Item>();
	
	/**
	 * Upload single file using Spring Controller
	 */
	
	@RequestMapping(value = "/proceed", method = RequestMethod.POST)
	public String proceedWithCart(@RequestParam("date") String date,@RequestParam("hours") String hours,@RequestParam("minutes") String minutes, Map<String, Object> map, HttpServletRequest request){
		
		/*HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		
		// fetched pickup time
		System.out.println(hours+":"+minutes+" - "+date);
		String str = date+" "+hours+":"+minutes+":"+"00";
		
		String pickDate[] = date.split("/");
		
		Calendar pick = Calendar.getInstance();
		pick.set(Integer.valueOf(pickDate[2]) ,Integer.valueOf(pickDate[0]), Integer.valueOf(pickDate[1]), Integer.valueOf(hours), Integer.valueOf(minutes), 00);
		
		System.out.println(pick.getTime());
		
		String orderStr = new String("order");
		int total_perp_time=0;
		//generating order string
		for(int i=0; i<cart.size(); i++)
		{
			orderStr= orderStr+ ":" + String.valueOf(cart.get(i).getId());
			total_perp_time += cart.get(i).getPrep_time();
		}
		
		//generating estimated time
		//int total_perp_time = orderService.totalPrepTime(cart);
		
		Calendar estimatedTime=Calendar.getInstance();
		estimatedTime.add(Calendar.MINUTE, total_perp_time);
		
		System.out.println("Estimated: "+estimatedTime);
		
		//start time
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(pick.getTime());
		
		startTime.add(Calendar.MINUTE, - total_perp_time);
		
		
		
		
		
		Order thisOrder = new Order();
		
		thisOrder.setEndTime(pick.getTime());
		thisOrder.setOrder(orderStr);
		thisOrder.setPickupTime(pick.getTime());
		thisOrder.setPrep_Time(total_perp_time);
		thisOrder.setStartTime(startTime.getTime());
		thisOrder.setStatus("Queued");
		thisOrder.setUser_id(Integer.valueOf(userId));
		
		orderService.add(thisOrder);*/
		
		/*map.put("item", tempItem);
		map.put("itemList", itemService.getAllItems());
		map.put("unavailableItemList", itemService.getUnavailableItems());
		map.put("cart", sessionObj.getAttribute("cart"));
		System.out.println("Cart:"+ cart.size());
		*/return "proceed";
	}
	
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public String setupItemsForm(Map<String, Object> map){
		Item item= new Item();
		map.put("item", item);
		map.put("itemList", itemService.getAllItems());
		map.put("drinksList", itemService.getDrinks());
		map.put("appetizerList", itemService.getAppetizers());
		map.put("maincourseList", itemService.getMainCourse());
		map.put("dessertList", itemService.getDesserts());
		//System.out.println(map);
		return "items";
	}
	
	@RequestMapping(value = "/proceed", method = RequestMethod.GET)
	public String setupProceedForm(Map<String, Object> map){
		/*Order order= new Order();
		map.put("orderList", orderService.getAllOrders());
		*/return "proceed";
	}
	
	
	@RequestMapping(value = "/removeItem", method = RequestMethod.GET)
	public String setupRemoveForm(Map<String, Object> map){
		Item item= new Item();
		map.put("item", item);
		map.put("itemList", itemService.getAllItems());
		map.put("unavailableItemList", itemService.getUnavailableItems());
		return "removeItem";
	}
	
	@RequestMapping(value = "/removeItem", method = RequestMethod.POST)
	public String removeSelectedItem(@RequestBody String item_id, Map<String, Object> map){
		Item tempItem = itemService.getItem(Integer.valueOf(item_id));
		tempItem.setavailability(false);
		itemService.editItem(tempItem);
		
		map.put("item", tempItem);
		map.put("itemList", itemService.getAllItems());
		map.put("unavailableItemList", itemService.getUnavailableItems());
		
		return "removeItem";
	}
	
	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public String removeItemFromCart(@RequestBody String item_id, Map<String, Object> map, HttpSession sessionObj){
		
		Item tempItem = itemService.getItem(Integer.valueOf(item_id));
		System.out.println(item_id+"**********************************\n"+tempItem);
		
		//Remove the selected item
		for(int i=0; i<cart.size(); i++){						
			if(cart.get(i).getName().equals(tempItem.getName())){
				cart.remove(i);
				break;
			}
		}
		System.out.println(cart);
		
		sessionObj.setAttribute("cart", cart);

		//request.getSession().getAttribute("object");
		
		map.put("item", tempItem);
		map.put("itemList", itemService.getAllItems());
		map.put("unavailableItemList", itemService.getUnavailableItems());
		map.put("cart", sessionObj.getAttribute("cart"));
		System.out.println("Cart:"+ cart.size());
		return "items";
	}
	
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addItemToCart(@RequestBody String item_id, Map<String, Object> map, HttpSession sessionObj){
		
		Item tempItem = itemService.getItem(Integer.valueOf(item_id));
		System.out.println(item_id+"**********************************\n"+tempItem);
		cart.add(tempItem);
		sessionObj.setAttribute("cart", cart);

		//request.getSession().getAttribute("object");
		
		map.put("item", tempItem);
		map.put("itemList", itemService.getAllItems());
		map.put("unavailableItemList", itemService.getUnavailableItems());
		map.put("cart", sessionObj.getAttribute("cart"));
		System.out.println("Cart:"+ cart.size());
		return "items";
	}
	
	
	@RequestMapping(value = "/addThisItem", method = RequestMethod.POST)
	public String addSelectedItem(@RequestBody String item_id, Map<String, Object> map){
		Item tempItem = itemService.getItem(Integer.valueOf(item_id));
		tempItem.setavailability(true);
		itemService.editItem(tempItem);
		
		map.put("item", tempItem);
		map.put("itemList", itemService.getAllItems());
		map.put("unavailableItemList", itemService.getUnavailableItems());
		
		return "removeItem";
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.GET)
	public String setupForm(Map<String, Object> map){
		Item item= new Item();
		map.put("item", item);
		map.put("itemList", itemService.getAllItems());
		return "addItem";
	}
	
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("item_name") String item_name,
			@RequestParam("image") MultipartFile file, @RequestParam("category") String category, 
			@RequestParam("item_price") double item_price, @RequestParam("item_calories") double item_calories, 
			@RequestParam("item_prep_time") int item_prep_time, Map<String, Object> map) {
		
		//creating item pojo
		Item tempItem = new Item();
		
		if (!file.isEmpty()) {
			try {

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
				String rootPath = "E:/F/Lectures/275-Zhang/CMPE-275-Project/TOMSystem/src/main/webapp/WEB-INF";
				
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
				
				map.put("message", "Successful");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("message", "Exception Occured!");
				return "You failed to upload " + item_name + " => " + e.getMessage();
			}
		} else {
			map.put("message", "Empty image!");
		}
		map.put("item", tempItem);
		map.put("itemList", itemService.getAllItems());
		return "addItem";
	}	
}
