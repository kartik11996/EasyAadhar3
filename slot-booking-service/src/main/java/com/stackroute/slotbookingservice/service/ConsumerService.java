package com.stackroute.slotbookingservice.service;

import com.stackroute.slotbookingservice.configuration.RabbitMqConfiguration;
import com.stackroute.slotbookingservice.dto.CustomerDto;
import com.stackroute.slotbookingservice.dto.OperatorDto;
import com.stackroute.slotbookingservice.model.Booking;
import com.stackroute.slotbookingservice.repository.BookingRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ConsumerService {



    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private RabbitTemplate template;




    @RabbitListener(queues = RabbitMqConfiguration.QUEUE2)
    public void saveCustomer(CustomerDto customer) {

        Booking booking1=new Booking();
        booking1.setCustomername(customer.getName());
        booking1.setMobile(customer.getMobile());
        booking1.setEmailId(customer.getEmail());
        System.out.println(booking1);
        bookingRepo.save(booking1);


    }

    OperatorDto operator1=new OperatorDto();

    @RabbitListener(queues = RabbitMqConfiguration.QUEUE3)
    public void saveOperator(OperatorDto operator) {

        System.out.println(operator);

         operator1=operator;

    }

    public Booking  generateBooking(String emailId){
       List<Booking> bookings=bookingRepo.findAllByEmailId(emailId) ;
        Booking booking = bookings.stream().findFirst().get();

        try {


            Booking booking1 = new Booking();
            booking1.setCustomername(booking.getCustomername());
            booking1.setEmailId(booking.getEmailId());
            booking1.setMobile(booking.getMobile());
            booking1.setCenterName(operator1.getCenterName());
            booking1.setAddress(operator1.getCenterAddress());
            booking1.setAppointmentDate(operator1.getAppointmentDate());
            booking1.setAppointmentTime(operator1.getAppointmentTime());


            Booking booking2=bookingRepo.save(booking1);

            template.convertAndSend(RabbitMqConfiguration.EXCHANGE,RabbitMqConfiguration.ROUTING_KEY,booking2);
            System.out.println("Slot booked    "+ booking2);
            return booking2;
           //

        }catch (Exception e){
            System.out.println(e);
            System.out.println("value not present");
        }

        return null;
    }
}
