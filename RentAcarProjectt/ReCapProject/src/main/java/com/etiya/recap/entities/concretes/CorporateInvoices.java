package com.etiya.recap.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@Table(name = "corpareteinvoices")
public class CorporateInvoices {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoices_id")
	private int id;
	
	@Column(name = "invoiceNumber")
	private int invoiceNumber;
	
	@Column(name = "creationDate")
	private Date creationDate;
	
	@Column(name = "rentDateCount")
	private long rentDateCount;
	
	@Column(name = "rentPrice")
	private double rentPrice;
	
	@OneToOne
	private Rental rental;
	
	@ManyToOne
	@JoinColumn(name = "corporate_id")
	private CorporateCustomer corporateCustomer;

}

