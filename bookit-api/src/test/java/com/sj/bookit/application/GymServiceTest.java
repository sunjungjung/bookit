package com.sj.bookit.application;

import com.sj.bookit.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class GymServiceTest {

    private GymService gymService;

    @Mock
    private GymRepository gymRepository;

    @Mock
    private TypeItemRepository typeItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockGymRepository();
        mockTypeItemRepository();

        gymService = new GymService(gymRepository, typeItemRepository);
    }

    private void mockTypeItemRepository() {
        List<TypeItem> typeItems = new ArrayList<>();
        typeItems.add(new TypeItem("Jogging"));
        given(typeItemRepository.findAllByGymId(1001L))
                .willReturn(typeItems);


    }

    private void mockGymRepository() {
        List<Gym> gyms = new ArrayList<>();
        Gym gym = new Gym(1001L, "work out", "Seoul");
        gyms.add(gym);

        given(gymRepository.findAll()).willReturn(gyms);

        given(gymRepository.findById(1001L)).willReturn(Optional.of(gym));

    }


    @Test
    public void getGyms() {
        List<Gym> gyms = gymService.getGyms();

        Gym gym = gyms.get(0);
        assertThat(gym.getID(), is(1001L));
    }


    @Test
    public void getGym() {
        Gym gym = gymService.getGym(1001L);

        assertThat(gym.getID(), is(1001L));

        TypeItem typeItem = gym.getTypeItems().get(0);

        assertThat(typeItem.getName(), is("Jogging"));
    }


    @Test
    public void addGym() {
        Gym gym = new Gym("Lynn", "Busan");
        Gym saved = new Gym(1234L, "Lynn", "Busan");

        given(gymRepository.save(any())).willReturn(gym);

        Gym created = gymService.addGym(gym);

//        assertThat(created.getID(), is(1234L));
    }
}