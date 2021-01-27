package com.edulcs.graphqlapi.resolver;

import com.edulcs.graphqlapi.domain.Author;
import com.edulcs.graphqlapi.domain.Book;
import com.edulcs.graphqlapi.domain.Publisher;
import com.edulcs.graphqlapi.service.AuthorService;
import com.edulcs.graphqlapi.service.BookService;
import com.edulcs.graphqlapi.service.PublisherService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
public class EntityQueryResolver implements GraphQLQueryResolver {

    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final BookService bookService;

    /**
     * Returns a Publisher for the given id
     *
     * @param id Primary key of the Publisher
     * @return An instance of Publisher
     */
    public Publisher getPublisherById(Long id) {
        try {
            Optional<Publisher> publisher = publisherService.findOne(id);
            if (publisher.isPresent()) {
                return publisher.get();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * Returns an Author for the given id
     *
     * @param id Primary key of the Author
     * @return An instance of Author
     */
    public Author getAuthorById(Long id) {
        try {
            Optional<Author> author = authorService.findOne(id);
            if (author.isPresent()) {
                author.get().getBooks();
                return author.get();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    /**
     * Returns a Book for the given id
     *
     * @param id Primary key of the Book
     * @return An instance of Book
     */
    public Book getBookById(Long id) {
        try {
            Optional<Book> book = bookService.findOne(id);
            if (book.isPresent()) {
                return book.get();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

}
