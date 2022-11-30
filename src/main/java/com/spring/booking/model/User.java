package com.spring.booking.model;


import com.spring.booking.exceptions.IdAlreadyAllocatedException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "user"/*, cascade = {CascadeType.PERSIST, CascadeType.MERGE}*/)
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password=password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRoles=" + userRoles +
                ", reservations=" + reservations +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (this.id == null || this.id.equals(id)) {
            this.id = id;
        } else {
            throw new IdAlreadyAllocatedException(
                    "Id is already allocated cannot be changed");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getUserRoles() {
        if (userRoles==null){
            userRoles=new ArrayList<>();
        }
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
