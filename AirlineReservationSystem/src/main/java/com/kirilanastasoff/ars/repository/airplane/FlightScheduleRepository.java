package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirilanastasoff.ars.model.airplane.FlightSchedule;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

}
