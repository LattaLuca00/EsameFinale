package org.example.cinema.service.impl;

import org.example.cinema.enums.UserRole;
import org.example.cinema.model.GenericResponse;
import org.example.cinema.model.User;

import org.example.cinema.repository.UserRepository;
import org.example.cinema.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceHImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceHImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User insert(String username, String password, UserRole role) {
        User user = null;
        if (userRepository.getByUsername(username).size() == 0) {
            user = userRepository.save(User.builder()
                    .username(username)
                    .password(password)
                    .role(role)
                    .build());
        }
        return user;
    }

    @Override
    public GenericResponse<User> update(int id, String username, String password, UserRole role) {
        GenericResponse<User> response = new GenericResponse<>();
        Optional<User> user = getById(id);
        if (user.isPresent()) {
            user.get().setPassword(password);
            user.get().setUsername(username);
            user.get().setRole(role);
            userRepository.save(user.get());
            response.setBody(user.get());
        } else {
            response.setErrorMessage("Aggiornamento non eseguito, user con " + id + " non trovato");
        }
        return response;
    }

    @Override
    public Boolean deleteById(int id) {
        Boolean res = Boolean.FALSE;
        Optional<User> userToDelete = getById(id);
        if (userToDelete.isPresent()) {
            try {
                userRepository.delete(userToDelete.get());
                res = Boolean.TRUE;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                res = Boolean.FALSE;
            }
        }
        return res;
    }

    @Override
    public Boolean deleteAll() {
        userRepository.deleteAll();
        return Boolean.TRUE;
    }
}
