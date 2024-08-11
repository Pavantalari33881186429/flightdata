package com.wipro.flightdata.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wipro.flightdata.entity.Flight;
import com.wipro.flightdata.repository.FlightRepository;

class FlightServiceImplTest {

    @InjectMocks
    private FlightServiceImpl flightServiceImpl;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFlight() {
        Flight flight = new Flight();
        flight.setFlightId(1L);
        flight.setFlightNumber("FL1001");
        flight.setFlightName("IndiGo");
        flight.setSource("Mumbai");
        flight.setDestination("Delhi");
        flight.setPrice(3500.00);
        flight.setTravelDate(LocalDate.of(2024, 8, 5));
        flight.setStartTime("08:00");
        flight.setReachTime("10:30");
        flight.setSourceAirport("BOM");
        flight.setDestinationAirport("DEL");
        when(flightRepository.save(flight)).thenReturn(flight);

        Flight savedFlight = flightServiceImpl.saveFlight(flight);

        assertNotNull(savedFlight);
        assertEquals(flight.getFlightNumber(), savedFlight.getFlightNumber());
    }

    @Test
    void testGetAllFlights() {
        List<Flight> flights = new ArrayList<>();
        when(flightRepository.findAll()).thenReturn(flights);

        List<Flight> result = flightServiceImpl.getAllFlights();

        assertEquals(flights, result);
    }

    @Test
    void testGetFlightById() {
        Long flightId = 1L;
        Flight flight = new Flight();
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        Flight result = flightServiceImpl.getFlightById(flightId);

        assertNotNull(result);
        assertEquals(flight, result);
    }

    @Test
    void testDeleteFlight() {
        Long flightId = 1L;
        flightServiceImpl.deleteFlight(flightId);
        verify(flightRepository, times(1)).deleteById(flightId);
    }

    // Add more test cases for each method in FlightServiceImpl
}
