package com.gilsontsc.livariaapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gilsontsc.livariaapi.exception.BusinessException;
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
		when(repository.existsByIsbn(Mockito.anyString())).thenReturn(false);
		
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
	
	@Test
    @DisplayName("Deve lançar erro de negocio ao tentar salvar um livro com isbn duplicado")
    public void shouldNotSaveABookWithDuplicatedISBN(){
        //cenario
        Book book = createValidBook();
        
        //simulando o retorno do metodo existsByIsbn para sempre retornar verdaderiro
        when(repository.existsByIsbn(Mockito.anyString())).thenReturn(true);

        //execucao
        Throwable exception = Assertions.catchThrowable(() -> service.save(book));

        //verificacoes
        assertThat(exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("Isbn já cadastrado.");
        
        //verificar se o repository nunca deve chamou o metodo save
        Mockito.verify(repository, Mockito.never()).save(book);

    }
	
	private Book createValidBook() {
        return Book.builder().isbn("123").author("Fulano").title("As aventuras").build();
    }
	
}