package com.kirilanastasoff.ars.airplane.service;

import com.kirilanastasoff.ars.model.airplane.FlightSchedule;
import com.kirilanastasoff.ars.model.airplane.Ticket;
import com.kirilanastasoff.ars.model.customer.Customer;

public interface TicketService {
	Ticket bookTicket(FlightSchedule flightSchedule, Customer customer);
}
