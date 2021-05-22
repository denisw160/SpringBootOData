package me.wirries.demo.springodata.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * An implementation for {@link GrantedAuthority} to create authority only in upper case.
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.21
 */
public class ApplicationGrantedAuthority implements GrantedAuthority {

    /**
     * Authority prefix for the roleId.
     */
    public static final String ROLE = "ROLE_";
    /**
     * Default authority for administrate the app.
     */
    public static final String ADMIN = ROLE + "ADMIN";
    /**
     * Default authority for display the app.
     */
    public static final String USER = ROLE + "USER";


    private final String role;

    /**
     * Creates a new {@link GrantedAuthority} for the given role.
     *
     * @param role Role
     */
    public ApplicationGrantedAuthority(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        String r = StringUtils.upperCase(role);
        this.role = StringUtils.startsWith(r, ROLE) ? r : ROLE + r;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof GrantedAuthority) {
            return role.equals(((GrantedAuthority) obj).getAuthority());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.role.hashCode();
    }

    @Override
    public String toString() {
        return this.role;
    }

}
