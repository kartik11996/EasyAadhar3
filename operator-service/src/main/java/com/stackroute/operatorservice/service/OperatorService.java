package com.stackroute.operatorservice.service;

import com.stackroute.operatorservice.configuration.RabbitMqConfiguration;
import com.stackroute.operatorservice.model.Operator;
import com.stackroute.operatorservice.repository.OperatorRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository repo;

  @RabbitListener(queues = RabbitMqConfiguration.QUEUE1)
    public void saveOperator(String email) {
        System.out.println("operator details recieved from queue : " + email);
        Operator operator = new Operator();
        operator.setEmail(email);

       repo.save(operator);
    }
}