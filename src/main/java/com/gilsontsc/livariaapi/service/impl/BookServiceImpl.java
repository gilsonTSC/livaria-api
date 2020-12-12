package com.gilsontsc.livariaapi.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gilsontsc.livariaapi.exception.BusinessException;
import com.gilsontsc.livariaapi.model.entity.Book;
import com.gilsontsc.livariaapi.model.repository.BookRepository;
import com.gilsontsc.livariaapi.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository repository;
	
	public BookServiceImpl(BookRepository repository) {
		this.repository = repository;
	}

	@Override
	public Book save(Book book) {
		if(this.repository.existsByIsbn(book.getIsbn())) {
			throw new BusinessException("Isbn já cadastrado.");
		}
		return repository.save(book);
	}

	@Override
	public Optional<Book> getById(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public void delete(Book book) {
		if(book == null || book.getId() == null) {
			throw new IllegalArgumentException("Book null.");
		}
		this.repository.delete(book);
	}

	@Override
	public Book update(Book book) {
		if(book == null || book.getId() == null) {
			throw new IllegalArgumentException("Book null.");
		}
		return this.repository.save(book);
	}

	@Override
	public Page<Book> find(Book book, Pageable pageRequest) {
		Example<Book> example = Example.of(book,
	                ExampleMatcher
	                        .matching()
	                        .withIgnoreCase()
	                        .withIgnoreNullValues()
	                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
	    );
	    return repository.findAll(example, pageRequest);
	}

	@Override
	public Optional<Book> getBookByIsbn(String isbn) {
		return null;
	}

}