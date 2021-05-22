package me.wirries.demo.springodata.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * This controller handles the interaction with the user data.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ModelAttribute("module")
    public String module() {
        return "user";
    }

    /**
     * Open the start page.
     *
     * @param model Model for the view
     * @return name of the view
     */
    @GetMapping("/user/list")
    public String list(Model model) {
        LOGGER.debug("Entering user/list view");
        return "user/list";
    }

}
