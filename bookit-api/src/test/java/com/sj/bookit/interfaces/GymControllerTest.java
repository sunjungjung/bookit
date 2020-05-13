package com.sj.bookit.interfaces;

import com.sj.bookit.application.GymService;
import com.sj.bookit.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(GymController.class)
class GymControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GymService gymService;

//    @SpyBean(GymRepositoryImpl.class)
//    private GymRepositoryImpl gymRepository;
//
//    @SpyBean(TypeItemRepositorImpl.class)
//    private TypeItemRepository typeItemRepository;

    @Test
    public void list() throws Exception {
        List<Gym> gyms = new ArrayList<>();
        gyms.add(new Gym(1001L, "work out", "Seoul"));

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
    public void detail() throws Exception {
        Gym gym = new Gym(1001L, "work out", "Seoul");
        gym.addTypeItem(new TypeItem("Jogging"));

        given(gymService.getGym(1001L)).willReturn(gym);

        mvc.perform(get("/gyms/1001"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1001")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"work out\"")
                ));
    }


    @Test
    public void create() throws Exception {
        mvc.perform(post("/gyms")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Lynn\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/gyms/1234"))
                .andExpect(content().string("{}"));

        verify(gymService).addGym(any());
    }

}