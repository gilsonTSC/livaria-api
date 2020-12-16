package com.gilsontsc.livariaapi.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gilsontsc.livariaapi.exception.BusinessException;
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
	public Optional<Loan> getById(long loan) {
		return null;
	}

	@Override
	public Loan update(Loan loan) {
		return null;
	}

}