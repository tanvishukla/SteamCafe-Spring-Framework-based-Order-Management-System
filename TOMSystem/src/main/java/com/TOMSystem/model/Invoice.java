package com.TOMSystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int invoice_id;
	//@Column
	//private String invoice;
	@Column
	private Date startTime;
	@Column
	private Date endTime;
	@Column
	private Date orderDate;
	@Column
	private Date pickupTime;
	@Column
	private int prep_Time;
	@Column
	private String status;
	@Column
	private String email;
	
	
public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*	@ManyToMany(cascade=CascadeType.ALL, mappedBy="invoices")
	private Set<InvoiceDetails> invoiceDetails = new HashSet<InvoiceDetails>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "EMAIL")
	private User user;
*/	
	public Invoice(){}
	
	public Invoice(int invoice_id, int user_id, String invoice, Date startTime, Date endTime, Date pickupTime, int prep_Time,
			String status) {
		super();
		this.invoice_id = invoice_id;
		//this.user_id = user_id;
		//this.invoice = invoice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pickupTime = pickupTime;
		this.prep_Time = prep_Time;
		this.status = status;
	}


	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

/*	public Set<InvoiceDetails> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetails> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public int getPrep_Time() {
		return prep_Time;
	}

	public void setPrep_Time(int prep_Time) {
		this.prep_Time = prep_Time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

