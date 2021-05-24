package me.wirries.demo.springodata.service;

import com.github.dozermapper.core.Mapper;
import me.wirries.demo.springodata.config.DataConfig;
import me.wirries.demo.springodata.model.Sample;
import me.wirries.demo.springodata.model.SampleDto;
import me.wirries.demo.springodata.model.Tenant;
import me.wirries.demo.springodata.repo.SampleRepository;
import me.wirries.demo.springodata.repo.TenantRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    private final Mapper mapper;

    private final DataConfig dataConfig;

    private final SampleRepository sampleRepository;
    private final TenantRepository tenantRepository;

    @Autowired
    public SampleService(Mapper mapper,
                         DataConfig dataConfig,
                         SampleRepository sampleRepository,
                         TenantRepository tenantRepository) {

        this.mapper = mapper;
        this.dataConfig = dataConfig;
        this.sampleRepository = sampleRepository;
        this.tenantRepository = tenantRepository;
    }

    /**
     * Load all tenants.
     *
     * @return List with {@link Tenant}s
     */
    public List<Tenant> loadTenants() {
        // TODO Filter by user tenants
        List<Tenant> list = new ArrayList<>();
        tenantRepository.findAll().forEach(list::add);
        return Collections.unmodifiableList(list);
    }

    /**
     * Load all samples for the given tenantId.
     *
     * @param tenantId Tenant ID
     * @return List with {@link Sample}s
     */
    public List<Sample> load(String tenantId) {
        // TODO Filter by user tenants
        List<Sample> list = new ArrayList<>();

        if (StringUtils.isBlank(tenantId)) {
            sampleRepository.findAll().forEach(list::add);
        } else {
            list.addAll(sampleRepository.findAllByTenantTenantId(tenantId));
        }

        return Collections.unmodifiableList(list);
    }

    /**
     * Load a Sample by UUID from the database and return the detached object.
     *
     * @param sample {@link SampleDto} for update from database
     * @throws Exception Exception during loading
     */
    public void load(SampleDto sample) throws Exception {
        LOGGER.debug("Load sample {}", sample);

        // Check if reference to entity exists
        if (sample == null || StringUtils.isBlank(sample.getUuid())) {
            return;
        }

        // Load from database
        final Optional<Sample> savedSample = sampleRepository.findById(sample.getUuid());
        if (savedSample.isEmpty()) {
            throw new Exception("Sample with uuid " + sample.getUuid() + " not exists");
        }

        // Copy values (detach)
        mapper.map(savedSample.get(), sample);
    }

    /**
     * Save the given sample to database.
     *
     * @param sample Sample
     */
    public void save(SampleDto sample) {
        LOGGER.debug("Saving sample {}", sample);

        // Update with saved values
        Optional<Sample> savedSample = sampleRepository.findById(sample.getUuid());
        if (savedSample.isPresent()) {
            Sample newSample = mapper.map(savedSample.get(), Sample.class);
            mapper.map(sample, newSample);
            sampleRepository.save(newSample);
        } else {
            // Map to new entity and save
            Sample newSample = mapper.map(sample, Sample.class);
            sampleRepository.save(newSample);
        }
    }

    /**
     * Delete the sample with the given uuid.
     *
     * @param uuid {@link UUID}
     */
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

}
