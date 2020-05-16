package com.sj.bookit.application;

import com.sj.bookit.domain.Reservation;
import com.sj.bookit.domain.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation addReservation(Long gymId, Long userId, String name,
                                      String date, String time, Integer partySize) {
        Reservation reservation = Reservation.builder()
                .restaurantId(gymId)
                .userId(userId)
                .name(name)
                .date(date)
                .time(time)
                .partySize(partySize)
                .build();

        return reservationRepository.save(reservation);
    }

}
