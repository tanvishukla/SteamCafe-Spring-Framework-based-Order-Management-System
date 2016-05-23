package com.TOMSystem.service;

import java.util.List;

import com.TOMSystem.model.Item;

public interface ItemService {
	public void addItem(Item item);
	public void editItem(Item item);
	public void removeItem(int itemId);
	public void removeAll();
	public Item getItem(int itemId);
	public List getAllItems();
	public List getDrinks();
	public List getAppetizers();
	public List getMainCourse();
	public List getDesserts();
	public List getUnavailableItems();
}
