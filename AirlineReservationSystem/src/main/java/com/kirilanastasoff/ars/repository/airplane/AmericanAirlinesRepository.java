package com.kirilanastasoff.ars.repository.airplane;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.customer.Customer;

@Repository
public interface AmericanAirlinesRepository extends JpaRepository<AmericanAirlines, Long> {

	AmericanAirlines findByAaCode(String aaCode);
	
//	AmericanAirlines findByCustomer(Customer customer);
	
	AmericanAirlines findByName(String name);
	
	@Query(value = "select * from airplanes "
			+ "JOIN american_airlines "
			+ "ON airplanes.american_airlines_id = american_airlines.aa_id;", nativeQuery = true)
	List<AmericanAirlines> getQueryAmericanAirlines();
	
	
}
