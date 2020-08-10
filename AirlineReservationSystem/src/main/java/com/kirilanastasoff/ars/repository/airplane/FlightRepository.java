package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirilanastasoff.ars.model.airplane.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
