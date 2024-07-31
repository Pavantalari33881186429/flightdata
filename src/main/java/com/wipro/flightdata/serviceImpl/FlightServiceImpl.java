package com.wipro.flightdata.serviceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.flightdata.entity.Flight;
import com.wipro.flightdata.repository.FlightRepository;
import com.wipro.flightdata.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }
    
    @Override
    public List<Flight> findFlights(String source, String destination, LocalDate travelDate) {
        // Implement the logic to search flights based on the source, destination, and travelDate
        return flightRepository.findFlightsBySourceAndDestinationAndTravelDate(source, destination, travelDate);
    }
        
    @Override
    public List<Flight> saveFlights(List<Flight> flights) {
        return flightRepository.saveAll(flights);
    }
    
    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
    	
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> searchFlights(String source, String destination, Date travelDate) {
        return flightRepository.findBySourceAndDestinationAndTravelDate(source, destination, travelDate);
    }

    @Override
    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            flight.setFlightNumber(flightDetails.getFlightNumber());
            flight.setFlightName(flightDetails.getFlightName());
            flight.setSource(flightDetails.getSource());
            flight.setDestination(flightDetails.getDestination());
            flight.setPrice(flightDetails.getPrice());
            flight.setTravelDate(flightDetails.getTravelDate());
            flight.setStartTime(flightDetails.getStartTime());
            flight.setReachTime(flightDetails.getReachTime());
            return flightRepository.save(flight);
        }
        return null;
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
