package com.kirilanastasoff.ars.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	@GetMapping("/")
	public String showAllCustomers(Model model) {
		model.addAttribute("listCustomerAccounts", customerService.getAllCustomers());
		return "index";
	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customerObj", customer);
		return "login";
	}

	@GetMapping(value = "/showCustomerForm")
	public String showCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customerObj", customer);
		return "register";
	}

	@PostMapping(value = "/register")
	public String saveCustomer(@ModelAttribute("customerObj") @Valid Customer customer, BindingResult bindingResult) {
		Customer customerTemp = customerService.findByEmail(customer.getEmail());
		if(customerTemp != null) {
			bindingResult.rejectValue("email", "There is already an account with this email");
		}
		
		if(bindingResult.hasErrors()) {
			return "register";
		}
		customerService.saveCustomer(customer);
		return "redirect:/";
	}
	
	@GetMapping(value = "/deleteCustomer/{id}") 
	public String deleteCustomerById(@PathVariable (value = "id") Long id) {
		customerService.deleteCustomerById(id);
		return "redirect:/";
	}
	
	
	
	
}
