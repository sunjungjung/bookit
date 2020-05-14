package com.sj.bookit.interfaces;

import com.sj.bookit.application.TypeItemService;
import com.sj.bookit.domain.TypeItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TypeItemController.class)
class TypeItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TypeItemService typeItemService;

    @Test
    public void bulkUpdate() throws Exception {
        mvc.perform(patch("/gyms/1/typeitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isOk());

        verify(typeItemService).bulkUpdate(eq(1L), any());
    }

}