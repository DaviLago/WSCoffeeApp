package br.edu.fatec.main.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -9172488221276176592L;

	private String token;

	public AuthenticationResponse() {
	}
	
	public AuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
