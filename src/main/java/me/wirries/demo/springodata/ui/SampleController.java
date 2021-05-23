package me.wirries.demo.springodata.ui;

import me.wirries.demo.springodata.model.Sample;
import me.wirries.demo.springodata.model.Tenant;
import me.wirries.demo.springodata.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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
@SessionAttributes("selectedTenant")
public class SampleController extends AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    // Spring dependencies
    private final SampleService service;

    @Autowired
    public SampleController(SampleService service) {
        this.service = service;
    }

    // Model- and session attributes
    @Override
    public String module() {
        return "sample";
    }

    @ModelAttribute("selectedTenant")
    public SelectedTenant selectedTenant() {
        return new SelectedTenant();
    }

    // Request methods

    /**
     * Open the start page.
     *
     * @param model          Model for the view
     * @param selectedTenant currently selected tenant or updated
     * @return name of the view
     */
    @RequestMapping(path = "/sample/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model, @ModelAttribute("selectedTenant") SelectedTenant selectedTenant) {
        LOGGER.debug("Entering sample/list view");

        // Set default values
        if (selectedTenant.getTenantId() == null) {
            selectedTenant.setTenantId("");
        }

        final List<Tenant> tenants = new ArrayList<>();
        execute(model, () -> tenants.addAll(service.loadTenants()));
        model.addAttribute("tenants", tenants);

        final List<Sample> samples = new ArrayList<>();
        execute(model, () -> samples.addAll(service.load(selectedTenant.getTenantId())));
        model.addAttribute("samples", samples);

        return "sample/list";
    }

    @PostMapping("/sample/delete")
    public String reset(Model model,
                        @ModelAttribute("selectedTenant") SelectedTenant selectedTenant,
                        @ModelAttribute("uuid") String uuid) {
        LOGGER.info("Deleting sample {} ...", uuid);
        execute(model, () -> service.delete(uuid));
        return list(model, selectedTenant);
    }

    /**
     * Delete the existing data and generate 25 new sample records.
     *
     * @param model          Model for the view
     * @param selectedTenant currently selected tenant
     * @return Success message
     */
    @PostMapping("/sample/reset")
    public String reset(Model model, @ModelAttribute("selectedTenant") SelectedTenant selectedTenant) {
        LOGGER.info("Resetting data ...");
        execute(model, service::reset);
        return list(model, selectedTenant);
    }

    // Internals

    /**
     * This is a internal class for storing the selected tenantId.
     */
    private static class SelectedTenant implements Serializable {
        private String tenantId;

        public String getTenantId() {
            return tenantId;
        }

        public void setTenantId(String tenantId) {
            this.tenantId = tenantId;
        }
    }

}
