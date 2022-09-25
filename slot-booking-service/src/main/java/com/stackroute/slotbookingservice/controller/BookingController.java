package com.stackroute.slotbookingservice.controller;

import java.util.List;

import com.stackroute.slotbookingservice.configuration.RabbitMqConfiguration;
import com.stackroute.slotbookingservice.exception.BookingAlreadyExist;
import com.stackroute.slotbookingservice.exception.BookingNotFoundException;
import com.stackroute.slotbookingservice.model.Customer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//
//    @Autowired
//    private RabbitTemplate template;

//    @RabbitListener(queues =  RabbitMqConfiguration.QUEUE2)
//    public void consumeLoanDetailsFromQueue(Customer customer) {
//        System.out.println("User details recieved from queue : " + customer);
//        //   service.save(userDetails);
//    }
    @PostMapping("/saveBooking")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) throws BookingAlreadyExist {

        try {
         //   template.convertAndSend(RabbitMqConfiguration.EXCHANGE,RabbitMqConfiguration.ROUTING_KEY,booking);
            return new ResponseEntity<>(bookingService.saveData(booking), HttpStatus.OK);

        } catch (BookingAlreadyExist e) {

            return new ResponseEntity<>(e.getErrorMessage(), HttpStatus.CONFLICT);

        }

    }


    @GetMapping("/getAllBooking")
    public List<Booking> getAllBookedData() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/deleteBooking/{appointmentId}")
    public String deleteBooking(@PathVariable Integer appointmentId) {
        try {
            bookingService.deleteBooking(appointmentId);
            return "delete operation successful";
        } catch (BookingNotFoundException e) {
            return e.getErrorMsg();
        }
    }

    @PutMapping("/update/{appointmentId}")
    public ResponseEntity<?> updateBooking(@RequestBody Booking booking, @PathVariable("appointmentId") Integer id) {
        try {
            return new ResponseEntity<>(bookingService.updateBooking(booking, id), HttpStatus.OK);
        } catch (BookingNotFoundException e) {
            return new ResponseEntity<>(e.getErrorMsg(), HttpStatus.CONFLICT);
        }


    }
}