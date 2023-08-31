package eu.aboutdev.microservice.eventservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.aboutdev.microservice.eventservice.activemq.artemis.EventSendService;
import eu.aboutdev.microservice.eventservice.client.DataServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventServiceController {

    private final DataServiceClient dataServiceClient;

    private final EventSendService eventSendService;

    @GetMapping("/event/{id}")
    public List<String> getEventById(@PathVariable("id") Long id) throws JsonProcessingException {
        log.info("EventId in: {}", id);
        final List<String> eventIds = dataServiceClient.findByEventId(id);
        eventSendService.send(eventIds.get(0));
        log.info("EventId out: {}", eventIds.get(0));
        return eventIds;
    }
}
