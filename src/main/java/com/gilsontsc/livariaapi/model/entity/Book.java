package com.gilsontsc.livariaapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private Long id;
	private String title;
	private String author;
	private String isbn;
	
}