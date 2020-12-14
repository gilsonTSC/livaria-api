package com.gilsontsc.livariaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsontsc.livariaapi.model.entity.Book;
import com.gilsontsc.livariaapi.model.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

	boolean existsByBookAndNotReturned(Book book);

}