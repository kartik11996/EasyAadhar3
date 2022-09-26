//package com.stackroute.customerservice.service;
//
//import com.stackroute.customerservice.configuration.RabbitMqConfiguration;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
////    @Autowired
////    private JwtUserDetailsService service;
//
//    // @RabbitListener(queues = ConsumerConfig.QUEUE1)
//    @RabbitListener(queues = RabbitMqConfiguration.QUEUE)
//    public void consumeLoanDetailsFromQueue(String email) {
//        System.out.println("User details received from queue : " + email);
////        service.save(email);
//    }
//
//}