package com.TOMSystem.controller;

import com.TOMSystem.model.Item;

public class CartItems {
	
	
	public int itemId;
	public String itemName;
	public double price;
	public int Item_PrepTime;
	public int getItem_PrepTime() {
		return Item_PrepTime;
	}
	public void setItem_PrepTime(int item_PrepTime) {
		Item_PrepTime = item_PrepTime;
	}
	public int quantity;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
}
