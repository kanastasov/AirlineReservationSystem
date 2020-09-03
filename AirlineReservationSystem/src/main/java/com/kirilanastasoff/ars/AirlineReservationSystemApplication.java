package com.kirilanastasoff.ars;


import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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


@SpringBootApplication
public class AirlineReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AirplanesRepository airplanesRepository, AmericanAirlinesRepository americanAirlinesRepository, FlightRepository flightRepository,
			FlightScheduleRepository flightScheduleRepository, StopRepository stopRepository, TicketRepository ticketRepository, CustomerRepository customerRepository) {
		return args -> {
			
			Stop stopBerlin = stopRepository.findByCode("stopBerlin");
			if(stopBerlin == null) {
				stopBerlin = new Stop();
				stopBerlin.setName("Stop Berlin");
				stopBerlin.setDetail("Western Europe Stop in Germany");
				stopBerlin.setCode("stopBerlin");
			}
			
			Stop stopParis = stopRepository.findByCode("stopParis");
			if(stopParis == null) {
				stopParis = new Stop();
				stopParis.setName("Stop Berlin");
				stopParis.setDetail("Western Europe Stop in France");
				stopParis.setCode("stopParis");
			
			}
			
			Flight tempFlight = null;
			Optional<Flight> flight = flightRepository.findById(1l);
			if(!flight.isPresent()) {
				tempFlight = new Flight();
				tempFlight.setFare(20);
				tempFlight.setJourneyTime(70);
				tempFlight.setDestStop(stopBerlin);
				tempFlight.setSourceStop(stopParis);
				stopBerlin.addFlight(tempFlight);
				stopParis.addFlight(tempFlight);
			}
			
			stopRepository.save(stopParis);
			stopRepository.save(stopBerlin);
			
			Customer customer = customerRepository.findByEmail("add@gmail.com");
			if(customer == null) {
				customer = new Customer();
				customer.setEmail("add@gmail.com");
				customer.setFirstName("test");
				customer.setLastName("customer");
				customer.setUsername("admin");
				customer.setPassword("password");
			}
			
			
			AmericanAirlines americanAirlines = americanAirlinesRepository.findByName("airlines name");
			if(americanAirlines == null) {
				americanAirlines = new AmericanAirlines();
				americanAirlines.setAaCode("codeAA");
				americanAirlines.setDetails("New American Airlines");
				americanAirlines.setName("airlines name");
				americanAirlines.setCustomer(customer);
//				americanAirlines.setFlight(tempFlight);
			}
			
			Airplanes airplane = airplanesRepository.findByaCode("code20");
			if(airplane == null) {
				airplane = new Airplanes();
				airplane.setCapacity(320);
				airplane.setACode("code20");
				airplane.setMake("Transport airplane average size");
				americanAirlines.addAirplanes(airplane);
				americanAirlines.getListOfAirplanes().add(airplane);
			}
			
			Airplanes airplane2 = airplanesRepository.findByaCode("code60");
			if(airplane2 == null) {
				airplane2 = new Airplanes();
				airplane2.setCapacity(120);
				airplane2.setACode("code60");
				airplane2.setMake("Model");
				americanAirlines.addAirplanes(airplane2);
				americanAirlines.getListOfAirplanes().add(airplane2);
			}
			

			
			
			customer.addAmericanAirlines(americanAirlines);
			customerRepository.save(customer);
			
			FlightSchedule tempFlightSchedule = new FlightSchedule();;
			Optional<FlightSchedule> flightSchedule = flightScheduleRepository.findById(111l);
			if(!flightSchedule.isPresent()) {
				tempFlightSchedule.setAvailableSeats(120);
			}
			
			Ticket tempTicket = new Ticket();
			Optional<Ticket> ticket = ticketRepository.findById(111l);
			
			if(!ticket.isPresent()) {
				tempTicket.setCancellable(false);
				tempTicket.setSeatNumber(120);
				tempFlightSchedule.addTicket(tempTicket);
//				tempTicket.setCustomer(customer);
			}
			
			
			
//			customer.addTicket(tempTicket);
			tempFlightSchedule.getTicketsSold().add(tempTicket);
			flightScheduleRepository.save(tempFlightSchedule);
			
			
			
			
		};
	}
   
}
