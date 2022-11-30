package com.spring.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "reservation_seq",
            sequenceName = "reservation_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "roomreservation_id")
    private Long id;

    @OneToMany(mappedBy = "reservation")
    private List<RoomReservation> roomReservations;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column (name = "check_in")
    private LocalDateTime checkIn;

    @Column (name = "check_out")
    private LocalDateTime checkOut;

    public Reservation() {
    }

    public Reservation(List<RoomReservation> roomReservations, User user,
                       LocalDateTime checkIn, LocalDateTime checkOut) {
        this.roomReservations = roomReservations;
        this.user = user;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", roomReservations=" + roomReservations +
                ", user_id=" + user.getId() +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}
