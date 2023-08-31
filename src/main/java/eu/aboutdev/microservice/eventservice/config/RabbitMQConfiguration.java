package eu.aboutdev.microservice.eventservice.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    void setup() {
        this.rabbitTemplate.setObservationEnabled(true);
    }

    @Bean
    public Queue createUserRegistrationQueue() {
        return new Queue("q.event-queue", true);
    }

    @Bean
    Exchange fanoutExchange() {
        return new FanoutExchange("test.exchange", true, false);
    }

    @Bean
    Binding queueBinding() {
        return new Binding("q.event-queue", Binding.DestinationType.QUEUE, "test.exchange", "", null);
    }
}