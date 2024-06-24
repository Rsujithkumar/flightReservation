package com.flight_reservation_app.controller;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight_reservation_app.repository.FlightRepository;
import com.flight_reservation_app.entity.Flight;
import com.flight_reservation_app.repository.PassengerRepository;
import com.flight_reservation_app.entity.Passenger;
import com.flight_reservation_app.repository.ReservationRepository;

import com.flight_reservation_app.util.PDFgenerator;
import com.flight_reservation_app.util.SendEmail;
import com.flight_reservation_app.entity.Reservation;


@Controller
public class ReservationController {
 private static String folderPath =	"C:\\Users\\sujit\\Documents\\workspace-spring-tool-suite-4-4.8.1.RELEASE\\flight_reservation_app\\tickets\\";
	
 
 private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	PDFgenerator pdfGen;
	
	@Autowired
	SendEmail sendEmail;
	
	
	@RequestMapping("/showCompleteReservation")
	public String completeReservation(@RequestParam("flightId") Long flightId,ModelMap modelMap) {
		LOGGER.info("Inside completeReservation()");
		System.out.println(flightId);
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		modelMap.addAttribute("flights", flight);
		return "showCompleteReservation";
	}
	
	@RequestMapping("/confirmReservation")
	public String confirmReservation(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,@RequestParam("middleName") String middleName,@RequestParam("email") String email, @RequestParam("phone") String phone,@RequestParam("flightsId") Long flightsId, ModelMap modelMap ) {
		LOGGER.info("Inside confirmReservation()");
		Passenger passenger = new Passenger();
		passenger.setFirstName(firstName);
		passenger.setLastName(lastName);
		passenger.setMiddleName(middleName);
		passenger.setEmail(email);
		passenger.setPhone(phone);
		
	    passengerRepo.save(passenger);
		
		Optional<Flight> findById = flightRepo.findById(flightsId);
		Flight flight = findById.get();
		
		Reservation reservation = new Reservation();
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		reservation.setPassenger(passenger);
		reservation.setFlight(flight);
		
		
		reservationRepo.save(reservation);
		
		modelMap.addAttribute("firstName", firstName);
		modelMap.addAttribute("lastName", lastName);
		modelMap.addAttribute("middleName", middleName);
		modelMap.addAttribute("email", email);
		modelMap.addAttribute("phone", phone);
		
		modelMap.addAttribute("operatingAirlines", flight.getOperatingAirlines());
		modelMap.addAttribute("departureCity", flight.getDepartureCity());
		modelMap.addAttribute("arrivalCity", flight.getArrivalCity());
		modelMap.addAttribute("estimatedDepartureTime", flight.getEstimatedDepartureTime());
		
		
		String estimatedDepartureTime = flight.getEstimatedDepartureTime().toString();
		pdfGen.generatePDF(folderPath+"ticket"+passenger.getId()+".pdf",passenger.getFirstName(),passenger.getEmail(),passenger.getPhone(),flight.getOperatingAirlines(),estimatedDepartureTime,flight.getDepartureCity(),flight.getArrivalCity());
		
	//	sendEmail.sendTicket(passenger.getEmail(),"Ticket Details","Please download the ticket",folderPath+"ticket"+passenger.getId()+".pdf");
		
		String emailSubject = "Ticket Details";
        String emailContent = String.format("Dear %s %s,\n\nPlease find your ticket attached.\n\nFlight Details:\nAirline: %s\nDeparture: %s\nArrival: %s\nDeparture Time: %s\n\nThank you for choosing our service.", firstName, lastName, flight.getOperatingAirlines(), flight.getDepartureCity(), flight.getArrivalCity(), estimatedDepartureTime);

        sendEmail.sendTicket(passenger.getEmail(), emailSubject, emailContent, folderPath+"ticket"+passenger.getId()+".pdf");

		
		
		return "confirmationPage";
	}

}

