package com.practice.spring.databsespringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /*@Bean
    // Authentication
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails admin =
                User.withUsername("munin")
                        .password(encoder.encode("password1"))
                        .roles("ADMIN")
                        .build();

        UserDetails user =
                User.withUsername("user")
                        .password(encoder.encode("password2"))
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/

    @Bean
    // Authentication
    public UserDetailsService userDetailsService() {

        return new UserInfoUserDetailsService();
    }

    @Bean
    // Authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*return http.csrf(auth -> auth.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                "/products/welcome").permitAll()
                                .requestMatchers(
                                        "/products/**").authenticated()
                                .requestMatchers(
                                        "/users/**").authenticated())
                .formLogin(form -> form.permitAll()).build();*/

        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/products/welcome","/products/add","/users/add").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/products/**").authenticated()
                .and().formLogin()
                .and().build();
    }

    /*@Bean
    @Order(2)
    SecurityFilterChain userFilters(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests.anyRequest().authenticated())
                .formLogin(cust -> cust.defaultSuccessUrl(""));

        return http.build();
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
