package com.example.websocketclient;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebSocketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketClientApplication.class, args);
	}

}
