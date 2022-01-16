package com.etiya.recap.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "rentals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int Id;
	
	@OneToOne
	@JoinColumn(name ="car_id")
	private Car car;
	
	@OneToOne
	@JoinColumn(name ="customer_id")
	private Customer customer;
	
	
	@Column(name = "rent_date")
	private Date rentDate;
	
	@Column(name = "return_date")
	private Date returnDate;
	
	
	
	
	
	
	
	
	

}
