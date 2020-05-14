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
        typeItems.add(TypeItem.builder()
                .name("Jogging")
                .build());
        given(typeItemRepository.findAllByGymId(1001L))
                .willReturn(typeItems);


    }

    private void mockGymRepository() {
        List<Gym> gyms = new ArrayList<>();
        Gym gym = Gym.builder()
                .id(1001L)
                .address("Seoul")
                .name("work out")
                .typeItems(new ArrayList<TypeItem>())
                .build();
        gyms.add(gym);

        given(gymRepository.findAll()).willReturn(gyms);

        given(gymRepository.findById(1001L)).willReturn(Optional.of(gym));

    }


    @Test
    public void getGyms() {
        List<Gym> gyms = gymService.getGyms();

        Gym gym = gyms.get(0);
        assertThat(gym.getId(), is(1001L));
    }


    @Test
    public void getGymWithExisting() {
        Gym gym = gymService.getGym(1001L);

        assertThat(gym.getId(), is(1001L));

        TypeItem typeItem = gym.getTypeItems().get(0);

        assertThat(typeItem.getName(), is("Jogging"));
    }

    @Test(expected = GymNotFoundException.class) //19
    public void getGymWithNotExisting() {
        gymService.getGym(404L);
    }


    @Test
    public void addGym() {
        given(gymRepository.save(any())).will(invocation -> {
            Gym gym = invocation.getArgument(0);
            gym.setId(1234L);
            return gym;
        });

        Gym gym = Gym.builder()
                .name("Lynn")
                .address("Busan")
                .build();

        Gym saved = Gym.builder()
                .id(1234L)
                .name("Lynn")
                .address("Busan")
                .build();


        Gym created = gymService.addGym(gym);

        assertThat(created.getId(), is(1234L));
    }

    @Test
    public void updateGym() {
        Gym gym = Gym.builder()
                .id(1001L)
                .name("work out")
                .address("Seoul")
                .build();

        given(gymRepository.findById(1001L))
                .willReturn(Optional.of(gym));

        gymService.updateGym(1001L, "woo", "Ssss");

        assertThat(gym.getName(), is("woo"));
        assertThat(gym.getAddress(), is("Ssss"));
    }
}