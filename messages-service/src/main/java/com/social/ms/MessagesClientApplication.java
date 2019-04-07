package com.social.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
@EnableSocial
@PropertySource("classpath:application.properties")
public class MessagesClientApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "messages-ms");
        SpringApplication.run(MessagesClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
