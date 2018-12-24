package com.fraczekkrzysztof.gymreservation.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraczekkrzysztof.gymreservation.entity.Lesson;
import com.fraczekkrzysztof.gymreservation.service.LessonService;

@RestController
@RequestMapping("/api")
public class LessonRestController {

	@Autowired
	LessonService lessonService;
	
	@GetMapping("/lesson")
	public List<Lesson> getAllLessons(){
		return lessonService.findAll();
	}
	
	@GetMapping("/lesson/{id}")
	public Lesson getLessonById(@PathVariable("id") int theId) {
		return lessonService.findById(theId);
	}
	
	@PostMapping("/lesson")
	public Lesson addLesson(@RequestBody Lesson theLesson) {
		return null;
	}
}
