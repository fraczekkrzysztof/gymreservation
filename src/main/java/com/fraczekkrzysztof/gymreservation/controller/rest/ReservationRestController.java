package com.fraczekkrzysztof.gymreservation.controller.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraczekkrzysztof.gymreservation.component.ReservationComponent;
import com.fraczekkrzysztof.gymreservation.controller.rest.exception.NotFoundException;
import com.fraczekkrzysztof.gymreservation.dto.ReservationDto;
import com.fraczekkrzysztof.gymreservation.email.EmailSender;
import com.fraczekkrzysztof.gymreservation.entity.Lesson;
import com.fraczekkrzysztof.gymreservation.entity.Reservation;
import com.fraczekkrzysztof.gymreservation.service.LessonService;
import com.fraczekkrzysztof.gymreservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private LessonService lessonService;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private ReservationComponent reservationComponent;

	@GetMapping("/reservation")
	public List<Reservation> getAllReservation() {
		return reservationService.findAll();
	}

	@GetMapping("/reservation/{reservationId}")
	public Reservation getReservationById(@PathVariable("reservationId") int theId) {
		return reservationService.findById(theId);
	}

	@GetMapping("/reservation/lesson/{lessonId}")
	public List<Reservation> getReservationByLessonId(@PathVariable("lessonId") int theId) {
		return reservationService.findByLessonId(theId);
	}

	@PostMapping("/reservation")
	public Reservation addReservation(@RequestBody ReservationDto theReservation) {
		// find first lesson
		Lesson theLesson = lessonService.findById(theReservation.getLesson());
		if (theLesson == null) {
			throw new NotFoundException("There is no lesson with id " + theReservation.getLesson());
		}
		LocalDateTime now = LocalDateTime.now();
		Reservation insertedReservation = new Reservation(0, theLesson, theReservation.getName(),
				theReservation.getEmail(),false,0,now,false);
		reservationService.saveOrUpdate(insertedReservation);
		theLesson.changeAvailable();
		lessonService.saveOrUdpdate(theLesson);
		emailSender.sendEmail(theReservation.getEmail(), EmailSender.EMAIL_TOPIC_RESERVATION, EmailSender.TEMPLATE_NAME,
				emailSender.generateReservationEmail(theLesson,insertedReservation));
		return insertedReservation;
	}
	
	@PostMapping("/reservation/waiting")
	public Reservation addToWaitingList(@RequestBody ReservationDto theReservation) {
		Lesson theLesson = lessonService.findById(theReservation.getLesson());
		if (theLesson == null) {
			throw new NotFoundException("There is no lesson with id " + theReservation.getLesson());
		}
		int maxWaiting = reservationService.findMaxWaitingNumber(theReservation.getLesson());
		maxWaiting++;
		LocalDateTime now = LocalDateTime.now();
		Reservation insertedReservation = new Reservation (0, theLesson, theReservation.getName(), theReservation.getEmail(),false,maxWaiting,now,false);
		reservationService.saveOrUpdate(insertedReservation);
		return insertedReservation;
	}
	
	@GetMapping("/reservation/{reservationId}/confirm")
	public String confirmReservation(@PathVariable("reservationId") int theReservationId) {
		Reservation theReservation = reservationService.findById(theReservationId);
		theReservation.setConfirmed(true);
		reservationService.saveOrUpdate(theReservation);
		return "Reservation Confirmed!";
	}
	@GetMapping("/reservation/{reservationId}/cancel")
	public String cancelReservation(@PathVariable("reservationId") int theReservationId) {
		Reservation theReservation = reservationService.findById(theReservationId);
		theReservation.setCanceled(true);
		reservationService.saveOrUpdate(theReservation);
		reservationComponent.updateAvailable(theReservation.getLesson());
		return "Reservation Calceled!";
	}
	
	
}
