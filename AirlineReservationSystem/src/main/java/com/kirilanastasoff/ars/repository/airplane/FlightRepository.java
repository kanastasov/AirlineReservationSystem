package com.kirilanastasoff.ars.repository.airplane;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
		List<Flight> findByName(String name);
		
		List<Flight> findByAmericanAirlines(AmericanAirlines americanAirlines);
}
