package com.sj.bookit.interfaces;

import com.sj.bookit.application.ReviewService;
import com.sj.bookit.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;


    @Test
    public void createWithValidAttributes() throws Exception {
        given(reviewService.addReview(eq(1L), any())).willReturn(
                Review.builder()
                        .id(123L)
                        .name("doe")
                        .score(3)
                        .description("diff")
                        .build()
        );

        mvc.perform(post("/gyms/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"doe\",\"score\":3,\"description\":\"diff\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("location", "/gyms/1/reviews/1001"));

        verify(reviewService).addReview(eq(1L), any());
    }


    @Test
    public void createWithInvalidAttributes() throws Exception {
        mvc.perform(post("/gyms/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());

        verify(reviewService, never()).addReview(eq(1L), any());
    }
}