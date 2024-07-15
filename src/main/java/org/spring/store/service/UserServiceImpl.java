package org.spring.store.service;

import lombok.AllArgsConstructor;
import org.spring.store.model.Role;
import org.spring.store.model.User;
import org.spring.store.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void banUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        user.setBanned(true);
        userRepository.save(user);
    }

    @Override
    public void unbanUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        user.setBanned(false);
        userRepository.save(user);
    }

    @Override
    public void updateUserRole(Long userId, Role role) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<User> findByEmailContaining(String email) {
        return userRepository.findByEmailContaining(email);
    }
}
