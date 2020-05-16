package com.sj.bookit.application;

import com.sj.bookit.domain.Reservation;
import com.sj.bookit.domain.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getReservations() {
        Long gymId = 1001L;

        List<Reservation> reservations =
                reservationService.getReservations(gymId);

        verify(reservationRepository).findAllByRestaurantId(gymId);
    }

}