package me.wirries.demo.springodata.repo;

import me.wirries.demo.springodata.model.Role;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Repository for {@link Role}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Transactional
public interface RoleRepository extends CrudRepository<Role, String> {

}
