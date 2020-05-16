package com.sj.bookit.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GymRepository extends CrudRepository<Gym, Long> {
    List<Gym> findAll();

    List<Gym> findAllByAddressContainingAndCategoryId (String region, Long categoryId);

    Optional<Gym> findById(Long id);

    Gym save(Gym gym);
}
