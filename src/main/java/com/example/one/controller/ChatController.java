package com.example.one.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.one.dao.ChatDAO;
import com.example.one.model.Conversation;
import com.example.one.model.MessageConversation;

@RestController
public class ChatController {
	static ChatDAO chatDAO = new ChatDAO();

	public static List<String> getStringListFromJsonArray(org.json.JSONArray jArray) throws JSONException {
		List<String> returnList = new ArrayList<String>();

		for (int i = 0; i < jArray.length(); i++) {
			String val = jArray.getString(i);
			returnList.add(val);
		}
		return returnList;
	}

	@GetMapping("/api/conversation")
	public List<MessageConversation> getConversation(Authentication auth) {
		return chatDAO.getConversation(auth.getName());
	}

	@GetMapping("/api/friend")
	public List<Conversation> getFriend(Authentication auth) {
		return chatDAO.getFriend(auth.getName());
	}

	@GetMapping("/api/conversation/{conversationId}")
	public Conversation getConversationWithId(Authentication auth,
			@PathVariable("conversationId") String conversationId, HttpServletResponse res) throws IOException {

		if (chatDAO.checkConversationId(auth.getName(), conversationId))
			return chatDAO.getConversationWithId(conversationId, auth.getName());
		else {
			res.sendError(520, "User does not have access privileges");
			return null;
		}
	}

	@GetMapping("/api/conversations")
	public List<MessageConversation> getConversations(Authentication auth) {
		return chatDAO.getConversation(auth.getName());
	}

	@PostMapping(value = "/api/conversation")
	public Object addConversation(@RequestBody String body, HttpServletResponse res) throws JSONException, IOException {
		org.json.JSONArray json = new org.json.JSONObject(body).getJSONArray("listUsername");
		List<String> listUsername = getStringListFromJsonArray(json);

		String conversationId = chatDAO.addConversation(listUsername);
		if (conversationId.equals("0")) {
			res.sendError(520, "Some error has occurred");
			return null;
		}
		return "{\"" + "conversationId" + "\": \"" + conversationId + "\"}";
	}

	@GetMapping(value = "/api/message/{conversationId}")
	public Object getMessage(@PathVariable("conversationId") String conversationId, HttpServletResponse res,
			Authentication auth) throws IOException {
		if (chatDAO.checkConversationId(auth.getName(), conversationId))
			return chatDAO.getMessage(conversationId, 0, 100);
		else {
			res.sendError(520, "User does not have access privileges");
			return null;
		}
	}

	@GetMapping(value = "/api/message/{conversationId}", params = { "offset", "limit" })
	public Object getMessageLimit(@PathVariable("conversationId") String conversationId, HttpServletResponse res,
			Authentication auth, @RequestParam("limit") int limit, @RequestParam("offset") int offset)
			throws IOException {
		if (chatDAO.checkConversationId(auth.getName(), conversationId))
			return chatDAO.getMessage(conversationId, offset, limit);
		else {
			res.sendError(520, "User does not have access privileges");
			return null;
		}
	}
	 
}
