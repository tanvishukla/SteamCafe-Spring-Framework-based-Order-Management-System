package com.TOMSystem.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TOMSystem.controller.UserController;
import com.TOMSystem.service.InvoiceService;

public class Scheduler {
	@Autowired
	private InvoiceService invoiceService ;

	static boolean flag = false; 
	public void run() {
		
		Calendar nowTime = Calendar.getInstance();
		try {
			List<Invoice> myList = new ArrayList<Invoice>();
			myList.addAll(invoiceService.getAllInvoice());
			Invoice tempInvoice = new Invoice();
			for (int i = 0; i < myList.size(); i++) {
				if (myList.get(i).getStartTime().before(nowTime.getTime())
						&& myList.get(i).getEndTime().after(nowTime.getTime())) {
					//change status to "In progress"
					if(myList.get(i).getStatus().equals("Queued")){
						tempInvoice = invoiceService.getInvoice(myList.get(i).getInvoice_id());
						tempInvoice.setStatus("In progress");
						invoiceService.edit(tempInvoice);
						UserController.sendThisMail(myList.get(i).getEmail(), "", String.valueOf(myList.get(i).getInvoice_id()), myList.get(i).getEndTime(), "In progress");
					}
				}
				if (myList.get(i).getEndTime().before(nowTime.getTime()) && (myList.get(i).getStatus().equals("Completed") == false)) {
					// change status to completed
					System.out.println("HERE");
					tempInvoice = invoiceService.getInvoice(myList.get(i).getInvoice_id());
					tempInvoice.setStatus("Completed");
					invoiceService.edit(tempInvoice);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("In scheduler: " + nowTime.getTime());
	}
	
	
	/*public void initializeApp(){
		flag = true;
		System.out.println("NOW HERE");
		itemService.removeAll();
		Item i1 = new Item("Drinks",  "Pineapple Juice",  "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjNu7_z5-7MAhVL8GMKHSRaDhwQjRwIBw&url=http%3A%2F%2Fwww.newhealthadvisor.com%2FPineapple-to-Induce-Labor.html&psig=AFQjCNG_Wd9-2fdmrHXFPkyfmhoQLxVWsQ&ust=1464045302642190",  50,  2.99, 1, true);
		Item i2 = new Item("Drinks",  "Apple Juice",  "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjHr_G16O7MAhUQzGMKHWP0AZgQjRwIBw&url=http%3A%2F%2Fwww.erinnudi.com%2F2014%2F10%2F01%2Fdifference-apple-cider-apple-juice%2F&bvm=bv.122448493,d.cGc&psig=AFQjCNHQrV9EndRphS8pwOkJZIBxz3QE-Q&ust=1464045436260843",  30,  3.99, 2, true);
		Item i3 = new Item("Drinks",  "Mango Juice",  "https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwjzyYr16O7MAhUE6GMKHciiDAYQjRwIBw&url=http%3A%2F%2Fcookdiary.net%2Fmango-juice%2F&bvm=bv.122448493,d.cGc&psig=AFQjCNHlotZ65ZW3MDY_lWPPoY_7LS7_Bw&ust=1464045540827122",  10,  1.99, 1, true);
		
		itemService.addItem(i1);
		itemService.addItem(i2);
		itemService.addItem(i3);	
		 
	}*/
}
