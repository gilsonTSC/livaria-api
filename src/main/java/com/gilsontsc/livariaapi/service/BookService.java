package com.gilsontsc.livariaapi.service;

import java.util.Optional;

import com.gilsontsc.livariaapi.model.entity.Book;

public interface BookService {

	Book save(Book book);

	Optional<Book> getById(Long id);

	void delete(Book book);

	Book update(Book book);

}