## RABBIT MQ
docker run -d -p 15672:15672 -p 5672:5672 rabbitmq:3-management 

#### rabbit mq with spring boot

- simple message listener container

public class SimpleMessageListenerContainer
extends AbstractMessageListenerContainer
implements ExceptionListener
Message listener container that uses the plain JMS client API's MessageConsumer.setMessageListener() method to create concurrent MessageConsumers for the specified listeners.
This is the simplest form of a message listener container. It creates a fixed number of JMS Sessions to invoke the listener, not allowing for dynamic adaptation to runtime demands. Its main advantage is its low level of complexity and the minimum requirements on the JMS provider: Not even the ServerSessionPool facility is required.

See the AbstractMessageListenerContainer javadoc for details on acknowledge modes and transaction options. Note that this container exposes standard JMS behavior for the default "AUTO_ACKNOWLEDGE" mode: that is, automatic message acknowledgment after listener execution, with no redelivery in case of a user exception thrown but potential redelivery in case of the JVM dying during listener execution.

For a different style of MessageListener handling, through looped MessageConsumer.receive() calls that also allow for transactional reception of messages (registering them with XA transactions), see DefaultMessageListenerContainer.


-Message acknowledgment
Doing a task can take a few seconds. You may wonder what happens if one of the consumers starts a long task and dies with it only partly done. Spring AMQP by default takes a conservative approach to message acknowledgement. If the listener throws an exception the container calls:

channel.basicReject(deliveryTag, requeue)
Requeue is true by default unless you explicitly set:

defaultRequeueRejected=false

-Fair dispatch vs Round-robin dispatching
  
  -SimpleMessageListenerContainer

kafka: streaming system, ensures messages are delivered
rabbitmq: messaging bus, ensures messages are delivered only once.

```
However, "Fair dispatch" is the default configuration for Spring AMQP. The SimpleMessageListenerContainer defines the value for DEFAULT_PREFETCH_COUNT to be 250. If the DEFAULT_PREFETCH_COUNT were set to 0 the behavior would be round robin messaging as described above.
with the prefetchCount set to 250 by default, this tells RabbitMQ not to give more than 250 messages to a worker at a time. Or, in other words, don't dispatch a new message to a worker while the number of unacked messages is 250. Instead, it will dispatch it to the next worker that is not still busy.

```

##### ref links:
`https://www.rabbitmq.com/configure.html#define-environment-variables`
`https://dzone.com/articles/rabbitmq-in-microservices`
` https://dzone.com/articles/rabbitmq-and-spring-boot-integration-with-fault-to`
`https://dzone.com/articles/all-you-need-to-know-about-asyncronous-messaging-u`
`https://dzone.com/articles/rabbitmq-in-microservices`
Pattern Saga and Event-driven microservices:`https://microservices.io/patterns/data/saga.html`
converting json:`https://thepracticaldeveloper.com/2016/10/23/produce-and-consume-json-messages-with-spring-boot-amqp/`
`https://www.squaremobius.net/amqp.node/channel_api.html#failure_promises`




