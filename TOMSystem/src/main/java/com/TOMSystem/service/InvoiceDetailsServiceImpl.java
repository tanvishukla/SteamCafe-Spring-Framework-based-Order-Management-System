package com.TOMSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.TOMSystem.dao.InvoiceDetailsDao;
import com.TOMSystem.dao.InvoiceDetalisDao;
import com.TOMSystem.model.InvoiceDetails;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {

	@Autowired
	InvoiceDetalisDao invoiceDetailsDao;
	@Transactional
	public void add(InvoiceDetails invoiceDetails) {
		// TODO Auto-generated method stub
		invoiceDetailsDao.add(invoiceDetails);
	}

	@Transactional
	public void edit(InvoiceDetails invoiceDetails) {
		// TODO Auto-generated method stub
		invoiceDetailsDao.edit(invoiceDetails);
	}

	@Transactional
	public void delete(int invoice_id) {
		// TODO Auto-generated method stub
		invoiceDetailsDao.delete(invoice_id);
	}

	@Transactional
	public List<InvoiceDetails> getInvoiceDetails(int invoice_id) {
		// TODO Auto-generated method stub
		return invoiceDetailsDao.getInvoiceDetails(invoice_id);
	}

	@Transactional
	public void deleteAll() {
		// TODO Auto-generated method stub
		invoiceDetailsDao.deleteAll();
	}

}
