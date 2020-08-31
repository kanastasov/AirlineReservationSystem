package com.kirilanastasoff.ars.airplane.service;

import java.time.LocalDate;
import java.util.List;

import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.FlightSchedule;

public interface FlightScheduleService {

	List<FlightSchedule> getAvailableFlightSchedules(String sourceStopCode, String destinationStopCode,
			LocalDate flightDate);

	FlightSchedule getFlightSchedule(Flight flight, LocalDate flightDate);
}
