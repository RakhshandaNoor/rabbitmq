package com.stackroute.emailservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceListenerImpl {



    public void getMessage(String msg){

        System.out.println("the message recieved is "+ msg);
    }
}