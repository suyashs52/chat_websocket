package com.example.one.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

public interface TokenAuthenticationService {
	void addAuthentication(HttpServletResponse res, Authentication authentication);

	Authentication getAuthentication(HttpServletRequest request);
}
