package com.TOMSystem.Order;

import java.util.Date;
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
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int order_id;
	@Column
	private int user_id;
	@Column
	private String order;
	@Column
	private Date startTime;
	@Column
	private Date endTime;
	@Column
	private Date pickupTime;
	@Column
	private int prep_Time;
	@Column
	private String status;
	
	public Order(){}
	
	public Order(int order_id, int user_id, String order, Date startTime, Date endTime, Date pickupTime, int prep_Time,
			String status) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
		this.order = order;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pickupTime = pickupTime;
		this.prep_Time = prep_Time;
		this.status = status;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public int getPrep_Time() {
		return prep_Time;
	}

	public void setPrep_Time(int prep_Time) {
		this.prep_Time = prep_Time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

