package me.wirries.demo.springodata.ui;

import me.wirries.demo.springodata.AbstractWebTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Tests for {@link HomeController}.
 *
 * @author denisw
 * @version 1.0
 * @since 23.05.2021
 */
class HomeControllerTest extends AbstractWebTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Setup for tests
    }

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sample/list"));
    }

    @Test
    void favicon() throws Exception {
        mockMvc.perform(get("/favicon.ico"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/resources/images/favicon.ico"));
    }

}
