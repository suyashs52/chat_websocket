package com.example.one.model;

public class UserReg {
	String username;
	String password;
	String displayName;
	String email;
	String hash;

	public UserReg(String username, String password, String displayName, String email,String hash) {
		super();
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.email = email;
		this.hash=hash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
}
