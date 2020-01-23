package com.example.one.model;

import java.util.List;

public class MessageConversation {
	List<Conversation> conversion;
	List<Message> message;

	public List<Conversation> getConversion() {
		return conversion;
	}

	public void setConversion(List<Conversation> conversion) {
		this.conversion = conversion;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}

}
