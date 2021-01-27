package com.edulcs.graphqlapi.service.impl;

import com.edulcs.graphqlapi.domain.Book;
import com.edulcs.graphqlapi.repository.BookRepository;
import com.edulcs.graphqlapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    /**
     * Save a book.
     *
     * @param book the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Book save(Book book) {
        log.debug("Request to save Book: {}", book);
        return bookRepository.save(book);
    }


    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }

    /**
     * Get one book by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findOne(Long id) {
        log.debug("Request to get Book: {}", id);
        return bookRepository.findById(id);
    }

    @Override
    public void delete(Long id) {

    }
}
