package net.ghaines.booksql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQlBookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public GraphQlBookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping("allBooks")
    public Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping("bookByIsbn")
    public Book byIsbn(@Argument("isbn") String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @QueryMapping("booksByAuthorLastName")
    public Iterable<Book> byAuthorLastName(@Argument("lastName") String lastName) {
        return bookRepository.findByAuthorLastName(lastName);
    }

    @MutationMapping("createBook")
    public Book createBook(@Argument("book") Book book) {
        Author savedAuthor = authorRepository.save(book.getAuthor());
        book.setAuthor(savedAuthor);
        return bookRepository.save(book);
    }
}
