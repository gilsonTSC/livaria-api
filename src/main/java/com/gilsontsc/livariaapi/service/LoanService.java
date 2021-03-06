package com.gilsontsc.livariaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gilsontsc.livariaapi.api.dto.LoanFilterDTO;
import com.gilsontsc.livariaapi.model.entity.Book;
import com.gilsontsc.livariaapi.model.entity.Loan;

public interface LoanService {

	Loan save(Loan loan);

	Optional<Loan> getById(long id);

	Loan update(Loan loan);

	Page<Loan> find(LoanFilterDTO filterDTO, Pageable pageable);

	Page<Loan> getLoansByBook(Book book, Pageable pageable);
	
	List<Loan> getAllateLoan();

}