package com.edulcs.graphqlapi.service.impl;

import com.edulcs.graphqlapi.domain.Publisher;
import com.edulcs.graphqlapi.repository.PublisherRepository;
import com.edulcs.graphqlapi.service.PublisherService;
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
public class PublisherServiceImpl implements PublisherService {

    private final Logger log = LoggerFactory.getLogger(PublisherServiceImpl.class);

    private final PublisherRepository publisherRepository;

    /**
     * Save a publisher.
     *
     * @param publisher the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Publisher save(Publisher publisher) {
        log.debug("Request to save Publisher : {}", publisher);
        return publisherRepository.save(publisher);
    }

    /**
     * Get all the publishers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Publisher> findAll(Pageable pageable) {
        log.debug("Request to get all Publishers");
        return publisherRepository.findAll(pageable);
    }

    /**
     * Get one publisher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Publisher> findOne(Long id) {
        log.debug("Request to get Publisher : {}", id);
        return publisherRepository.findById(id);
    }

    /**
     * Delete the publisher by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Publisher : {}", id);
        publisherRepository.deleteById(id);
    }
}