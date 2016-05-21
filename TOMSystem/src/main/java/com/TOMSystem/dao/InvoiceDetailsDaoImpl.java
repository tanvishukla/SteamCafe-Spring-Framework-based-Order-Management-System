package com.TOMSystem.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TOMSystem.model.InvoiceDetails;
@Repository
public class InvoiceDetailsDaoImpl implements InvoiceDetalisDao {
	
	@Autowired
	private SessionFactory session;
	@Override
	public void add(InvoiceDetails invoiceDetails) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(invoiceDetails);
	}

	@Override
	public void edit(InvoiceDetails invoiceDetails) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(invoiceDetails);
	}

	@Override
	public void delete(int invoice_id) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getInvoiceDetails(invoice_id));
	}
	
	@Override
	public InvoiceDetails getInvoiceDetails(int invoice_id) {
		// TODO Auto-generated method stub
		return (InvoiceDetails)session.getCurrentSession().get(InvoiceDetails.class, invoice_id);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		session.getCurrentSession().createQuery("delete from InvoiceDetails").executeUpdate();
	}

}
