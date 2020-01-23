package com.example.one;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ChatApp {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(ChatApp.class, args);
	}
}
