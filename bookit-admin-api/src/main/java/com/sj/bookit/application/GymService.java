package com.sj.bookit.application;

import com.sj.bookit.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GymService {

    private final GymRepository gymRepository;

    @Autowired
    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }


    public List<Gym> getGyms() {
        List<Gym> gyms = gymRepository.findAll();

        return gyms;
    }


    public Gym getGym(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFoundException(id));


        return gym;
    }

    public Gym addGym(Gym gym) {
        return gymRepository.save(gym);
    }

    @Transactional
    public Gym updateGym(Long id, Long categoryId, String name, String address) {
        //Todo: update Gym

        Gym gym = gymRepository.findById(id).orElse(null);

        gym.updateInformation(categoryId, name, address);


        return gym;
    }
}
