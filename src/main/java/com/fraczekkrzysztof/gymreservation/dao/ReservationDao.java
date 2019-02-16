package com.fraczekkrzysztof.gymreservation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fraczekkrzysztof.gymreservation.entity.Reservation;

@Repository
public interface ReservationDao extends CrudRepository<Reservation, Integer> {
	
	@Query("SELECT r FROM Reservation as r"
			+ " JOIN r.lesson as l"
			+ " WHERE l.id=:theId")
	public List<Reservation> findByLessonId(@Param("theId") int theId);

	@Query ("SELECT max(r.waiting) FROM Reservation as r"
			+ " JOIN r.lesson as l"
			+ " WHERE l.id = :theId")
	public Optional<Integer> findMaxWaitingNumber(@Param("theId") int theId);
}
