package com.kirilanastasoff.ars.repository.airplane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.customer.Customer;

@Repository
public interface AmericanAirlinesRepository extends JpaRepository<AmericanAirlines, Long> {

	AmericanAirlines findByCode(String code);
	
	AmericanAirlines findByCustomer(Customer customer);
	
	AmericanAirlines findByName(String name);
}
