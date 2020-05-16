package com.sj.bookit.interfaces;

import com.sj.bookit.application.GymService;
import com.sj.bookit.application.ReservationService;
import com.sj.bookit.domain.Reservation;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/gyms/{gymId}/reservations")
    public ResponseEntity<?> create(
            Authentication authentication,
            @PathVariable Long gymId,
            @Valid @RequestBody Reservation resource
    ) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();

        Long userId = claims.get("userId", Long.class);
        String name = claims.get("name", String.class);

        String date = resource.getDate();
        String time = resource.getTime();
        Integer partySize = resource.getPartySize();

        Reservation reservation = reservationService.addReservation(
                gymId, userId, name, date, time, partySize);

        String url = "/gyms/" + gymId +
                "/gyms/" + reservation.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
