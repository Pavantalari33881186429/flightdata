package com.wipro.flightdata.controller;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.flightdata.entity.Flight;
import com.wipro.flightdata.service.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PutMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }
    @PostMapping
    public ResponseEntity<List<Flight>> saveFlights(@RequestBody List<Flight> flights) {
        List<Flight> savedFlights = flightService.saveFlights(flights);
        return new ResponseEntity<>(savedFlights, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam String travelDate) {

        LocalDate date;
        try {
            date = LocalDate.parse(travelDate); // Convert String to LocalDate
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid date format
        }
        if(source.length()>3&&destination.length()>3) {
        List<Flight> flights = flightService.findFlights(source, destination, date);
        return new ResponseEntity<>(flights, HttpStatus.OK);
        }
        else {
        	List<Flight> flights = flightService.findFlightsByAirportId(source, destination, date);
        	return new ResponseEntity<>(flights, HttpStatus.OK);
        }
        
    }
    
    
    @GetMapping("/search/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PutMapping("/update/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flightDetails) {
        return flightService.updateFlight(id, flightDetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
