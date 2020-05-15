package com.sj.bookit.application;

import com.sj.bookit.domain.TypeItem;
import com.sj.bookit.domain.TypeItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TypeItemServiceTest {

    @InjectMocks
    private TypeItemService typeItemService;

    @Mock
    private TypeItemRepository typeItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getTypeItems() {
        List<TypeItem> mockTypeItems = new ArrayList<>();

        mockTypeItems.add(TypeItem.builder().name("jogging").build());

        given(typeItemRepository.findAllByGymId(1001L)).willReturn(mockTypeItems);

        List<TypeItem> typeItems = typeItemService.getTypeItems(1001L);

        TypeItem typeItem = typeItems.get(0);

        assertThat(typeItem.getName(), is("Jogging"));
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

        verify(typeItemRepository, times(2)).save(any());
        verify(typeItemRepository, times(1)).deleteById(eq(1001L));


    }

}