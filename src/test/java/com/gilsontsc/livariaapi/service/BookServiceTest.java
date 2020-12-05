package com.gilsontsc.livariaapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gilsontsc.livariaapi.model.entity.Book;
import com.gilsontsc.livariaapi.model.repository.BookRepository;
import com.gilsontsc.livariaapi.service.impl.BookServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

	BookService service;
	
	@MockBean
	BookRepository repository;
	
	@BeforeEach
	public void setUp() {
		this.service = new BookServiceImpl(repository);
	}
	
	@Test
	@DisplayName("Deve salvar um livro")
	public void saveBookTest() {
		//cenario
		Book book = Book.builder().isbn("123").author("Fulano").title("As aventuras").build();
		
		Mockito.when(repository.save(book))
			.thenReturn(Book.builder()
							.id(1L)
							.isbn("123")
							.author("Fulano")
							.title("As aventuras")
							.build());
		
		//execucao
		Book saveBook = service.save(book);
		
		//verificacao
		assertThat(saveBook.getId()).isNotNull();
		assertThat(saveBook.getIsbn()).isEqualTo("123");
		assertThat(saveBook.getTitle()).isEqualTo("As aventuras");
		assertThat(saveBook.getAuthor()).isEqualTo("Fulano");
		
	}
	
}