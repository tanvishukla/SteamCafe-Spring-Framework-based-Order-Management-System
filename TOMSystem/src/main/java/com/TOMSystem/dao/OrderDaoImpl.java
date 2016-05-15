package com.TOMSystem.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TOMSystem.Item.Item;
import com.TOMSystem.Order.*;
import com.TOMSystem.service.ItemService;
@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private SessionFactory session;
	@Autowired
	private ItemService itemService;
	
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
	public void delete(int order_id){
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getOrder(order_id));
	}

	@Override
	public Order getOrder(int order_id){
		// TODO Auto-generated method stub		
		return (Order)session.getCurrentSession().get(Order.class, order_id);
	}

	@Override
	public List getAllOrders(){
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from Order").list();
	}
	
	@Override
	public int totalPrepTime(ArrayList<Item>  order){
		int totalPrepTime=0;
		Item tempItem;
		
		//String idArray[] = order.getOrder().split(":");
		
		for(int i=0; i<order.size(); i++){
			tempItem=itemService.getItem(order.get(i).getId());
			totalPrepTime+=tempItem.getPrep_time();
		}
		return totalPrepTime;
	}
	
	@Override
	public List<Item> getAllItems(Order order){
		List<Item> tempItemList = null;
		int itemId;
		
		String idArray[] = order.getOrder().split(":");
		
		for(int i=0; i<idArray.length; i++){
			tempItemList.add(itemService.getItem(Integer.valueOf(idArray[i])));
		}
		return tempItemList;
	}
}
