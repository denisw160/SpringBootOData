package me.wirries.demo.springodata.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Utility methods for security handling.
 *
 * @author denisw
 * @version 1.0
 */
public final class SecurityUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    private SecurityUtils() {
        // Util methods only
    }

    /**
     * Convert roleId to authorityId.
     *
     * @param roleId roleId, e.g. user or admin
     * @return authorityId, e.g. ROLE_USER or ROLE_ADMIN
     */
    public static String toAuthority(String roleId) {
        return ApplicationGrantedAuthority.ROLE + roleId.toUpperCase();
    }

    /**
     * Gets the user name of the currently signed in user.
     *
     * @return the user name of the current user or <code>null</code> if the user
     * has not signed in
     */
    public static String getUsername() {
        ApplicationUserDetails user = getUser();
        if (user != null) {
            return user.getUsername();
        }
        // Anonymous or no authentication.
        return null;
    }

    /**
     * Get the {@link GrantedAuthority}s from the current user or null.
     *
     * @return List of {@link GrantedAuthority}s
     */
    public static Collection<GrantedAuthority> getAuthorities() {
        ApplicationUserDetails user = getUser();
        if (user != null) {
            return user.getAuthorities();
        }
        // Anonymous or no authentication.
        return null;
    }

    /**
     * Has the user access to the role.
     *
     * @param authorityId Authority ID, e.g. ROLE_USER or ROLE_ADMIN
     * @return true if rule matched (equals)
     */
    public static boolean hasAccess(String authorityId) {
        ApplicationUserDetails user = getUser();
        if (user != null) {
            return user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(authorityId::equals);
        }
        // Anonymous or no authentication.
        return false;
    }

    /**
     * Has the user access to one of the given authorityIds.
     *
     * @param authorityIds AuthorityIDs, e.g. ROLE_USER or ROLE_ADMIN
     * @return true if a (any) rule matched
     */
    public static boolean hasAccessAny(String[] authorityIds) {
        for (String authorityId : authorityIds) {
            boolean hasAccess = hasAccess(authorityId);
            if (hasAccess) {
                return true;
            }
        }
        // Anonymous or no authentication.
        return false;
    }

    /**
     * Validate if the user is logged in.
     * <code>isUserLoggedIn</code> checks if the current user is logged in.
     *
     * @return TRUE if user is logged in
     */
    public static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null
                && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }

    /**
     * Gets the user of the currently signed in user.
     *
     * @return the user of the current user or <code>null</code> if the user
     * has not signed in
     */
    public static ApplicationUserDetails getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof ApplicationUserDetails) {
                return (ApplicationUserDetails) principal;
            }
            LOGGER.warn("Invalid principal: {} in authentication: {}", principal, authentication);
        }

        // Anonymous or no authentication.
        return null;
    }

}
