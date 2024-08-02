package com.wipro.flightdata.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.flightdata.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findBySourceAndDestinationAndTravelDate(String source, String destination, Date travelDate);
    List<Flight> findFlightsBySourceAndDestinationAndTravelDate(String source, String destination, LocalDate travelDate);
    List<Flight> findBySourceAirportAndDestinationAirportAndTravelDate(String sourceAirport, String destinationAirport, LocalDate travelDate);
}
