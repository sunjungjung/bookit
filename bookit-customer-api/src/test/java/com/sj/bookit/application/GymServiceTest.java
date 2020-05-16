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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class GymServiceTest {

    private GymService gymService;

    @Mock
    private GymRepository gymRepository;

    @Mock
    private TypeItemRepository typeItemRepository;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockGymRepository();
        mockTypeItemRepository();
        mockReviewRepository();
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

    private void mockTypeItemRepository() {
        List<TypeItem> typeItems = new ArrayList<>();
        typeItems.add(TypeItem.builder()
                .name("Jogging")
                .build());
        given(typeItemRepository.findAllByGymId(1001L))
                .willReturn(typeItems);


    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .name("anno")
                .score(1)
                .description("bad")
                .build());

        given(reviewRepository.findAllByGymId(1001L))
                .willReturn(reviews);
    }


    @Test
    public void getGyms() {
        String region = "Seoul";
        Long categoryId = 1L;

        List<Gym> gyms = gymService.getGyms(region, categoryId);

        Gym gym = gyms.get(0);

        assertThat(gym.getId(), is(1001L));
    }


    @Test
    public void getGymWithExisting() {
        Gym gym = gymService.getGym(1001L);

        verify(typeItemRepository).findAllByGymId(eq(1001L));
        verify(reviewRepository).findAllByGymId(eq(1001L));

        assertThat(gym.getId(), is(1001L));

        TypeItem typeItem = gym.getTypeItems().get(0);

        assertThat(typeItem.getName(), is("Jogging"));

        Review review = gym.getReviews().get(0);

        assertThat(review.getDescription(), is("diff"));


    }

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

        gymService.updateGym(1001L, 2L,"woo", "Ssss");

        assertThat(gym.getName(), is("woo"));
        assertThat(gym.getAddress(), is("Ssss"));
    }
}