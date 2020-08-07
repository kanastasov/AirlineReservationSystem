package com.kirilanastasoff.ars.service;

import java.util.List;
import java.util.Optional;

import com.kirilanastasoff.ars.model.customer.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();
	
	Optional<Customer> getCustomerById(Long id);
	
	Customer findByEmail(String email);
	
	void deleteCustomerById(Long id);
	
	Customer saveCustomer(Customer customer);

	Customer findCustomerByUsername(String username);
}
