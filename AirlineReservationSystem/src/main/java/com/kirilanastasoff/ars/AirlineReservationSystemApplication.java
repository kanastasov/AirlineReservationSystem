package com.kirilanastasoff.ars;


import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kirilanastasoff.ars.model.airplane.Airplanes;
import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.Stop;
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
				stopRepository.save(stopBerlin);
			}
			
			Stop stopParis = stopRepository.findByCode("stopParis");
			if(stopParis == null) {
				stopParis = new Stop();
				stopParis.setName("Stop Berlin");
				stopParis.setDetail("Western Europe Stop in France");
				stopParis.setCode("stopParis");
				stopRepository.save(stopParis);
			}
			
			
			Optional<Flight> flight = flightRepository.findById(1l);
			if(flight.isEmpty()) {
				Flight tempFlight = new Flight();
				tempFlight.setFare(20);
				tempFlight.setJourneyTime(70);
				tempFlight.setDestStop(stopBerlin);
				tempFlight.setSourceStop(stopParis);
				flightRepository.save(tempFlight);
			}
			
			Airplanes airplane = airplanesRepository.findByCode("code20");
			if(airplane == null) {
				airplane = new Airplanes();
				airplane.setCapacity(120);
				airplane.setCode("code20");
				airplane.setMake("Transport airplane average size");
				airplanesRepository.save(airplane);
			}
			
			
			AmericanAirlines americanAirlines = americanAirlinesRepository.findByName("airlines name");
			if(americanAirlines == null) {
				americanAirlines = new AmericanAirlines();
				americanAirlines.setCode("codeAA");
				americanAirlines.setDetails("New American Airlines");
				americanAirlines.setName("airlines name");
				americanAirlinesRepository.save(americanAirlines);
			}
			
			
			Customer customer = customerRepository.findByEmail("add@gmail.com");
			if(customer == null) {
				customer = new Customer();
				customer.setEmail("add@gmail.com");
				customer.setFirstName("test");
				customer.setLastName("customer");
				customer.setUsername("admin");
				customer.setPassword("password");
				customerRepository.save(customer);
			}
			
		};
	}
   
}
