package me.wirries.demo.springodata.service;

import me.wirries.demo.springodata.config.DataConfig;
import me.wirries.demo.springodata.model.Sample;
import me.wirries.demo.springodata.model.Tenant;
import me.wirries.demo.springodata.repo.SampleRepository;
import me.wirries.demo.springodata.repo.TenantRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the business service for the {@link Sample} objects.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Service
public class SampleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleService.class);

    private final DataConfig dataConfig;

    private final SampleRepository sampleRepository;
    private final TenantRepository tenantRepository;

    @Autowired
    public SampleService(DataConfig dataConfig, SampleRepository sampleRepository, TenantRepository tenantRepository) {
        this.dataConfig = dataConfig;
        this.sampleRepository = sampleRepository;
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> loadTenants() {
        // TODO Filter by user tenants
        List<Tenant> list = new ArrayList<>();
        tenantRepository.findAll().forEach(list::add);
        return list;
    }

    public List<Sample> load(String tenantId) {
        // TODO Filter by user tenants
        List<Sample> list = new ArrayList<>();

        if (StringUtils.isBlank(tenantId)) {
            sampleRepository.findAll().forEach(list::add);
        } else {
            list.addAll(sampleRepository.findAllByTenantTenantId(tenantId));
        }

        return list;
    }

    public void delete(String uuid) {
        LOGGER.debug("Delete samples {}", uuid);
        sampleRepository.deleteById(uuid);
        LOGGER.debug("Count after deletion: " + sampleRepository.count());
    }

    /**
     * Resetting the sample data.
     */
    public void reset() {
        LOGGER.debug("Deleting all samples");
        sampleRepository.deleteAll();
        LOGGER.debug("Count after deletion: " + sampleRepository.count());

        dataConfig.setup();
        LOGGER.debug("Count after setup: " + sampleRepository.count());
        LOGGER.info("Data reset complete.");
    }

    // TODO Implement functions

}
