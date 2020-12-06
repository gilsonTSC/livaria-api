package com.gilsontsc.livariaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsontsc.livariaapi.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	boolean existsByIsbn( String isbn );

}