package com.TOMSystem.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Invoicedetails")
public class InvoiceDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int invoice_details_id; 	
	@Column
	private int item_id;
	

	@Column
	private int invoice_id;
	@Column
	private int quantity;	
	@Column
	private double price;
	@Column
	private String item_name;
	


	/*	@ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(name="invoice", joinColumns=@JoinColumn(name="invoice_details_id"), inverseJoinColumns = @JoinColumn(name="invoice_id"))
    private Set<Invoice> invoices = new HashSet<Invoice>();
*/	
	public InvoiceDetails(){}
	
	public InvoiceDetails(int id, int item_id, int quantity, double price) {
		super();
		this.item_id = item_id;
		this.quantity = quantity;
		this.price = price;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getInvoice_details_id() {
		return invoice_details_id;
	}

	public void setInvoice_details_id(int invoice_details_id) {
		this.invoice_details_id = invoice_details_id;
	}

/*	public Set<Invoice> getInvoice() {
		return invoices;
	}

	public void setInvoice(Set<Invoice> invoice) {
		this.invoices = invoice;
	}
*/
	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}	
	
	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
}
