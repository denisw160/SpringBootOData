package me.wirries.demo.springodata.config;

import me.wirries.demo.springodata.model.BooleanType;
import me.wirries.demo.springodata.model.Role;
import me.wirries.demo.springodata.model.Tenant;
import me.wirries.demo.springodata.model.User;
import me.wirries.demo.springodata.repo.RoleRepository;
import me.wirries.demo.springodata.repo.TenantRepository;
import me.wirries.demo.springodata.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * This component configures the initial user, role and tenant data ({@link User}, {@link Role} and {@link Tenant}),
 * if no data is available.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Component
public class UserConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConfig.class);

    private final TenantRepository tenantRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserConfig(TenantRepository tenantRepository,
                      RoleRepository roleRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.tenantRepository = tenantRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (tenantRepository.count() == 0) {
            LOGGER.warn("No tenants exists - generate default tenants");
            Tenant company1 = new Tenant();
            company1.setTenantId("C1");
            company1.setName("Company 1");
            tenantRepository.save(company1);

            Tenant company2 = new Tenant();
            company2.setTenantId("C2");
            company2.setName("Company 2");
            tenantRepository.save(company2);
        }
        LOGGER.debug("In database existing {} tenants", tenantRepository.count());

        if (roleRepository.count() == 0) {
            LOGGER.warn("No roles exists - generate default roles");
            Role roleAdmin = new Role();
            roleAdmin.setRoleId("admin");
            roleAdmin.setName("Administrator");
            roleRepository.save(roleAdmin);

            Role roleUser = new Role();
            roleUser.setRoleId("user");
            roleUser.setName("User");
            roleRepository.save(roleUser);
        }
        LOGGER.debug("In database existing {} roles", roleRepository.count());

        if (userRepository.count() == 0) {
            // Load roles and tenants
            final List<Role> roles = new ArrayList<>();
            roleRepository.findAll().forEach(roles::add);
            final List<Tenant> tenants = new ArrayList<>();
            tenantRepository.findAll().forEach(tenants::add);

            LOGGER.warn("No users exists - generate default users");
            User admin = new User();
            admin.setUserId("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFirstName("System");
            admin.setLastName("Administrator");
            admin.setDisabled(BooleanType.FALSE);
            admin.setRoles(roles);
            admin.setTenants(tenants);
            userRepository.save(admin);

            User user = new User();
            user.setUserId("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setFirstName("Default");
            user.setLastName("User");
            user.setDisabled(BooleanType.FALSE);
            user.setRoles(roles); // TODO Filter roles
            user.setTenants(tenants); // TODO Filter tenants
            userRepository.save(user);
        }
        LOGGER.debug("In database existing {} users", userRepository.count());

    }

}
