package com.TOMSystem.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.TOMSystem.model.Item;
import com.TOMSystem.service.ItemService;

@Controller
public class FileUploadController {
	@Autowired
	private ItemService itemService;

	//private OrderService orderService;

	public ArrayList<Item> cart = new ArrayList<Item>();

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

	@RequestMapping(value = "/proceed", method = RequestMethod.GET)
	public String setupProceedForm(Map<String, Object> map,HttpServletRequest request){
		/*Order order= new Order();
		map.put("orderList", orderService.getAllOrders());
		 */
		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null && !session.getAttribute("userId").toString().equalsIgnoreCase("admin"))
		{
			return "proceed";
		}
		else
		{
			return "login";	
		}
	}
}