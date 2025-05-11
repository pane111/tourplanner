package com.fhtw.tpserver.rest;

import com.fhtw.tpserver.model.Tour;
import com.fhtw.tpserver.repo.TourRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TourController.class)
@Import(TourControllerTest.MockConfig.class)
class TourControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TourRepo tourRepo;

    @Test
    void testGetAllTours() throws Exception {
        Tour tour = new Tour();
        tour.setId(1L);
        tour.setName("Test Tour");

        when(tourRepo.findAll()).thenReturn(List.of(tour));

        mockMvc.perform(get("/tours"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Tour"));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public TourRepo tourRepo() {
            return Mockito.mock(TourRepo.class);
        }
    }
}
