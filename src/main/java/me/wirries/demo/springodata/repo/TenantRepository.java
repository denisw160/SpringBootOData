package me.wirries.demo.springodata.repo;

import me.wirries.demo.springodata.model.Tenant;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Repository for {@link Tenant}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Transactional
public interface TenantRepository extends CrudRepository<Tenant, String> {

}
