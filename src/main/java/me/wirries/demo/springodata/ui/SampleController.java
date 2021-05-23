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
    public String list(Model model,
                       @ModelAttribute("selectedTenant") SelectedTenant selectedTenant) {
        LOGGER.debug("Entering sample/list view");

        // Set default values
        if (selectedTenant.getTenantId() == null) {
            selectedTenant.setTenantId("");
        }

        loadTenants(model);
        loadSamples(model, selectedTenant);

        return "sample/list";
    }

    /**
     * Create a new sample record and show the edit mask.
     *
     * @param model Model for the view
     * @return name of the view
     */
    @PostMapping("/sample/create")
    public String create(Model model) {
        LOGGER.info("Create new sample ...");

        loadTenants(model);

        final Sample sample = new Sample();
        model.addAttribute("sample", sample);

        return "sample/edit";
    }

    /**
     * Load an existing sample record and show the edit mask.
     *
     * @param model Model for the view
     * @param uuid  UUID of the {@link Sample}
     * @return name of the view
     */
    @PostMapping("/sample/edit")
    public String edit(Model model, @ModelAttribute("uuid") String uuid) {
        LOGGER.info("Edit sample {} ...", uuid);

        loadTenants(model);

        final Sample sample = new Sample();
        sample.setUuid(uuid);
        execute(model, () -> service.load(sample));
        model.addAttribute("sample", sample);

        return "sample/edit";
    }

    /**
     * Save a sample record and goto list view
     *
     * @param model          Model for the view
     * @param selectedTenant currently selected tenant
     * @param sample         Changed sample record
     * @return name of the view
     */
    @PostMapping("/sample/save")
    public String edit(Model model,
                       @SessionAttribute("selectedTenant") SelectedTenant selectedTenant,
                       @ModelAttribute("sample") Sample sample) {
        LOGGER.info("Save sample {} ...", sample);
        execute(model, () -> service.save(sample));
        return list(model, selectedTenant);
    }

    /**
     * Delete an existing sample record. Reload the list.
     *
     * @param model          Model for the view
     * @param selectedTenant currently selected tenant
     * @param uuid           UUID of the {@link Sample}
     * @return name of the view
     */
    @PostMapping("/sample/delete")
    public String reset(Model model,
                        @SessionAttribute("selectedTenant") SelectedTenant selectedTenant,
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
    public String reset(Model model,
                        @SessionAttribute("selectedTenant") SelectedTenant selectedTenant) {
        LOGGER.info("Resetting data ...");
        execute(model, service::reset);
        return list(model, selectedTenant);
    }

    // Internals

    /**
     * Load all tenants in the model.
     */
    private void loadTenants(Model model) {
        final List<Tenant> tenants = new ArrayList<>();
        execute(model, () -> tenants.addAll(service.loadTenants()));
        model.addAttribute("tenants", tenants);
    }

    /**
     * Load the samples for the selected tenant.
     */
    private void loadSamples(Model model, SelectedTenant selectedTenant) {
        final List<Sample> samples = new ArrayList<>();
        execute(model, () -> samples.addAll(service.load(selectedTenant.getTenantId())));
        model.addAttribute("samples", samples);
    }

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
