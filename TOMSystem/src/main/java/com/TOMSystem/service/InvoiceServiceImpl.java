package com.TOMSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TOMSystem.dao.InvoiceDao;
import com.TOMSystem.model.Invoice;
import com.TOMSystem.model.Item;
@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Transactional
	public void add(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceDao.add(invoice);
	}

	@Transactional
	public void edit(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceDao.edit(invoice);
	}

	@Transactional
	public void delete(int invoice_id) {
		// TODO Auto-generated method stub
		invoiceDao.delete(invoice_id);
	}

	@Transactional
	public Invoice getInvoice(int invoice_id) {
		// TODO Auto-generated method stub
		return invoiceDao.getInvoice(invoice_id);
	}

	@Transactional
	public List<Invoice> getAllInvoice() {
		// TODO Auto-generated method stub
		return invoiceDao.getAllInvoice();
	}

	@Transactional
	public int totalPrepTime(ArrayList<Item> invoice) {
		// TODO Auto-generated method stub
		return invoiceDao.totalPrepTime(invoice);
	}

	@Transactional
	public List<Item> getAllItems(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceDao.getAllItems(invoice);
	}

	@Transactional
	public void deleteAll() {
		// TODO Auto-generated method stub
		invoiceDao.deleteAll();
	}

	@Transactional
	public List<Invoice> getAllQueuedInvoices(String email) {
		// TODO Auto-generated method stub
		return invoiceDao.getAllQueuedInvoices(email);
	}

	@Transactional
	public List<Invoice> getAllInProgressInvoices(String email) {
		// TODO Auto-generated method stub
		return invoiceDao.getAllInProgressInvoices(email);
	}

	@Transactional
	public List<Invoice> getAllCompletedInvoices(String email) {
		// TODO Auto-generated method stub
		return invoiceDao.getAllCompletedInvoices(email);
	}

}
