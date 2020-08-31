package com.kirilanastasoff.ars.airplane.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.kirilanastasoff.ars.model.airplane.Airplanes;
import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.FlightSchedule;
import com.kirilanastasoff.ars.model.airplane.Stop;
import com.kirilanastasoff.ars.model.airplane.Ticket;
import com.kirilanastasoff.ars.model.customer.Customer;

public interface AirplaneReservationService {

	// Stop related methods
	Set<Stop> getAllStops();
	Stop getStopByCode(String stopCode);
	
	//American Airlines related methods
	AmericanAirlines getAmericanAirlines(Customer customer);
	AmericanAirlines addAmericanAirlines(AmericanAirlines americanAirlines);
	AmericanAirlines updateAmericanAirlines(AmericanAirlines americanAirlines, Flight flight);
	List<AmericanAirlines> getAllAmericanAirlines();
	List<AmericanAirlines> getQueryAmericanAirlines();
	
	
	//Flight related methods
	Flight getFlightById(Long id);
	List<Flight> addFlight(Flight flight);
	List<Flight> getAvailableFlightBetweenStops(String sourceStopCode, String destinationStopCode);
	List<Flight> getAllFlights();
	
	//FlightSchedule
	List<FlightSchedule> getAvailableFlightSchedules(String sourceStopCode, String destinationStopCode, LocalDate flightDate);
	FlightSchedule getFlightSchedule(Flight flight, LocalDate flightDate);
	
	//Ticket related methods
	Ticket bookTicket(FlightSchedule flightSchedule, Customer customer);
	
	//Airplanes related methods
	List<Airplanes> getAllAirplanes();
	
}
