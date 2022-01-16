package com.etiya.recap.entities.requests.carRequests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarAvailableRequest {

	private int id;

	@NotNull
	private boolean carIsAvailable;
}
