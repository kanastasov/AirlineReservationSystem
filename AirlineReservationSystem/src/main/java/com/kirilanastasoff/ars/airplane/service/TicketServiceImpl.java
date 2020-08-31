package com.kirilanastasoff.ars.airplane.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.airplane.FlightSchedule;
import com.kirilanastasoff.ars.model.airplane.Ticket;
import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.repository.airplane.FlightScheduleRepository;
import com.kirilanastasoff.ars.repository.airplane.TicketRepository;
import com.kirilanastasoff.ars.repository.customer.CustomerRepository;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private FlightScheduleRepository flightScheduleRepository;

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
