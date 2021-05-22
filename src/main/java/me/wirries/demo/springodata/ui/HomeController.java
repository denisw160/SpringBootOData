package me.wirries.demo.springodata.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Default Controller for handling user requests.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Controller
public class HomeController {

    /**
     * Redirects to the start page.
     *
     * @return Redirect
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/sample/list";
    }

}
