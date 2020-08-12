package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kirilanastasoff.ars.model.airplane.Stop;

public interface StopRepository extends JpaRepository<Stop, Long>{
	Stop findByCode(String code);
}
