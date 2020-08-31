package com.kirilanastasoff.ars.controller.airplanes;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kirilanastasoff.ars.airplane.service.AirplanesService;
import com.kirilanastasoff.ars.airplane.service.AmericanAirlinesService;
import com.kirilanastasoff.ars.airplane.service.FlightScheduleService;
import com.kirilanastasoff.ars.airplane.service.FlightService;
import com.kirilanastasoff.ars.airplane.service.StopService;
import com.kirilanastasoff.ars.airplane.service.TicketService;
import com.kirilanastasoff.ars.customer.service.CustomerService;
import com.kirilanastasoff.ars.model.airplane.AmericanAirlines;
import com.kirilanastasoff.ars.model.airplane.Flight;
import com.kirilanastasoff.ars.model.airplane.TempResponse;
import com.kirilanastasoff.ars.model.customer.Customer;

@Controller
public class AirplanesController {

	@Autowired
	private AirplanesService airplaneService;
	
	@Autowired
	private AmericanAirlinesService americanAirlinesService;
	
	@Autowired
	private FlightScheduleService flightScheduleService;
	
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private StopService stopService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/flight")
	public ModelAndView flightDetails() {
		ModelAndView modelAndView = new ModelAndView("flight");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		modelAndView.addObject("flightObj", flightService.getAllFlights());
		modelAndView.setViewName("flight");
		return modelAndView;
	}

	@PostMapping("/flight")
	public ModelAndView addFlight(@Valid @ModelAttribute("customerObjData") BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("flight");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.findByEmail(auth.getName());
		AmericanAirlines americanAirlines = americanAirlinesService.getAmericanAirlines(customer);
		modelAndView.addObject("customerObjData", customer);
		modelAndView.addObject("americanAirlinesObjData", americanAirlines);

		if (!bindingResult.hasErrors()) {
			Flight flight = new Flight();
			flight.setAmericanAirlines(americanAirlines);
			AmericanAirlines updateAirlines = americanAirlinesService.updateAmericanAirlines(americanAirlines,
					flight);
			modelAndView.addObject("flightObjData", customer);
			modelAndView.addObject("americanAirlinesObjData", updateAirlines);

		}
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

	@GetMapping("/airplane")
	public ModelAndView airplaneDetails() {
		ModelAndView modelAndView = new ModelAndView("airplane");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		modelAndView.addObject("airplaneObj", airplaneService.getAllAirplanes());
		modelAndView.setViewName("airplane");
		return modelAndView;
	}

	@GetMapping("/americanairlines")
	public ModelAndView americanAirlinesDetails() {
		ModelAndView modelAndView = new ModelAndView("americanairlines");
		List<AmericanAirlines> tempAAList = americanAirlinesService.getQueryAmericanAirlines();
		TempResponse tempResponse = null;
		List<TempResponse> tempResponseList = new ArrayList<>();

		for (int i = 0; i < tempAAList.size(); i++) {
			tempResponse = new TempResponse();
			tempResponse.setAaId(tempAAList.get(i).getAaId());
			tempResponse.setAaCode(tempAAList.get(i).getAaCode());
			tempResponse.setName(tempAAList.get(i).getName());
			tempResponse.setDetails(tempAAList.get(i).getDetails());
			tempResponse.setMake(tempAAList.get(i).getListOfAirplanes().get(i).getMake());
			tempResponse.setAId(tempAAList.get(i).getListOfAirplanes().get(i).getAId());
			tempResponse.setCapacity(tempAAList.get(i).getListOfAirplanes().get(i).getCapacity());
			tempResponseList.add(tempResponse);
		}

		modelAndView.addObject("americanairlinesObj", tempResponseList);
		modelAndView.setViewName("americanairlines");
		return modelAndView;
	}

	@GetMapping("/profile")
	public ModelAndView profileDetails() {
		ModelAndView modelAndView = new ModelAndView("profile");
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		modelAndView.addObject("profileObj", customerService.findByEmail("add@gmail.com"));
		modelAndView.setViewName("profile");
		return modelAndView;
	}
	
}
