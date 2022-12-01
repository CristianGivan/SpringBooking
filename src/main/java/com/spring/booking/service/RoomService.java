package com.spring.booking.service;

import com.spring.booking.exceptions.RoomNotFoundException;
import com.spring.booking.model.Room;
import com.spring.booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public Room saveRoom(Room room){
        return roomRepository.save(room);
    }
    public Room getRoomById(Long id){
        return roomRepository.findById(id).orElseThrow(
                ()->new RoomNotFoundException("The room was not found"));
    }
}
