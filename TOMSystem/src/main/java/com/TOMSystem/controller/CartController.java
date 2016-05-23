package com.TOMSystem.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	private ItemService itemService;

	@Autowired
	private InvoiceDetailsService invoiceDetailsService;

	@Autowired
	private InvoiceService invoiceService;

	// Creating an list of all items in the cart
	// public HashMap<Item,Integer> cart = new HashMap<Item,Integer>();
	// public ArrayList<Item> cart = new ArrayList<Item>();

	public static ArrayList<CartItems> cartItemsList = new ArrayList<CartItems>();

	/*
	 * To add an element in the cart First check whether the element exists. If
	 * YES? increment count, else add
	 */
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addItemToCart(@RequestParam("item_id") int item_id, @RequestParam("item_quantity") int item_quantity,
			Map<String, Object> map, Model model, HttpServletRequest request) throws NoSuchFieldException,
			SecurityException {

		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			System.out.println("Item Id is....." + item_id);
			System.out.println("Item quantity is...." + item_quantity);

			Item tempItem = itemService.getItem(Integer.valueOf(item_id));

			CartItems tempCartItem = new CartItems();
			tempCartItem.setItemId(tempItem.getId());
			tempCartItem.setItemName(tempItem.getName());
			tempCartItem.setPrice(tempItem.getUnit_price() * item_quantity);
			tempCartItem.setItem_PrepTime(tempItem.getPrep_time());
			tempCartItem.setPicture(tempItem.getPicture());
			tempCartItem.setQuantity(item_quantity);

			cartItemsList.add(tempCartItem);
			session.setAttribute("cart", cartItemsList);

			System.out.println("Cart Items are******* " + session.getAttribute("cart"));
			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());

			// System.out.println("Cart:"+ cart.size());
			map.put("drinksList", itemService.getDrinks());
			map.put("appetizerList", itemService.getAppetizers());
			map.put("maincourseList", itemService.getMainCourse());
			map.put("dessertList", itemService.getDesserts());
			model.addAttribute("cart", session.getAttribute("cart"));
			return "items";
		} else {
			return "login";
		}
	}

	/*
	 * To remove an element from the cart
	 */
	@RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public String removeItemFromCart(@RequestBody String item_id, Map<String, Object> map, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			// Fetch the item from table
			Item tempItem = itemService.getItem(Integer.valueOf(item_id));

			// Remove the selected item
			// cart.remove(tempItem);
			for (int i = 0; i < cartItemsList.size(); i++)
				if (cartItemsList.get(i).getItemName().equals(tempItem.getName())) {
					cartItemsList.remove(i);
					break;
				}

			session.setAttribute("cart", cartItemsList);

			model.addAttribute("item", tempItem);
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			model.addAttribute("cart", session.getAttribute("cart"));
			System.out.println("Cart:" + cartItemsList.size());

			map.put("drinksList", itemService.getDrinks());
			map.put("appetizerList", itemService.getAppetizers());
			map.put("maincourseList", itemService.getMainCourse());
			map.put("dessertList", itemService.getDesserts());
			return "items";
		} else {
			return "login";
		}

	}

	// Takes time and date from customer and sets the invoice
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(@RequestParam("hours") int hours, Model model, @RequestParam("minutes") int minutes,
			@RequestParam("date") String date, Map<String, Object> map, HttpServletRequest request)
			throws ParseException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, MessagingException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			if (cartItemsList.size() != 0) {
				
				Invoice tempInvoice = new Invoice();
				
				String messageBody = new String();
				System.out.println("Before----------------->" + date);
				String[] strArr = date.split("/");
				int year = Integer.parseInt(strArr[2]);
				int month = Integer.parseInt(strArr[0]) - 1;
				int day = Integer.parseInt(strArr[1]);

				Calendar cal = Calendar.getInstance();
				cal.set(year, month, day, hours, minutes, 0);
				Date d = cal.getTime();
				System.out.println("After----------------->" + d);

				// ///

				Calendar endTime = Calendar.getInstance();
				endTime.setTime(d);
				System.out.println("endTime is**- " + endTime);

				Calendar startTime = Calendar.getInstance();
				startTime.setTime(endTime.getTime());
				startTime.add(Calendar.MINUTE, -(Integer) session.getAttribute("totalPrepTime"));

				if (startTime.before(Calendar.getInstance())) {
					model.addAttribute("message", "Sorry! All chefs are busy. Please change your order / pickup time");
					return "checkout";
				}
				// Check availability of chef (run this a maximun of 60 times)

				boolean success = false;
				for (int i = 0; i < 60; i++) {
					if (checkAvailability(startTime.getTime(), endTime.getTime()) == true) {
						
						tempInvoice.setEmail(session.getAttribute("userId").toString());
						tempInvoice.setPickupTime(d);
						tempInvoice.setEndTime(endTime.getTime());
						tempInvoice.setStartTime(startTime.getTime());
						tempInvoice.setOrderDate((Date) session.getAttribute("start"));
						tempInvoice.setPrep_Time((Integer) session.getAttribute("totalPrepTime"));
						tempInvoice.setStatus("Queued");
						invoiceService.add(tempInvoice);

						// ***************Adding to invoice Deatils
						// table***********///

						System.out.println("Invoice id is----" + tempInvoice.getInvoice_id());

						InvoiceDetails invoiceDetails = new InvoiceDetails();
						invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());

						messageBody = "Order Id: "
								+ tempInvoice.getInvoice_id()
								+ "</br><table border='1'><thead><tr><th>Item Name</th><th>Quantity</th><th>Price</th></tr></thead>";
						for (int j = 0; j < cartItemsList.size(); j++) {

							invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());
							invoiceDetails.setItem_id(cartItemsList.get(j).getItemId());
							invoiceDetails.setItem_name(cartItemsList.get(j).getItemName());
							invoiceDetails.setPrice(cartItemsList.get(j).getPrice());
							invoiceDetails.setQuantity((cartItemsList.get(j).getQuantity()));
							invoiceDetailsService.add(invoiceDetails);

							messageBody = messageBody + "<tr>";
							messageBody = messageBody + "<td>" + cartItemsList.get(j).getItemName() + "</td>";
							messageBody = messageBody + "<td>" + cartItemsList.get(j).getQuantity() + "</td>";
							messageBody = messageBody + "<td>" + cartItemsList.get(j).getPrice() + "</td>";
							messageBody = messageBody + "</tr>";
						}
						messageBody = messageBody + "</table>";
						success = true;
						break;
					} else if (startTime.before(Calendar.getInstance().getTime())) {
						break;
					} else if (startTime.getTime().getHours() < 5) {
						break;
					} else {
						startTime.add(Calendar.MINUTE, -1);
						endTime.add(Calendar.MINUTE, -1);
					}
				}
				
				//setting start and end time
				tempInvoice.setEndTime(endTime.getTime());
				tempInvoice.setStartTime(startTime.getTime());

				// clearig cart to get any new order
				cartItemsList.clear();
				
				if (success == true) {
					model.addAttribute("message", "Your order has been placed!");
					UserController
							.sendThisMail(session.getAttribute("userId").toString(), "", messageBody, d, "Queued");
					
					return "invoices";
				} else {
					model.addAttribute("message", "Sorry! All chefs are busy. Please change your order / pickup time");
					return "checkout";
				}
				
			}
		}
		else
		return "login";
		
		return "login";
	}

	/*
	 * Add functionality HERE
	 */
	// Provides earliest pickup time and sets the invoice
	@RequestMapping(value = "/earliestPickUp", method = RequestMethod.POST)
	public String earliestPickUp(@RequestBody String item_id, Model model, Map<String, Object> map,
			HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, MessagingException {
		HttpSession session = request.getSession();

		System.out.println("in earliest pickup");

		String messageBody = new String();
		if (session.getAttribute("userId") != null) {

			System.out.println(session.getAttribute("pickup").toString());
			System.out.println(session.getAttribute("userId").toString());

			// ////
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			Calendar pickUpTime = Calendar.getInstance();

			int totalPrepTime = (Integer) session.getAttribute("totalPrepTime");

			endTime.setTime(startTime.getTime());
			endTime.add(Calendar.MINUTE, (Integer) session.getAttribute("totalPrepTime"));

			System.out.println("End Time: " + endTime.getTime());

			// Checking for timing constraints
			if ((endTime.getTime().getHours() >= 21)) {
				// Start the order from 5 AM
				startTime.add(Calendar.DAY_OF_MONTH, 1);
				startTime.set(Calendar.HOUR, 5);
				startTime.set(Calendar.MINUTE, 00);
				// Push the end time ahead
				endTime.setTime(startTime.getTime());
				endTime.add(Calendar.MINUTE, totalPrepTime);
			}

			while (checkAvailability(startTime.getTime(), endTime.getTime()) == false) {
				endTime.add(Calendar.MINUTE, 1);
				startTime.add(Calendar.MINUTE, 1);
				if ((endTime.getTime().getHours() == 21)) {
					startTime.add(Calendar.MINUTE, 480 + totalPrepTime);
					endTime.add(Calendar.MINUTE, 480 + totalPrepTime);
				}
			}

			// available start and end times
			System.out.println("Start Time: " + startTime.getTime());
			System.out.println("End Time: " + endTime.getTime());

			// if order is ready before shop opens
			if (endTime.get(Calendar.HOUR) < 6 && endTime.get(Calendar.AM_PM) == 0) {
				pickUpTime.setTime(endTime.getTime());
				pickUpTime.set(Calendar.HOUR, 6);
				pickUpTime.set(Calendar.MINUTE, 0);
			} else
				pickUpTime.setTime(endTime.getTime());

			if (cartItemsList.size() != 0) {

				// adding data to invoice table
				Invoice tempInvoice = new Invoice();
				tempInvoice.setEmail(session.getAttribute("userId").toString());
				tempInvoice.setPickupTime(pickUpTime.getTime());
				tempInvoice.setEndTime(endTime.getTime());
				tempInvoice.setStartTime(startTime.getTime());
				tempInvoice.setOrderDate(Calendar.getInstance().getTime());
				tempInvoice.setPrep_Time((Integer) session.getAttribute("totalPrepTime"));
				tempInvoice.setStatus("Queued");
				invoiceService.add(tempInvoice);

				// ***************Adding to invoice Deatils table***********///

				InvoiceDetails invoiceDetails = new InvoiceDetails();
				invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());
				messageBody = "Order Id: "
						+ tempInvoice.getInvoice_id()
						+ "</br><table border='1'><thead><tr><th>Item Name</th><th>Quantity</th><th>Price</th></tr></thead>";
				for (int i = 0; i < cartItemsList.size(); i++) {
					invoiceDetails.setInvoice_id(tempInvoice.getInvoice_id());
					invoiceDetails.setItem_id(cartItemsList.get(i).getItemId());
					invoiceDetails.setItem_name(cartItemsList.get(i).getItemName());
					invoiceDetails.setPrice(cartItemsList.get(i).getPrice());
					invoiceDetails.setQuantity((cartItemsList.get(i).getQuantity()));
					invoiceDetailsService.add(invoiceDetails);
					messageBody = messageBody + "<tr>";
					messageBody = messageBody + "<td>" + cartItemsList.get(i).getItemName() + "</td>";
					messageBody = messageBody + "<td>" + cartItemsList.get(i).getQuantity() + "</td>";
					messageBody = messageBody + "<td>" + cartItemsList.get(i).getPrice() + "</td>";
					messageBody = messageBody + "</tr>";
				}

				messageBody = messageBody + "</table>";
				model.addAttribute("message", "Your order has been placed!");
				UserController.sendThisMail(session.getAttribute("userId").toString(), "", messageBody,
						pickUpTime.getTime(), "Queued");

				// clearig cart to get any new order
				cartItemsList.clear();
			}
			model.addAttribute("invoiceList",
					invoiceService.getAllQueuedInvoices(session.getAttribute("userId").toString()));
			model.addAttribute("inProgressInvoiceList",
					invoiceService.getAllInProgressInvoices(session.getAttribute("userId").toString()));
			model.addAttribute("CompletedInvoiceList",
					invoiceService.getAllCompletedInvoices(session.getAttribute("userId").toString()));
			return "invoices";

		} else {
			return "login";
		}
	}

	/*
	 * To display checkout page
	 */

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String confirmOrder(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			// session.setAttribute("cart", cart);

			Calendar endTime = Calendar.getInstance();
			Calendar pickUpTime = Calendar.getInstance();
			Calendar startTime = Calendar.getInstance();
			Calendar TodaysDate = Calendar.getInstance();
			Date orderDate = TodaysDate.getTime();
			session.setAttribute("orderDate", orderDate);

			session.setAttribute("start", startTime.getTime());

			// calculate total prepTime
			int totalPrepTime = 0;
			for (int i = 0; i < cartItemsList.size(); i++) {
				totalPrepTime += cartItemsList.get(i).getItem_PrepTime() * cartItemsList.get(i).quantity;
			}

			// storing it in session
			session.setAttribute("totalPrepTime", totalPrepTime);

			endTime.add(Calendar.MINUTE, totalPrepTime);
			System.out.println("End Time: " + endTime.getTime());

			// Checking for timing constraints
			if ((endTime.getTime().getHours() >= 21)) {
				// Start the order from 5 AM
				startTime.add(Calendar.DAY_OF_MONTH, 1);
				startTime.set(Calendar.HOUR, 5);
				startTime.set(Calendar.MINUTE, 00);
				// Push the end time ahead
				endTime.setTime(startTime.getTime());
				endTime.add(Calendar.MINUTE, totalPrepTime);
			}

			while (checkAvailability(startTime.getTime(), endTime.getTime()) == false) {
				endTime.add(Calendar.MINUTE, 1);
				startTime.add(Calendar.MINUTE, 1);
				if ((endTime.getTime().getHours() == 21)) {
					startTime.add(Calendar.MINUTE, 480 + totalPrepTime);
					endTime.add(Calendar.MINUTE, 480 + totalPrepTime);
				}
			}

			// available start and end times
			System.out.println("Start Time: " + startTime.getTime());
			System.out.println("End Time: " + endTime.getTime());

			// if order is ready before shop opens
			if (endTime.get(Calendar.HOUR) < 6 && endTime.get(Calendar.AM_PM) == 0) {
				pickUpTime.setTime(endTime.getTime());
				pickUpTime.set(Calendar.HOUR, 6);
				pickUpTime.set(Calendar.MINUTE, 0);
			} else
				pickUpTime.setTime(endTime.getTime());

			session.setAttribute("endTime", endTime.getTime());
			session.setAttribute("pickup", pickUpTime.getTime());

			model.addAttribute("pickup_time", pickUpTime.getTime());
			model.addAttribute("itemList", itemService.getAllItems());
			model.addAttribute("unavailableItemList", itemService.getUnavailableItems());
			model.addAttribute("cart", session.getAttribute("cart"));
			model.addAttribute("message", "");
			return "checkout";
		} else {
			return "login";
		}
	}

	/*
	 * Convert date to PST format
	 */
	public static String formatDateToString(Date date) {

		String timeZone = "PST";
		// null check
		if (date == null)
			return null;
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
	 * boolean success = false; for(int i=0; i<60; i++){
	 * if(checkAvaialability(startTime, endTime) == true){ insert into table
	 * success = true; break; }else if(startTime.before(new
	 * Calendar.getInstance())){ break; }else{ startTime.add(Calendar.MINUTE,
	 * -1); endTime.add(Calendar.MINUTE, -1); } }
	 * 
	 * if(success == true){ model.addAttribute("message",
	 * "Your order has been placed!"); } else
	 * model.addAttribute("message, "Sorry! All chefs are busy. Please change
	 * your order / pickup time");
	 * 
	 * return "checkout";
	 */

	public boolean checkAvailability(Date startTime, Date endTime) {
		int chefCount = 0;
		List<Invoice> tempInvoiceList = new ArrayList<Invoice>();
		tempInvoiceList.addAll(invoiceService.getAllInvoice());
		for (int i = 0; i < tempInvoiceList.size(); i++) {

			if ((startTime.before(tempInvoiceList.get(i).getStartTime()) && endTime.after(tempInvoiceList.get(i)
					.getStartTime()))
					|| (startTime.before(tempInvoiceList.get(i).getEndTime()) && endTime.after(tempInvoiceList.get(i)
							.getStartTime()))
					|| (startTime.before(tempInvoiceList.get(i).getEndTime()) && endTime.after(tempInvoiceList.get(i)
							.getEndTime()))) {
				chefCount++;
				if (chefCount == 3)
					return false;
			}
		}
		return true;
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public String SetupInvoicesPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			String userName = session.getAttribute("userId").toString();
			System.out.println("email for invoices: " + userName);
			model.addAttribute("invoiceList", invoiceService.getAllQueuedInvoices(userName));
			model.addAttribute("inProgressInvoiceList", invoiceService.getAllInProgressInvoices(userName));
			model.addAttribute("CompletedInvoiceList", invoiceService.getAllCompletedInvoices(userName));
			return "invoices";
		} else
			return "login";
	}

	@RequestMapping(value = "/invoiceDetail/{id}", method = RequestMethod.GET)
	public String invoiceDetailGET(@PathVariable String id, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("userId") != null) {
			System.out.println("Id: " + id);
			model.addAttribute("invoiceDetailList", invoiceDetailsService.getInvoiceDetails(Integer.valueOf(id)));
			return "invoiceDetails";
		} else {
			return "login";
		}
	}

	@RequestMapping(value = "/removeInvoice", method = RequestMethod.POST)
	public String removeSelectedItem(@RequestBody String id, Model model,HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId")!=null)
		{
			invoiceService.delete(Integer.valueOf(id));				
			invoiceDetailsService.delete(Integer.valueOf(id));
			System.out.println("HERE");
			String userName = session.getAttribute("userId").toString();
			model.addAttribute("invoiceList", invoiceService.getAllQueuedInvoices(userName));
			model.addAttribute("inProgressInvoiceList", invoiceService.getAllInProgressInvoices(userName));
			model.addAttribute("CompletedInvoiceList", invoiceService.getAllCompletedInvoices(userName));
			return "invoices";
		}
		else
		{
			return "login";
		}
	}
}
