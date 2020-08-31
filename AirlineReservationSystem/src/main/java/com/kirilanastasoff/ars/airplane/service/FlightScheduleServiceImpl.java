package com.kirilanastasoff.ars.airplane.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.FlightSchedule;
import com.kirilanastasoff.ars.repository.airplane.FlightScheduleRepository;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {
	
	@Autowired
	private FlightScheduleRepository flightScheduleRepository;
	
	@Override
	public List<FlightSchedule> getAvailableFlightSchedules(String sourceStopCode, String destinationStopCode,
			LocalDate flightDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlightSchedule getFlightSchedule(Flight flight, LocalDate flightDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
