package com.kirilanastasoff.ars.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.model.customer.Role;
import com.kirilanastasoff.ars.repository.CustomerRepository;
import com.kirilanastasoff.ars.repository.RoleRepository;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
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

	@Override
	public void deleteCustomerById(Long id) {
		this.customerRepository.deleteById(id);
		
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		Customer tempCustomer = new Customer();
		tempCustomer.setEmail(customer.getEmail());
		tempCustomer.setFirstName(customer.getFirstName());
		tempCustomer.setLastName(customer.getLastName());
		tempCustomer.setPassword(encoder.encode(customer.getPassword()));
		
		Role customerRole = roleRepository.findByRole("ADMIN");
		tempCustomer.setRoles(new HashSet<Role>(Arrays.asList(customerRole)));
		
		
		this.customerRepository.save(tempCustomer);
		return tempCustomer;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(email);
		if(customer == null) {
			throw new UsernameNotFoundException("Invalid details");
		}
		List<GrantedAuthority> authorities = getCustomerAutority((Set<Role>) customer.getRoles());
		return buildUserForAuthentication(customer, authorities);
	}
	
	private List<GrantedAuthority> getCustomerAutority(Set<Role> role) {
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		for(Role currRole : role) {
			roles.add(new SimpleGrantedAuthority(currRole.getRole()));
		}
		List<GrantedAuthority> grandAuthorities = new ArrayList<>(roles);
		return grandAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(Customer customer, List<GrantedAuthority> authorities) {
		return new User(customer.getEmail(), customer.getPassword(),true, true, true, true, authorities);
	}
	
	

}
