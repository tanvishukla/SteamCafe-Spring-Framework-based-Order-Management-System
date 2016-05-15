package com.TOMSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.TOMSystem.Item.Item;
import com.TOMSystem.Order.Order;
import com.TOMSystem.dao.OrderDao;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
		
	@Transactional
	public void add(Order order) {
		// TODO Auto-generated method stub
		orderDao.add(order);
	}

	@Transactional
	public void edit(Order order) {
		// TODO Auto-generated method stub
		orderDao.edit(order);
	}

	@Transactional
	public void delete(int order_id) {
		// TODO Auto-generated method stub
		orderDao.delete(order_id);
	}

	@Transactional
	public Order getOrder(int order_id) {
		// TODO Auto-generated method stub
		return orderDao.getOrder(order_id);
	}

	@Transactional
	public List getAllOrders() {
		// TODO Auto-generated method stub
		return orderDao.getAllOrders();
	}
	

	@Transactional
	public int totalPrepTime(ArrayList<Item>  order) {
		// TODO Auto-generated method stub
		return orderDao.totalPrepTime(order);
	}

	@Transactional
	public List<Item> getAllItems(Order order) {
		// TODO Auto-generated method stub
		return orderDao.getAllItems(order);
	}

}
