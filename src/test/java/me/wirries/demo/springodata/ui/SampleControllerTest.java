package me.wirries.demo.springodata.ui;

import me.wirries.demo.springodata.AbstractWebTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Tests for {@link SampleController}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
class SampleControllerTest extends AbstractWebTests {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        createTestData();
    }

    @Test
    void welcome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/sample/list"));
    }

    @Test
    @Disabled
    void reset() throws Exception {
        assertEquals(100, count());
        mockMvc.perform(post("/sample/reset"))
                .andExpect(status().isOk());
        assertEquals(10, count());
    }

}
