package com.social.backend;

import com.social.backend.exceptions.ErrorDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableDiscoveryClient
public class BackendApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "backend");
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @ControllerAdvice
    public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity defaultExceptionHandler(Exception ex, WebRequest request) {
            ErrorDetails errorDetails = ErrorDetails.builder().timestamp(new Date()).message(ex.getCause().getMessage()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }
    }

}
