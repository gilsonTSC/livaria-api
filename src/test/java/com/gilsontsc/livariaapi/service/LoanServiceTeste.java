package com.gilsontsc.livariaapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gilsontsc.livariaapi.model.entity.Book;
import com.gilsontsc.livariaapi.model.entity.Loan;
import com.gilsontsc.livariaapi.model.repository.LoanRepository;
import com.gilsontsc.livariaapi.service.impl.LoanServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LoanServiceTeste {

	LoanService service;

    @MockBean
    LoanRepository repository;
    
    @BeforeEach
    public void setUp(){
        this.service = new LoanServiceImpl(repository);
    }

	@Test
    @DisplayName("Deve salvar um empréstimo")
    public void saveLoanTest(){
        Book book = Book.builder().id(1l).build();
        String customer = "Fulano";

        Loan savingLoan =
                Loan.builder()
                .book(book)
                .customer(customer)
                .loanDate(LocalDate.now())
                .build();

        Loan savedLoan = Loan.builder()
                    .id(1l)
                    .loanDate(LocalDate.now())
                    .customer(customer)
                    .book(book).build();

        when(repository.save(savingLoan)).thenReturn(savedLoan);

        Loan loan = service.save(savingLoan);

        assertThat(loan.getId()).isEqualTo(savedLoan.getId());
        assertThat(loan.getBook().getId()).isEqualTo(savedLoan.getBook().getId());
        assertThat(loan.getCustomer()).isEqualTo(savedLoan.getCustomer());
        assertThat(loan.getLoanDate()).isEqualTo(savedLoan.getLoanDate());
    }

}