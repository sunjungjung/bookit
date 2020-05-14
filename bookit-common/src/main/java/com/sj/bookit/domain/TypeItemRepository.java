package com.sj.bookit.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TypeItemRepository extends CrudRepository<TypeItem, Long>{
    List<TypeItem> findAllByGymId(Long gymId);

    void deleteById(Long id);

}