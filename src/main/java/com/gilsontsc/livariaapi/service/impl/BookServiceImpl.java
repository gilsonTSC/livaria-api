package com.gilsontsc.livariaapi.service.impl;

import org.springframework.stereotype.Service;

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
		return repository.save(book);
	}

}
