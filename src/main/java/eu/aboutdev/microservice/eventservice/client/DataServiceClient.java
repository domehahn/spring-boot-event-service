package eu.aboutdev.microservice.eventservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "data-service")
public interface DataServiceClient {

    @GetMapping("/data/{eventId}")
    List<String> findByEventId(@PathVariable("eventId") Long eventId);
}
