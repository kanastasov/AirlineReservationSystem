package com.kirilanastasoff.ars.airplane.service;

import java.util.List;

import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.customer.Customer;

public interface AmericanAirlinesService {

	AmericanAirlines getAmericanAirlines(Customer customer);

	AmericanAirlines addAmericanAirlines(AmericanAirlines americanAirlines);

	AmericanAirlines updateAmericanAirlines(AmericanAirlines americanAirlines, Flight flight);

	List<AmericanAirlines> getAllAmericanAirlines();

	List<AmericanAirlines> getQueryAmericanAirlines();
}
