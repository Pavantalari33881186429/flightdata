package com.wipro.flightdata.controller;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.flightdata.entity.Flight;
import com.wipro.flightdata.service.FlightService;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllFlights() throws Exception {
        List<Flight> flights = new ArrayList<>();
        when(flightService.getAllFlights()).thenReturn(flights);

        mockMvc.perform(get("/flights"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testCreateFlight() throws Exception {
        Flight flight = new Flight();
        // set flight properties
        when(flightService.saveFlight(any(Flight.class))).thenReturn(flight);

        mockMvc.perform(put("/flights")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flight)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.flightNumber").value(flight.getFlightNumber()));
    }

    @Test
    void testSearchFlights() throws Exception {
        List<Flight> flights = new ArrayList<>();
        when(flightService.findFlights(anyString(), anyString(), any(LocalDate.class))).thenReturn(flights);

        mockMvc.perform(get("/flights/search")
                .param("source", "source")
                .param("destination", "destination")
                .param("travelDate", "2023-01-01"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }

    // Add more test cases for each endpoint in FlightController
}
