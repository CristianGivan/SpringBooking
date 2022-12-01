package com.spring.booking.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_seq")
    @SequenceGenerator(name = "hotel_seq",
            sequenceName = "hotel_seq",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "hotel_id")
    private Long id;

    @OneToMany (mappedBy = "hotel")
    private List<Room> rooms;

    @Column(name = "name")
    private String name;

    public Hotel() {
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", rooms=" + rooms +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Room> getRooms() {
        if (rooms==null){
            rooms=new ArrayList<>();
        }
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
