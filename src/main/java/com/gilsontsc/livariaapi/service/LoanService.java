package com.gilsontsc.livariaapi.service;

import java.util.Optional;

import com.gilsontsc.livariaapi.model.entity.Loan;

public interface LoanService {

	Loan save(Loan loan);

	Optional<Loan> getById(long loan);

	Loan update(Loan loan);

}