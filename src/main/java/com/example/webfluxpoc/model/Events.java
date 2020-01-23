package com.example.webfluxpoc.model;

public class Events {
	private String eventId;
	private String eventDt;

	public Events(String eventId, String eventDt) {
		super();
		this.eventId = eventId;
		this.eventDt = eventDt;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventDt() {
		return eventDt;
	}

	public void setEventDt(String eventDt) {
		this.eventDt = eventDt;
	}

}
