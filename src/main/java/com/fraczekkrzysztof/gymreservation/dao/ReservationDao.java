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
    List<Reservation> findByLessonId(@Param("theId") int theId);

    @Query("SELECT max(r.waiting) FROM Reservation as r"
            + " JOIN r.lesson as l"
            + " WHERE l.id = :theId")
    Optional<Integer> findMaxWaitingNumber(@Param("theId") int theId);

    //	@Query(value = "SELECT TOP  r FROM Reservation as r" +
//			" JOIN r.lesson as l" +
//			" WHERE l.id=:theId" +
//			" AND r.canceled = false" +
//			" AND r.waiting != 0" +
//			" ORDER BY r.waiting" +
//			" ")
    @Query(
            value = "SELECT * FROM reservation WHERE reservation.res_cl_id = :theId " +
                    "and reservation.res_canceled = 0 and reservation.res_waiting_lp != 0 order by res_waiting_lp limit 1", nativeQuery = true
    )
    Optional<Reservation> findFirstWaitingFromLesson(@Param(value = "theId") int theLessonId);

}
