package com.fraczekkrzysztof.gymreservation.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraczekkrzysztof.gymreservation.controller.rest.exception.MyParseException;
import com.fraczekkrzysztof.gymreservation.controller.rest.exception.NotFoundException;
import com.fraczekkrzysztof.gymreservation.dto.LessonDto;
import com.fraczekkrzysztof.gymreservation.entity.Activity;
import com.fraczekkrzysztof.gymreservation.entity.Lesson;
import com.fraczekkrzysztof.gymreservation.entity.Trainer;
import com.fraczekkrzysztof.gymreservation.service.ActivityService;
import com.fraczekkrzysztof.gymreservation.service.LessonService;
import com.fraczekkrzysztof.gymreservation.service.TrainerService;

@RestController
@RequestMapping("/api")
public class LessonRestController {

	@Autowired
	LessonService lessonService;
	@Autowired
	ActivityService activityService;
	@Autowired
	TrainerService trainerService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",new Locale("en","PL"));

	@GetMapping("/lesson")
	public List<Lesson> getAllLessons() {
		return lessonService.findAll();
	}

	@GetMapping("/lesson/{id}")
	public Lesson getLessonById(@PathVariable("id") int theId) {
		Lesson temp = lessonService.findById(theId);
		return temp;
	}

	@PostMapping("/lesson")
		public Lesson addLesson(@RequestBody LessonDto theLessonDto) throws ParseException {
			System.out.println(theLessonDto.getDate());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			try {
				LocalDateTime date = LocalDateTime.parse(theLessonDto.getDate(),dtf);
				System.out.println(date);
				Lesson theLesson = new Lesson(0, theLessonDto.getName(),date, theLessonDto.getMax(),
						theLessonDto.getAvailable(), null, null);
				Activity tempActivity = activityService.findById(theLessonDto.getActivity());
				Trainer tempTrainer = trainerService.findById(theLessonDto.getTrainer());
				if (tempActivity == null) {
					throw new NotFoundException("Can't add Lesson without Activity");
				} else {
					theLesson.setActivity(tempActivity);
				}
				if (tempTrainer == null) {
					throw new NotFoundException("Can't add Lesson without Trainer");
				} else {
					theLesson.setTrainer(tempTrainer);
				}
				Lesson addedLesson = lessonService.saveOrUdpdate(theLesson);
				return addedLesson;
			}
			catch(DateTimeParseException ex) {
				throw new MyParseException("Bad date format!");
			}

		}
	
	@DeleteMapping("/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int theId) {
		lessonService.delete(theId);
	}
}

