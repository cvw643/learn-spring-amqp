package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author YQ.Huang
 */
@Component
public class Consumer {
    private static Logger logger = LoggerFactory.getLogger(Consumer.class);

    private static final String ROUTING_KEY = "someRoutingKey";
    private static final String EXCHANGE_NAME = "someTopicExchange";
    private static final String QUEUE_NAME = "someQueue";

    @Autowired
    Consumer(AmqpAdmin amqpAdmin) {
        Exchange exchange = ExchangeBuilder.topicExchange(EXCHANGE_NAME).build();
        Queue queue = QueueBuilder.durable(QUEUE_NAME).build();
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
        amqpAdmin.declareExchange(exchange);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareBinding(binding);
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void processMessage(Message message) {
        logger.info("Received: " + message);
    }
}
