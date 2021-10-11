package com.etiya.recap.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditCardDto {

	private String nameOnTheCard;

	private String cardNumber;

	private Date expirationDate;

	private String cvc;

}
