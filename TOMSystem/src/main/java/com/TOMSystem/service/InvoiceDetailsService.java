package com.TOMSystem.service;

import com.TOMSystem.model.InvoiceDetails;

public interface InvoiceDetailsService {
	public void add(InvoiceDetails invoiceDetails);
	public void edit(InvoiceDetails invoiceDetails);
	public void delete(int invoice_id);
	public InvoiceDetails getInvoiceDetails(int invoice_id);
	public void deleteAll();
}
