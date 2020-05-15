package com.sj.bookit.interfaces;

import com.sj.bookit.application.TypeItemService;
import com.sj.bookit.domain.TypeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class TypeItemController {

    @Autowired
    private TypeItemService typeItemService;

    @GetMapping("/gyms/{gymId}/typeitems")
    public List<TypeItem> list(@PathVariable Long typeId) {
        List<TypeItem> typeItems = typeItemService.getTypeItems(typeId);

        return typeItems;
    }


    @PatchMapping("/gyms/{gymId}/typeitems")
    public String bulkUpdate(
            @PathVariable("gymId") Long gymId,
            @RequestBody List<TypeItem> typeItems
    ) {

        typeItemService.bulkUpdate(gymId, typeItems);


        return "";
    }

}
