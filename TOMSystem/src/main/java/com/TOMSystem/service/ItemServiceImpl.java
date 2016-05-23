package com.TOMSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TOMSystem.dao.ItemDao;
import com.TOMSystem.model.Item;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;
	
	@Transactional
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.addItem(item);
	}

	@Transactional
	public void editItem(Item item) {
		// TODO Auto-generated method stub
		itemDao.editItem(item);
	}

	@Transactional
	public void removeItem(int itemId) {
		// TODO Auto-generated method stub
		itemDao.removeItem(itemId);
	}

	@Transactional
	public Item getItem(int itemId) {
		// TODO Auto-generated method stub
		return itemDao.getItem(itemId);
	}

	@Transactional
	public List getAllItems() {
		// TODO Auto-generated method stub
		return itemDao.getAllItems();
	}
	
	@Transactional
	public List getDrinks() {
		// TODO Auto-generated method stub
		return itemDao.getDrinks();
	}

	@Transactional
	public List getAppetizers() {
		// TODO Auto-generated method stub
		return itemDao.getAppetizers();
	}
	@Transactional
	public List getMainCourse() {
		// TODO Auto-generated method stub
		return itemDao.getMainCourse();
	}

	@Transactional
	public List getDesserts() {
		// TODO Auto-generated method stub
		return itemDao.getDesserts();
	}

	@Transactional
	public List getUnavailableItems() {
		// TODO Auto-generated method stub
		return itemDao.getUnavailableItems();
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		itemDao.removeAll();
	}

}
