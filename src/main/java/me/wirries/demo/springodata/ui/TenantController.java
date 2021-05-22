package me.wirries.demo.springodata.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * This controller handles the interaction with the tenant data.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Controller
public class TenantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenantController.class);

    @ModelAttribute("module")
    public String module() {
        return "tenant";
    }

    /**
     * Open the start page.
     *
     * @param model Model for the view
     * @return name of the view
     */
    @GetMapping("/tenant/list")
    public String list(Model model) {
        LOGGER.debug("Entering tenant/list view");
        return "tenant/list";
    }

}
