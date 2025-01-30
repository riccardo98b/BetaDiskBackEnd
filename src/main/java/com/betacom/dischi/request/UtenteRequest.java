package com.betacom.dischi.request;

public class UtenteRequest {

	private Integer idUtente;
	private String username;
	private String email;
	private String password;
	private String roles;
	private Integer idCliente;
	public Integer getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	@Override
	public String toString() {
		return "UtenteRequest [idUtente=" + idUtente + ", username=" + username + ", email=" + email + ", password="
				+ password + ", roles=" + roles + ", idCliente=" + idCliente + "]";
	}
	
	
}
