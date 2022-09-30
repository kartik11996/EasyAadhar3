package com.stackroute.slotbookingservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {



    public static final String EXCHANGE = "slot_exchange";
    public static final String QUEUE = "slot_queue";
    public static final String ROUTING_KEY = "slot_routingKey";



    public static final String QUEUE2 = "customer_queue";

    public static final String EXCHANGE2 = "exchange.topic";
    public static final String ROUTING_KEY2 = "customer_routingKey";


    public static final String QUEUE3= "operator_queue";
    public static final String EXCHANGE3 = "operator_exchange";
    public static final String ROUTING_KEY3= "operator_routingKey";


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }



    @Bean
    public TopicExchange exchange2() {
        return new TopicExchange(EXCHANGE2);
    }
    @Bean
    public TopicExchange exchange3() {
        return new TopicExchange(EXCHANGE3);
    }



    // for sending the data to payment service

    @Bean
    public Queue queue() {

        return new Queue(QUEUE);
    }



    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }


    // for receiving data from customer service
    @Bean
    public Queue queue2() {

        return new Queue(QUEUE2);
    }












    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY2);
    }



    //for receiving data from
    @Bean
    public Queue queue3() {

        return new Queue(QUEUE3);
    }


    @Bean
    public Binding binding3(Queue queue3, TopicExchange exchange) {
        return BindingBuilder.bind(queue3).to(exchange).with(ROUTING_KEY3);
    }



    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }



}