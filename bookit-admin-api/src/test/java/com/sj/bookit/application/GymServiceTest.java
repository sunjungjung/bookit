package com.sj.bookit.application;

import com.sj.bookit.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class GymServiceTest {

    @InjectMocks
    private GymService gymService;

    @Mock
    private GymRepository gymRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockGymRepository();

    }

    private void mockGymRepository() {
        List<Gym> gyms = new ArrayList<>();
        Gym gym = Gym.builder()
                .id(1001L)
                .categoryId(1L)
                .address("Seoul")
                .name("work out")
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

    }

    //TODO: handle error
//    @Test
//    public void getGymWithNotExisting() {
//
////        Throwable e = assertThrows(IllegalArgumentException.class, () -> {
////            throw new IllegalArgumentException("Argument Required"); });
//
//        gymService.getGym(404L);
//
//        Throwable e = assertThrows(GymNotFoundException.class, () -> {
//            throw new GymNotFoundException(404L); });
//        assertEquals(+ 404L, e.getMessage());
//    }


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

        gymService.updateGym(1001L, 1L, "woo", "Ssss");

        assertThat(gym.getName(), is("woo"));
        assertThat(gym.getAddress(), is("Ssss"));
    }
}