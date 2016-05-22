package com.TOMSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reviews")
public class Reviews {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int review_id;
	@Column
	String email;
	@Column
	int item_id;
	@Column
	double review;
	
	public Reviews(){}
	
	public Reviews(int review_id, String email, int item_id, double review) {
		super();
		this.review_id = review_id;
		this.email = email;
		this.item_id = item_id;
		this.review = review;
	}


	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	
/*	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="EMAIL")
	private User reviewUser;
*/	

	public double getReview() {
		return review;
	}

	public void setReview(double review) {
		this.review = review;
	}	
}
