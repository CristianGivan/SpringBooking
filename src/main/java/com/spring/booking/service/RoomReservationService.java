package com.spring.booking.service;

import com.spring.booking.model.RoomReservation;
import com.spring.booking.repository.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomReservationService {
    private RoomReservationRepository roomReservationRepository;

    @Autowired
    public RoomReservationService(RoomReservationRepository roomReservationRepository) {
        this.roomReservationRepository = roomReservationRepository;
    }
    public RoomReservation saveRoomReservation(RoomReservation roomReservation){
        return roomReservationRepository.save(roomReservation);
    }
}
