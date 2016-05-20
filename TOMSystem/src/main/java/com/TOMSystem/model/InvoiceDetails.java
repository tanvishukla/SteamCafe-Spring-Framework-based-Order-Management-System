package com.TOMSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InvoiceDetails {
	
	@Id
	private int invoice_details_id; 
	
	@Column
	private int item_id;
	
	@Column
	private int quantity;
	
	@Column
	private double price;
	
	@ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;
	
	public InvoiceDetails(){}
	
	public InvoiceDetails(int id, int item_id, int quantity, double price) {
		super();
		this.item_id = item_id;
		this.quantity = quantity;
		this.price = price;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	
}
