package com.spring.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "room_reservation")
public class RoomReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_reservation_seq")
    @SequenceGenerator(name = "room_reservation_seq",
            sequenceName = "room_reservation_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "room_reservatio_id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "reservation_id")
    @JsonIgnore
    private Reservation reservation;

    @Column(name = "reservationDate")
    private LocalDate reservationDate;

    //todo de analizat daca ar trebui sa adaug aici userul
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;


    public RoomReservation() {
    }

    public RoomReservation(Room room, Reservation reservation, LocalDate reservationDate) {
        this.room = room;
        this.reservation = reservation;
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "RoomReservation{" +
                "id=" + id +
                ", room_Id=" + room.getId() +
                ", reservation_id=" + reservation.getId() +
                ", reservationDate=" + reservationDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}
