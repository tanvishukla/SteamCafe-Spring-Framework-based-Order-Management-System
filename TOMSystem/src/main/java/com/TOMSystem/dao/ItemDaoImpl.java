package com.TOMSystem.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TOMSystem.model.Item;

@Repository
public class ItemDaoImpl implements ItemDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(item);
	}

	@Override
	public void editItem(Item item) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(item);
	}

	@Override
	public void removeItem(int itemId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getItem(itemId));
	}

	@Override
	public Item getItem(int itemId) {
		// TODO Auto-generated method stub
		return (Item)session.getCurrentSession().get(Item.class, itemId);
	}

	@Override
	public List getAllItems() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Item where Availability=true").list();
	}
	
	
	
	@Override
	public List getDrinks(){
		
		return session.getCurrentSession().createQuery("from Item where category ='Drinks' and availability='1'").list();
	}
	
	@Override
	public List getAppetizers(){
		
		return session.getCurrentSession().createQuery("from Item where category ='Appetizer' and availability='1'").list();
	}
	
	@Override
	public List getMainCourse(){
		
		return session.getCurrentSession().createQuery("from Item where category ='Main Course' and availability='1'").list();
	}
	
	@Override
	public List getDesserts(){
		
		return session.getCurrentSession().createQuery("from Item where category ='Dessert' and availability='1'").list();
	}
	
	@Override
	public List getUnavailableItems(){
		return session.getCurrentSession().createQuery("from Item where availability='0'").list();
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		session.getCurrentSession().createQuery("delete from Item where id > 0");
	}

}
