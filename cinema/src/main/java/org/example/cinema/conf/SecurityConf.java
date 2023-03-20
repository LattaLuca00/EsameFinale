package org.example.cinema.conf;

import org.example.cinema.enums.UserRole;
import org.example.cinema.model.User;
import org.example.cinema.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@EnableWebSecurity
public class SecurityConf {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //con questo codice indichiamo che il metodo delete può essere gestito solo dall'admin
        //la post (ovvero la insert) può essere uttilizzata solo dal CREATE_USER (utente di default)
        // e il resto delle API da tutti previa autenticazione con username e password
        //Stateless sta ad indicare che l'oggetto viene ricreato ad ogni esecuzione
        return http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE).hasRole(UserRole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/user").hasRole(UserRole.CREATE_USER.toString())
                .antMatchers("/**").hasAnyRole(UserRole.USER.toString(), UserRole.ADMIN.toString())
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager usersDetailInMemory(BCryptPasswordEncoder passwordEncoder, UserService userService) {

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        List<User> users = userService.getAll();
        users.forEach(user -> {
            userDetailsManager.createUser(org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .roles(user.getRole().toString())
                    .build());
        });

        //Creiamo un utente di "default" così da poter eseguire tutte le operazioni che altrimenti sarebbero non autorizzate
        userDetailsManager.createUser(org.springframework.security.core.userdetails.User
                .withUsername("create_user")
                .password(passwordEncoder.encode("create_user"))
                .roles(UserRole.CREATE_USER.toString())
                .build());

        return userDetailsManager;
    }
}
