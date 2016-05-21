package com.TOMSystem.service;

import java.util.ArrayList;
import java.util.List;

import com.TOMSystem.model.Item;
import com.TOMSystem.model.Invoice;


public interface InvoiceService{
	public void add(Invoice invoice);
	public void edit(Invoice invoice);
	public void delete(int invoice_id);
	public void deleteAll();
	public Invoice getInvoice(int invoice_id);
	public List getAllInvoice();
	public int totalPrepTime(ArrayList<Item> invoice);
	public List<Item> getAllItems(Invoice invoice);

}
