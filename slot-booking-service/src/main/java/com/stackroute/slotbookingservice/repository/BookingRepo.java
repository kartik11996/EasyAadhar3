package com.stackroute.slotbookingservice.repository;

import com.stackroute.slotbookingservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
