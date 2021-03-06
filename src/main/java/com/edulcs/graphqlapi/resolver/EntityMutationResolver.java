package com.edulcs.graphqlapi.resolver;

import com.edulcs.graphqlapi.domain.Author;
import com.edulcs.graphqlapi.domain.Book;
import com.edulcs.graphqlapi.domain.Publisher;
import com.edulcs.graphqlapi.service.AuthorService;
import com.edulcs.graphqlapi.service.BookService;
import com.edulcs.graphqlapi.service.PublisherService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityMutationResolver implements GraphQLMutationResolver {

    /**
     * @param publisherService An instance of Spring managed PublisherService bean
     * @param authorService An instance of Spring managed AuthorService bean
     * @param bookService An instance of Spring managed BookService bean
     */
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final BookService bookService;

    /**
     * Adds a Publisher entity
     *
     * @param name name of the Publisher
     * @return An instance of Publisher if created successfully
     * @throws Exception If any occurred
     */
    public Publisher addPublisher(String name) throws Exception {
        Publisher book = new Publisher(name);
        return publisherService.save(book);
    }

    /**
     * Adds a Author entity
     *
     * @param name Name of the Author
     * @param publisherId Id of the parent Publisher
     * @return An instance of Author if created successfully
     * @throws Exception If any occurred
     */
    public Author addAuthor(String name, Long publisherId) throws Exception {
        Author book = new Author(name);
        book.setPublisher(publisherService.findOne(publisherId).get());
        return authorService.save(book);
    }

    /**
     * Adds a Book entity
     *
     * @param title Title of the Book
     * @param ISBN ISBN of the Book
     * @param publisherId Id of the parent Publisher
     * @param authorId Id of the parent Author
     * @return An instance of Book if created successfully
     * @throws Exception If any occurred
     */
    public Book addBook(String title, String ISBN, Long publisherId, Long authorId) throws Exception {
        Book book = new Book(title, ISBN);
        book.setAuthor(authorService.findOne(authorId).get());
        book.setPublisher(publisherService.findOne(publisherId).get());
        return bookService.save(book);

    }

}
