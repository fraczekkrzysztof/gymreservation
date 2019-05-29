package com.fraczekkrzysztof.gymreservation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fraczekkrzysztof.gymreservation.entity.Lesson;

import java.util.List;

@Repository
public interface LessonDao extends CrudRepository<Lesson, Integer> {

    public List<Lesson> findLessonsByActivityId(int id);

    public List<Lesson> findLessonsByTrainerId(int id);
}
