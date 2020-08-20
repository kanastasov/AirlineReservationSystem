package com.kirilanastasoff.ars.repository.airplane;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.FlightSchedule;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
//	FlightSchedule findByFlightDetailAndFlightDate(Flight flight, LocalDate flightDate);
}
