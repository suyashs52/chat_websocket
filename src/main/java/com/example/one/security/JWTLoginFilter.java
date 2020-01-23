package com.example.one.security;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.one.dao.UserDAO;
import com.example.one.service.TokenAuthenticationService;
import com.example.one.service.TokenAuthenticationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();
	static UserDAO userDAO = new UserDAO();// Application.context.getBean("UserDAO", UserDAO.class);

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				req.getParameter("username"), req.getParameter("password"), Collections.emptyList()));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		tokenAuthenticationService.addAuthentication(res, auth);
		String username = auth.getName();
		Map<String, Object> currentUser = userDAO.getCurrentUserInfo(username);
		String active = currentUser.get("active").toString();
		if (active.equals("false")) {
			System.out.println("logout!!!!!!!!!!!!!");
		}
		currentUser.put("avatarUrl", currentUser.get("avatarUrl"));
		currentUser.remove("active");
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("active", Boolean.valueOf(active));
		response.put("user", currentUser);
		ObjectMapper objectMapper = new ObjectMapper();

		res.getOutputStream().write(objectMapper.writeValueAsString(response).getBytes("UTF-8"));
	}

}
