package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirilanastasoff.ars.model.airplane.Airplanes;

public interface AirplanesRepository extends JpaRepository<Airplanes, Long> {

}
