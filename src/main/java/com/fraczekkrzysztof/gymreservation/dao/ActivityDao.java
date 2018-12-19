package com.fraczekkrzysztof.gymreservation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraczekkrzysztof.gymreservation.entity.Activity;

@Repository
public interface ActivityDao extends CrudRepository<Activity, Integer> {
	
	@Query("from Activity act where act.symbol=:actSymbol")
	public Activity findBySymbol(@Param("actSymbol") String symbol);

}
