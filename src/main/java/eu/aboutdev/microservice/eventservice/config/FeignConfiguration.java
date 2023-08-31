package eu.aboutdev.microservice.eventservice.config;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("eu.aboutdev.microservice.eventservice.client")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class FeignConfiguration {
}
