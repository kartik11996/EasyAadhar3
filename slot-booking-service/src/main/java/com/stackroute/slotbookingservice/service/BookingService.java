package com.stackroute.slotbookingservice.service;

import com.stackroute.slotbookingservice.exception.BookingAlreadyExist;
import com.stackroute.slotbookingservice.exception.BookingNotFoundException;
import com.stackroute.slotbookingservice.model.Booking;

import java.util.List;

public interface BookingService {
    public Booking saveData(Booking booking) throws BookingAlreadyExist;
    public List<Booking> getAllBookings();
    public Booking findBookingById(Integer appointmentId);
    public void deleteBooking (Integer appointmentId) throws BookingNotFoundException;

    public Booking updateBooking(Booking booking,Integer id) throws BookingNotFoundException;

}
