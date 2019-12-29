package com.stackroute.emailservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceListenerImpl {



    public void getMessage(String msg){

        System.out.println("the message recieved is "+ msg);
    }

//    @RabbitListener(queues = "#{autoDeleteQueue1.name}")
//    public void receive1(String in) throws InterruptedException {
//        System.out.println("the message from fanout is ++++"+ in);
//    }
//
//    @RabbitListener(queues = "#{autoDeleteQueue2.name}")
//    public void receive2(String in) throws InterruptedException {
//        System.out.println("the message from direct is ++++"+ in);
//    }

}