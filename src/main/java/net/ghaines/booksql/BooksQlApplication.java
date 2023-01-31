package net.ghaines.booksql;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BooksQlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksQlApplication.class, args);
	}

	@Bean
	public ApplicationRunner dataLoader(BookRepository bookRepository, AuthorRepository authorRepository) {
		return args -> {
			Author smith = authorRepository.save(new Author("John", "Smith"));
			Author doe = authorRepository.save(new Author("John", "Doe"));
			bookRepository.save(new Book("123", "Hello World", smith));
			bookRepository.save(new Book("234", "Hello GraphQL", doe));
		};
	}

}
