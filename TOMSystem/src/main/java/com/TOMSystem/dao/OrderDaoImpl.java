package com.TOMSystem.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.TOMSystem.User.User;
import com.TOMSystem.Item.Item;
import com.TOMSystem.Order.*;
@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private SessionFactory session;
		
	@Override
	public void add(Order order) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(order);
	}

	@Override
	public void edit(Order order) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(order);		
	}

	
	@Override
	public void delete(Order order) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(order);
	}

	@Override
	public Order getOrder(Order orderId) {
		// TODO Auto-generated method stub
		
		return (Order)session.getCurrentSession().get(Order.class, (Serializable) orderId);
	}

	@Override
	public List getAllOrders(Order order) {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Order").list();
	}

	

}
