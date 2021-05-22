package me.wirries.demo.springodata.service;

import me.wirries.demo.springodata.AbstractDatabaseTests;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Testcase for {@link SampleService}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
class SampleServiceTest extends AbstractDatabaseTests {

    @Autowired
    private SampleService service;

    @BeforeEach
    void setUp() {
        createTestData();
    }

    // TODO Write tests

}
