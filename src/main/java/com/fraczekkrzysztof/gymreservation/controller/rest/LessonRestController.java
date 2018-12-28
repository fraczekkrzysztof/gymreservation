package com.fraczekkrzysztof.gymreservation.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Lesson addLesson(@RequestBody LessonDto theLessonDto) {
		Lesson theLesson = new Lesson(0, theLessonDto.getName(), theLessonDto.getDate(), theLessonDto.getMax(),
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
	
	@DeleteMapping("/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int theId) {
		lessonService.delete(theId);
	}
}
