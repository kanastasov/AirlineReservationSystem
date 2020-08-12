package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirilanastasoff.ars.model.airplane.Stop;

@Repository
public interface StopRepository extends JpaRepository<Stop, Long>{
	Stop findByCode(String code);
}
