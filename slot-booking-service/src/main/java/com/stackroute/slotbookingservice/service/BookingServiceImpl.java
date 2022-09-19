package com.stackroute.slotbookingservice.service;

import com.stackroute.slotbookingservice.model.Booking;
import com.stackroute.slotbookingservice.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    @Override
    public Booking saveData(Booking booking) {
        return bookingRepo.save(booking);

    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> booking = bookingRepo.findAll();
        return booking;
    }

    @Override
    public Booking findBookingById(Integer appointmentId) {
        Optional<Booking> findById = bookingRepo.findById(appointmentId);
        Booking booking = findById.get();
        return booking;
    }

    @Override
    public void deleteBooking(Integer appointmentId) {
        bookingRepo.deleteById(appointmentId);

    }


    @Override
    public Booking updateBooking(Booking booking,Integer id) {

        return bookingRepo.save(booking);
    }
}
