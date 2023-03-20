package org.example.cinema.service;

import org.example.cinema.enums.UserRole;
import org.example.cinema.model.GenericResponse;
import org.example.cinema.model.User;


import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(int id);

    List<User> getAll();

    User insert(String username, String password, UserRole role);

    GenericResponse<User> update(int id, String username, String password, UserRole role);

    Boolean deleteById(int id);

    Boolean deleteAll();
}
