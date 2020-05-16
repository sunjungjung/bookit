package com.sj.bookit.application;

import com.sj.bookit.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GymService {

    private final GymRepository gymRepository;
    private final TypeItemRepository typeItemRepository;
    private final ReviewRepository reviewRepository;

    public GymService(GymRepository gymRepository, TypeItemRepository typeItemRepository, ReviewRepository reviewRepository) {
        this.gymRepository = gymRepository;
        this.typeItemRepository = typeItemRepository;
        this.reviewRepository = reviewRepository;
    }


    public List<Gym> getGyms(String region, long categoryId) {
        List<Gym> gyms = gymRepository.findAllByAddressContainingAndCategoryId(region, categoryId);

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

    public Gym updateGym(Long id, Long categoryId, String name, String address) {
        //Todo: update Gym

        Gym gym = gymRepository.findById(id).orElse(null);

        gym.updateInformation(categoryId, name, address);


        return gym;
    }
}
