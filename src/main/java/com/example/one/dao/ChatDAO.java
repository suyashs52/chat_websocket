package com.example.one.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.one.model.Conversation;
import com.example.one.model.Message;
import com.example.one.model.MessageConversation;
import com.example.one.model.UserConversation;
import com.example.one.model.UserReg;

public class ChatDAO {

	DataSource dataSource;
	static Map<String, Conversation> conversations = new HashMap<>();
	static Map<String, String> strConvers = new HashMap<>(); // list of user, conversation id

	public String addConversation(List<String> usernames) {
		Collections.sort(usernames);
		String list_user = String.join(",", usernames);
		if (strConvers.containsKey(list_user))
			return strConvers.get(list_user);

	//KeyHolder keyHolder = new GeneratedKeyHolder();
		Conversation conv = new Conversation();
		String conversationId = java.util.UUID.randomUUID().toString();//keyHolder.getKey().toString();
		List<UserConversation> listUser = new ArrayList<>();

		for (String name : usernames) {
			UserReg ur = DataSource.users.stream().filter(u -> name.equals(u.getUsername())).findAny().orElse(null);
			listUser.add(new UserConversation(ur));
		}

		conv.setListUser(listUser);

		conv.setConversationId(conversationId);
		conv.setConversationName("chat>>" + conversationId);
		;

		conversations.put(conversationId, conv);
		return conversationId;
	}

	public List<String> getRevUser(String username, String conversationId) {
		if (conversations.containsKey(conversationId)) {
			List<UserConversation> listUser = conversations.get(conversationId).getListUser();
			List<String> user = new ArrayList<>();
			for (UserConversation uc : listUser) {
				user.add(uc.getUsername());
			}
			return user;

		}

		return new ArrayList<String>();

	}

	private List<UserConversation> getUserOfConversation(String conversationId) {

		return conversations.get(conversationId).getListUser();
	}

	public Conversation getConversationWithId(String conversationId, String username) {
		return conversations.get(conversationId);
	}

	static Map<String, List<Message>> message = new HashMap<>();

	public Message insertMessage(Message mes) {
		Date currentTime = new Date();
		 
		String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		Message msg = mes;
		msg.setDatetime(datetime);
		msg.setMessageId(java.util.UUID.randomUUID().toString());
		if (message.containsKey(msg.getConversationId())) {
			message.get(msg.getConversationId()).add(msg);
		} else {
			message.put(msg.getConversationId(), Collections.singletonList(msg));
		}

		return mes;
	}

	public List<Message> getMessage(String conversationId, int offset, int limit) {
		return message.get(conversationId);
	}

	public List<MessageConversation> getConversation(String username) {
		List<MessageConversation> mc = new ArrayList<>();
		for (Map.Entry<String, Conversation> c : conversations.entrySet()) {
			if (c.getValue().getListUser().stream().filter(f -> f.getUsername().equals(username)).findAny()
					.isPresent()) {
				MessageConversation m = new MessageConversation();
				m.setConversion(List.of(c.getValue()));
				m.setMessage(message.get(c.getValue()));
				mc.add(m);
			}
		}

		return mc;
	}

	public List<String> getRevUserJoin(String username, int online) {
		List<String> user = new ArrayList<>();
		for (Map.Entry<String, Conversation> c : conversations.entrySet()) {
			if (c.getValue().getListUser().stream().filter(f -> f.getUsername().equals(username)).findAny()
					.isPresent()) {
				for (UserConversation uc : c.getValue().getListUser()) {
					user.add(uc.getUsername());
				}
			}
		}
		return user;
	}

	public List<Conversation> getFriend(String username) {
		List<Conversation> user = new ArrayList<>();
		for (Map.Entry<String, Conversation> c : conversations.entrySet()) {
			if (c.getValue().getListUser().contains(username)) {
				// for (UserConversation uc : c.getValue().getListUser()) {
				user.add(c.getValue());
				// }
			}
		}
		return user;
	}

	public boolean checkConversationId(String username, String conversationId) {
		Conversation c = conversations.get(conversationId);
		if (c.getListUser().stream().filter(f -> f.getUsername().equals(username)).findFirst().isPresent())
			return true;
		return false;

	}
}
