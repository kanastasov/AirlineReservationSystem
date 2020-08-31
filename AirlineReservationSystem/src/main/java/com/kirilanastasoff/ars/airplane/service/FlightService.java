package com.kirilanastasoff.ars.airplane.service;

import java.util.List;

import com.kirilanastasoff.ars.model.airplane.Flight;

public interface FlightService {
	Flight getFlightById(Long id);
	List<Flight> addFlight(Flight flight);
	List<Flight> getAvailableFlightBetweenStops(String sourceStopCode, String destinationStopCode);
	List<Flight> getAllFlights();
}
