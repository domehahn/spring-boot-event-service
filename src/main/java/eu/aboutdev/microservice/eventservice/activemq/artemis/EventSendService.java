package eu.aboutdev.microservice.eventservice.activemq.artemis;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventSendService {

    private final Tracer tracer;

    private final ObservationRegistry observationRegistry;

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.event.queue}")
    private String eventQueue;

    public void send(final String eventId) {
        Observation.createNotStarted("rabbit-producer", this.observationRegistry).observe(() -> {
            log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from producer", this.tracer.currentSpan().context().traceId());
            rabbitTemplate.convertAndSend(eventQueue, eventId);
        });
    }
}
