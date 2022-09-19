package com.stackroute.slotbookingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.slotbookingservice.model.Booking;
import com.stackroute.slotbookingservice.service.BookingService;

@RestController
@RequestMapping("/getOne")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/getAllBookingByLocation")
    public List<Booking> getAllBookedData(){
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/deleteBooking/{appointmentId}")
    public String deleteBooking(@PathVariable Integer appointmentId){
        bookingService.deleteBooking(appointmentId);
        return "delete operation successful";
    }
    @PostMapping("/saveBooking")
    public Booking saveBooking(@RequestBody Booking booking){
       return bookingService.saveData(booking);

    }

    @PutMapping("/update/{appointmentId}")
    public Booking updateBooking(@RequestBody Booking booking,@PathVariable("appointmentId") Integer id){
        return bookingService.updateBooking(booking,id);
    }


}
