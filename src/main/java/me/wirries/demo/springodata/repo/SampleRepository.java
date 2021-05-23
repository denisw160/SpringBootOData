package me.wirries.demo.springodata.repo;

import me.wirries.demo.springodata.model.Sample;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This repository handles the {@link Sample}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Transactional
public interface SampleRepository extends CrudRepository<Sample, String> {

    /**
     * Find all {@link Sample}s for the given tenantId.
     *
     * @param tenantId ID of the tenant
     * @return List of matching {@link Sample}s
     */
    List<Sample> findAllByTenantTenantId(String tenantId);

    /**
     * Find all {@link Sample}s by the given colUnique value.
     *
     * @param colString colUnique
     * @return List of matching {@link Sample}s
     */
    List<Sample> findAllByColString(String colString);

    /**
     * Find the first sample with the lowest colInt value.
     *
     * @return Sample record
     */
    Sample findTopByOrderByColInt();

    /**
     * Find the first sample with the biggest colInt value.
     *
     * @return Sample record
     */
    Sample findTopByOrderByColIntDesc();

}
