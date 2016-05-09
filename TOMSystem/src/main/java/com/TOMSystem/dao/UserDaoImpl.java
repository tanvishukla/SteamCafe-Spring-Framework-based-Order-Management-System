package com.TOMSystem.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.TOMSystem.User.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory session;
		
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		session.getCurrentSession().save(user);
	}

	@Override
	public void edit(User user) {
		// TODO Auto-generated method stub
		session.getCurrentSession().update(user);		
	}

	@Override
<<<<<<< HEAD
	public void delete(String emailId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getUser(emailId));
	}

	@Override
	public User getUser(String emailId) {
		// TODO Auto-generated method stub
		return (User)session.getCurrentSession().get(User.class, emailId);
=======
	public void delete(String email) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getUser(email));
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		
		return (User)session.getCurrentSession().get(User.class, email);
		//return (User)session.getCurrentSession().get(User.class, email);
>>>>>>> 799d81ffefb0bdf9d1cec927e69fc982962b510a
	}

	@Override
	public List getAllUsers() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from User").list();
	}

}
