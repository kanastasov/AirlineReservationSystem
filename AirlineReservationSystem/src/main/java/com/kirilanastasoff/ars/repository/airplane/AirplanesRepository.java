package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirilanastasoff.ars.model.airplane.Airplanes;
import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;

@Repository
public interface AirplanesRepository extends JpaRepository<Airplanes, Long> {
	Airplanes findByaCode(String aCode);
	
//	Airplanes findByCodeAndAmericanAirlines(String code, AmericanAirlines americanAirlines);
}
