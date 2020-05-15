package com.sj.bookit.interfaces;

import com.sj.bookit.domain.TypeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeItemController {

    @Autowired
    private TypeItemController typeItemService;

    @PatchMapping("/gyms/{gymId}/typeitems")
    public String bulkUpdate(
            @PathVariable("gymId") Long gymId,
            @RequestBody List<TypeItem> typeItems
    ) {

        typeItemService.bulkUpdate(gymId, typeItems);


        return "";
    }

}
