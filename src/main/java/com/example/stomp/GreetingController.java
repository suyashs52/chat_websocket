package com.example.stomp;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

//@Controller
public class GreetingController {
//	@MessageMapping("/hello/{id}")
//	@SendTo("/topic/greetings/{id}")
	public Greeting greeting(@DestinationVariable String id,HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
}
