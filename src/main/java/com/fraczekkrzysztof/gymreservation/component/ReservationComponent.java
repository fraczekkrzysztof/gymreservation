package com.fraczekkrzysztof.gymreservation.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fraczekkrzysztof.gymreservation.entity.Lesson;
import com.fraczekkrzysztof.gymreservation.service.LessonService;
import com.fraczekkrzysztof.gymreservation.service.ReservationService;

@Component
public class ReservationComponent {

	@Autowired
	ReservationService reservationService;
	@Autowired
	LessonService lessonService;
	
	public ReservationComponent() {
		
	}

	public void updateAvailable(Lesson theLesson) {
		int available = theLesson.getAvailable();
		available++;
		theLesson.setAvailable(available);
		lessonService.saveOrUdpdate(theLesson);
	}
}
