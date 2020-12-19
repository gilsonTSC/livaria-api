package com.gilsontsc.livariaapi.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gilsontsc.livariaapi.api.dto.BookDTO;
import com.gilsontsc.livariaapi.api.dto.LoanDTO;
import com.gilsontsc.livariaapi.api.dto.LoanFilterDTO;
import com.gilsontsc.livariaapi.api.dto.ReturnedLoanDTO;
import com.gilsontsc.livariaapi.model.entity.Book;
import com.gilsontsc.livariaapi.model.entity.Loan;
import com.gilsontsc.livariaapi.service.BookService;
import com.gilsontsc.livariaapi.service.LoanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/loans")
@RequiredArgsConstructor
@Api("Empréstimo API")
public class LoanController {

	private final LoanService service;
    private final BookService bookService;
    private final ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Criar empréstimo")
	@ApiResponses({
		@ApiResponse(code = 401, message = "Não autorizado"),
		@ApiResponse(code = 404, message = "Não encontrado"),
		@ApiResponse(code = 403, message = "Acesso negado")
	})
    public Long create(@RequestBody LoanDTO dto) {
        Book book = bookService
                .getBookByIsbn(dto.getIsbn())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book not found for passed isbn"));
        Loan entity = Loan.builder()
                .book(book)
                .customer(dto.getCustomer())
                .loanDate(LocalDate.now())
                .build();

        entity = service.save(entity);
        return entity.getId();
    }
    
    @PatchMapping("{id}")
    @ApiOperation("Atualizar emp´restimo por id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Ok"),
		@ApiResponse(code = 204, message = "Atualizado"),
		@ApiResponse(code = 401, message = "Não autorizado"),
		@ApiResponse(code = 403, message = "Acesso negado")
	})
    public void returnBook(@PathVariable Long id, @RequestBody ReturnedLoanDTO dto) {
        Loan loan = service.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        loan.setReturned(dto.getReturned());
        service.update(loan);
    }
    
    @GetMapping
    @ApiOperation("Obter empréstimo por id")
	@ApiResponses({
		@ApiResponse(code = 401, message = "Não autorizado"),
		@ApiResponse(code = 404, message = "Não encontrado"),
		@ApiResponse(code = 403, message = "Acesso negado")
	})
    public Page<LoanDTO> find(LoanFilterDTO dto, Pageable pageRequest) {
        Page<Loan> result = service.find(dto, pageRequest);
        List<LoanDTO> loans = result
                .getContent()
                .stream()
                .map(entity -> {

                    Book book = entity.getBook();
                    BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
                    LoanDTO loanDTO = modelMapper.map(entity, LoanDTO.class);
                    loanDTO.setBook(bookDTO);
                    return loanDTO;

                }).collect(Collectors.toList());
        return new PageImpl<LoanDTO>(loans, pageRequest, result.getTotalElements());
    }
    
}