package com.TOMSystem.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.input.TeeInputStream;
import org.hibernate.usertype.UserCollectionType;
import org.springframework.beans.factory.annotation.Autowired;

import com.TOMSystem.controller.UserController;
import com.TOMSystem.service.InvoiceService;

public class Scheduler {
	@Autowired
	private InvoiceService invoiceService ;

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
}
