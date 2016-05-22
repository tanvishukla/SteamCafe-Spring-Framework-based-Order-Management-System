package com.TOMSystem.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TOMSystem.model.Item;
import com.TOMSystem.model.Invoice;
import com.TOMSystem.service.ItemService;


@Repository
public class InvoiceDaoImpl implements InvoiceDao {
	@Autowired
	private SessionFactory session;
	@Autowired
	private ItemService itemService;
	
	@Override
	public void add(Invoice invoice) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(invoice);
	}

	@Override
	public void edit(Invoice invoice) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(invoice);		
	}

	
	@Override
	public void delete(int invoice_id){
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getInvoice(invoice_id));
	}

	@Override
	public Invoice getInvoice(int invoice_id){
		// TODO Auto-generated method stub		
		return (Invoice)session.getCurrentSession().get(Invoice.class, invoice_id);
	}

	@Override
	public List<Invoice> getAllInvoice(){
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Invoice").list();
	}
	
	@Override
	public int totalPrepTime(ArrayList<Item>  invoice){
		int totalPrepTime=0;
		Item tempItem;
		
		//String idArray[] = order.getOrder().split(":");
		
		for(int i=0; i<invoice.size(); i++){
			tempItem=itemService.getItem(invoice.get(i).getId());
			totalPrepTime+=tempItem.getPrep_time();
		}
		return totalPrepTime;
	}
	
	@Override
	public List<Item> getAllItems(Invoice invoice){
		List<Item> tempItemList = null;
		/*int itemId;
		
		String idArray[] = invoice.getInvoice().split(":");
		
		for(int i=0; i<idArray.length; i++){
			tempItemList.add(itemService.getItem(Integer.valueOf(idArray[i])));
		}*/
		return tempItemList;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		session.getCurrentSession().createQuery("delete from Invoice").executeUpdate();		
	}

	@Override
	public List<Invoice> getAllQueuedInvoices(String email) {
		// TODO Auto-generated method stub
		if(email.equals("admin"))
			return session.getCurrentSession().createQuery("from Invoice where status='Queued'").list();
		return session.getCurrentSession().createQuery("from Invoice where email='"+email+"' AND status='Queued'").list();
	}

	@Override
	public List<Invoice> getAllInProgressInvoices(String email) {
		// TODO Auto-generated method stub
		if(email.equals("admin"))
			return session.getCurrentSession().createQuery("from Invoice where status='In progress'").list();
		return  session.getCurrentSession().createQuery("from Invoice where email='"+email+"' AND status='In progress'").list();
	}

	@Override
	public List<Invoice> getAllCompletedInvoices(String email) {
		// TODO Auto-generated method stub
		if(email.equals("admin"))
			return session.getCurrentSession().createQuery("from Invoice where status='Completed'").list();
		return session.getCurrentSession().createQuery("from Invoice where email='"+email+"' AND status='Completed'").list();
	}
}
