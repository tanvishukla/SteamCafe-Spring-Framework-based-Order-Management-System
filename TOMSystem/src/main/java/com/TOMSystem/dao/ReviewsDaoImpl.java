package com.TOMSystem.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TOMSystem.model.Reviews;
import com.TOMSystem.model.User;

@Repository
public class ReviewsDaoImpl implements ReviewsDao {
	
	@Autowired
	private SessionFactory session;
	
	@Override
	public void add(Reviews review){
		session.getCurrentSession().save(review);
	}
	
	@Override
	public void edit(Reviews review){
		session.getCurrentSession().update(review);
	}
	
	@Override
	public List getReview(int user_id){
		return session.getCurrentSession().createQuery("from Reviews where userId = "+user_id).list();
	}
	
	@Override
	public List getAllReviews(){
		return session.getCurrentSession().createQuery("from Reviews").list();
	}
}
