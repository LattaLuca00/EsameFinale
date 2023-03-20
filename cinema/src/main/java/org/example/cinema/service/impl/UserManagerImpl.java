package org.example.cinema.service.impl;


import org.example.cinema.enums.UserRole;
import org.example.cinema.service.UserManagerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManagerService {
    private final InMemoryUserDetailsManager userDetailsManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserManagerImpl(@Qualifier("usersDetailInMemory") InMemoryUserDetailsManager userDetailsManager, BCryptPasswordEncoder passwordEncoder) {
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails addSecurityUsers(String username, String password, UserRole role) {
        UserDetails user = User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles(role.toString())
                .build();
        userDetailsManager.createUser(user);
        return user;
    }

    @Override
    public Boolean userExist(String username) {
        return userDetailsManager.userExists(username);
    }
}
