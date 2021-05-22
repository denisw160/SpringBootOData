package me.wirries.demo.springodata.service;

import me.wirries.demo.springodata.model.Sample;
import me.wirries.demo.springodata.repo.SampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is the business service for the {@link Sample} objects.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Service
public class SampleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleService.class);

    private final SampleRepository repository;

    @Autowired
    public SampleService(SampleRepository repository) {
        this.repository = repository;
    }

    // TODO Implement functions

}
