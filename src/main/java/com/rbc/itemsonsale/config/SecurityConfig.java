package com.rbc.itemsonsale.config;

import com.rbc.itemsonsale.filter.JwtRequestFilter;
import com.rbc.itemsonsale.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //    below is the built-in in-memory authorization from spring security
    //    @Bean
    //    public PasswordEncoder passwordEncoder() {
    //        return new BCryptPasswordEncoder();
    //    }
    //
    //    @Bean
    //    @Override
    //    protected UserDetailsService userDetailsService() {
    //        UserDetails user1 = User
    //                .withUsername("user")
    //                .password("$2a$10$Gxb1pXklHDDByHNnpvPkRenBfwofwnrAO5hZDg7fMSLKhs0Mpsnde")
    //                .roles("USER")
    //                .build();
    //        UserDetails user2 = User
    //                .withUsername("admin")
    //                .password("$2a$10$Gxb1pXklHDDByHNnpvPkRenBfwofwnrAO5hZDg7fMSLKhs0Mpsnde")
    //                .roles("ADMIN")
    //                .build();
    //
    //        return new InMemoryUserDetailsManager(user1, user2);
    //    }
    //
    //    @Override
    //    protected void configure(HttpSecurity security) throws Exception
    //    {
    //        security.httpBasic().disable();
    //    }

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtUserDetailsService jwtUserDetailsService,
            JwtRequestFilter jwtRequestFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    /**
     * config for JWT user service and password encoder
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * init AuthenticationManager
     * @return AuthenticationManager
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * configure for security
     * @param httpSecurity configure for security
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf().disable()

                // dont authenticate this particular request
                .authorizeRequests()
                .antMatchers("/authenticate", "/h2-console","/h2-console/**", "/api-docs/**",
                        "/swagger-ui.html","/swagger-ui/**","/actuator/**")
                .permitAll()

                // all other requests need to be authenticated
                .anyRequest().authenticated()
                .and()

                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().frameOptions().disable();
    }
}
