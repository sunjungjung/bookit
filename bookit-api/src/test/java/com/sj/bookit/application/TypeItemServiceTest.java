package com.sj.bookit.application;

import com.sj.bookit.domain.TypeItem;
import com.sj.bookit.domain.TypeItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TypeItemServiceTest {

    private TypeItemService typeItemService;

    @Mock
    private TypeItemRepository typeItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        typeItemService = new TypeItemService(typeItemRepository);
    }

    @Test
    public void bulkUpdate() {
        List<TypeItem> typeItems = new ArrayList<TypeItem>();

        typeItems.add(TypeItem.builder()
                .name("Jogging")
                .build());
        typeItems.add(TypeItem.builder()
                .id(12L)
                .name("climbing")
                .build());
        typeItems.add(TypeItem.builder()
                .id(1001L)
                .destroy(true)
                .build());
        typeItemService.bulkUpdate(1L, typeItems);

        verify(typeItemRepository, times(1)).save(any());
        verify(typeItemRepository, times(1)).deleteById(eq(1001L));


    }

}