package com.spring.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    @SequenceGenerator(name = "room_seq",
            sequenceName = "room_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<RoomReservation> roomReservations;

    public Room() {
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel_id=" + hotel.getId() +
                ", roomReservations=" + roomReservations +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<RoomReservation> getRoomReservations() {
        return roomReservations;
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }
}
