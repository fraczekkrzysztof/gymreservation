package com.fraczekkrzysztof.gymreservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraczekkrzysztof.gymreservation.dao.ReservationDao;
import com.fraczekkrzysztof.gymreservation.entity.Reservation;

@Service
public class ReservationService {
		
	@Autowired
	private ReservationDao reservationDao;
	
	public List<Reservation> findAll(){
		List<Reservation> listOfReservation = new ArrayList<Reservation>();
		for(Reservation theReservation : reservationDao.findAll()) {
			listOfReservation.add(theReservation);
		}
		return listOfReservation;
	}
	
	public Reservation findById(int theId) {
		return reservationDao.findById(theId).orElse(null);
	}
	
	public Reservation saveOrUpdate(Reservation theReservation) {
		return reservationDao.save(theReservation);
	}
	
	public void delete(Reservation theReservation) {
		reservationDao.delete(theReservation);
	}
	
	public void deleteById(int theId) {
		reservationDao.deleteById(theId);
	}
	
	public List<Reservation> findByLessonId(int theId){
		return reservationDao.findByLessonId(theId);
	}

}
