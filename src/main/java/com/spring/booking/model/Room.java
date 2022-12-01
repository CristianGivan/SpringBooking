package com.spring.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "hotel_id")
    @JsonIgnore
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<RoomReservation> roomReservations;

    @Column(name = "room_number")
    private String roomName;

    @Column(name = "number_persons")
    private int numberOfPersons;

    public Room() {
    }

    public Room(String roomName, int numberOfPersons) {
        this.roomName = roomName;
        this.numberOfPersons=numberOfPersons;
    }

    public Room(Hotel hotel, String roomName, int numberOfPersons) {
        this.hotel = hotel;
        this.roomName = roomName;
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel_id=" + hotel.getId() +
                ", roomReservations=" + roomReservations +
                ", roomName=" + roomName +
                ", numberOfPersons=" + numberOfPersons +
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
        if (roomReservations==null){
            roomReservations=new ArrayList<>();
        }
        return roomReservations;
    }

    public void setRoomReservations(List<RoomReservation> roomReservations) {
        this.roomReservations = roomReservations;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
