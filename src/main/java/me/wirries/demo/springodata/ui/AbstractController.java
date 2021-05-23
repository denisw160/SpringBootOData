package me.wirries.demo.springodata.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Base class for controllers with common methods.
 *
 * @author denisw
 * @version 1.0
 * @since 23.05.2021
 */
public abstract class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    /**
     * Return the name of the module.
     *
     * @return Name
     */
    @ModelAttribute("module")
    public abstract String module();

    /**
     * Execute a command in try-catch. If an exception occurs the exception details
     * are added to the model.
     *
     * @param model   Model for the view
     * @param command Command for execution
     * @return TRUE - success / FALSE - failed
     */
    protected boolean execute(Model model, Command command) {
        try {
            command.execute();
            return true;
        } catch (Exception e) {
            LOGGER.error("Exception during execute command", e);
            handleError(model, e);
            return false;
        }
    }

    /**
     * Add the exception details to the model.
     *
     * @param model Model for the view
     * @param ex    Exception of the error
     */
    protected void handleError(Model model, Exception ex) {
        if (model == null) {
            return;
        }

        // Set the errorMessage to the model
        if (ex != null) {
            model.addAttribute("errorMessage", ex.getMessage());
        }
    }

}
