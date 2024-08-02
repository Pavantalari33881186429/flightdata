package com.wipro.flightdata.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.wipro.flightdata.entity.Flight;

public interface FlightService {
    Flight saveFlight(Flight flight);
    List<Flight> getAllFlights();
    Flight getFlightById(Long id);
    List<Flight> searchFlights(String source, String destination, Date travelDate);
    List<Flight> findFlights(String source, String destination, LocalDate travelDate);
    Flight updateFlight(Long id, Flight flightDetails);
    void deleteFlight(Long id);
    List<Flight> saveFlights(List<Flight> flights);
	List<Flight> findFlightsByAirportId(String sourceAirport, String destinationAirport, LocalDate travelDate);
}
