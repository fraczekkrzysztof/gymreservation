package com.fraczekkrzysztof.gymreservation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fraczekkrzysztof.gymreservation.entity.Lesson;

@Repository
public interface LessonDao extends CrudRepository<Lesson, Integer> {

}
