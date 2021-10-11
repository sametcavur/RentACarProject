package com.etiya.recap.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "creditcards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nameOnTheCard")
	private String nameOnTheCard;
	
	@Column(name = "cardNumber")
	private String cardNumber;
	
	@Column(name = "expirationDate")
	private Date expirationDate;
	
	@Column(name = "cvc")
	private String cvc;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private ApplicationUser applicationUser;

	
}
