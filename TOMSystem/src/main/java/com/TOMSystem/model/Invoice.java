package com.TOMSystem.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
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
	private Date pickupTime;
	@Column
	private int prep_Time;
	@Column
	private String status;
	
	@OneToMany(mappedBy="invoice")
	private Set<InvoiceDetails> invoiceDetails;
	
	@ManyToOne
	@JoinColumn(name = "EMAIL")
	private User user;
	
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

	public Set<InvoiceDetails> getInvoiceDetails() {
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

