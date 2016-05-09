package com.TOMSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.TOMSystem.User.User;
import com.TOMSystem.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void add(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);
	}

	@Transactional
	public void edit(User user) {
		// TODO Auto-generated method stub
		userDao.edit(user);
	}

	@Transactional
<<<<<<< HEAD
	public void delete(String emailId) {
		// TODO Auto-generated method stub
		userDao.delete(emailId);
	}

	@Transactional
	public User getUser(String emailId) {
		// TODO Auto-generated method stub
		return userDao.getUser(emailId);
=======
	public void delete(String email) {
		// TODO Auto-generated method stub
		userDao.delete(email);
	}

	@Transactional
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return userDao.getUser(email);
>>>>>>> 799d81ffefb0bdf9d1cec927e69fc982962b510a
	}

	@Transactional
	public List getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}

}
