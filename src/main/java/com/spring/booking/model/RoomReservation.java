package com.spring.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

    //todo de analizat daca ar trebui sa adaug aici userul
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private User user;
}
