package com.stackroute.customerservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqConfiguration {

    public static final String QUEUE = "user_queue";
    public static final String EXCHANGE = "user_exchange";
    public static final String ROUTING_KEY = "user_routingKey";


    public static final String QUEUE2 = "customer_queue";
    public static final String EXCHANGE2 = "customer_exchange";
    public static final String ROUTING_KEY2 = "customer_routingKey";


    // for receiving data
    @Bean
    public Queue queue() {

        return new Queue(QUEUE);
    }


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // for sending the data
    @Bean
    public Queue queue2() {

        return new Queue(QUEUE2);
    }


    @Bean
    public TopicExchange exchange2() {
        return new TopicExchange(EXCHANGE2);
    }



    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange).with(ROUTING_KEY2);
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