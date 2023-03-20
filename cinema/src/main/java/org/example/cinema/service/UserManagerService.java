package org.example.cinema.service;

import org.example.cinema.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserManagerService {
    UserDetails addSecurityUsers(String username, String password, UserRole role);

    Boolean userExist(String username);
}
