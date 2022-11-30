package com.spring.booking.service;

import com.spring.booking.dto.RegisterDTO;
import com.spring.booking.exceptions.UserNotFoundException;
import com.spring.booking.model.Role;
import com.spring.booking.model.RoleType;
import com.spring.booking.model.User;
import com.spring.booking.model.UserRole;
import com.spring.booking.repository.UserRepository;
import com.spring.booking.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository,
                       RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found"));
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User registerStandardUser(RegisterDTO newUser) throws ResponseStatusException {
        User user = userRepository.findByUsername(newUser.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "already exist");
        }
        user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role role = roleService.findRoleByType(RoleType.ROLE_CLIENT);
        UserRole userRole = new UserRole(user, role, LocalDateTime.now());
        role.getUserRoles().add(userRole);
        user.getUserRoles().add(userRole);
        userRoleRepository.save(userRole);
        return user;
    }
}
