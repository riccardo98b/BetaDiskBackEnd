package com.betacom.dischi.DTO;

public class UtenteDTO {

	private Integer idUtente;
	private String username;
	private String email;
	private String password;
	private String roles;
	private ClienteDTO cliente;
	private UtenteDTO() {
		super();
	}
	public static class Builder{
		private Integer idUtente;
		private String username;
		private String email;
		private String password;
		private String roles;
		private ClienteDTO cliente;
		
		public Builder() {}

	
		public Builder setIdUtente(Integer idUtente) {
			this.idUtente = idUtente;
			return this;
		}

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder setRoles(String roles) {
			this.roles = roles;
			return this;
		}

		public Builder setCliente(ClienteDTO cliente) {
			this.cliente = cliente;
			return this;
		}
		
		public UtenteDTO build() {
			UtenteDTO dto = new UtenteDTO();
			dto.idUtente= this.idUtente;
			dto.username= this.username;
			dto.password= this.password;
			dto.email = this.email;
			dto.roles = this.roles;
			dto.cliente = this.cliente;
			return dto;
		}
		
		
		
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
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRoles() {
		return roles;
	}
	
	public ClienteDTO getCliente() {
		return cliente;
	}
	
	@Override
	public String toString() {
		return "UtenteDTO [idUtente=" + idUtente + ", username=" + username + ", email=" + email + ", password="
				+ password + ", roles=" + roles + ", cliente=" + cliente + "]";
	}
	
	
}
