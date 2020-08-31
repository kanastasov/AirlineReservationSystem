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
import com.kirilanastasoff.ars.repository.airplane.AirplanesRepository;
import com.kirilanastasoff.ars.repository.airplane.AmericanAirlinesRepository;
import com.kirilanastasoff.ars.repository.airplane.FlightRepository;
import com.kirilanastasoff.ars.repository.airplane.FlightScheduleRepository;
import com.kirilanastasoff.ars.repository.airplane.StopRepository;
import com.kirilanastasoff.ars.repository.airplane.TicketRepository;
import com.kirilanastasoff.ars.repository.customer.CustomerRepository;

@Service
public class AirplanesServiceImpl implements AirplanesService {

	@Autowired
	private AirplanesRepository airplanesRepository;

	@Override
	public List<Airplanes> getAllAirplanes() {
		return airplanesRepository.findAll();
	}

}
