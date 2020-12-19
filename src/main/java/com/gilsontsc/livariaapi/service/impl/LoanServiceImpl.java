package com.gilsontsc.livariaapi.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gilsontsc.livariaapi.api.dto.LoanFilterDTO;
import com.gilsontsc.livariaapi.exception.BusinessException;
import com.gilsontsc.livariaapi.model.entity.Book;
import com.gilsontsc.livariaapi.model.entity.Loan;
import com.gilsontsc.livariaapi.model.repository.LoanRepository;
import com.gilsontsc.livariaapi.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService{

	private LoanRepository repository;
	
	public LoanServiceImpl(LoanRepository repository) {
		this.repository = repository;
	}

	@Override
	public Loan save(Loan loan) {
		if(this.repository.existsByBookAndNotReturned(loan.getBook())) {
			throw new BusinessException("Livro já empréstado");
		}
		return this.repository.save(loan);
	}

	@Override
	public Optional<Loan> getById(long id) {
		return this.repository.findById(id);
	}

	@Override
	public Loan update(Loan loan) {
		return this.repository.save(loan);
	}

	@Override
	public Page<Loan> find(LoanFilterDTO filterDTO, Pageable pageable) {
		return repository.findByBookIsbnOrCustomer(filterDTO.getIsbn(), filterDTO.getCustomer(), pageable);
	}

	@Override
	public Page<Loan> getLoansByBook(Book book, Pageable pageable) {
		return repository.findByBook(book, pageable);
	}

	@Override
	public List<Loan> getAllateLoan() {
		final Integer loanDays = 4;
		LocalDate threDaysAgo = LocalDate.now().minusDays(loanDays);
		return this.repository.findByLoanDateLessThanAndNotReturned(threDaysAgo);
	}

}