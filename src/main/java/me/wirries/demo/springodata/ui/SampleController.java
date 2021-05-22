package me.wirries.demo.springodata.ui;

import me.wirries.demo.springodata.config.DataConfig;
import me.wirries.demo.springodata.model.Sample;
import me.wirries.demo.springodata.repo.SampleRepository;
import me.wirries.demo.springodata.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Default controller for interaction with the sample data.
 * The controller handles all interactions with the UI.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Controller
public class SampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    private static final String VIEW_NAME = "sample";

    private final DataConfig dataConfig;
    private final SampleService service;
    private final SampleRepository repository;

    @Autowired
    public SampleController(DataConfig dataConfig, SampleService service, SampleRepository repository) {
        this.dataConfig = dataConfig;
        this.service = service;
        this.repository = repository;
    }

    @ModelAttribute("module")
    public String module() {
        return "sample";
    }

    /**
     * Open the start page.
     *
     * @param model Model for the view
     * @return name of the view
     */
    @GetMapping("/sample/list")
    public String list(Model model) {
        LOGGER.debug("Entering sample/list view");

        final List<Sample> list = new ArrayList<>();
        repository.findAll().forEach(list::add);

        model.addAttribute("samples", list);

        return "sample/list";
    }

    /**
     * Delete the existing data and generate 25 new sample records.
     *
     * @return Success message
     */
    @PostMapping("/sample/reset")
    @ResponseBody
    public String reset() {
        LOGGER.info("Resetting data ...");

        repository.deleteAll();
        LOGGER.debug("Count after deletion: " + repository.count());

        dataConfig.setup();
        LOGGER.debug("Count after setup: " + repository.count());

        LOGGER.info("Data reset complete.");
        return "Data reset complete";
    }

}
