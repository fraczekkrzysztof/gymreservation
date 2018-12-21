package com.fraczekkrzysztof.gymreservation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraczekkrzysztof.gymreservation.entity.Trainer;

@Repository
public interface TrainerDao extends CrudRepository<Trainer, Integer> {
	
	@Query("from Trainer tr where tr.symbol = :trSymbol")
	public Trainer finbBySymbol(@Param("trSymbol") String symbol);

}
