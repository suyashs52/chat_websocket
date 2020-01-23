package com.example.webfluxpoc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;

//@Configuration
public class ReactiveConfiguration {
//	@Autowired
//	@Qualifier("ReactiveHandler")
//	private WebSocketHandler webSocketHandler;
//
//	@Bean
//	public HandlerMapping webSocketHandlerMapping() {
//		Map<String, WebSocketHandler> map = new HashMap<>();
//		map.put("/event-emitter", webSocketHandler);
//		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//		handlerMapping.setOrder(1);
//		handlerMapping.setUrlMap(map);
//		return handlerMapping;
//	}
//
//	@Bean
//	public WebSocketHandlerAdapter handlerAdapter() {
//	//	return new WebSocketHandlerAdapter();
//		return new WebSocketHandlerAdapter(webSocketService());
//	}
//	
//	@Bean
//    public WebSocketService webSocketService() {
//        return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
//    }
}
