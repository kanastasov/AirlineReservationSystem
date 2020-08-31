package com.kirilanastasoff.ars.airplane.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.repository.airplane.AmericanAirlinesRepository;
import com.kirilanastasoff.ars.repository.customer.CustomerRepository;

@Service
public class AmericanAirlinesServiceImpl implements AmericanAirlinesService {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AmericanAirlinesRepository aaRepository;
	
	
	@Override
	public List<AmericanAirlines> getAllAmericanAirlines() {
		return aaRepository.findAll();
	}
	@Override
	public List<AmericanAirlines> getQueryAmericanAirlines() {
		return aaRepository.getQueryAmericanAirlines();
	}
	
	
	@Override
	public AmericanAirlines getAmericanAirlines(Customer customer) {
		Customer tempCustomer = customerRepository.findByEmail(customer.getEmail());
		if(tempCustomer != null) {
//			Optional<AmericanAirlines> americanAirlines = Optional.ofNullable(aaRepository.findByCustomer(tempCustomer));
//			if(americanAirlines.isPresent()) {
//				return americanAirlines.get();
//			}
		}
		return null;
	}

	@Override
	public AmericanAirlines addAmericanAirlines(AmericanAirlines americanAirlines) {
		Customer customer = customerRepository.findByEmail(americanAirlines.getCustomer().getEmail());
		
		if(customer != null) {
			Optional<AmericanAirlines> tempAmericanAirlines = Optional.ofNullable(aaRepository.findByName(americanAirlines.getName()));
			if(!tempAmericanAirlines.isPresent()) {
				AmericanAirlines american = new AmericanAirlines();
				american.setAaCode(tempAmericanAirlines.get().getAaCode());
				american.setCustomer(tempAmericanAirlines.get().getCustomer());
				american.setDetails(tempAmericanAirlines.get().getDetails());
				american.setListOfAirplanes(tempAmericanAirlines.get().getListOfAirplanes());
				american.setName(tempAmericanAirlines.get().getName());
				aaRepository.save(american);
				return american;
			}
		}
		
		return null;
	}

	//todo add findByCodenameandAmericanAirlines
	@Override
	public AmericanAirlines updateAmericanAirlines(AmericanAirlines americanAirlines, Flight flight) {
		AmericanAirlines tempAmericanAirlines = aaRepository.findByAaCode(americanAirlines.getAaCode());
		if(tempAmericanAirlines != null) {
			if(flight != null) {
//				Optional<Airplanes> airplanes = Optional.ofNullable(flightRepository.findByAmericanAirlines(tempAmericanAirlines))
			}
		}
		
		return null;
	}
}
