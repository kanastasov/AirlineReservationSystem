package com.kirilanastasoff.ars.airplane.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.airplane.Airplanes;
import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.FlightSchedule;
import com.kirilanastasoff.ars.model.airplane.Stop;
import com.kirilanastasoff.ars.model.airplane.Ticket;
import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.repository.airplane.AmericanAirlinesRepository;
import com.kirilanastasoff.ars.repository.airplane.FlightRepository;
import com.kirilanastasoff.ars.repository.airplane.FlightScheduleRepository;
import com.kirilanastasoff.ars.repository.airplane.StopRepository;
import com.kirilanastasoff.ars.repository.airplane.TicketRepository;
import com.kirilanastasoff.ars.repository.customer.CustomerRepository;

@Service
public class AirplaneReservationServiceImpl implements AirplaneReservationService{

	@Autowired
	private StopRepository stopRepository;
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AmericanAirlinesRepository aaRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private FlightScheduleRepository flightScheduleRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public Set<Stop> getAllStops() {
		return (Set<Stop>) stopRepository.findAll();
	}

	@Override
	public Stop getStopByCode(String stopCode) {
		Optional<Stop> stop = Optional.ofNullable(stopRepository.findByCode(stopCode));
		if(stop != null )
		{
			return stop.get();
		}
		return null;
	}

	@Override
	public AmericanAirlines getAmericanAirlines(Customer customer) {
		Customer tempCustomer = customerRepository.findByEmail(customer.getEmail());
		if(tempCustomer != null) {
			Optional<AmericanAirlines> americanAirlines = Optional.ofNullable(aaRepository.findByCustomer(tempCustomer));
			if(americanAirlines.isPresent()) {
				return americanAirlines.get();
			}
		}
		return null;
	}

	@Override
	public AmericanAirlines addAmericanAirlines(AmericanAirlines americanAirlines) {
		Customer customer = customerRepository.findByEmail(americanAirlines.getCustomer().getEmail());
		
		if(customer != null) {
			Optional<AmericanAirlines> tempAmericanAirlines = Optional.ofNullable(aaRepository.findByName(americanAirlines.getName()));
			if(!tempAmericanAirlines.isPresent()) {
				AmericanAirlines american = new AmericanAirlines();
				american.setCode(tempAmericanAirlines.get().getCode());
				american.setCustomer(tempAmericanAirlines.get().getCustomer());
				american.setDetails(tempAmericanAirlines.get().getDetails());
				american.setListOfAirplanes(tempAmericanAirlines.get().getListOfAirplanes());
				american.setName(tempAmericanAirlines.get().getName());
				aaRepository.save(american);
				return american;
			}
		}
		
		return null;
	}

	//todo add findByCodenameandAmericanAirlines
	@Override
	public AmericanAirlines updateAmericanAirlines(AmericanAirlines americanAirlines, Flight flight) {
		AmericanAirlines tempAmericanAirlines = aaRepository.findByCode(americanAirlines.getCode());
		if(tempAmericanAirlines != null) {
			if(flight != null) {
//				Optional<Airplanes> airplanes = Optional.ofNullable(flightRepository.findByAmericanAirlines(tempAmericanAirlines))
			}
		}
		
		return null;
	}

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
	public List<FlightSchedule> getAvailableFlightSchedules(String sourceStopCode, String destinationStopCode,
			LocalDate flightDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlightSchedule getFlightSchedule(Flight flight, LocalDate flightDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket bookTicket(FlightSchedule flightSchedule, Customer customer) {
		Customer tempCustomer = customerRepository.findByEmail(customer.getEmail());
		if(tempCustomer != null) {
			Optional<FlightSchedule> tempFlightSchedule = flightScheduleRepository.findById(flightSchedule.getId());
			if(tempFlightSchedule.isPresent()) {
				Ticket ticket = new Ticket();
				ticket.setCancellable(false);
				ticket.setCustomer(customer);
				ticket.setJourneyDate(tempFlightSchedule.get().getTripDate());
				ticket.setSeatNumber(tempFlightSchedule.get().getFlight().getAirplanes().getCapacity() 
						- tempFlightSchedule.get().getAvailableSeats());
				ticketRepository.save(ticket);
				
				tempFlightSchedule.get().setAvailableSeats(tempFlightSchedule.get().getAvailableSeats() - 1);
				flightScheduleRepository.save(tempFlightSchedule.get());
				return ticket;
			}
		}
		
		return null;
	}

}
