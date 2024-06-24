package com.flight_reservation_app.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight_reservation_app.repository.FlightRepository;
import com.flight_reservation_app.entity.Flight;

@Controller
public class FlightController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	
	@Autowired
	private FlightRepository flightRepo;
	
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from,@RequestParam("to") String to, @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate, ModelMap modelMap) {
		LOGGER.info("Inside findFlights()");
		List<Flight> flights = flightRepo.findByDepartureCityAndArrivalCityAndDateOfDeparture(from, to, departureDate);

		modelMap.addAttribute("flights", flights);
		return "displayFlights";
	}

}
