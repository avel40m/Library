package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ConfigurationSecurity {

    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsManager detailsManager(DataSource dataSource){
            return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/api/authenticate", "/v3/api-docs/**",  "/swagger-ui/**", "/swagger-ui.html")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/book/").hasRole("Employee")
                                .requestMatchers(HttpMethod.GET, "/api/book/**").hasRole("Employee")
                                .requestMatchers(HttpMethod.POST, "/api/book/**").hasRole("Boss")
                                .requestMatchers(HttpMethod.POST, "/api/category/**").hasRole("Boss")
                                .requestMatchers(HttpMethod.PUT, "/api/book/**").hasRole("Boss")
                                .requestMatchers(HttpMethod.DELETE, "/api/book/**").hasRole("Boss")
                )
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    }

