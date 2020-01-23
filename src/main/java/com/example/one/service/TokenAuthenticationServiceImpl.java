package com.example.one.service;

import java.security.SignatureException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.one.security.TokenHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

	private TokenHandler tokenHandler = new TokenHandler();

	@Override
	public void addAuthentication(HttpServletResponse res, Authentication authentication) {
		// TODO Auto-generated method stub
		String user = authentication.getName();

		String JWT = tokenHandler.build(user);

		res.addHeader(tokenHandler.HEADER_STRING, tokenHandler.TOKEN_PREFIX + " " + JWT);

	}

	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(tokenHandler.HEADER_STRING);
		if (token != null && token.startsWith(tokenHandler.TOKEN_PREFIX)) {
			String user = null;

			try {
				user = tokenHandler.parse(token);
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
			} catch (UnsupportedJwtException e) {
				e.printStackTrace();
			} catch (MalformedJwtException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			} else {
				return null;
			}
		}
		return null;
	}

}
