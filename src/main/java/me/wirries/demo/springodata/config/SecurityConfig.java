package me.wirries.demo.springodata.config;

import me.wirries.demo.springodata.security.ApplicationUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * In this configuration will setup the security for the application.
 * See more https://www.baeldung.com/security-spring
 *
 * @author denisw
 * @version 1.0
 * @since 22.05.2021
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    // Configuration for login
    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    /**
     * Initialize the SecurityBuilder. Here only shared state should be created and modified, but not
     * properties on the SecurityBuilder used for building the object. This ensures that the
     * configure(SecurityBuilder) method uses the correct shared objects when building. Configurers
     * should be applied here.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Restrict access to our application
                .authorizeRequests()

                // Allow access to the api - security is in the api
//                .antMatchers("/odata/**").permitAll()

                // Allow access to the h2 console - disabled h2 for production
                .antMatchers("/h2").permitAll()

                .antMatchers("/**").permitAll()

                // Restrict access to Swagger docs to administrators
//                .antMatchers("/swagger-ui/**").hasAuthority(Authorities.API_DOC)
//                .antMatchers("/swagger-resources/**").hasAuthority(Authorities.API_DOC)
//                .antMatchers("/v2/**").hasAuthority(Authorities.API_DOC)
//                .antMatchers("/v3/**").hasAuthority(Authorities.API_DOC)

                // Allow all requests by logged in users
                .anyRequest().authenticated();


        http
                // Configure the login page
                .formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL)
                .and()

                // Configure remember me cookie
                .rememberMe().key("h!hjgh3bZbhvz6").alwaysRemember(true)
                .and()

                // Configure logout
                .logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .and()

                // Allow also http basic
                .httpBasic();

        LOGGER.info("SecurityConfig configure http security done");
    }

    /**
     * Configure the SecurityBuilder by setting the necessary properties on the SecurityBuilder.
     */
    @Override
    public void configure(WebSecurity web) {
        web
                // Ignore resources from Spring Security
                .ignoring().antMatchers(
                // the standard favicon URI
                "/favicon.ico",
                // the robots exclusion standard
                "/robots.txt",
                // static resources
                "/resources/**",
                // webjars
                "/webjars/**",
                // (development mode) h2 console
                "/h2/**");

        LOGGER.info("SecurityConfig configure web security done");
    }

    // *** Beans for Security ***

    /**
     * Configure the {@link org.springframework.security.core.userdetails.UserDetailsService} for resolving the
     * user from the application database.
     *
     * @return the {@link ApplicationUserDetailsService}
     */
    @Bean
    @Override
    protected ApplicationUserDetailsService userDetailsService() {
        return new ApplicationUserDetailsService();
    }

    /**
     * Create the {@link PasswordEncoder} for the application.
     *
     * @return password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
