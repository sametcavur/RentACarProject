package com.etiya.recap.entities.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.etiya.recap.core.entities.concretes.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler","creditCards"})
@Table(name = "applicationUser")
public class ApplicationUser extends User {

	@JsonIgnore
	@OneToOne(mappedBy = "applicationUser")
	private IndividualCustomer individualCustomer;
	
	@JsonIgnore
	@OneToOne(mappedBy = "applicationUser")
	private CorporateCustomer corporateCustomer;
	
	@JsonIgnore
	@OneToMany(mappedBy = "applicationUser")
	private List<CreditCard> creditCards;

}
