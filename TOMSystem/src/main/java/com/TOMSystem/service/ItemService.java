package com.TOMSystem.service;

import java.util.List;

import com.TOMSystem.Item.Item;

public interface ItemService {
	public void addItem(Item item);
	public void editItem(Item item);
	public void removeItem(int itemId);
	public Item getItem(int itemId);
	public List getAllItems();
}
