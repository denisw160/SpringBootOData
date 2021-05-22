package me.wirries.demo.springodata.config;

import me.wirries.demo.springodata.model.Sample;
import me.wirries.demo.springodata.model.Tenant;
import me.wirries.demo.springodata.repo.SampleRepository;
import me.wirries.demo.springodata.repo.TenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This component configures the initial test data ({@link Sample}), if no data is available.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Component
public class DataConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataConfig.class);

    private final UserConfig userConfig;

    private final TenantRepository tenantRepository;
    private final SampleRepository sampleRepository;

    @Autowired
    public DataConfig(UserConfig userConfig,
                      TenantRepository tenantRepository,
                      SampleRepository sampleRepository) {
        this.userConfig = userConfig;
        this.tenantRepository = tenantRepository;
        this.sampleRepository = sampleRepository;
    }

    /**
     * Start with the initialization and setup when the application is ready.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        setup();
    }

    /**
     * Check if data exists, else create default data.
     */
    @Transactional(rollbackOn = {Exception.class})
    public void setup() {
        if (sampleRepository.count() == 0) {
            LOGGER.warn("No sample data exists - generate new data");
            userConfig.setup();

            final List<Tenant> tenants = new ArrayList<>();
            tenantRepository.findAll().forEach(tenants::add);

            final int elements = tenants.size();
            for (int i = 0; i < 25; i++) {
                Sample sample = new Sample();
                sample.setColString("Sample-" + i);
                sample.setColInt(i);
                sample.setColDate(new Date());
                sample.setTenant(tenants.get(i % elements));
                sampleRepository.save(sample);
            }
        }
    }

}
