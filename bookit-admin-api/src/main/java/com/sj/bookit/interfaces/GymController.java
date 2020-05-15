package com.sj.bookit.interfaces;

import com.sj.bookit.application.GymService;
import com.sj.bookit.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> create(@Valid @RequestBody Gym resource) throws URISyntaxException {

        Gym gym = gymService.addGym(
                Gym.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build());


        URI location = new URI("/gyms/" + gym.getId());
        return ResponseEntity.created(location).body("{}");
    }


    @PatchMapping("/gyms/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @RequestBody Gym resource) {
        Long categoryId = resource.getCategoryId();
        String name = resource.getName();
        String address = resource.getAddress();
        gymService.updateGym(id, categoryId, name, address);

        return "{}";
    }
}
