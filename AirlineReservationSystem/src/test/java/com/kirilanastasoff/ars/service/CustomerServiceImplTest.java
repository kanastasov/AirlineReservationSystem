package com.kirilanastasoff.ars.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.repository.CustomerRepository;
import com.kirilanastasoff.ars.repository.RoleRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRespository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private BCryptPasswordEncoder encoder;

	private CustomerServiceImpl customerService;

	private Customer customer;

	@BeforeEach
	void setUp() {
		initMocks(this);
		customerService = new CustomerServiceImpl(customerRespository, roleRepository, encoder);
		customer = Customer.builder().id(1l).firstName("john").lastName("doll").email("johny@gmail.com").build();

		Mockito.when(customerRespository.save(any())).thenReturn(customer);
		Mockito.when(customerRespository.findByEmail(anyString())).thenReturn(customer);

	}

	@Test
	public void testFindCustomerByEmail() {
		final String email = "johny@gmail.com";

		final Customer customer = customerService.findByEmail(email);

		assertEquals(email, customer.getEmail());
	}

	@Test
	public void testSaveCustomer() {
		final String email = "johny@gmail.com";

		Customer customer = customerService.saveCustomer(Customer.builder().email("johny@gmail.com").build());

		assertEquals(email, customer.getEmail());
	}

}
