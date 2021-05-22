package me.wirries.demo.springodata.security;

import me.wirries.demo.springodata.model.Tenant;
import me.wirries.demo.springodata.model.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is a wrapper for the {@link User} from the database to the interface {@link UserDetails}.
 * The {@link UserDetails} are used for authentication and authorisation of the user.
 * And can also use this details in the application.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.21
 */
public class ApplicationUserDetails implements UserDetails, CredentialsContainer {

    private final String uuid;
    private final String username;
    private String password;
    private final String displayName;
    private final boolean enabled;
    private final String remoteAddr;
    private final Set<GrantedAuthority> authorities;
    private final Set<Tenant> tenants;

    /**
     * Create an {@link ApplicationUserDetails} from an existing {@link User}
     * without a remote address.
     *
     * @param user existing user
     */
    public ApplicationUserDetails(User user) {
        this(user, null);
    }

    /**
     * Create an {@link ApplicationUserDetails} from an existing {@link User}.
     *
     * @param user       existing user
     * @param remoteAddr current remote address of the user
     */
    public ApplicationUserDetails(User user, String remoteAddr) {
        this.uuid = user.getUuid();
        this.username = user.getUserId();
        this.displayName = user.getFirstName() + " " + user.getLastName();
        this.password = user.getPassword();
        this.enabled = !user.getDisabled().value();
        this.remoteAddr = remoteAddr;
        this.authorities = new HashSet<>();
        this.tenants = new HashSet<>();
        setAuthorities(user);
        setTenants(user);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(authorities);
    }

    public Collection<Tenant> getTenants() {
        return Collections.unmodifiableCollection(tenants);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    private void setAuthorities(User user) {
        // Add default user authority to all users
        authorities.add(new ApplicationGrantedAuthority("ROLE_USER"));

        // Convert all permission to authorities
        if (user.getRoles() != null) {
            user.getRoles().forEach(p -> authorities.add(new ApplicationGrantedAuthority(p.getRoleId())));
        }
    }

    private void setTenants(User user) {
        // Convert all permission to authorities
        if (user.getTenants() != null) {
            tenants.addAll(user.getTenants());
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("username", username)
                .append("displayName", displayName)
                .append("enabled", enabled)
                .append("remoteAddr", remoteAddr)
                .append("authorities", authorities)
                .append("tenants", tenants)
                .toString();
    }

}
