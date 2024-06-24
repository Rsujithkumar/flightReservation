package com.flight_reservation_app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight_reservation_app.entity.Reservation;
import com.flight_reservation_app.entity.ReservationUpdateRequest;
import com.flight_reservation_app.repository.ReservationRepository;


@RestController
public class ReservationRestfulController {
	@Autowired
	ReservationRepository reservationRepo;
	
	@GetMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		 Optional<Reservation> findById = reservationRepo.findById(id);
		return findById.get();
		
	}
	
	@RequestMapping("/reservation")
   public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
	   long id = request.getId();
		 Optional<Reservation> findById = reservationRepo.findById(request.getId());
		 Reservation reservation = findById.get();
		 reservation.setNumberOfBags(request.getNumberOfBags());
		 reservation.setCheckedIn(request.isCheckedIn());
		 return reservationRepo.save(reservation);
		 
	}
}

