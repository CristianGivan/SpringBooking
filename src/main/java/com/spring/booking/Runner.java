package com.spring.booking;

import com.spring.booking.model.Role;
import com.spring.booking.model.RoleType;
import com.spring.booking.model.User;
import com.spring.booking.model.UserRole;
import com.spring.booking.repository.RoleRepository;
import com.spring.booking.repository.UserRepository;
import com.spring.booking.repository.UserRoleRepository;
import com.spring.booking.service.RoleService;
import com.spring.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private UserRepository userRepository;
    private UserService userService;
    private RoleService roleService;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;



    @Autowired
    public Runner(UserRepository userRepository, UserService userService, RoleService roleService,
                  RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //dbInit();
    }
    public void dbInit(){

        User user1 = new User("User1",
                "$2a$12$sJZ7/SZpCOTSeMg1jos87ulm.OcN31uQKnYisY/5r5XlNXSoQKPRi");//pas1
        User user2 = new User("User2",
                "$2a$12$gaUsXx4r4JlzHYXomu/XguBgQZbj2XXOWq5h683u7KCOPUozoRy56");//pas2
        User user3 = new User("User3",
                "$2a$12$b/jcMc9LC8sTk.8mV6Mzv.0GFCqgD7O/oK6m96nojFVKuGj8LlJv2");//pas3

        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        User savedUser3 = userRepository.save(user3);

        Role admin  =new Role(RoleType.ROLE_ADMIN);
        Role client=new Role(RoleType.ROLE_CLIENT);

        Role savedAdmin=roleRepository.save(admin);
        Role savedClient=roleRepository.save(client);

        UserRole userRole1 =new UserRole(savedUser1,savedAdmin, LocalDateTime.now());
        UserRole userRole2 =new UserRole(savedUser2,savedClient, LocalDateTime.now());
        UserRole userRole3 =new UserRole(savedUser3,savedClient, LocalDateTime.now());
        UserRole userRole4 =new UserRole(savedUser3,savedAdmin, LocalDateTime.now());

        savedUser1.getUserRoles().add(userRole1);
        savedUser2.getUserRoles().add(userRole2);
        savedUser3.getUserRoles().add(userRole3);
        savedUser3.getUserRoles().add(userRole4);

        savedAdmin.getUserRoles().add(userRole1);
        savedAdmin.getUserRoles().add(userRole3);
        savedClient.getUserRoles().add(userRole2);
        savedClient.getUserRoles().add(userRole3);

        userRoleRepository.save(userRole1);
        userRoleRepository.save(userRole2);
        userRoleRepository.save(userRole3);
        userRoleRepository.save(userRole4);


        System.out.println(user3.getUserRoles());
        System.out.println(savedAdmin.getUserRoles());
    }
}