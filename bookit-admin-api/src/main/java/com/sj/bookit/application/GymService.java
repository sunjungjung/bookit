package com.sj.bookit.application;

import com.sj.bookit.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GymService {

    private final GymRepository gymRepository;
    private final TypeItemRepository typeItemRepository;
    private final ReviewRepository reviewRepository;

    public GymService(GymRepository gymRepository, TypeItemRepository typeItemRepository, ReviewRepository reviewRepository) {
        this.gymRepository = gymRepository;
        this.typeItemRepository = typeItemRepository;
        this.reviewRepository = reviewRepository;
    }


    public List<Gym> getGyms() {
        List<Gym> gyms = gymRepository.findAll();

        return gyms;
    }


    public Gym getGym(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new GymNotFoundException(id));

        List<TypeItem> typeItems = typeItemRepository.findAllByGymId(id);
        gym.setTypeItems(typeItems);

        List<Review> reviews = reviewRepository.findAllByGymId(id);
        gym.setReviews(reviews);

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
