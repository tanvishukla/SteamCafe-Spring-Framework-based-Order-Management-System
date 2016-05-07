package com.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TOMSystem.Item.Item;

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
		return session.getCurrentSession().createQuery("from Item").list();
	}

}
