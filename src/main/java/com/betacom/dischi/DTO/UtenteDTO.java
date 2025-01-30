package com.betacom.dischi.DTO;

public class UtenteDTO {

	private Integer idUtente;
	private String username;
	private String email;
	private String password;
	private String roles;
	private ClienteDTO cliente;
	public UtenteDTO() {
		super();
	}
	public UtenteDTO(Integer idUtente, String username, String email, String password, String roles,
			ClienteDTO cliente) {
		super();
		this.idUtente = idUtente;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.cliente = cliente;
	}
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
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "UtenteDTO [idUtente=" + idUtente + ", username=" + username + ", email=" + email + ", password="
				+ password + ", roles=" + roles + ", cliente=" + cliente + "]";
	}
	
	
}
