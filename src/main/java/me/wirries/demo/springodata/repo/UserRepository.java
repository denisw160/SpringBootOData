package me.wirries.demo.springodata.repo;

import me.wirries.demo.springodata.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository for {@link User}.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Transactional
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Return the user with the matching userId.
     *
     * @param userId UserId
     * @return List with matches
     */
    List<User> findAllByUserId(String userId);

}
