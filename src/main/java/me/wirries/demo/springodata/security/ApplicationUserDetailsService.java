package me.wirries.demo.springodata.security;


import me.wirries.demo.springodata.model.User;
import me.wirries.demo.springodata.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This service loads the user details from the application database.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.21
 */
public class ApplicationUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUserDetailsService.class);

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    private UserRepository repository;

    @Override
    public ApplicationUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        List<User> users = repository.findAllByUserId(userId);
        String remoteAddr = request != null ? request.getRemoteAddr() : "N/A";

        if (users.isEmpty()) {
            LOGGER.warn("Query returned no results for user {} from remote address: {}", userId, remoteAddr);
            throw new UsernameNotFoundException(String.format("Username %s not found", userId));
        }

        LOGGER.debug("UserDetails for {} loaded by request from remote address: {}", userId, remoteAddr);
        return new ApplicationUserDetails(users.get(0), remoteAddr);
    }

}
