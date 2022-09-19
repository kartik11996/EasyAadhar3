package com.stackroute.slotbookingservice.service;

import com.stackroute.slotbookingservice.model.Booking;

import java.util.List;

public interface BookingService {
    public Booking saveData(Booking booking);
    public List<Booking> getAllBookings();
    public Booking findBookingById(Integer appointmentId);
    public void deleteBooking (Integer appointmentId);

    public Booking updateBooking(Booking booking,Integer id);

}
