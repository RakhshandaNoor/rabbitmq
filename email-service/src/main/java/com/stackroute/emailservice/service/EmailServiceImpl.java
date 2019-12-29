package com.stackroute.emailservice.service;

import com.stackroute.emailservice.EmailServiceApplication;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
        private RabbitTemplate rabbitTemplate;


        public void sendMessage(String msg) {
            System.out.println("Sending message to the queue using routingKey {}. Message= {}"+ msg);
            rabbitTemplate.convertAndSend(EmailServiceApplication.SFG_MESSAGE_QUEUE, msg);
            System.out.println("The message has been sent to the queue.");
        }

}
