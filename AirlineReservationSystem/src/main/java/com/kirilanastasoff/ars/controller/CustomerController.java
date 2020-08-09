package com.kirilanastasoff.ars.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kirilanastasoff.ars.exceptions.CustomerException;
import com.kirilanastasoff.ars.model.customer.Customer;
import com.kirilanastasoff.ars.service.CustomerService;
import com.kirilanastasoff.ars.service.PdfService;
import com.lowagie.text.DocumentException;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private PdfService pdfService;

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
	public ModelAndView saveCustomer(@ModelAttribute("customerObj") @Valid Customer customer,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		Customer customerTemp = customerService.findByEmail(customer.getEmail());
		if (customerTemp != null) {
			bindingResult.rejectValue("email", "There is already an account with this email");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
			return modelAndView;
		}
		customerService.saveCustomer(customer);
		modelAndView.addObject("successMessage", "Customer has been registered successfully");
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

	@GetMapping(value = "/deleteCustomer/{id}")
	public String deleteCustomerById(@PathVariable(value = "id") Long id) {
		customerService.deleteCustomerById(id);
		return "redirect:/";
	}

	@GetMapping(value = "/admin/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		modelAndView.addObject("adminObj", "Welcome " + username);
		modelAndView.addObject("adminMessage", "Content available only for users with admin rights");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

	@GetMapping("/download-pdf")
	public void downloadPDFResource(HttpServletResponse response) {
		try {
			Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
			if (Files.exists(file)) {
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
		} catch (DocumentException | IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@GetMapping("/customer/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable("email") String email) throws CustomerException {
		Customer customer = customerService.findByEmail(email);
		if(customer == null) {
			throw new CustomerException("Customer does not exist");
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		
	}
	
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomers()  {
		List<Customer> customer = customerService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
		
	}
	
	

}
