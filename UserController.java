package com.suramya.flightreservation.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suramya.flightreservation.entities.Flight;
import com.suramya.flightreservation.entities.User;
import com.suramya.flightreservation.repos.UserRepository;
//import com.suramya.flightreservation.services.FlightServiceImpl;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;

	//@Autowired(required = true)
	//private FlightServiceImpl flightService;

	@RequestMapping("/showReg")
	public String showRegistrationPage() {

		return "login/registerUser";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@RequestBody User user) {
		userRepository.save(user);
		System.out.println("Controller");
		return "login/login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public boolean login(@RequestBody User userInput,ModelMap modelMap){ 
		User user= userRepository.findByEmail(userInput.getEmail());
		if(user == null)
			return false;
		return user.getPassword().equals(userInput.getPassword());
	}
	/*
	@RequestMapping(value = "/flightSearch", method = RequestMethod.POST)
	@ResponseBody
	public List<Flight> flightSearch(@RequestBody Flight flight,ModelMap modelMap) {
		List<Flight> flightList=flightService.getFlight(flight);
		return flightList;
		*/
	}
