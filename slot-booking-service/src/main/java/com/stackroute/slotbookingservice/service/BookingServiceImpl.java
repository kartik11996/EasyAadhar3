package com.stackroute.slotbookingservice.service;

import com.stackroute.slotbookingservice.exception.BookingAlreadyExist;
import com.stackroute.slotbookingservice.exception.BookingNotFoundException;
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
    public Booking saveData(Booking booking) throws BookingAlreadyExist {
       if (bookingRepo.findById(booking.getAppointmentId()).isPresent()){
           throw new BookingAlreadyExist("Booking is already exist");
       }else
        {
            return bookingRepo.save(booking);
        }
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
    public void deleteBooking(Integer appointmentId) throws BookingNotFoundException {

        if (bookingRepo.findById(appointmentId).isPresent()){
            bookingRepo.deleteById(appointmentId);
        }else
        {
             throw new BookingNotFoundException("Booking not exist");
        }


    }


    @Override
    public Booking updateBooking(Booking booking,Integer id) throws BookingNotFoundException {

        if (bookingRepo.findById(id).isPresent()) {
            Booking booking1 = bookingRepo.findById(id).get();

            booking1.setAddress(booking.getAddress());
            booking1.setAppointmentDate(booking1.getAppointmentDate());
            booking1.setCustomername(booking.getCustomername());
            booking1.setMobile(booking.getMobile());
            booking1.setAppointmentTime(booking.getAppointmentTime());
            booking1.setEmailId(booking.getEmailId());
            booking1.setCenterName(booking.getCenterName());

          return  bookingRepo.save(booking1);
        } else {
            throw new BookingNotFoundException("booking not found ") ;
        }

    }

}
