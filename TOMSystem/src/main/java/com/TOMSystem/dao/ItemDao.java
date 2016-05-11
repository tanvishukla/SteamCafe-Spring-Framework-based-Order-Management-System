package com.TOMSystem.dao;

import java.util.List;
import com.TOMSystem.Item.Item;

public interface ItemDao {
	public void addItem(Item item);
	public void editItem(Item item);
	public void removeItem(int itemId);
	public Item getItem(int itemId);
	public List getAllItems();
	public List getDrinks();
	public List getAppetizers();
	public List getMainCourse();
	public List getDesserts();
	
}
