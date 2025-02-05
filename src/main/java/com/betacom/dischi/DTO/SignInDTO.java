package com.betacom.dischi.DTO;

public class SignInDTO {
	
	private Boolean logged;
	private String role;
	
	public SignInDTO() {
		super();
	}
	public SignInDTO(Boolean logged, String role) {
		super();
		this.logged = logged;
		this.role = role;
	}
	public Boolean getLogged() {
		return logged;
	}
	public String getRole() {
		return role;
	}
	public void setLogged(Boolean logged) {
		this.logged = logged;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
