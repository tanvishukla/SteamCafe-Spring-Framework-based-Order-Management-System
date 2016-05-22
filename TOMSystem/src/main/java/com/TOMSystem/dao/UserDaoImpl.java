package com.TOMSystem.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TOMSystem.model.User;

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
	public void delete(String emailId) {
		// TODO Auto-generated method stub
		session.getCurrentSession().delete(getUser(emailId));
	}

	@Override
	public User getUser(String emailId) {
		// TODO Auto-generated method stub
		Query query= session.getCurrentSession().createQuery("from User where email=:parameter");
		query.setParameter("parameter", emailId);
		User user = (User) query.uniqueResult();	
		return user;
	}

	@Override
	public List getAllUsers() {
		// TODO Auto-generated method stub
		return session.getCurrentSession().createQuery("from User").list();
	}
	
	@Override
	public User getUserFromAccessToken(String accessToken)
	{
		//String query="select * from User where activation_token='"+accessToken+"'";
		//return (User)session.getCurrentSession().createSQLQuery(query).uniqueResult();
		//return (User)session.getCurrentSession().createQuery(query);
		Query query= session.getCurrentSession().createQuery("from User where activation_token=:name");
		query.setParameter("name", accessToken);
		User user = (User) query.uniqueResult();		
		return user;
	}

}
