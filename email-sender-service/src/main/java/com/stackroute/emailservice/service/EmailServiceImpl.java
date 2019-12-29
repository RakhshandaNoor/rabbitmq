package com.stackroute.emailservice.service;

import com.stackroute.emailservice.EmailServiceApplication;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {

    @Autowired
        private RabbitTemplate rabbitTemplate;
    @Autowired
    private FanoutExchange fanoutExchange;
    @Autowired
    private DirectExchange directExchange;
    @Autowired
    private TopicExchange topicExchange;


        public void sendMessage(String msg) {
            System.out.println("Sending message to the queue using routingKey {}. Message= {}"+ msg);
            rabbitTemplate.convertAndSend(EmailServiceApplication.SFG_MESSAGE_QUEUE, msg);
            System.out.println("The message has been sent to the queue.");
        }

        public void sendToFanout(String msg){

            System.out.println("sending message to queue +++ fanout");
            rabbitTemplate.convertAndSend(fanoutExchange.getName(),"" , msg);
        }

    public void sendToDirect(String msg){

        System.out.println("sending message to queue +++ direct");
        rabbitTemplate.convertAndSend(directExchange.getName(),"orange" , msg);
    }

    public void sendToTopic(String msg){

        System.out.println("sending message to queue +++ direct");
        rabbitTemplate.convertAndSend(topicExchange.getName(),"orange.apple" , msg);
    }


}
