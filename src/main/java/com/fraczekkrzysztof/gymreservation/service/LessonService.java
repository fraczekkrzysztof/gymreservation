package com.fraczekkrzysztof.gymreservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraczekkrzysztof.gymreservation.dao.LessonDao;
import com.fraczekkrzysztof.gymreservation.entity.Lesson;

@Service
public class LessonService {

	@Autowired
	LessonDao lessonDao;
	
	public List<Lesson> findAll(){
		List<Lesson> theLessonList = new ArrayList<Lesson>();
		for (Lesson theLesson : lessonDao.findAll()) {
			theLessonList.add(theLesson);
		}
		return theLessonList;
	}
	
	public Lesson findById(int theId) {
		return lessonDao.findById(theId).orElse(null);
	}
	
	public Lesson saveOrUdpdate(Lesson theLesson) {
		return lessonDao.save(theLesson);
	}
	
	public void delete(int theId) {
		lessonDao.deleteById(theId);
	}
	
	public void delete(Lesson theLesson) {
		lessonDao.delete(theLesson);
	}

	public List<Lesson> findLessonsByActivityId(int id){
		List<Lesson> theLessonList = new ArrayList<Lesson>();
		for (Lesson theLesson : lessonDao.findLessonsByActivityId(id)) {
			theLessonList.add(theLesson);
		}
		return theLessonList;
	}

	public List<Lesson> findLessonsByTrainerId(int id){
		List<Lesson> theLessonList = new ArrayList<Lesson>();
		for (Lesson theLesson : lessonDao.findLessonsByTrainerId(id)) {
			theLessonList.add(theLesson);
		}
		return theLessonList;
	}
}
