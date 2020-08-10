package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;

public interface AmericanAirlinesRepository extends JpaRepository<AmericanAirlines, Long> {

}
