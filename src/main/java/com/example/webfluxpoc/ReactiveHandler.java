package com.example.webfluxpoc;

//@Component("ReactiveHandler")
public class ReactiveHandler {
	//implements WebSocketHandler {
//
//
//	private static final ObjectMapper json = new ObjectMapper();
//	private Flux<String> eventFlux = Flux.generate(sink -> {
//		Events evnt = new Events(java.util.UUID.randomUUID().toString(), java.time.LocalDateTime.now().toString());
//		try {
//			sink.next(json.writeValueAsString(evnt));
//		} catch (JsonProcessingException ex) {
//			sink.error(ex);
//		}
//
//	});
//	private Flux<String> intervalFlux = Flux.interval(Duration.ofMillis(1000L)).zipWith(eventFlux,
//			(time, event) -> event);
//
//	@Override
//	public Mono<Void> handle(WebSocketSession webSocketSession) {
//
//		return webSocketSession.send(intervalFlux.map(webSocketSession::textMessage))
//				.and(webSocketSession.receive().map(WebSocketMessage::getPayloadAsText).log());
//
//	}

}
