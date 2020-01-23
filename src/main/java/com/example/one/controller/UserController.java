package com.example.one.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.one.dao.UserDAO;
import com.example.one.model.UserReg;

@RestController
public class UserController {
	static UserDAO userDAO;// = Application.context.getBean("UserDAO", UserDAO.class);

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostMapping(value = "/api/signup")
	public Object signUp(HttpServletResponse response, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("displayName") String displayName,
			@RequestParam("email") String email) throws IOException {
		response.setContentType("application/json");
		int result = 0;
		try {
			String encoded_password = passwordEncoder.encode(password);
			result = userDAO.insert(username, encoded_password, email, displayName);
		} catch (Exception e) {
			response.sendError(409, "Username has been used");
			return null;
		}
		if (result == 1) {
			// String verify = userDAO.getVerifyLink(username);
			// String to = userDAO.getEmail(username);
			// MailService.sendMail(to, "Threadripper: Verify account",
			// verifyBody.replace("{{action_url}}", verify));
			return "{\"success\": true}";
		} else
			response.sendError(520, "Some error has occurred");
		return null;
	}

	@GetMapping("/api/verify/resend/{username}")
	public Object resend(@PathVariable("username") String username) throws IOException {
		String verify = userDAO.getVerifyLink(username);
		String email = userDAO.getEmail(username);

		return "{\"hashLink\": " + verify + ",\"email\":" + email + "}";
	}

	@GetMapping("/api/user")
	public List<UserReg> getUserList() {
		return userDAO.getUserList();
	}

	@GetMapping("/api/user/{username}")
	public UserReg getUserByUsername(@PathVariable("username") String username, HttpServletResponse res)
			throws IOException {
		System.out.println(username);
		List<UserReg> result = userDAO.getUserByUsername(username);
		if (result.size() == 1)
			return result.get(0);
		res.sendError(404, "User doesn't exist");
		return null;
	}

}
