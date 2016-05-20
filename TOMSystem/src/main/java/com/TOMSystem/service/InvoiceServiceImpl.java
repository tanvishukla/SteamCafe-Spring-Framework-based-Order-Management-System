package com.TOMSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.TOMSystem.dao.InvoiceDao;
import com.TOMSystem.model.Invoice;
import com.TOMSystem.model.Item;

public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Override
	public void add(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceDao.add(invoice);
	}

	@Override
	public void edit(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceDao.edit(invoice);
	}

	@Override
	public void delete(int invoice_id) {
		// TODO Auto-generated method stub
		invoiceDao.delete(invoice_id);
	}

	@Override
	public Invoice getInvoice(int invoice_id) {
		// TODO Auto-generated method stub
		return invoiceDao.getInvoice(invoice_id);
	}

	@Override
	public List getAllInvoice() {
		// TODO Auto-generated method stub
		return invoiceDao.getAllInvoice();
	}

	@Override
	public int totalPrepTime(ArrayList<Item> invoice) {
		// TODO Auto-generated method stub
		return invoiceDao.totalPrepTime(invoice);
	}

	@Override
	public List<Item> getAllItems(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceDao.getAllItems(invoice);
	}

}
