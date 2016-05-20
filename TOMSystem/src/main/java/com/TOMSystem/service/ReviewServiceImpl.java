package com.TOMSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TOMSystem.dao.ReviewsDao;
import com.TOMSystem.model.Reviews;
@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewsDao reviewsDao;
	
	@Transactional
	public void add(Reviews review) {
		// TODO Auto-generated method stub
		reviewsDao.add(review);
	}

	@Transactional
	public void edit(Reviews review) {
		// TODO Auto-generated method stub
		reviewsDao.edit(review);
	}

	@Transactional
	public List getReview(int user_id) {
		// TODO Auto-generated method stub
		return reviewsDao.getReview(user_id);
	}

	@Transactional
	public List getAllReviews() {
		// TODO Auto-generated method stub
		return reviewsDao.getAllReviews();
	}

}
