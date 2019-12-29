package com.stackroute.emailservice;

import com.stackroute.emailservice.service.EmailServiceListenerImpl;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmailServiceApplication {

	public final static String SFG_MESSAGE_QUEUE = "sfg-message-queue";

	@Bean
	Queue queue() {
		return new Queue(SFG_MESSAGE_QUEUE, false);
	}

	@Bean
	public Queue autoDeleteQueue1() {
		return new Queue("fanoutQueue", false);
	}

	@Bean
	public Queue autoDeleteQueue2() {
		return new Queue("directQueue", false);
	}

	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	DirectExchange directExchange(){
		return new DirectExchange("directExchange");
	}

	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(SFG_MESSAGE_QUEUE.concat(".*"));
	}

	@Bean
	Binding binding2(Queue autoDeleteQueue1, FanoutExchange fanout) {
		return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
	}

	@Bean
	Binding binding3(Queue autoDeleteQueue2, DirectExchange directExchange){
		return BindingBuilder.bind(autoDeleteQueue2).to(directExchange).with("orange");
	}


	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(SFG_MESSAGE_QUEUE,"fanoutQueue","directQueue");
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(EmailServiceListenerImpl receiver) {
		return new MessageListenerAdapter(receiver, "getMessage");
	}


	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}

