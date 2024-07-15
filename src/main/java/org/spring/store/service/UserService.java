package org.spring.store.service;


import org.spring.store.model.Role;
import org.spring.store.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user) throws Exception;
    void delete(Long id);
    Optional<User> findById(Long id);
    List<User> findAll();
    boolean userExists(String email);
    Optional<User> findByEmail(String email);
    void banUser(Long userId);
    void unbanUser(Long userId);
    void updateUserRole(Long userId, Role role);
    List<User> findByEmailContaining(String username);
}
