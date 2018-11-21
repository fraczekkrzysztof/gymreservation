package com.fraczekkrzysztof.gymreservation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fraczekkrzysztof.gymreservation.entity.Activity;

@Repository
public interface ActivityDao extends CrudRepository<Activity, Integer> {

}
