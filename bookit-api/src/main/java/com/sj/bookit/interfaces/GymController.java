package com.sj.bookit.interfaces;

import com.sj.bookit.application.GymService;
import com.sj.bookit.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class GymController {

    @Autowired
    private GymService gymService;


    @GetMapping("/gyms")
    public List<Gym> list() {
        List<Gym> gyms = gymService.getGyms();

        return gyms;
    }


    @GetMapping("/gyms/{id}")
    public Gym detail(@PathVariable("id") Long id) {
        Gym gym = gymService.getGym(id);

        return gym;
    }

    @PostMapping("/gyms")
    public ResponseEntity<?> create(@RequestBody Gym resource) throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();

        Gym gym = new Gym(1234L, name, address);
        gymService.addGym(gym);

        URI location = new URI("/gyms/" + gym.getID());
        return ResponseEntity.created(location).body("{}");
    }
}
