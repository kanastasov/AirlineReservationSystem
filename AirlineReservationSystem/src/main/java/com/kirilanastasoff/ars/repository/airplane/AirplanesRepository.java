package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirilanastasoff.ars.model.airplane.Airplanes;
import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;

public interface AirplanesRepository extends JpaRepository<Airplanes, Long> {
	Airplanes findByCode(String code);
	
	Airplanes findByCodeAndAmericanAirlines(String code, AmericanAirlines americanAirlines);
}
