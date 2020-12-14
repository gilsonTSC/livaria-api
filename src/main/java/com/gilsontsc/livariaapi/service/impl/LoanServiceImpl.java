package com.gilsontsc.livariaapi.service.impl;

import org.springframework.stereotype.Service;

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
		return this.repository.save(loan);
	}

}
