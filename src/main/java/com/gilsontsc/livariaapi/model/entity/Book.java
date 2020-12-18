package com.gilsontsc.livariaapi.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String author;
	
	private String isbn;
	
	/**
	 * FetchType.LAZY: para não trazer a lista de empréstimos ao carregar o book. Valor default.
	 * FetchType.EAGER: para trazer a lista de empréstimos ao carregar o book.
	 */
	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private List<Loan> loans;
	
}