package com.TOMSystem.dao;

import java.util.List;

import com.TOMSystem.model.InvoiceDetails;

public interface InvoiceDetalisDao {
		public void add(InvoiceDetails invoiceDetails);
		public void edit(InvoiceDetails invoiceDetails);
		public void delete(int invoice_id);
		public List<InvoiceDetails> getInvoiceDetails(int invoice_id);
		public void deleteAll();
}
