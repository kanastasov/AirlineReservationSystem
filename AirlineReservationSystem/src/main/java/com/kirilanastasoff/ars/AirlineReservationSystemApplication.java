package com.kirilanastasoff.ars;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.Stop;
import com.kirilanastasoff.ars.repository.airplane.AirplanesRepository;
import com.kirilanastasoff.ars.repository.airplane.AmericanAirlinesRepository;
import com.kirilanastasoff.ars.repository.airplane.FlightRepository;
import com.kirilanastasoff.ars.repository.airplane.FlightScheduleRepository;
import com.kirilanastasoff.ars.repository.airplane.StopRepository;
import com.kirilanastasoff.ars.repository.airplane.TicketRepository;

@SpringBootApplication
public class AirlineReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationSystemApplication.class, args);
	}

	
    @Bean
    CommandLineRunner init(AirplanesRepository airplanesRepository, AmericanAirlinesRepository americanAirlinesRepository,
                           FlightRepository flightRepository, FlightScheduleRepository flightScheduleRepository,
                           StopRepository stopRepository, TicketRepository ticketRepository) {
							return args -> {
//					            Stop stopBerlin = stopRepository.findByCode("stopBerlin");
								  Stop stopBerlin  = new Stop();
					            if (stopBerlin != null) {
					            	stopBerlin = new Stop();
					            	stopBerlin.setName("Stop Berlin");
					            	stopBerlin.setDetail("Eastern Europe berlin");
					            	stopBerlin.setCode("stopBerlin");
					                stopRepository.save(stopBerlin);
					            }
					            
					            
//					            Optional<Flight> flight =  flightRepository.findById(1l);
					            Flight flight = new Flight();
					            if(flight != null) {
					            	flight.setDestStop(stopBerlin);
					            	flight.setSourceStop(stopBerlin);
					            	flightRepository.save(flight);
					            }
					            
					            
							};
    }
}
