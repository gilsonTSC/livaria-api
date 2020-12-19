package com.gilsontsc.livariaapi.api.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

	private Long id;
	
	@NotEmpty
	private String isbn;
	
	@NotEmpty
	private String customer;
	
	@NotEmpty
	private String email;
	
	private BookDTO book;
	
}