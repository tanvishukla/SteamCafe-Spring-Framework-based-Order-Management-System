package com.TOMSystem.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TOMSystem.service.InvoiceService;

public class Scheduler {
	@Autowired
	private InvoiceService invoiceService ;

	public void run() {
		Calendar nowTime = Calendar.getInstance();
		try {
			List<Invoice> myList = new ArrayList<Invoice>();
			myList.addAll(invoiceService.getAllInvoice());
			
			for (int i = 0; i < myList.size(); i++) {
				if (myList.get(i).getStartTime().before(nowTime.getTime())
						&& myList.get(i).getEndTime().after(nowTime.getTime())) {
					//change status to "In progress"
					myList.get(i).setStatus("In progress");
				} else if (myList.get(i).getEndTime().after(nowTime.getTime())) {
					// change status to completed
					myList.get(i).setStatus("Completed");
				}
			}
		} catch (Exception e) {
			System.out.print("A");
			e.printStackTrace();
		}
		System.out.println("In scheduler: " + nowTime.getTime());
	}
}
