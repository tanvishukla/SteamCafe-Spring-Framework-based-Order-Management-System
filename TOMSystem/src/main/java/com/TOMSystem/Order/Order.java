package com.TOMSystem.Order;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.TOMSystem.Item.*;




@Entity
public class Order {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column
	private int order_id;
	
	//@OneToMany(mappedBy="order1")
	private int item_id;
	@Column
	private int user_id;
	@Column
	private String person_assigned;
	@Column
	private String status;
	@Column
	private int quantity;
	
	public Order(){}
	
	public Order(int order_id, int item_id, int user_id, String person_assigned, String status, int quantity) {
		super();
		this.order_id = order_id;
		this.item_id = item_id;
		this.user_id = user_id;
		this.person_assigned = person_assigned;
		this.status = status;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return order_id;
	}

	public void setOrderId(int id) {
		this.order_id = order_id;
	}

	public int getItemId() {
		return item_id;
	}

	public void setItemId(int item_id) {
		this.item_id = item_id;
	}
	
	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	
	public String getPersonAssigned() {
		return person_assigned;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus(String status){
		return status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}

