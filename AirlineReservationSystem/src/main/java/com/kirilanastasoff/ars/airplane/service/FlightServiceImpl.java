package com.kirilanastasoff.ars.airplane.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.repository.airplane.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepository;
	

	@Override
	public Flight getFlightById(Long id) {
		Optional<Flight> flight = flightRepository.findById(id);
		if(flight.isEmpty()) {
			return flight.get();
		}
		return null;
	}

	@Override
	public List<Flight> addFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getAvailableFlightBetweenStops(String sourceStopCode, String destinationStopCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}
}
