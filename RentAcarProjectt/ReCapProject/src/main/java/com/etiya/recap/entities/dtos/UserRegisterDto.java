package com.etiya.recap.entities.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterDto {

	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;

}
