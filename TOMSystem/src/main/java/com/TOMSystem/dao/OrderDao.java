package com.TOMSystem.dao;

import java.util.List;
import com.TOMSystem.User.User;

import com.TOMSystem.Order.*;
public interface OrderDao {
		public void add(Order order);
		public void edit(Order order);
		public void delete(Order order);
		public Order getOrder(Order order);
		public List getAllOrders(Order order);
}
