package com.betacom.dischi.request;

public class SignInRequest {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "SignInRequest [username=" + username + ", password=" + password + "]";
	}

	
}
