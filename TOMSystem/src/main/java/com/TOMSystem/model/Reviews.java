package com.TOMSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reviews {
	@Id
	private int review_id;
	@Column
	int columnId;
	@Column
	double review;
	
	@ManyToOne
	@JoinColumn(name="EMAIL")
	private User reviewUser;
	
	public Reviews(int userId, int columnId, int review) {
		super();
		//this.userId = userId;
		this.columnId = columnId;
		this.review = review;
	}
	
	public Reviews(){}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public double getReview() {
		return review;
	}

	public void setReview(double review) {
		this.review = review;
	}	
}
