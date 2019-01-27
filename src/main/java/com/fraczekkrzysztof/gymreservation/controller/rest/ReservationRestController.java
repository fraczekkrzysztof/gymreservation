package com.fraczekkrzysztof.gymreservation.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraczekkrzysztof.gymreservation.entity.Reservation;
import com.fraczekkrzysztof.gymreservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/reservation")
	public List<Reservation> getAllReservation(){
		return reservationService.findAll();
	}
	
	@GetMapping("/reservation/{reservationId}")
	public Reservation getReservationById(@PathVariable("reservationId") int theId) {
		return reservationService.findById(theId);
	}
	
	@GetMapping("/reservation/lesson/{lessonId}")
	public List<Reservation> getReservationByLessonId(@PathVariable("lessonId") int theId){
		return reservationService.findByLessonId(theId);
	}
}
