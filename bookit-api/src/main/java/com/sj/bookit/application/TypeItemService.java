package com.sj.bookit.application;


import com.sj.bookit.domain.TypeItem;
import com.sj.bookit.domain.TypeItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeItemService {

    private TypeItemRepository typeItemRepository;

    @Autowired
    public TypeItemService(TypeItemRepository typeItemRepository) {
        this.typeItemRepository = typeItemRepository;
    }

    public void bulkUpdate(Long gymId, List<TypeItem> typeItems) {
        for (TypeItem typeItem : typeItems) {
            update(gymId, typeItem);
        }
    }


    private void update(Long gymId, TypeItem typeItem) {
        if (typeItem.isDestroy()) {
            typeItemRepository.deleteById(typeItem.getId());
            return;
        }
        typeItem.setGymId(gymId);
        typeItemRepository.save(typeItem);
    }
}
