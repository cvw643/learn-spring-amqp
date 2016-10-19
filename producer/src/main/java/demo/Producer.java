package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author YQ.Huang
 */
@Component
@EnableScheduling
public class Producer {
    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    private final RabbitTemplate rabbitTemplate;

    private int id = 0;

    private static final String EXCHANGE_NAME = "someTopicExchange";
    private static final String ROUTING_KEY = "someRoutingKey";

    @Autowired
    public Producer(AmqpAdmin amqpAdmin, RabbitTemplate rabbitTemplate) {
        Exchange exchange = ExchangeBuilder.topicExchange(EXCHANGE_NAME).build();
        amqpAdmin.declareExchange(exchange);
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void sendPayload() throws Exception {
        Message message = new Message();
        message.setId(++id);
        message.setContent("Hello");
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        logger.info("Sent: " + message);
    }
}
