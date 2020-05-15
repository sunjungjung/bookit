package com.sj.bookit.interfaces;

import com.sj.bookit.application.GymService;
import com.sj.bookit.domain.Gym;
import com.sj.bookit.domain.GymNotFoundException;
import com.sj.bookit.domain.Review;
import com.sj.bookit.domain.TypeItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GymController.class)
class GymControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GymService gymService;

    @Test
    public void list() throws Exception {
        List<Gym> gyms = new ArrayList<>();
        gyms.add(Gym.builder()
                .id(1001L)
                .name("work out")
                .address("Seoul")
                .build());

        given(gymService.getGyms()).willReturn(gyms);

        mvc.perform(get("/gyms"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1001")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"work out\"")
//                ))
//        .andExpect(content().string(
//                containsString("Jogging")
        ));
    }


    @Test
    public void detailWithExisting() throws Exception {
        Gym gym = Gym.builder()
                .id(1001L)
                .address("Seoul")
                .name("work out")
                .typeItems(new ArrayList<TypeItem>())
                .build();

        TypeItem typeItem = TypeItem.builder()
                .name("Jogging")
                .build();

        gym.setTypeItems(Arrays.asList(typeItem));
        Review review = Review.builder()
                .name("doe")
                .score(3)
                .description("great")
                .build();
        gym.setReviews(Arrays.asList(review));

        given(gymService.getGym(1001L)).willReturn(gym);

        mvc.perform(get("/gyms/1001"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1001")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"work out\"")
                ))
                .andExpect(content().string(
                        containsString("jogging")
                ))
                .andExpect((content().string(
                        containsString("great"))
        ));
    }

    @Test
    public void detailWithNotExisting() throws Exception {
        given(gymService.getGym(404L))
                .willThrow(new GymNotFoundException(404L));
        mvc.perform(get("/gyms/404"))
                .andExpect(status().isNotFound())
        .andExpect(content().string("{}"));
    }


    @Test
    public void createWithValidData() throws Exception {
        given(gymService.addGym(any())).will(invocation -> {
            Gym gym = invocation.getArgument(0);
            return Gym.builder()
                    .id(1234L)
                    .name(gym.getName())
                    .address(gym.getAddress())
                    .build();
        });


        mvc.perform(post("/gyms")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Lynn\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/gyms/1234"))
                .andExpect(content().string("{}"));

        verify(gymService).addGym(any());
    }

    @Test
    public void createWithInvalidData() throws Exception {
        mvc.perform(post("/gyms")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateWithValidData() throws Exception {
        mvc.perform(patch("/gyms/1001")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"work out st\",\"address\":\"Seoul\"}"))
                .andExpect(status().isOk());

        verify(gymService).updateGym(1001L, "work out st", "Seoul");
    }

    @Test
    public void updateWithInvalidData() throws Exception {
        mvc.perform(patch("/gyms/1001")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"address\":\"\"}"))
                .andExpect(status().isBadRequest());
    }
}