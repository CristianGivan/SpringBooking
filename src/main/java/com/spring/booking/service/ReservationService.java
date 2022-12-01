package com.spring.booking.service;

import com.spring.booking.model.Reservation;
import com.spring.booking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public Reservation saveReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }
}
