package com.stackroute.slotbookingservice.repository;

import com.stackroute.slotbookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{





     @Query("SELECT u FROM Booking u WHERE u.emailId =:e")
     List<Booking> findAllByEmailId(@Param("e") String emailId);

}
