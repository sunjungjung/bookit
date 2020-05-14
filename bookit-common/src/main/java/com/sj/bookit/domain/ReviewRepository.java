package com.sj.bookit.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findAllByGymId(Long gymId);

    Review save(Review review);

}
