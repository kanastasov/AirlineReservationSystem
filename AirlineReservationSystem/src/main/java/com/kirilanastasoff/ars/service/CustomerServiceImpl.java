package com.kirilanastasoff.ars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return  customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		return this.customerRepository.findById(id);
	}

	@Override
	public Customer findByEmail(String email) {
		return this.customerRepository.findByEmail(email);
	}

}
