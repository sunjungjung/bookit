package com.sj.bookit.application;

import com.sj.bookit.domain.Gym;
import com.sj.bookit.domain.GymRepository;
import com.sj.bookit.domain.TypeItem;
import com.sj.bookit.domain.TypeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GymService {

    @Autowired
    GymRepository gymRepository;

    @Autowired
    TypeItemRepository typeItemRepository;

    public GymService(GymRepository gymRepository, TypeItemRepository typeItemRepository) {
        this.gymRepository = gymRepository;
        this.typeItemRepository = typeItemRepository;
    }


    public List<Gym> getGyms() {
        List<Gym> gyms = gymRepository.findAll();

        return gyms;
    }


    public Gym getGym(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);

        List<TypeItem> typeItems = typeItemRepository.findAllByGymId(id);
        gym.setTypeItems(typeItems);

        return gym;
    }

    public Gym addGym(Gym gym) {
        return gymRepository.save(gym);
    }

    @Transactional
    public Gym updateGym(Long id, String name, String address) {
        //Todo: update Gym

        Gym gym = gymRepository.findById(id).orElse(null);

        gym.updateInformation(name, address);


        return gym;
    }
}
