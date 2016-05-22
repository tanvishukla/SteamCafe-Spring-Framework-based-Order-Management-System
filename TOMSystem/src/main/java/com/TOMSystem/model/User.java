package com.TOMSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	@Column()
	private String name;	
	@Id
	@Column(nullable=false, unique=true)
	private String email;
	@Column
	private String password;
	@Column
	private String activation_token;
	@Column
	private boolean enabled;
	
/*	@OneToMany(cascade=CascadeType.ALL, mappedBy="reviewUser")
	private Set<Reviews> userReviews;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Invoice> userInvoice;
*/	
	
	//getters & setters
/*	public Set<Reviews> getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(Set<Reviews> userReviews) {
		this.userReviews = userReviews;
	}

	public Set<Invoice> getUserInvoice() {
		return userInvoice;
	}

	public void setUserInvoice(Set<Invoice> userInvoice) {
		this.userInvoice = userInvoice;
	}
*/
	public User(){}
	
	public User(int id, String name, String email, String password, String activation_token, boolean enabled) {
		super();
		this.userId = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.activation_token = activation_token;
		this.enabled = enabled;
	}

	public int getId() {
		return userId;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivation_token() {
		return activation_token;
	}

	public void setActivation_token(String activation_token) {
		this.activation_token = activation_token;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
