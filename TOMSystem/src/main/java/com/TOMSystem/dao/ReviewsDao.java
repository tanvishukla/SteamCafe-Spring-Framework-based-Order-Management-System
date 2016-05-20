package com.TOMSystem.dao;

import java.util.List;

import com.TOMSystem.model.Reviews;

public interface ReviewsDao {
	public void add(Reviews review);
	public void edit(Reviews review);
	public List getReview(int user_id);
	public List getAllReviews();
}
